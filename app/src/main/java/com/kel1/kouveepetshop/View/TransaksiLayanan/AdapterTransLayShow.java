package com.kel1.kouveepetshop.View.TransaksiLayanan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.DAO.detailLayananDAO;
import com.kel1.kouveepetshop.DAO.detailProdukDAO;
import com.kel1.kouveepetshop.DAO.hargalayananDAO;
import com.kel1.kouveepetshop.DAO.produkDAO;
import com.kel1.kouveepetshop.R;

import java.util.List;

public class AdapterTransLayShow extends RecyclerView.Adapter<AdapterTransLayShow.ViewHolder>{
    private Context context;
    private  List<detailLayananDAO> arrayList;
    private List<hargalayananDAO> mListLayanan;

    public AdapterTransLayShow(Context context, List<detailLayananDAO> arrayList, List<hargalayananDAO> mListLayanan) {
        this.context = context;
        this.arrayList = arrayList;
        this.mListLayanan = mListLayanan;
    }

    @Override
    public AdapterTransLayShow.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.translay_show_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterTransLayShow.ViewHolder holder, final int position) {
        detailLayananDAO detailLayananDAO=arrayList.get(position);
        hargalayananDAO hargalayananDAO = new hargalayananDAO();
        for (int i = 0; i < mListLayanan.size(); i++) {
            if(detailLayananDAO.getId_harga_layanan()==mListLayanan.get(i).getId_harga_layanan()){
                hargalayananDAO = mListLayanan.get(i);
            }
        }
        holder.namaLayanan.setText(hargalayananDAO.getNama_layananHL());
        holder.harga.setText("Rp."+hargalayananDAO.getHarga_Layanan());
        holder.hargajual=hargalayananDAO.getHarga_Layanan();
        holder.jumlah.setText("x"+String.valueOf(detailLayananDAO.getJumlah_beli_layanan()));
        holder.subtotal.setText(String.valueOf(detailLayananDAO.getSubtotal_layanan()));

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

    public List<detailLayananDAO> getArrayList()
    {
        return arrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView harga,subtotal,total,jumlah, sisa;
        public TextView namaLayanan;
        public int hargajual;

        public ViewHolder(View itemView) {
            super(itemView);
            harga = itemView.findViewById(R.id.hargajual);
            namaLayanan = itemView.findViewById(R.id.namaLayanan);
            subtotal = itemView.findViewById(R.id.subtotal);
            total = itemView.findViewById(R.id.total);
            jumlah = itemView.findViewById(R.id.jumlahbeli);
            sisa = itemView.findViewById(R.id.sisaStok);

        }

        public int settotal(){
            int total = 0, i;
            for(i=0;i<arrayList.size();i++){
                total+=arrayList.get(i).getSubtotal_layanan();
            }
            return total;
        }

    }

}
