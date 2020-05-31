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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_transaksi_layanan, container, false);

        setAtribut(root);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(root.getContext(), getChildFragmentManager());
        ViewPager viewPager = root.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = root.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        fabtranspro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
                builder.setMessage("Siliahkan pilih jenis member:")
                        .setCancelable(false)
                        .setPositiveButton("MEMBER", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(root.getContext(), transLayAdd.class);
                                intent.putExtra("cekMember","member");
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("NON MEMBER", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(root.getContext(), transLayAdd.class);
                                intent.putExtra("cekMember","non member");
                                startActivity(intent);
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        return root;
    }

    public void setAtribut(View root) {
        fabtranspro = root.findViewById(R.id.CS_fab_translay);
    }
}
