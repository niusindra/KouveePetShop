package com.kel1.kouveepetshop.View.Customer;

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

import com.kel1.kouveepetshop.DAO.customerDAO;
import com.kel1.kouveepetshop.R;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder>{
    private Context context;
    private List<customerDAO> result;
    public RecycleAdapter(Context context, List<customerDAO> result){
        this.context=context;
        this.result=result;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_adapter,viewGroup,false);
        final MyViewHolder holder = new MyViewHolder(v);
        return  holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mNama, mAlamat, mTgllahir, mTelp;
        private LinearLayout mParent;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mNama=itemView.findViewById(R.id.txtNama);
            mAlamat=itemView.findViewById(R.id.txtAlamat);
            mTgllahir=itemView.findViewById(R.id.txtTgllahir);
            mTelp=itemView.findViewById(R.id.txtTelp);
            mParent=itemView.findViewById(R.id.Parent);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,"Oh you touch me?",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        customerDAO customerDAO=result.get(i);
        myViewHolder.mNama.setText("Nama\t: "+customerDAO.getNama_customer());
        myViewHolder.mAlamat.setText("Alamat\t: "+customerDAO.getAlamat_customer());
        myViewHolder.mTgllahir.setText("Tgl Lahir\t: "+customerDAO.getTgllahir_customer());
        myViewHolder.mTelp.setText("Telp\t: "+customerDAO.getTelp_customer()+"\n");
//        myViewHolder.mParent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context,"Oh you touch me?",Toast.LENGTH_SHORT).show();
//                context.startActivity(new Intent(context,CRUD.class));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }
}
