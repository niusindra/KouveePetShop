package com.kel1.kouveepetshop.View.TransaksiLayanan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.DAO.detailLayananDAO;
import com.kel1.kouveepetshop.DAO.transaksiLayananDAO;
import com.kel1.kouveepetshop.R;

import java.util.List;

public class RecycleAdapterTransLayShow extends RecyclerView.Adapter<RecycleAdapterTransLayShow.MyViewHolder>{
    private Context context;
    private List<transaksiLayananDAO> result;
    private List<detailLayananDAO> detailLayananList;

    public static final String EXTRA_TEXT = "com.kel1.kouveepetshop.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.kel1.kouveepetshop.EXTRA_NUMBER";

    public RecycleAdapterTransLayShow(Context context, List<transaksiLayananDAO> result){
        this.context=context;
        this.result=result;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_adapter_translay_show,viewGroup,false);
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
        final transaksiLayananDAO transaksiLayananDAO=result.get(i);
        myViewHolder.mNama.setText("ID Transaksi\t: "+transaksiLayananDAO.getId_trans_layanan());

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public void filterList(List<transaksiLayananDAO> filteredList) {
        result = filteredList;
        notifyDataSetChanged();
    }

    public void setResult(List<transaksiLayananDAO> result) {
        this.result = result;
        notifyDataSetChanged();
    }

    public List<transaksiLayananDAO> getResult() {
        return result;
    }

//    private void getDetailPengadaan(final String idpengadaan){
//        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
//        Call<readDetailLayanan> layananCall = apiService.getDetailLayanan(idpengadaan);
//        layananCall.enqueue(new Callback<readDetailLayanan>(){
//
//            @Override
//            public void onResponse(Call<readDetailLayanan> call, Response<readDetailLayanan> response) {
//                if(response.body()!=null) {
//                    detailLayananList = response.body().getMessage();
//                    ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
//                    for (int i = 0; i < detailLayananList.size(); i++) {
//                        Call<cudDataMaster> detailLayananCall = apiService.pengadaanProduk(detailLayananList.get(i).getId_produk(),
//                                detailLayananList.get(i).getJml_pengadaan_produk());
//                        detailLayananCall.enqueue(new Callback<cudDataMaster>(){
//                            @Override
//                            public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response) {
//                                if(response.body()!=null) {
//                                    response.body().getMessage();
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<cudDataMaster> call, Throwable t) {
//                                Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                }
//            }
//            @Override
//            public void onFailure(Call<readDetailLayanan> call, Throwable t) {
//                Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


}
