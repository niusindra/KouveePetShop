package com.kel1.kouveepetshop.View.Hewan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.DAO.hewanDAO;
import com.kel1.kouveepetshop.R;

import java.util.List;

public class RecycleAdapterHewan extends RecyclerView.Adapter<RecycleAdapterHewan.MyViewHolder>{
    private Context context;
    private List<hewanDAO> result;

    public static final String EXTRA_TEXT = "com.kel1.kouveepetshop.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.kel1.kouveepetshop.EXTRA_NUMBER";

    public RecycleAdapterHewan(Context context, List<hewanDAO> result){
        this.context=context;
        this.result=result;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_adapter_hewan_show,viewGroup,false);
        final MyViewHolder holder = new MyViewHolder(v);
        return  holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mNama, mTglLahir,mCusPro;
        private LinearLayout mParent;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mCusPro=itemView.findViewById(R.id.adapterCusPro);
            mNama=itemView.findViewById(R.id.adapterNamaHewan);
            mTglLahir=itemView.findViewById(R.id.adapterTglLhrHewan);
            mParent=itemView.findViewById(R.id.hewanParent);
            context = itemView.getContext();
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,"Oh you touch me?", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        final hewanDAO hewanDAO=result.get(i);
        myViewHolder.mNama.setText("Nama\t: "+hewanDAO.getNama_hewan());
        myViewHolder.mTglLahir.setText("Tanggal Lahir\t: "+hewanDAO.getTgl_lahir_hewan());
        myViewHolder.mCusPro.setText("Pemilik\t: "+hewanDAO.getNama_customer());
        myViewHolder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, hewanEdit.class);
                intent.putExtra(EXTRA_TEXT, new String[] {hewanDAO.getNama_hewan(),hewanDAO.getTgl_lahir_hewan()});
                intent.putExtra(EXTRA_NUMBER, new int[] {hewanDAO.getId_hewan(),hewanDAO.getId_customer()});
                context.startActivity(intent);
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