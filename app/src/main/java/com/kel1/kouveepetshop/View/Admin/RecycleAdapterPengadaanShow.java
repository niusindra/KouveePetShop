package com.kel1.kouveepetshop.View.Admin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.DAO.detailPengadaanDAO;
import com.kel1.kouveepetshop.DAO.pengadaanDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.cudDataMaster;
import com.kel1.kouveepetshop.Respon.readDetailPengadaan;
import com.kel1.kouveepetshop.View.Customer.customerEdit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecycleAdapterPengadaanShow extends RecyclerView.Adapter<RecycleAdapterPengadaanShow.MyViewHolder>{
    private Context context;
    private List<pengadaanDAO> result;
    private List<detailPengadaanDAO> detailPengadaanList;

    public static final String EXTRA_TEXT = "com.kel1.kouveepetshop.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.kel1.kouveepetshop.EXTRA_NUMBER";

    public RecycleAdapterPengadaanShow(Context context, List<pengadaanDAO> result){
        this.context=context;
        this.result=result;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_adapter_pengadaan_show,viewGroup,false);
        final MyViewHolder holder = new MyViewHolder(v);
        return  holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mNama, mTanggal, mTotal, mStatus;
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

        @Override
        public void onClick(View view) {
            Toast.makeText(context,"Oh you touch me?",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        final pengadaanDAO pengadaanDAO=result.get(i);
        myViewHolder.mNama.setText("Nama Supplier\t: "+pengadaanDAO.getNama_supplier());
        myViewHolder.mTanggal.setText("Tanggal\t: "+pengadaanDAO.getTgl_pengadaan());
        myViewHolder.mTotal.setText("Total\t: Rp "+pengadaanDAO.getTotal_pengadaan()+", \n\tdengan "+pengadaanDAO.getJumlah_jenis()+" jenis produk");
        myViewHolder.mStatus.setText("Status\t: "+pengadaanDAO.getStatus_pengadaan()+"\n");
        myViewHolder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pengadaanDAO.getStatus_pengadaan().equalsIgnoreCase("Pending")){
                    Intent intent = new Intent(context,pengadaanEdit.class);
                    intent.putExtra(EXTRA_TEXT, new String[] {pengadaanDAO.getNama_supplier(),pengadaanDAO.getTgl_pengadaan(),
                            pengadaanDAO.getStatus_pengadaan()});
                    intent.putExtra(EXTRA_NUMBER, new int[] {pengadaanDAO.getId_pengadaan(), pengadaanDAO.getId_supplier(), pengadaanDAO.getTotal_pengadaan()});
                    context.startActivity(intent);
                }else if(pengadaanDAO.getStatus_pengadaan().equalsIgnoreCase("belum sampai")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Anda yakin ingin konfirmasi kedatangan?")
                            .setCancelable(false)
                            .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                getDetailPengadaan(pengadaanDAO.getId_pengadaan());
                                }
                            })
                            .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }else{
                    Toast.makeText(context,"Sudah sampai bos",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public void filterList(List<pengadaanDAO> filteredList) {
        result = filteredList;
        notifyDataSetChanged();
    }

    public void setResult(List<pengadaanDAO> result) {
        this.result = result;
        notifyDataSetChanged();
    }

    public List<pengadaanDAO> getResult() {
        return result;
    }

    private void getDetailPengadaan(final int idpengadaan){
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
                    konfirmasiSampai(idpengadaan);
                }
            }
            @Override
            public void onFailure(Call<readDetailPengadaan> call, Throwable t) {
                Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void konfirmasiSampai(int idpengadaan){
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<cudDataMaster> pengadaanCall = apiService.editPengadaan(idpengadaan,"Sampai");
        pengadaanCall.enqueue(new Callback<cudDataMaster>(){
            @Override
            public void onResponse(Call<cudDataMaster> call, Response<cudDataMaster> response) {
                if(response.body()!=null) {
                    Toast.makeText(context,"Konfirmasi Berhasil",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<cudDataMaster> call, Throwable t) {
                Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
