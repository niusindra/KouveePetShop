package com.kel1.kouveepetshop.View.HargaLayanan;

import android.content.Context;
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
import com.kel1.kouveepetshop.R;

import java.util.List;

public class RecycleAdapterHargaLayananLog extends RecyclerView.Adapter<RecycleAdapterHargaLayananLog.MyViewHolder>{
    private Context context;
    private List<hargalayananDAO> result;
    public CardView cardView;

    public RecycleAdapterHargaLayananLog(Context context, List<hargalayananDAO> result){
        this.context=context;
        this.result=result;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_adapter_hargalayanan_show_log,viewGroup,false);
        final MyViewHolder holder = new MyViewHolder(v);
        return  holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mNamaLayanan, mNamaJenis, mNamaUkuran, mHargaLayanan, mDeletedAt, mCreatedAt, mEditedAt, mDeletedBy, mCreatedBy, mEditedBy;
        private LinearLayout mParent;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mNamaLayanan=itemView.findViewById(R.id.adapterNamaLayananLogHL);
            mNamaJenis=itemView.findViewById(R.id.adapterNamaJenisLogHL);
            mNamaUkuran=itemView.findViewById(R.id.adapterNamaUkuranLog);
            mHargaLayanan=itemView.findViewById(R.id.adapterHargaLayananLog);
            mCreatedAt=itemView.findViewById(R.id.hlcreatedatlay);
            mEditedAt=itemView.findViewById(R.id.hleditedatlay);
            mDeletedAt=itemView.findViewById(R.id.hldeletedatlay);
            mParent=itemView.findViewById(R.id.RC_HargaLayanan_Event_Log_Parent);
            context = itemView.getContext();
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,"Oh you touch me?", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        final hargalayananDAO hargalayananDAO=result.get(i);
        myViewHolder.mNamaLayanan.setText("Nama Layanan\t: "+hargalayananDAO.getNama_layananHL());
        myViewHolder.mNamaJenis.setText("Jenis Hewan\t: "+hargalayananDAO.getJenisHL());
        myViewHolder.mNamaUkuran.setText("Ukuran Hewan\t: "+hargalayananDAO.getUkuranHL());
        myViewHolder.mHargaLayanan.setText("Harga Layanan\t: "+hargalayananDAO.getHarga_Layanan());
        myViewHolder.mCreatedAt.setText("Dibuat pada\t: "+hargalayananDAO.getHarga_created_at());
        myViewHolder.mEditedAt.setText("Diedit pada\t: "+hargalayananDAO.getHarga_edited_at());
        myViewHolder.mDeletedAt.setText("Dihapus pada\t: "+hargalayananDAO.getHarga_deleted_at());
        myViewHolder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
