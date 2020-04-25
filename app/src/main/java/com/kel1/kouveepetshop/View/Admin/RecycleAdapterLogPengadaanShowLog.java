package com.kel1.kouveepetshop.View.Admin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.DAO.pengadaanDAO;
import com.kel1.kouveepetshop.R;

import java.util.List;

public class RecycleAdapterLogPengadaanShowLog extends RecyclerView.Adapter<RecycleAdapterLogPengadaanShowLog.MyViewHolder>{
    private Context context;
    private List<pengadaanDAO> result;

    public static final String EXTRA_TEXT = "com.kel1.kouveepetshop.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.kel1.kouveepetshop.EXTRA_NUMBER";

    public RecycleAdapterLogPengadaanShowLog(Context context, List<pengadaanDAO> result){
        this.context=context;
        this.result=result;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_adapter_pengadaan_show_log,viewGroup,false);
        final MyViewHolder holder = new MyViewHolder(v);
        return  holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mNama, mTanggal, mTotal, mStatus, mCreatedAt, mCreatedBy, mEditedAt;
        private LinearLayout mParent;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mNama=itemView.findViewById(R.id.RC_Pengadaan_Name1);
            mTanggal=itemView.findViewById(R.id.RC_tglPengadaan1);
            mTotal=itemView.findViewById(R.id.RC_totalPengadaan1);
            mStatus=itemView.findViewById(R.id.RC_statusPengadaan1);
            mCreatedAt=itemView.findViewById(R.id.RC_AdaanCreated);
            mCreatedBy=itemView.findViewById(R.id.RC_AdaanEdited);
            mEditedAt=itemView.findViewById(R.id.RC_AdaanDeleted);
            mParent=itemView.findViewById(R.id.RC_Pengadaan_Event_Parent);
            context = itemView.getContext();
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,"Oh you touch me?",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        final pengadaanDAO pengadaanDAO=result.get(i);
        myViewHolder.mNama.setText("Nama Supplier\t: "+pengadaanDAO.getNama_supplier());
        myViewHolder.mTanggal.setText("Tanggal\t: "+pengadaanDAO.getTgl_pengadaan());
        myViewHolder.mTotal.setText("Total\t: Rp "+pengadaanDAO.getTotal_pengadaan()+", \n\tdengan "+pengadaanDAO.getJumlah_jenis()+" jenis produk");
        myViewHolder.mStatus.setText("Status\t: "+pengadaanDAO.getStatus_pengadaan()+"\n");
        myViewHolder.mCreatedAt.setText("Dibuat pada\t: "+pengadaanDAO.getAdaan_created_at());
        myViewHolder.mEditedAt.setText("Diedit pada\t: "+pengadaanDAO.getAdaan_edited_at());
        myViewHolder.mCreatedBy.setText("Dihapus pada\t: "+pengadaanDAO.getAdaan_deleted_at());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public void filterList(List<pengadaanDAO> filteredList) {
        result = filteredList;
        notifyDataSetChanged();
    }

    public void setResult(List<pengadaanDAO> result) {
        this.result = result;
        notifyDataSetChanged();
    }

    public List<pengadaanDAO> getResult() {
        return result;
    }
}
