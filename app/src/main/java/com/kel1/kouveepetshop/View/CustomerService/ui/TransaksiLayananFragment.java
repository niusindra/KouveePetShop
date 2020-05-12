package com.kel1.kouveepetshop.View.CustomerService.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.kel1.kouveepetshop.R;

public class TransaksiLayananFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_transaksi_layanan, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        return root;
    }
}
