package com.kel1.kouveepetshop.View.TransaksiProduk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.DAO.detailPengadaanDAO;
import com.kel1.kouveepetshop.DAO.detailProdukDAO;
import com.kel1.kouveepetshop.DAO.produkDAO;
import com.kel1.kouveepetshop.R;

import java.util.List;

public class AdapterTransProShow extends RecyclerView.Adapter<AdapterTransProShow.ViewHolder>{
    private Context context;
    private  List<detailProdukDAO> arrayList;
    private List<produkDAO> mListProduk;

    public AdapterTransProShow(Context context, List<detailProdukDAO> arrayList, List<produkDAO> mListProduk) {
        this.context = context;
        this.arrayList = arrayList;
        this.mListProduk = mListProduk;
    }

    @Override
    public AdapterTransProShow.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.transpro_show_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterTransProShow.ViewHolder holder, final int position) {
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
        public TextView namaProduk;
        public int hargajual;

        public ViewHolder(View itemView) {
            super(itemView);
            harga = itemView.findViewById(R.id.hargajual);
            namaProduk = itemView.findViewById(R.id.namaProduk);
            subtotal = itemView.findViewById(R.id.subtotal);
            total = itemView.findViewById(R.id.total);
            jumlah = itemView.findViewById(R.id.jumlahbeli);
            sisa = itemView.findViewById(R.id.sisaStok);

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
