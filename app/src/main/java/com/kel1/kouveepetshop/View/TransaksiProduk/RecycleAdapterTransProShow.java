package com.kel1.kouveepetshop.View.TransaksiProduk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.DAO.detailPengadaanDAO;
import com.kel1.kouveepetshop.DAO.pengadaanDAO;
import com.kel1.kouveepetshop.DAO.transaksiProdukDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.cudDataMaster;
import com.kel1.kouveepetshop.Respon.readDetailPengadaan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecycleAdapterTransProShow extends RecyclerView.Adapter<RecycleAdapterTransProShow.MyViewHolder>{
    private Context context;
    private List<transaksiProdukDAO> result;
    private List<detailPengadaanDAO> detailPengadaanList;

    public static final String EXTRA_TEXT = "com.kel1.kouveepetshop.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.kel1.kouveepetshop.EXTRA_NUMBER";

    public RecycleAdapterTransProShow(Context context, List<transaksiProdukDAO> result){
        this.context=context;
        this.result=result;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_adapter_transpro_show,viewGroup,false);
        final MyViewHolder holder = new MyViewHolder(v);
        return  holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView mNama, mTanggal, mTotal, mStatus;
        private Button hapie;
        private LinearLayout mParent;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mNama=itemView.findViewById(R.id.RC_Pengadaan_Name);
            mTanggal=itemView.findViewById(R.id.RC_tglPengadaan);
            mTotal=itemView.findViewById(R.id.RC_hewan1);
            mParent=itemView.findViewById(R.id.RC_Pengadaan_Event_Parent);
            context = itemView.getContext();
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        final transaksiProdukDAO transaksiProdukDAO=result.get(i);
        myViewHolder.mNama.setText("ID Transaksi\t: "+transaksiProdukDAO.getId_trans_produk());
        myViewHolder.mTanggal.setText("Tanggal Transaksi\t: "+transaksiProdukDAO.getTanggal_trans_produk());
        myViewHolder.mTotal.setText("Hewan\t: "+transaksiProdukDAO.getNama_hewan()+"-"+transaksiProdukDAO.getJenis());
        myViewHolder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,transProEdit.class);
                intent.putExtra(EXTRA_TEXT, new String[] {transaksiProdukDAO.getId_trans_produk(), transaksiProdukDAO.getNama_customer(),
                                transaksiProdukDAO.getAlamat_customer(), transaksiProdukDAO.getTelp_customer(), transaksiProdukDAO.getNama_hewan(),
                                transaksiProdukDAO.getJenis()});
                intent.putExtra(EXTRA_NUMBER, new int[] {transaksiProdukDAO.getId_cs(), transaksiProdukDAO.getId_hewan()});
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public void filterList(List<transaksiProdukDAO> filteredList) {
        result = filteredList;
        notifyDataSetChanged();
    }

    public void setResult(List<transaksiProdukDAO> result) {
        this.result = result;
        notifyDataSetChanged();
    }

    public List<transaksiProdukDAO> getResult() {
        return result;
    }


}
