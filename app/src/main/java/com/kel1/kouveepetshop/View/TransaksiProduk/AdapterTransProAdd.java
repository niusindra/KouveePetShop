package com.kel1.kouveepetshop.View.TransaksiProduk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.DAO.detailProdukDAO;
import com.kel1.kouveepetshop.DAO.produkDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.cudDataMaster;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterTransProAdd extends RecyclerView.Adapter<AdapterTransProAdd.ViewHolder>{
    private Context context;
    private  List<detailProdukDAO> arrayList;
    private List<produkDAO> mListProduk;

    public AdapterTransProAdd(Context context, List<detailProdukDAO> arrayList, List<produkDAO> mListProduk) {
        this.context = context;
        this.arrayList = arrayList;
        this.mListProduk = mListProduk;
    }

    @Override
    public AdapterTransProAdd.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.transpro_add_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterTransProAdd.ViewHolder holder, final int position) {
        detailProdukDAO detailProdukDAO=arrayList.get(position);
        produkDAO produkDAO = new produkDAO();
        for (int i = 0; i < mListProduk.size(); i++) {
            if(detailProdukDAO.getId_produk()==mListProduk.get(i).getId_produk()){
                produkDAO = mListProduk.get(i);
            }
        }
        holder.namaProduk.setText(produkDAO.getNama_produk());
        holder.harga.setText("Rp."+produkDAO.getHarga_jual_produk());
        holder.hargajual=produkDAO.getHarga_jual_produk();
        holder.jumlah.setText(String.valueOf(detailProdukDAO.getJumlah_beli_produk()));
        holder.subtotal.setText(String.valueOf(detailProdukDAO.getSubtotal_produk()));
        holder.sisa.setText(String.valueOf(produkDAO.getStok()-detailProdukDAO.getJumlah_beli_produk()));

        if(position==arrayList.size()-1){
            holder.addProduk.setVisibility(View.VISIBLE);
        }else
            holder.addProduk.setVisibility(View.GONE);
        if(arrayList.size()>0 && position==arrayList.size()-1){
            holder.total.setVisibility(View.VISIBLE);
            holder.total.setText("Total : Rp."+holder.settotal());
        }else
            holder.total.setVisibility(View.GONE);
        holder.total.setText("Total : Rp."+holder.settotal());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public List<detailProdukDAO> getArrayList()
    {
        return arrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView harga,subtotal,total,jumlah, sisa;
        public AutoCompleteTextView namaProduk;
        public Button addProduk,kurang, tambah;
        public ImageView removeBtn;
        public int hargajual;

        public ViewHolder(View itemView) {
            super(itemView);
            harga = itemView.findViewById(R.id.hargajual);
            namaProduk = itemView.findViewById(R.id.namaProduk);
            subtotal = itemView.findViewById(R.id.subtotal);
            total = itemView.findViewById(R.id.total);
            removeBtn = itemView.findViewById(R.id.btnHapus);
            addProduk = itemView.findViewById(R.id.btnTambahProduk);
            kurang = itemView.findViewById(R.id.btnKurangJml);
            tambah = itemView.findViewById(R.id.btnTambahJml);
            jumlah = itemView.findViewById(R.id.jumlahbeli);
            sisa = itemView.findViewById(R.id.sisaStok);

            ArrayAdapter<produkDAO> adapter = new ArrayAdapter<produkDAO>
                    (context, R.layout.autocomplete_adapter, R.id.item, mListProduk);
            namaProduk.setAdapter(adapter);

            harga.setText("Rp.0");
            namaProduk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    produkDAO produk = (produkDAO) adapterView.getItemAtPosition(i);
                    hargajual=produk.getHarga_jual_produk();
                    harga.setText("Rp."+hargajual);
                    sisa.setText(String.valueOf(produk.getStok()));
                    detailProdukDAO detailProdukDAO = arrayList.get(getAdapterPosition());
                    detailProdukDAO.setId_produk(produk.getId_produk());
                    detailProdukDAO.setSubtotal_produk(detailProdukDAO.getJumlah_beli_produk()*hargajual);
                    arrayList.set(getAdapterPosition(), detailProdukDAO);
                    subtotal.setText("Rp."+detailProdukDAO.getJumlah_beli_produk()*hargajual);
                    total.setText("Total : Rp."+settotal());
                    notifyDataSetChanged();
                }
            });

            kurang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    harga.setText("Rp."+hargajual);
                    detailProdukDAO detailProdukDAO = arrayList.get(getAdapterPosition());
                    if(detailProdukDAO.getJumlah_beli_produk()>0){
                        detailProdukDAO.setJumlah_beli_produk(detailProdukDAO.getJumlah_beli_produk()-1);
                        jumlah.setText(String.valueOf(detailProdukDAO.getJumlah_beli_produk()));
                        sisa.setText(String.valueOf(Integer.parseInt(sisa.getText().toString())+1));
                        detailProdukDAO.setSubtotal_produk(detailProdukDAO.getJumlah_beli_produk()*hargajual);
                        arrayList.set(getAdapterPosition(), detailProdukDAO);
                        subtotal.setText("Rp "+detailProdukDAO.getJumlah_beli_produk()*hargajual);
                        total.setText("Total : Rp."+settotal());
                        notifyDataSetChanged();
                    }
                }
            });

            tambah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    harga.setText("Rp."+hargajual);
                    detailProdukDAO detailProdukDAO = arrayList.get(getAdapterPosition());
                    detailProdukDAO.setJumlah_beli_produk(detailProdukDAO.getJumlah_beli_produk()+1);
                    jumlah.setText(String.valueOf(detailProdukDAO.getJumlah_beli_produk()));
                    if(Integer.parseInt(sisa.getText().toString())>0)
                        sisa.setText(String.valueOf(Integer.parseInt(sisa.getText().toString())-1));
                    detailProdukDAO.setSubtotal_produk(detailProdukDAO.getJumlah_beli_produk()*hargajual);
                    arrayList.set(getAdapterPosition(), detailProdukDAO);
                    subtotal.setText("Rp "+detailProdukDAO.getJumlah_beli_produk()*hargajual);
                    total.setText("Total : Rp."+settotal());
                    notifyDataSetChanged();
                }
            });

            removeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    detailProdukDAO detailProdukDAO = arrayList.get(getAdapterPosition());
                    if(detailProdukDAO.getId_detail_produk()!=0){
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Anda yakin menghapus produk ini?")
                                .setCancelable(false)
                                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
                                        Call<cudDataMaster> pengadaanCall = apiService.deleteDetailPTransPro(arrayList.get(getAdapterPosition()).getId_detail_produk());
                                        pengadaanCall.enqueue(new Callback<cudDataMaster>(){
                                            @Override
                                            public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response) {
                                                if(response.body()!=null) {
                                                    Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<cudDataMaster> call, Throwable t) {
                                                Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                        arrayList.remove(arrayList.get(getAdapterPosition()));

                                        notifyItemRemoved(getAdapterPosition());
                                        notifyItemRangeChanged(getAdapterPosition(), arrayList.size());
                                        notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }else{
                        arrayList.remove(arrayList.get(getAdapterPosition()));
                        namaProduk.setText("");
                        harga.setText("Rp.0");
                        if(arrayList.size()==0)
                            addProduk.setVisibility(View.VISIBLE);
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), arrayList.size());
                        notifyDataSetChanged();
                    }
                }
            });

            addProduk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    detailProdukDAO detailProdukDAO = new detailProdukDAO();
                    arrayList.add(detailProdukDAO);
//                    arrayList.add(getAdapterPosition(),detailProdukDAO);
                    notifyItemInserted(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), arrayList.size());
                    notifyDataSetChanged();
                    addProduk.setVisibility(View.GONE);
                    total.setVisibility(View.GONE);
                }
            });

        }

        public int settotal(){
            int total = 0, i;
            for(i=0;i<arrayList.size();i++){
                total+=arrayList.get(i).getSubtotal_produk();
            }
            return total;
        }

    }

}
