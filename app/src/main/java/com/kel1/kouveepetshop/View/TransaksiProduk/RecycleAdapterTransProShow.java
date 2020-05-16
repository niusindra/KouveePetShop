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
            mTotal=itemView.findViewById(R.id.RC_totalPengadaan);
            mStatus=itemView.findViewById(R.id.RC_statusPengadaan);
            mParent=itemView.findViewById(R.id.RC_Pengadaan_Event_Parent);
            context = itemView.getContext();
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        final transaksiProdukDAO transaksiProdukDAO=result.get(i);
        myViewHolder.mNama.setText("ID Transaksi\t: "+transaksiProdukDAO.getId_trans_produk());

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

    private void getDetailPengadaan(final String idpengadaan){
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<readDetailPengadaan> produkCall = apiService.getDetailPengadaan(idpengadaan);
        produkCall.enqueue(new Callback<readDetailPengadaan>(){

            @Override
            public void onResponse(Call<readDetailPengadaan> call, Response<readDetailPengadaan> response) {
                if(response.body()!=null) {
                    detailPengadaanList = response.body().getMessage();
                    ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
                    for (int i = 0; i < detailPengadaanList.size(); i++) {
                        Call<cudDataMaster> pengadaanProdukCall = apiService.pengadaanProduk(detailPengadaanList.get(i).getId_produk(),
                                detailPengadaanList.get(i).getJml_pengadaan_produk());
                        pengadaanProdukCall.enqueue(new Callback<cudDataMaster>(){
                            @Override
                            public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response) {
                                if(response.body()!=null) {
                                    response.body().getMessage();
                                }
                            }

                            @Override
                            public void onFailure(Call<cudDataMaster> call, Throwable t) {
                                Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
            @Override
            public void onFailure(Call<readDetailPengadaan> call, Throwable t) {
                Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
