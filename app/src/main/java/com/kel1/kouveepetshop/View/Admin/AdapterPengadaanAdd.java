package com.kel1.kouveepetshop.View.Admin;

import android.content.Context;
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

import com.kel1.kouveepetshop.DAO.customerDAO;
import com.kel1.kouveepetshop.DAO.detailPengadaanDAO;
import com.kel1.kouveepetshop.DAO.produkDAO;
import com.kel1.kouveepetshop.R;

import java.util.List;

public class AdapterPengadaanAdd extends RecyclerView.Adapter<AdapterPengadaanAdd.ViewHolder>{
    private Context context;
    private  List<detailPengadaanDAO> arrayList, tempAll, tempNew;
    private List<produkDAO> mListProduk;

    public AdapterPengadaanAdd(Context context, List<detailPengadaanDAO> arrayList, List<produkDAO> mListProduk) {
        this.context = context;
        this.arrayList = arrayList;
        this.mListProduk = mListProduk;
    }

    @Override
    public AdapterPengadaanAdd.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.pengadaan_add_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterPengadaanAdd.ViewHolder holder, int position) {
        detailPengadaanDAO detailPengadaanDAO=arrayList.get(position);
        for (int i = 0; i < mListProduk.size(); i++) {
            if(mListProduk.get(i).getId_produk()==detailPengadaanDAO.getId_produk()){
                holder.edtdetPro.setSelection(i);
            }
        }
        holder.edtdetjmlPro.setText(String.valueOf(detailPengadaanDAO.getJml_pengadaan_produk()));
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
        EditText edtdetjmlPro;
        Spinner edtdetPro;
        Button removeBtn;
        int hargabeli;

        public ViewHolder(View itemView) {
            super(itemView);
            subtotal = itemView.findViewById(R.id.subtotal);
            edtdetPro = itemView.findViewById(R.id.detailProduk);
            edtdetjmlPro = itemView.findViewById(R.id.detailJmlProduk);
            removeBtn = itemView.findViewById(R.id.removeBtn);

            ArrayAdapter<produkDAO> adapter = new ArrayAdapter<produkDAO>(context,
                    android.R.layout.simple_spinner_item, mListProduk);
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
                    detailPengadaanDAO.setJml_pengadaan_produk(Integer.parseInt(charSequence.toString()));
                    detailPengadaanDAO.setSubtotal_pengadaan(detailPengadaanDAO.getJml_pengadaan_produk()*hargabeli);
                    arrayList.set(getAdapterPosition(), detailPengadaanDAO);
                    subtotal.setText("Subtotal Produk: Rp "+detailPengadaanDAO.getJml_pengadaan_produk()*hargabeli);
                }

                @Override
                public void afterTextChanged(Editable editable) {

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

        }

    }

}
