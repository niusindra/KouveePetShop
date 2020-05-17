package com.kel1.kouveepetshop.View.CustomerService;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kel1.kouveepetshop.R;
import com.kel1.kouveepetshop.View.CustomerService.ui.CrudsCustomerFragment;
import com.kel1.kouveepetshop.View.CustomerService.ui.CrudsHewanFragment;
import com.kel1.kouveepetshop.View.CustomerService.ui.TransaksiLayananFragment;
import com.kel1.kouveepetshop.View.CustomerService.ui.TransaksiProdukFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class CS_Dashboard extends AppCompatActivity {
    private BottomNavigationView bottomNav;
    private int loadFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cs_dashboard);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        bottomNav = findViewById(R.id.nav_view);

        if (getIntent().getExtras()!=null) {
            loadFragment = getIntent().getExtras().getInt("loadFragment");
            Fragment selectedFragment = null;
            switch (loadFragment) {
                case R.id.transaksi_produk:
                    selectedFragment = new TransaksiProdukFragment();
                    break;
                case R.id.transaksi_layanan:
                    selectedFragment = new TransaksiLayananFragment();
                    break;
                case R.id.cruds_customer:
                    selectedFragment = new CrudsCustomerFragment();
                    break;
                case R.id.cruds_hewan:
                    selectedFragment = new CrudsHewanFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                    selectedFragment).commit();

            bottomNav.setSelectedItemId(loadFragment);
        }


        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.transaksi_produk:
                        selectedFragment = new TransaksiProdukFragment();
                        break;
                    case R.id.transaksi_layanan:
                        selectedFragment = new TransaksiLayananFragment();
                        break;
                    case R.id.cruds_customer:
                        selectedFragment = new CrudsCustomerFragment();
                        break;
                    case R.id.cruds_hewan:
                        selectedFragment = new CrudsHewanFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        selectedFragment).commit();

                return true;
            }
        });

            //I added this if statement to keep the selected fragment when rotating the device
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
//                    new TransaksiProdukFragment()).commit();
//        }
    }
}
