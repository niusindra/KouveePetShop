package com.kel1.kouveepetshop.View.Layanan;

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

import com.kel1.kouveepetshop.DAO.layananDAO;
import com.kel1.kouveepetshop.R;

import java.util.List;

public class RecycleAdapterLayananLog extends RecyclerView.Adapter<RecycleAdapterLayananLog.MyViewHolder>{
    private Context context;
    private List<layananDAO> result;
    public CardView cardView;

    public RecycleAdapterLayananLog(Context context, List<layananDAO> result){
        this.context=context;
        this.result=result;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_adapter_layanan_show_log,viewGroup,false);
        final MyViewHolder holder = new MyViewHolder(v);
        return  holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mNamaLayanan, mDeletedAt, mCreatedAt, mEditedAt;
        private LinearLayout mParent;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mNamaLayanan=itemView.findViewById(R.id.adapterNamaLayananLog);
            mCreatedAt=itemView.findViewById(R.id.custcreatedatlay);
            mEditedAt=itemView.findViewById(R.id.custeditedatlay);
            mDeletedAt=itemView.findViewById(R.id.custdeletedatlay);
            mParent=itemView.findViewById(R.id.RC_Layanan_Event_Log_Parent);
            cardView =itemView.findViewById(R.id.RC_Layanan_Log_cardView);
            context = itemView.getContext();
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,"Oh you touch me?",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        final layananDAO layananDAO=result.get(i);
        myViewHolder.mNamaLayanan.setText("Nama Layanan\t: "+layananDAO.getNama_layanan());
        myViewHolder.mCreatedAt.setText("Dibuat pada\t: "+layananDAO.getLay_created_at());
        myViewHolder.mEditedAt.setText("Diedit pada\t: "+layananDAO.getLay_edited_at());
        myViewHolder.mDeletedAt.setText("Dihapus pada\t: "+layananDAO.getLay_deleted_at());
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

    public void filterList(List<layananDAO> filteredList) {
        result = filteredList;
        notifyDataSetChanged();
    }
}
