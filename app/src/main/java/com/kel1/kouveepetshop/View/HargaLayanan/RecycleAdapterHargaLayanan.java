package com.kel1.kouveepetshop.View.HargaLayanan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.DAO.hargalayananDAO;
import com.kel1.kouveepetshop.DAO.ukuranhewanDAO;
import com.kel1.kouveepetshop.R;

import java.util.List;

public class RecycleAdapterHargaLayanan extends RecyclerView.Adapter<RecycleAdapterHargaLayanan.MyViewHolder>{
    private Context context;
    private List<hargalayananDAO> result;

    public static final String EXTRA_TEXT = "com.kel1.kouveepetshop.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.kel1.kouveepetshop.EXTRA_NUMBER";

    public RecycleAdapterHargaLayanan(Context context, List<hargalayananDAO> result){
        this.context=context;
        this.result=result;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_adapter_hargalayanan_show,viewGroup,false);
        final MyViewHolder holder = new MyViewHolder(v);
        return  holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mLayanan, mJenis, mUkuran, mHarga;
        private LinearLayout mParent;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mLayanan=itemView.findViewById(R.id.RC_Layanan_HL);
            mJenis=itemView.findViewById(R.id.RC_Jenis_HL);
            mUkuran=itemView.findViewById(R.id.RC_Ukuran_HL);
            mHarga=itemView.findViewById(R.id.RC_Harga_HL);
            mParent=itemView.findViewById(R.id.hlParent);
            context = itemView.getContext();
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,"Oh you touch me?",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        final hargalayananDAO hargalayananDAO=result.get(i);
        myViewHolder.mLayanan.setText("Nama Layanan\t: "+hargalayananDAO.getNama_layananHL());
        myViewHolder.mJenis.setText("Jenis Hewan\t: "+hargalayananDAO.getJenisHL());
        myViewHolder.mUkuran.setText("Ukuran Hewan\t: "+hargalayananDAO.getUkuranHL());
        myViewHolder.mHarga.setText("Harga Layanan\t: "+hargalayananDAO.getHarga_Layanan()+"\n");
       final String harga = String.valueOf(hargalayananDAO.getHarga_Layanan());
        myViewHolder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, hargaLayananEdit.class);
                intent.putExtra(EXTRA_TEXT, new String[] {harga});
                intent.putExtra(EXTRA_NUMBER, new int[]{hargalayananDAO.getId_harga_layanan(),hargalayananDAO.getId_layanan(),hargalayananDAO.getId_jenis(),hargalayananDAO.getId_ukuran()});
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public void filterList(List<hargalayananDAO> filteredList) {
        result = filteredList;
        notifyDataSetChanged();
    }
}