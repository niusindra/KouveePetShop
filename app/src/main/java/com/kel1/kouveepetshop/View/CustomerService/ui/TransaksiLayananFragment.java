package com.kel1.kouveepetshop.View.CustomerService.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.kel1.kouveepetshop.Api.ApiClient;
import com.kel1.kouveepetshop.Api.ApiInterface;
import com.kel1.kouveepetshop.DAO.transaksiLayananDAO;
import com.kel1.kouveepetshop.DAO.transaksiProdukDAO;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.Respon.readTransLay;
import com.kel1.kouveepetshop.Respon.readTransPro;
import com.kel1.kouveepetshop.View.CustomerService.ui.main.SectionsPagerAdapter;
import com.kel1.kouveepetshop.View.ErrorCatch;
import com.kel1.kouveepetshop.View.TransaksiLayanan.RecycleAdapterTransLayShow;
import com.kel1.kouveepetshop.View.TransaksiLayanan.RecycleAdapterTransLayShowLog;
import com.kel1.kouveepetshop.View.TransaksiLayanan.transLayAdd;
import com.kel1.kouveepetshop.View.TransaksiProduk.transProAdd;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiLayananFragment extends Fragment {
    FloatingActionButton fabtranspro;
    private Switch aSwitch;
    private List<transaksiLayananDAO> mListTransLay =new ArrayList<>();
    private RecyclerView recyclerView;
    private RecycleAdapterTransLayShow recycleAdapter;
    private RecycleAdapterTransLayShowLog recycleAdapterLog;
    private EditText searchTransPro;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_transaksi_layanan, container, false);

        setAtribut(root);
//
//        mListTransLay =new ArrayList<>();
//        setRecycleAdapter(root);
//        setRecycleView(root);
//
//        fabtranspro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
//                builder.setMessage("Siliahkan pilih jenis member:")
//                        .setCancelable(false)
//                        .setPositiveButton("MEMBER", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Intent intent = new Intent(root.getContext(), transLayAdd.class);
//                                intent.putExtra("cekMember","member");
//                                startActivity(intent);
//                            }
//                        })
//                        .setNegativeButton("NON MEMBER", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Intent intent = new Intent(root.getContext(), transLayAdd.class);
//                                intent.putExtra("cekMember","non member");
//                                startActivity(intent);
//                            }
//                        });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//            }
//        });
        searchTransPro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                TabsBelumSelesai tb = new TabsBelumSelesai();
                tb.filter(editable.toString());
            }
        });
//        setRecycleView(root);
//        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b){
//                    setRecycleViewLog(root);
//                }else{
//                    setRecycleView(root);
//                }
//            }
//        });
//        if(aSwitch.isChecked()){
//            setRecycleViewLog(root);
//        }else{
//            setRecycleView(root);
//        }


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(root.getContext(), getChildFragmentManager());
        ViewPager viewPager = root.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = root.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        return root;
    }


    private void filter(String text) {

        List<transaksiLayananDAO> filteredList ,temp;
        filteredList = new ArrayList<>();
        temp = recycleAdapter.getResult();
        for (transaksiLayananDAO item : temp) {
            if (item.getId_trans_layanan().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        recycleAdapter.filterList(filteredList);
        recycleAdapterLog.filterList(filteredList);
    }
    private void setListPengadaan(String text) {
        List<transaksiLayananDAO> filteredList = new ArrayList<>();
        for (transaksiLayananDAO item : mListTransLay) {
            if (item.getId_trans_layanan().equalsIgnoreCase(text)) {
                filteredList.add(item);
            }
        }

        recycleAdapter.setResult(filteredList);
        recycleAdapterLog.setResult(filteredList);
    }

    public void setAtribut(View root) {
        fabtranspro = root.findViewById(R.id.CS_fab_translay);
        aSwitch = root.findViewById(R.id.CS_switchLog_translay);
        searchTransPro = root.findViewById(R.id.CS_search_translay);
    }
}
