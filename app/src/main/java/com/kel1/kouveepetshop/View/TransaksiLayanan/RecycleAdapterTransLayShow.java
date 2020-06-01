package com.kel1.kouveepetshop.View.TransaksiLayanan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.DAO.detailLayananDAO;
import com.kel1.kouveepetshop.DAO.transaksiLayananDAO;
import com.kel1.kouveepetshop.R;

import java.util.List;

public class RecycleAdapterTransLayShow extends RecyclerView.Adapter<RecycleAdapterTransLayShow.MyViewHolder>{
    private Context context;
    private List<transaksiLayananDAO> result;
    private List<detailLayananDAO> detailLayananList;

    public static final String EXTRA_TEXT = "com.kel1.kouveepetshop.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.kel1.kouveepetshop.EXTRA_NUMBER";

    public RecycleAdapterTransLayShow(Context context, List<transaksiLayananDAO> result){
        this.context=context;
        this.result=result;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_adapter_translay_show,viewGroup,false);
        final MyViewHolder holder = new MyViewHolder(v);
        return  holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView mNama, mTanggal, mTotal, mStatus;
        private Button hapie;
        private LinearLayout mParent;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mNama=itemView.findViewById(R.id.RC_Pengadaan_Name);
            mTanggal=itemView.findViewById(R.id.RC_tglPengadaan);
            mTotal=itemView.findViewById(R.id.RC_hewan2);
            mParent=itemView.findViewById(R.id.RC_Pengadaan_Event_Parent);
            context = itemView.getContext();
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        final transaksiLayananDAO transaksiLayananDAO=result.get(i);
        myViewHolder.mNama.setText("ID Transaksi\t: "+transaksiLayananDAO.getId_trans_layanan());
        myViewHolder.mTanggal.setText("Tanggal Transaksi\t: "+transaksiLayananDAO.getTanggal_trans_layanan());
        myViewHolder.mTotal.setText("Hewan\t: "+transaksiLayananDAO.getNama_hewan()+"-"+transaksiLayananDAO.getJenis());
        myViewHolder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,transLayEdit.class);
                intent.putExtra(EXTRA_TEXT, new String[] {transaksiLayananDAO.getId_trans_layanan()});
                intent.putExtra(EXTRA_NUMBER, new int[] {transaksiLayananDAO.getId_cs(), transaksiLayananDAO.getId_hewan()});
                context.startActivity(intent);
            }
        });

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
