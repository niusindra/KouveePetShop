package com.kel1.kouveepetshop.View.Admin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.DAO.detailPengadaanDAO;
import com.kel1.kouveepetshop.DAO.produkDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.cudDataMaster;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class detailShowAdapter extends RecyclerView.Adapter<detailShowAdapter.ViewHolder>{
    private Context context;
    private  List<detailPengadaanDAO> arrayList;
    private List<produkDAO> mListProduk;

    public detailShowAdapter(Context context, List<detailPengadaanDAO> arrayList, List<produkDAO> mListProduk) {
        this.context = context;
        this.arrayList = arrayList;
        this.mListProduk = mListProduk;
    }

    @Override
    public detailShowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.detail_show_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(detailShowAdapter.ViewHolder holder, int position) {
        detailPengadaanDAO detailPengadaanDAO=arrayList.get(position);
        holder.nama.setText("Nama Produk\t: "+detailPengadaanDAO.getNama_produk());
        holder.jumlah.setText("Jumlah Pesan\t: "+detailPengadaanDAO.getJml_pengadaan_produk()+" "+detailPengadaanDAO.getSatuan());
        holder.subtotal.setText("Subtotal\t: "+detailPengadaanDAO.getSubtotal_pengadaan());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public List<detailPengadaanDAO> getArrayList()
    {
        return arrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView subtotal;
        TextView nama;
        TextView jumlah;
        Button removeBtn;
        int hargabeli;

        public ViewHolder(View itemView) {
            super(itemView);
            subtotal = itemView.findViewById(R.id.detailshowSubtotal);
            nama = itemView.findViewById(R.id.detailshowNama);
            jumlah = itemView.findViewById(R.id.detailshowJumlah);
            removeBtn = itemView.findViewById(R.id.removeBtn1);
        }

    }

}
