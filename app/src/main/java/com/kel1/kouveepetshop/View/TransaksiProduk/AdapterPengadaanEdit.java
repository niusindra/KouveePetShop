package com.kel1.kouveepetshop.View.TransaksiProduk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.DAO.detailPengadaanDAO;
import com.kel1.kouveepetshop.DAO.produkDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.cudDataMaster;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterPengadaanEdit extends RecyclerView.Adapter<AdapterPengadaanEdit.ViewHolder>{
    private Context context;
    private  List<detailPengadaanDAO> arrayList;
    private List<produkDAO> mListProduk;

    public AdapterPengadaanEdit(Context context, List<detailPengadaanDAO> arrayList, List<produkDAO> mListProduk) {
        this.context = context;
        this.arrayList = arrayList;
        this.mListProduk = mListProduk;
    }

    @Override
    public AdapterPengadaanEdit.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.pengadaan_edit_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterPengadaanEdit.ViewHolder holder, int position) {
        detailPengadaanDAO detailPengadaanDAO=arrayList.get(position);
        for (int i = 0; i < mListProduk.size(); i++) {
            if(mListProduk.get(i).getId_produk()==detailPengadaanDAO.getId_produk()){
                holder.edtdetPro.setSelection(i);
            }
        }

        holder.edtdetjmlPro.setText(String.valueOf(detailPengadaanDAO.getJml_pengadaan_produk()));
        holder.edtdetsatPro.setText(detailPengadaanDAO.getSatuan());
        if(arrayList.size()==1)
            holder.removeBtn.setEnabled(false);
        else
            holder.removeBtn.setEnabled(true);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public List<detailPengadaanDAO> getArrayList()
    {
        return arrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView subtotal;
        EditText edtdetjmlPro, edtdetsatPro;
        Spinner edtdetPro;
        Button removeBtn;
        int hargabeli;

        public ViewHolder(View itemView) {
            super(itemView);
            subtotal = itemView.findViewById(R.id.subtotal1);
            edtdetPro = itemView.findViewById(R.id.detailProduk1);
            edtdetjmlPro = itemView.findViewById(R.id.detailJmlProduk1);
            edtdetsatPro = itemView.findViewById(R.id.detailSatuanProduk1);
            removeBtn = itemView.findViewById(R.id.removeBtn1);

            ArrayAdapter<produkDAO> adapter = new ArrayAdapter<produkDAO>(context,
                    R.layout.spinner_item_white, mListProduk);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            edtdetPro.setAdapter(adapter);

            edtdetPro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    produkDAO produk = (produkDAO) adapterView.getSelectedItem();
                    hargabeli = produk.getHarga_beli_produk();
                    detailPengadaanDAO detailPengadaanDAO = arrayList.get(getAdapterPosition());
                    detailPengadaanDAO.setId_produk(produk.getId_produk());
                    detailPengadaanDAO.setSubtotal_pengadaan(detailPengadaanDAO.getJml_pengadaan_produk()*hargabeli);
                    arrayList.set(getAdapterPosition(), detailPengadaanDAO);
                    subtotal.setText("Subtotal Produk: Rp "+detailPengadaanDAO.getJml_pengadaan_produk()*hargabeli);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            edtdetjmlPro.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    detailPengadaanDAO detailPengadaanDAO = arrayList.get(getAdapterPosition());
                    if(charSequence.toString().equalsIgnoreCase(""))
                        detailPengadaanDAO.setJml_pengadaan_produk(0);
                    else
                        detailPengadaanDAO.setJml_pengadaan_produk(Integer.parseInt(charSequence.toString()));
                    detailPengadaanDAO.setSubtotal_pengadaan(detailPengadaanDAO.getJml_pengadaan_produk()*hargabeli);
                    arrayList.set(getAdapterPosition(), detailPengadaanDAO);
                    subtotal.setText("Subtotal Produk: Rp "+detailPengadaanDAO.getJml_pengadaan_produk()*hargabeli);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            edtdetsatPro.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    detailPengadaanDAO detailPengadaanDAO = arrayList.get(getAdapterPosition());
                    detailPengadaanDAO.setSatuan(charSequence.toString());
                    arrayList.set(getAdapterPosition(), detailPengadaanDAO);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            removeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    detailPengadaanDAO detailPengadaanDAO = arrayList.get(getAdapterPosition());
                    if(detailPengadaanDAO.getId_detail_pengadaan()!=0){
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Anda yakin menghapus produk ini?")
                                .setCancelable(false)
                                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
                                        Call<cudDataMaster> pengadaanCall = apiService.deleteDetailPengadaan(arrayList.get(getAdapterPosition()).getId_detail_pengadaan());
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

                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), arrayList.size());
                        notifyDataSetChanged();
                    }

                }
            });

        }

    }

}
