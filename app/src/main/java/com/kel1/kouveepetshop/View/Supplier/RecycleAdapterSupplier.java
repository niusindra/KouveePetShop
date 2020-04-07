package com.kel1.kouveepetshop.View.Supplier;

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

import com.kel1.kouveepetshop.DAO.supplierDAO;
import com.kel1.kouveepetshop.R;

import java.util.List;

public class RecycleAdapterSupplier extends RecyclerView.Adapter<RecycleAdapterSupplier.MyViewHolder>{
    private Context context;
    private List<supplierDAO> result;

    public static final String EXTRA_TEXT = "com.kel1.kouveepetshop.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.kel1.kouveepetshop.EXTRA_NUMBER";

    public RecycleAdapterSupplier(Context context, List<supplierDAO> result){
        this.context=context;
        this.result=result;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_adapter_supplier_show,viewGroup,false);
        final MyViewHolder holder = new MyViewHolder(v);
        return  holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mNama, mAlamat, mTelp;
        private LinearLayout mParent;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mNama=itemView.findViewById(R.id.adapterNamaSupp);
            mAlamat=itemView.findViewById(R.id.adapterAlamatSupp);
            mTelp=itemView.findViewById(R.id.adapterTelpSupp);
            mParent=itemView.findViewById(R.id.RC_Supplier_Event_Parent);
            context = itemView.getContext();
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,"Oh you touch me?",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        final supplierDAO supplierDAO=result.get(i);
        myViewHolder.mNama.setText("Nama\t: "+supplierDAO.getNama_supplier());
        myViewHolder.mAlamat.setText("Alamat\t: "+supplierDAO.getAlamat_supplier());
        myViewHolder.mTelp.setText("Telp\t: "+supplierDAO.getTelp_supplier()+"\n");
        myViewHolder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, supplierEdit.class);
                intent.putExtra(EXTRA_TEXT, new String[] {supplierDAO.getNama_supplier(),supplierDAO.getAlamat_supplier(),supplierDAO.getTelp_supplier()});
                intent.putExtra(EXTRA_NUMBER, supplierDAO.getId_supplier());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public void filterList(List<supplierDAO> filteredList) {
        result = filteredList;
        notifyDataSetChanged();
    }
}
