package com.kel1.kouveepetshop.View.JenisHewan;

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

import com.kel1.kouveepetshop.DAO.jenishewanDAO;
import com.kel1.kouveepetshop.R;

import java.util.List;

public class RecycleAdapterJenisHewanLog extends RecyclerView.Adapter<RecycleAdapterJenisHewanLog.MyViewHolder>{
    private Context context;
    private List<jenishewanDAO> result;
    public CardView cardView;

    public RecycleAdapterJenisHewanLog(Context context, List<jenishewanDAO> result){
        this.context=context;
        this.result=result;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_adapter_jenis_show_log,viewGroup,false);
        final MyViewHolder holder = new MyViewHolder(v);
        return  holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mNamaUkuran, mDeletedAt, mCreatedAt, mEditedAt;
        private LinearLayout mParent;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mNamaUkuran=itemView.findViewById(R.id.adapterUkuranLog);
            mCreatedAt=itemView.findViewById(R.id.custcreatedatjh);
            mEditedAt=itemView.findViewById(R.id.custeditedatjh);
            mDeletedAt=itemView.findViewById(R.id.custdeletedatjh);
            mParent=itemView.findViewById(R.id.RC_Jenis_Event_Log_Parent);
            cardView =itemView.findViewById(R.id.RC_Layanan_Log_cardView);
            context = itemView.getContext();
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,"Oh you touch me?", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        final jenishewanDAO jenisDAO=result.get(i);
        myViewHolder.mNamaUkuran.setText("Jenis Hewan\t: "+jenisDAO.getJenis());
        myViewHolder.mCreatedAt.setText("Dibuat pada\t: "+jenisDAO.getJns_created_at());
        myViewHolder.mEditedAt.setText("Diedit pada\t: "+jenisDAO.getJns_edited_at());
        myViewHolder.mDeletedAt.setText("Dihapus pada\t: "+jenisDAO.getJns_deleted_at());
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

    public void filterList(List<jenishewanDAO> filteredList) {
        result = filteredList;
        notifyDataSetChanged();
    }
}

