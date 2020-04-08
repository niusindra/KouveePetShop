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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.DAO.customerDAO;
import com.kel1.kouveepetshop.R;

import java.util.List;

public class RecycleAdapterLog extends RecyclerView.Adapter<RecycleAdapterLog.MyViewHolder>{
    private Context context;
    private List<customerDAO> result;
    public CardView cardView;

    public RecycleAdapterLog(Context context, List<customerDAO> result){
        this.context=context;
        this.result=result;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_adapter_costumer_show_log,viewGroup,false);
        final MyViewHolder holder = new MyViewHolder(v);
        return  holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mNama, mAlamat, mTgllahir, mTelp, mDeletedAt, mCreatedAt, mEditedAt, mCreatedBy, mEditedBy,mDeletedBy;
        private LinearLayout mParent;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mNama=itemView.findViewById(R.id.RC_Costumer_Name);
            mAlamat=itemView.findViewById(R.id.RC_Alamat);
            mTgllahir=itemView.findViewById(R.id.RC_TanggalLahir);
            mTelp=itemView.findViewById(R.id.RC_Telp);
            mCreatedAt=itemView.findViewById(R.id.custcreatedat);
            mCreatedBy=itemView.findViewById(R.id.custcreatedby);
            mEditedAt=itemView.findViewById(R.id.custeditedat);
            mEditedBy=itemView.findViewById(R.id.custeditedby);
            mDeletedAt=itemView.findViewById(R.id.custdeletedat);
            mDeletedBy=itemView.findViewById(R.id.custdeletedby);
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
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        final customerDAO customerDAO=result.get(i);
        myViewHolder.mNama.setText("Nama\t: "+customerDAO.getNama_customer());
        myViewHolder.mAlamat.setText("Alamat\t: "+customerDAO.getAlamat_customer());
        myViewHolder.mTgllahir.setText("Tgl Lahir\t: "+customerDAO.getTgllahir_customer());
        myViewHolder.mTelp.setText("Telp\t: "+customerDAO.getTelp_customer());
        myViewHolder.mCreatedAt.setText("Dibuat pada\t: "+customerDAO.getCust_created_at());
        myViewHolder.mCreatedBy.setText("Dibuat oleh\t: "+customerDAO.getCust_created_by());
        myViewHolder.mEditedAt.setText("Diedit pada\t: "+customerDAO.getCust_edited_at());
        myViewHolder.mEditedBy.setText("Diedit oleh\t: "+customerDAO.getCust_edited_by());
        myViewHolder.mDeletedAt.setText("Dihapus pada\t: "+customerDAO.getCust_deleted_at());
        myViewHolder.mDeletedBy.setText("Dihapus oleh\t: "+customerDAO.getCust_deleted_by());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public void filterList(List<customerDAO> filteredList) {
        result = filteredList;
        notifyDataSetChanged();
    }
}
