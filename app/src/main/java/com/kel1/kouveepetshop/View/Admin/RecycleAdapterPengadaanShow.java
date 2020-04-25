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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.DAO.pengadaanDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.View.Customer.customerEdit;

import java.util.List;

public class RecycleAdapterPengadaanShow extends RecyclerView.Adapter<RecycleAdapterPengadaanShow.MyViewHolder>{
    private Context context;
    private List<pengadaanDAO> result;

    public static final String EXTRA_TEXT = "com.kel1.kouveepetshop.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.kel1.kouveepetshop.EXTRA_NUMBER";

    public RecycleAdapterPengadaanShow(Context context, List<pengadaanDAO> result){
        this.context=context;
        this.result=result;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_adapter_pengadaan_show,viewGroup,false);
        final MyViewHolder holder = new MyViewHolder(v);
        return  holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mNama, mTanggal, mTotal, mStatus;
        private LinearLayout mParent;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mNama=itemView.findViewById(R.id.RC_Pengadaan_Name);
            mTanggal=itemView.findViewById(R.id.RC_tglPengadaan);
            mTotal=itemView.findViewById(R.id.RC_totalPengadaan);
            mStatus=itemView.findViewById(R.id.RC_statusPengadaan);
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
        myViewHolder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pengadaanDAO.getStatus_pengadaan().equalsIgnoreCase("Pending")){
                    Intent intent = new Intent(context,pengadaanEdit.class);
                    intent.putExtra(EXTRA_TEXT, new String[] {pengadaanDAO.getNama_supplier(),pengadaanDAO.getTgl_pengadaan(),
                            pengadaanDAO.getStatus_pengadaan()});
                    intent.putExtra(EXTRA_NUMBER, new int[] {pengadaanDAO.getId_pengadaan(), pengadaanDAO.getId_supplier(), pengadaanDAO.getTotal_pengadaan()});
                    context.startActivity(intent);
                }else if(pengadaanDAO.getStatus_pengadaan().equalsIgnoreCase("belum sampai")){
                    Toast.makeText(context,"Tidak bisa edit",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context,"Sudah sampai bos",Toast.LENGTH_LONG).show();
                }

            }
        });
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
