package com.kel1.kouveepetshop.View.Customer;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.DAO.customerDAO;
import com.kel1.kouveepetshop.R;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder>{
    private Context context;
    private List<customerDAO> result;
    public CardView cardView;
    public static final String EXTRA_TEXT = "com.kel1.kouveepetshop.EXTRA_TEXT";

    public static final String EXTRA_NUMBER = "com.kel1.kouveepetshop.EXTRA_NUMBER";
    public RecycleAdapter(Context context, List<customerDAO> result){
        this.context=context;
        this.result=result;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_adapter_costumer_show,viewGroup,false);
        final MyViewHolder holder = new MyViewHolder(v);
        return  holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mNama, mAlamat, mTgllahir, mTelp;
        private LinearLayout mParent;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mNama=itemView.findViewById(R.id.RC_Costumer_Name);
            mAlamat=itemView.findViewById(R.id.RC_Alamat);
            mTgllahir=itemView.findViewById(R.id.RC_TanggalLahir);
            mTelp=itemView.findViewById(R.id.RC_Telp);
            mParent=itemView.findViewById(R.id.RC_Admin_Event_Parent);
            cardView =itemView.findViewById(R.id.RC_Customer_cardView);
            context = itemView.getContext();
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,"Oh you touch me?",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        final customerDAO customerDAO=result.get(i);
        myViewHolder.mNama.setText("Nama\t: "+customerDAO.getNama_customer());
        myViewHolder.mAlamat.setText("Alamat\t: "+customerDAO.getAlamat_customer());
        myViewHolder.mTgllahir.setText("Tgl Lahir\t: "+customerDAO.getTgllahir_customer());
        myViewHolder.mTelp.setText("Telp\t: "+customerDAO.getTelp_customer()+"\n");
        myViewHolder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Oh you touch me?",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,customerEdit.class);
                intent.putExtra(EXTRA_TEXT, new String[] {customerDAO.getNama_customer(),customerDAO.getAlamat_customer(),customerDAO.getTgllahir_customer()
                ,customerDAO.getTelp_customer()});
                intent.putExtra(EXTRA_NUMBER, customerDAO.getId_customer());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }
}
