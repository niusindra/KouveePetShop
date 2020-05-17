package com.kel1.kouveepetshop.View.TransaksiLayanan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.DAO.transaksiLayananDAO;
import com.kel1.kouveepetshop.DAO.transaksiProdukDAO;
import com.kel1.kouveepetshop.R;

import java.util.List;

public class RecycleAdapterTransLayShowLog extends RecyclerView.Adapter<RecycleAdapterTransLayShowLog.MyViewHolder>{
    private Context context;
    private List<transaksiLayananDAO> result;

    public static final String EXTRA_TEXT = "com.kel1.kouveepetshop.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.kel1.kouveepetshop.EXTRA_NUMBER";

    public RecycleAdapterTransLayShowLog(Context context, List<transaksiLayananDAO> result){
        this.context=context;
        this.result=result;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_adapter_translay_show_log,viewGroup,false);
        final MyViewHolder holder = new MyViewHolder(v);
        return  holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mNama, mTanggal, mTotal, mStatus, mCreatedAt, mDeletedAt, mEditedAt;
        private LinearLayout mParent;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mNama=itemView.findViewById(R.id.RC_Pengadaan_Name1);
            mTanggal=itemView.findViewById(R.id.RC_tglPengadaan1);
            mTotal=itemView.findViewById(R.id.RC_totalPengadaan1);
            mStatus=itemView.findViewById(R.id.RC_statusPengadaan1);
            mCreatedAt=itemView.findViewById(R.id.RC_AdaanCreated);
            mEditedAt= itemView.findViewById(R.id.RC_AdaanEdited);
            mDeletedAt=itemView.findViewById(R.id.RC_AdaanDeleted);
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
        final transaksiLayananDAO transaksiLayananDAO=result.get(i);
        myViewHolder.mNama.setText("ID Transaksi\t: "+transaksiLayananDAO.getId_trans_layanan());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public void filterList(List<transaksiLayananDAO> filteredList) {
        result = filteredList;
        notifyDataSetChanged();
    }

    public void setResult(List<transaksiLayananDAO> result) {
        this.result = result;
        notifyDataSetChanged();
    }

    public List<transaksiLayananDAO> getResult() {
        return result;
    }
}
