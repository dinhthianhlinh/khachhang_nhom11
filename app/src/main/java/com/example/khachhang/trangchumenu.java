package com.example.khachhang;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.khachhang.fragment.Profile;
import com.example.khachhang.fragment.fragment_QuanLyDonHang;
import com.example.khachhang.fragment.fragment_QuanLySanPham;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class trangchumenu extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    BottomNavigationView bottom;
//    private RecyclerView recyclerView;
//    private SanPhamAdapter sanPhamAdapter;
//    private TextView cartIcon;
    private int soLuongSanPhamTrongGioHang = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchumenu);


        // Ánh xạ các view
        drawerLayout = findViewById(R.id.drawer_layout);
        bottom = findViewById(R.id.botom);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationview);
        BottomNavigationView bottomNavigationView = findViewById(R.id.botom);
        bottom.setSelectedItemId(R.id.quanLySanPham);

        // Thiết lập toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                if (item.getItemId() == R.id.quanLySanPham) {
                    fragment = new fragment_QuanLySanPham();
                }else if (item.getItemId() == R.id.Profile) {
                    fragment = new Profile();
                }else if(item.getItemId() == R.id.hoahon){
                    fragment = new fragment_QuanLyDonHang();
                }

                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                }

                drawerLayout.closeDrawer(GravityCompat.START); // Đóng thanh bar sau khi chọn mục menu
                return true;
            }
        });


        // Thiết lập sự kiện khi chọn mục trong NavigationView
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                if (item.getItemId() == R.id.quanLySanPham) {
                    fragment = new fragment_QuanLySanPham();
                }

                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                }

                drawerLayout.closeDrawer(GravityCompat.START); // Đóng thanh bar sau khi chọn mục menu
                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
