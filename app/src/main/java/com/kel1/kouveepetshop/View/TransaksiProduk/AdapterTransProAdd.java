package com.kel1.kouveepetshop.View.TransaksiProduk;

import android.content.Context;
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

import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.DAO.detailProdukDAO;
import com.kel1.kouveepetshop.DAO.produkDAO;
import com.kel1.kouveepetshop.R;

import java.util.List;

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
        for (int i = 0; i < mListProduk.size(); i++) {
            if(mListProduk.get(i).getId_produk()==detailProdukDAO.getId_produk()){
                holder.namaProduk.setListSelection(i);
                holder.namaProduk.setText(mListProduk.get(i).getNama_produk());
            }
        }
        holder.jumlah.setText(String.valueOf(detailProdukDAO.getJumlah_beli_produk()));

        if(position==arrayList.size()-1){
            holder.addProduk.setVisibility(View.VISIBLE);
        }
        if(arrayList.size()>0 && position==arrayList.size()-1){
            holder.total.setVisibility(View.VISIBLE);
            holder.total.setText("Total : Rp."+holder.settotal());
        }else
            holder.total.setVisibility(View.GONE);

//        Log.e("posisi",String.valueOf(position));
//        Log.e("jml",String.valueOf(detailProdukDAO.getJumlah_beli_produk()));
//        Log.e("id",String.valueOf(detailProdukDAO.getId_produk()));
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
        public TextView harga,subtotal,total,jumlah;
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

            ArrayAdapter<produkDAO> adapter = new ArrayAdapter<produkDAO>
                    (context, android.R.layout.select_dialog_item, mListProduk);
            namaProduk.setAdapter(adapter);

            namaProduk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    produkDAO produk = (produkDAO) adapterView.getItemAtPosition(i);
                    hargajual=produk.getHarga_jual_produk();
                    harga.setText("Rp."+hargajual);
                    detailProdukDAO detailProdukDAO = arrayList.get(getAdapterPosition());
                    detailProdukDAO.setId_produk(produk.getId_produk());
                    detailProdukDAO.setSubtotal_produk(detailProdukDAO.getJumlah_beli_produk()*hargajual);
                    arrayList.set(getAdapterPosition(), detailProdukDAO);
                    subtotal.setText("Rp "+detailProdukDAO.getJumlah_beli_produk()*hargajual);
                    total.setText("Total : Rp."+settotal());
                    Log.e("posisi",String.valueOf(getAdapterPosition()));
                    Log.e("jml",String.valueOf(detailProdukDAO.getJumlah_beli_produk()));
                    Log.e("id",String.valueOf(detailProdukDAO.getId_produk()));
                    Log.e("harga",String.valueOf(hargajual));
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
                        detailProdukDAO.setSubtotal_produk(detailProdukDAO.getJumlah_beli_produk()*hargajual);
                        arrayList.set(getAdapterPosition(), detailProdukDAO);
                        subtotal.setText("Rp "+detailProdukDAO.getJumlah_beli_produk()*hargajual);
                        total.setText("Total : Rp."+settotal());
                        Log.e("posisi",String.valueOf(getAdapterPosition()));
                        Log.e("jml",String.valueOf(detailProdukDAO.getJumlah_beli_produk()));
                        Log.e("id",String.valueOf(detailProdukDAO.getId_produk()));
                        Log.e("harga",String.valueOf(hargajual));
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
                    detailProdukDAO.setSubtotal_produk(detailProdukDAO.getJumlah_beli_produk()*hargajual);
                    arrayList.set(getAdapterPosition(), detailProdukDAO);
                    subtotal.setText("Rp "+detailProdukDAO.getJumlah_beli_produk()*hargajual);
                    total.setText("Total : Rp."+settotal());
                    Log.e("posisi",String.valueOf(getAdapterPosition()));
                    Log.e("jml",String.valueOf(detailProdukDAO.getJumlah_beli_produk()));
                    Log.e("id",String.valueOf(detailProdukDAO.getId_produk()));
                    Log.e("harga",String.valueOf(hargajual));
                }
            });

            removeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    arrayList.remove(arrayList.get(getAdapterPosition()));
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), arrayList.size());
                    notifyDataSetChanged();
                }
            });

            addProduk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    detailProdukDAO detailProdukDAO = new detailProdukDAO();
//                    arrayList.add(detailProdukDAO);
                    arrayList.add(getAdapterPosition()+1,detailProdukDAO);
//                    notifyItemInserted(g);
//                    notifyItemRangeChanged(position, arrayList.size());
//                    notifyDataSetChanged();
                    addProduk.setVisibility(View.GONE);
                    total.setText("Total : Rp."+settotal());
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
