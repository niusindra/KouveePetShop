package com.kel1.kouveepetshop.View.UkuranHewan;

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

import com.kel1.kouveepetshop.DAO.ukuranhewanDAO;
import com.kel1.kouveepetshop.R;

import java.util.List;

public class RecycleAdapterUkuranLog extends RecyclerView.Adapter<RecycleAdapterUkuranLog.MyViewHolder>{
    private Context context;
    private List<ukuranhewanDAO> result;
    public CardView cardView;

    public RecycleAdapterUkuranLog(Context context, List<ukuranhewanDAO> result){
        this.context=context;
        this.result=result;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_adapter_ukuran_show_log,viewGroup,false);
        final MyViewHolder holder = new MyViewHolder(v);
        return  holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mNamaUkuran, mDeletedAt, mCreatedAt, mEditedAt;
        private LinearLayout mParent;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mNamaUkuran=itemView.findViewById(R.id.adapterUkuranLog);
            mCreatedAt=itemView.findViewById(R.id.custcreatedatuk);
            mEditedAt=itemView.findViewById(R.id.custeditedatuk);
            mDeletedAt=itemView.findViewById(R.id.custdeletedatuk);
            mParent=itemView.findViewById(R.id.RC_Ukuran_Event_Log_Parent);
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
        final ukuranhewanDAO ukuranDAO=result.get(i);
        myViewHolder.mNamaUkuran.setText("Ukuran Hewan\t: "+ukuranDAO.getUkuran());
        myViewHolder.mCreatedAt.setText("Dibuat pada\t: "+ukuranDAO.getUkrn_created_at());
        myViewHolder.mEditedAt.setText("Diedit pada\t: "+ukuranDAO.getUkrn_edited_at());
        myViewHolder.mDeletedAt.setText("Dihapus pada\t: "+ukuranDAO.getUkrn_deleted_at());
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

    public void filterList(List<ukuranhewanDAO> filteredList) {
        result = filteredList;
        notifyDataSetChanged();
    }
}
