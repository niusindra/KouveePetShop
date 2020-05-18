package com.kel1.kouveepetshop.View.TransaksiLayanan;

import android.content.Context;
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

import com.kel1.kouveepetshop.DAO.detailLayananDAO;
import com.kel1.kouveepetshop.DAO.detailProdukDAO;
import com.kel1.kouveepetshop.DAO.hargalayananDAO;
import com.kel1.kouveepetshop.DAO.produkDAO;
import com.kel1.kouveepetshop.R;

import java.util.List;

public class AdapterTransLayAdd extends RecyclerView.Adapter<AdapterTransLayAdd.ViewHolder>{
    private Context context;
    private  List<detailLayananDAO> arrayList;
    private List<hargalayananDAO> mListLayanan;

    public AdapterTransLayAdd(Context context, List<detailLayananDAO> arrayList, List<hargalayananDAO> mListLayanan) {
        this.context = context;
        this.arrayList = arrayList;
        this.mListLayanan = mListLayanan;
    }

    @Override
    public AdapterTransLayAdd.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.translay_add_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterTransLayAdd.ViewHolder holder, final int position) {
        detailLayananDAO detailLayananDAO=arrayList.get(position);
        for (int i = 0; i < mListLayanan.size(); i++) {
            if(detailLayananDAO.getId_harga_layanan()==mListLayanan.get(i).getId_harga_layanan()){
                holder.namaLayanan.setText(mListLayanan.get(i).getNama_layananHL());
                holder.harga.setText("Rp."+mListLayanan.get(i).getId_harga_layanan());
                holder.hargajual=mListLayanan.get(i).getId_harga_layanan();
            }
        }
        holder.jumlah.setText(String.valueOf(detailLayananDAO.getJumlah_beli_layanan()));
        holder.subtotal.setText(String.valueOf(detailLayananDAO.getSubtotal_layanan()));

        if(position==arrayList.size()-1 || arrayList.size()==0){
            holder.addLayanan.setVisibility(View.VISIBLE);
        }else
            holder.addLayanan.setVisibility(View.GONE);
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
        public TextView harga,subtotal,total,jumlah;
        public AutoCompleteTextView namaLayanan;
        public Button addLayanan,kurang, tambah;
        public ImageView removeBtn;
        public int hargajual;

        public ViewHolder(View itemView) {
            super(itemView);
            harga = itemView.findViewById(R.id.hargajual);
            namaLayanan = itemView.findViewById(R.id.namaLayanan);
            subtotal = itemView.findViewById(R.id.subtotal);
            total = itemView.findViewById(R.id.total);
            removeBtn = itemView.findViewById(R.id.btnHapus);
            addLayanan = itemView.findViewById(R.id.btnTambahLayanan);
            kurang = itemView.findViewById(R.id.btnKurangJml);
            tambah = itemView.findViewById(R.id.btnTambahJml);
            jumlah = itemView.findViewById(R.id.jumlahbeli);

            ArrayAdapter<hargalayananDAO> adapter = new ArrayAdapter<hargalayananDAO>
                    (context, android.R.layout.select_dialog_item, mListLayanan);
            namaLayanan.setAdapter(adapter);

            harga.setText("Rp.0");
            namaLayanan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    hargalayananDAO layanan = (hargalayananDAO) adapterView.getItemAtPosition(i);
                    hargajual=layanan.getHarga_Layanan();
                    harga.setText("Rp."+hargajual);
                    detailLayananDAO detailLayananDAO = arrayList.get(getAdapterPosition());
                    detailLayananDAO.setId_harga_layanan(layanan.getId_harga_layanan());
                    detailLayananDAO.setSubtotal_layanan(detailLayananDAO.getJumlah_beli_layanan()*hargajual);
                    arrayList.set(getAdapterPosition(), detailLayananDAO);
                    subtotal.setText("Rp."+detailLayananDAO.getJumlah_beli_layanan()*hargajual);
                    total.setText("Total : Rp."+settotal());
                    notifyDataSetChanged();
                }
            });

            kurang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    harga.setText("Rp."+hargajual);
                    detailLayananDAO detailLayananDAO = arrayList.get(getAdapterPosition());
                    if(detailLayananDAO.getJumlah_beli_layanan()>0){
                        detailLayananDAO.setJumlah_beli_layanan(detailLayananDAO.getJumlah_beli_layanan()-1);
                        jumlah.setText(String.valueOf(detailLayananDAO.getJumlah_beli_layanan()));
                        detailLayananDAO.setSubtotal_layanan(detailLayananDAO.getJumlah_beli_layanan()*hargajual);
                        arrayList.set(getAdapterPosition(), detailLayananDAO);
                        subtotal.setText("Rp "+detailLayananDAO.getJumlah_beli_layanan()*hargajual);
                        total.setText("Total : Rp."+settotal());
                        notifyDataSetChanged();
                    }
                }
            });

            tambah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    harga.setText("Rp."+hargajual);
                    detailLayananDAO detailLayananDAO = arrayList.get(getAdapterPosition());
                    detailLayananDAO.setJumlah_beli_layanan(detailLayananDAO.getJumlah_beli_layanan()+1);
                    jumlah.setText(String.valueOf(detailLayananDAO.getJumlah_beli_layanan()));
                    detailLayananDAO.setSubtotal_layanan(detailLayananDAO.getJumlah_beli_layanan()*hargajual);
                    arrayList.set(getAdapterPosition(), detailLayananDAO);
                    subtotal.setText("Rp "+detailLayananDAO.getJumlah_beli_layanan()*hargajual);
                    total.setText("Total : Rp."+settotal());
                    notifyDataSetChanged();
                }
            });

            removeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    arrayList.remove(arrayList.get(getAdapterPosition()));
                    namaLayanan.setText("");
                    harga.setText("Rp.0");
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), arrayList.size());
                    notifyDataSetChanged();
                }
            });

            addLayanan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    detailLayananDAO detailLayananDAO = new detailLayananDAO();
                    arrayList.add(detailLayananDAO);
//                    arrayList.add(getAdapterPosition(),detailLayananDAO);
                    notifyItemInserted(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), arrayList.size());
                    notifyDataSetChanged();
                    addLayanan.setVisibility(View.GONE);
                    total.setVisibility(View.GONE);
                }
            });

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
