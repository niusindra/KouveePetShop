package com.kel1.kouveepetshop.View.Produk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.DAO.produkDAO;
import com.kel1.kouveepetshop.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecycleAdapterProdukLog extends RecyclerView.Adapter<RecycleAdapterProdukLog.MyViewHolder>{
    private Context context;
    private List<produkDAO> result;

    public static final String EXTRA_TEXT = "com.kel1.kouveepetshop.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.kel1.kouveepetshop.EXTRA_NUMBER";

    public RecycleAdapterProdukLog(Context context, List<produkDAO> result){
        this.context=context;
        this.result=result;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_adapter_produk_show_log,viewGroup,false);
        final MyViewHolder holder = new MyViewHolder(v);
        return  holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mNama, mBeli, mJual, mStok, mMinStok,msuppPro, mDeletedAt, mCreatedAt, mEditedAt;
        public ImageView mfoto;
        private LinearLayout mParent;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            msuppPro=itemView.findViewById(R.id.adaptersuppProLog);
            mfoto=itemView.findViewById(R.id.fotoprodukLog);
            mNama=itemView.findViewById(R.id.adapterNamaProdukLog);
            mBeli=itemView.findViewById(R.id.adapterHargaBeliLog);
            mJual=itemView.findViewById(R.id.adapterHargaJualLog);
            mStok=itemView.findViewById(R.id.adapterStokHargaLog);
            mMinStok=itemView.findViewById(R.id.adapterMinStokHargaLog);
            mParent=itemView.findViewById(R.id.RC_Produk_Event_Parent);
            mCreatedAt=itemView.findViewById(R.id.createdatpro);
            mEditedAt=itemView.findViewById(R.id.editedatpro);
            mDeletedAt=itemView.findViewById(R.id.deletedatpro);
            context = itemView.getContext();
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,"Oh you touch me?",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        final produkDAO produkDAO=result.get(i);
        Picasso.get().load("http:/api.kouvee.site/upload/foto_produk/"+produkDAO.getFoto_produk()).into(myViewHolder.mfoto);
        myViewHolder.mNama.setText("Nama\t: "+produkDAO.getNama_produk());
        myViewHolder.msuppPro.setText("Supplier\t: "+produkDAO.getNama_supplier());
        myViewHolder.mBeli.setText("Harga Beli\t: "+produkDAO.getHarga_beli_produk());
        myViewHolder.mJual.setText("Harga Jual\t: "+produkDAO.getHarga_jual_produk());
        myViewHolder.mStok.setText("Stok\t: "+produkDAO.getStok());
        myViewHolder.mMinStok.setText("Minimal Stok\t: "+produkDAO.getMin_stok());
        myViewHolder.mCreatedAt.setText("Dibuat pada\t: "+produkDAO.getProduk_created_at());
        myViewHolder.mEditedAt.setText("Diedit pada\t: "+produkDAO.getProduk_edited_at());
        myViewHolder.mDeletedAt.setText("Dihapus pada\t: "+produkDAO.getProduk_deleted_at());
//        myViewHolder.mParent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, layananEdit.class);
//                intent.putExtra(EXTRA_TEXT, new String[] {produkDAO.getNama_produk()});
//                intent.putExtra(EXTRA_NUMBER, produkDAO.getId_produk());
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public void filterList(List<produkDAO> filteredList) {
        result = filteredList;
        notifyDataSetChanged();
    }
}
