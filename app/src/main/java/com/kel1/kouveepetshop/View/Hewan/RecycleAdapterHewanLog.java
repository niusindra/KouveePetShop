package com.kel1.kouveepetshop.View.Hewan;

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

import com.kel1.kouveepetshop.DAO.hewanDAO;
import com.kel1.kouveepetshop.R;

import java.util.List;

public class RecycleAdapterHewanLog extends RecyclerView.Adapter<RecycleAdapterHewanLog.MyViewHolder>{
    private Context context;
    private List<hewanDAO> result;
    public CardView cardView;

    public RecycleAdapterHewanLog(Context context, List<hewanDAO> result){
        this.context=context;
        this.result=result;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_adapter_hewan_show_log,viewGroup,false);
        final MyViewHolder holder = new MyViewHolder(v);
        return  holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mNamaHewan, mTanggal, mPemilik, mDeletedAt, mCreatedAt, mEditedAt, mDeletedBy, mCreatedBy, mEditedBy;
        private LinearLayout mParent;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mNamaHewan=itemView.findViewById(R.id.RC_Hewan_Name);
            mTanggal=itemView.findViewById(R.id.RC_Tgl_Lahir_Hewan);
            mPemilik=itemView.findViewById(R.id.RC_Nama_Pemilik);
            mCreatedAt=itemView.findViewById(R.id.custcreatedath);
            mCreatedBy=itemView.findViewById(R.id.custcreatedbyh);
            mEditedAt=itemView.findViewById(R.id.custeditedath);
            mEditedBy=itemView.findViewById(R.id.custeditedbyh);
            mDeletedAt=itemView.findViewById(R.id.custdeletedath);
            mDeletedBy=itemView.findViewById(R.id.custdeletedbyh);
            mParent=itemView.findViewById(R.id.RC_Hewan_Parent);
            cardView =itemView.findViewById(R.id.RC_Hewan_Log_cardView);
            context = itemView.getContext();
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,"Oh you touch me?",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        final hewanDAO hewanDAO=result.get(i);
        myViewHolder.mNamaHewan.setText("Nama\t: "+hewanDAO.getNama_hewan());
        myViewHolder.mTanggal.setText("Tanggal Lahir\t: "+hewanDAO.getTgl_lahir_hewan());
        myViewHolder.mPemilik.setText("Pemilik\t: "+hewanDAO.getNama_customer());
        myViewHolder.mCreatedAt.setText("Dibuat pada\t: "+hewanDAO.getHwn_created_at());
        myViewHolder.mCreatedBy.setText("Dibuat oleh\t: "+hewanDAO.getHwn_created_by());
        myViewHolder.mEditedAt.setText("Diedit pada\t: "+hewanDAO.getHwn_edited_at());
        myViewHolder.mEditedBy.setText("Diedit oleh\t: "+hewanDAO.getHwn_edited_by());
        myViewHolder.mDeletedAt.setText("Dihapus pada\t: "+hewanDAO.getHwn_deleted_at());
        myViewHolder.mDeletedBy.setText("Dihapus oleh\t: "+hewanDAO.getHwn_deleted_by());
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

    public void filterList(List<hewanDAO> filteredList) {
        result = filteredList;
        notifyDataSetChanged();
    }
}

