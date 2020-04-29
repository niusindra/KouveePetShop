package com.kel1.kouveepetshop.View.UkuranHewan;

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

import com.kel1.kouveepetshop.DAO.ukuranhewanDAO;
import com.kel1.kouveepetshop.R;

import java.util.List;

public class RecycleAdapterUkuran extends RecyclerView.Adapter<RecycleAdapterUkuran.MyViewHolder>{
    private Context context;
    private List<ukuranhewanDAO> result;

    public static final String EXTRA_TEXT = "com.kel1.kouveepetshop.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.kel1.kouveepetshop.EXTRA_NUMBER";

    public RecycleAdapterUkuran(Context context, List<ukuranhewanDAO> result){
        this.context=context;
        this.result=result;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_adapter_ukuran_show,viewGroup,false);
        final MyViewHolder holder = new MyViewHolder(v);
        return  holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mNama;
        private LinearLayout mParent;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mNama=itemView.findViewById(R.id.adapterNamaUk);
            mParent=itemView.findViewById(R.id.RC_Ukuran_Event_Parent);
            context = itemView.getContext();
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,"Oh you touch me?",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        final ukuranhewanDAO ukuranDAO =result.get(i);
        myViewHolder.mNama.setText("Ukuran Hewan\t: "+ukuranDAO.getUkuran());
        myViewHolder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ukuranEdit.class);
                intent.putExtra(EXTRA_TEXT, new String[] {ukuranDAO.getUkuran()});
                intent.putExtra(EXTRA_NUMBER, ukuranDAO.getId_ukuran());
                context.startActivity(intent);
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
