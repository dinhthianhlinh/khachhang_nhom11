package com.example.khachhang;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khachhang.Adapter.SanPhamAdapter;
import com.example.khachhang.DTO.SanPham;
import com.example.khachhang.fragment.Profile;
import com.example.khachhang.fragment.fragment_QuanLySanPham;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.Query;

public class trangchumenu extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
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
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationview);
        BottomNavigationView bottomNavigationView = findViewById(R.id.botom);
//        recyclerView = findViewById(R.id.recycler_view);
//        cartIcon = findViewById(R.id.cartIcon);

//        SearchView searchView = findViewById(R.id.searchView);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // Tạo truy vấn để tìm kiếm các sản phẩm có tên chứa từ khóa tìm kiếm
//                Query searchQuery = Utility.getCollectionReference().orderBy("timestamp", Query.Direction.ASCENDING)
//                        .whereEqualTo("tenSanPham", query); // Tìm kiếm sản phẩm có tên chứa từ khóa tìm kiếm
//                updateAdapterWithQuery(searchQuery);
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                // Lọc dữ liệu sản phẩm theo tên khi người dùng thay đổi văn bản trong trường tìm kiếm
//                // newText là văn bản mới được nhập vào trường tìm kiếm
//                // Thực hiện truy vấn cơ sở dữ liệu hoặc làm mới danh sách hiển thị sản phẩm dựa trên newText
//                if (newText.isEmpty()) {
//                    // Nếu trường tìm kiếm rỗng, hiển thị toàn bộ danh sách sản phẩm
//                    updateAdapterWithQuery(Utility.getCollectionReference().orderBy("timestamp", Query.Direction.ASCENDING));
//                } else {
//                    // Nếu có văn bản trong trường tìm kiếm, tìm kiếm sản phẩm có tên chứa văn bản đó
//                    Query searchQuery = Utility.getCollectionReference().orderBy("timestamp", Query.Direction.ASCENDING)
//                            .whereEqualTo("tenSanPham", newText); // Tìm kiếm sản phẩm có tên chứa newText
//                    updateAdapterWithQuery(searchQuery);
//                }
//                return true;
//            }
//
//            private void updateAdapterWithQuery(Query query) {
//                if (sanPhamAdapter != null) {
//                    FirestoreRecyclerOptions<SanPham> options = new FirestoreRecyclerOptions.Builder<SanPham>()
//                            .setQuery(query, SanPham.class).build();
//                    sanPhamAdapter.updateOptions(options); // Cập nhật danh sách sản phẩm với kết quả tìm kiếm mới
//                }
//            }
//        });

        // Thiết lập toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        // Cập nhật giỏ hàng
//        updateCartIcon();

        // Thiết lập RecyclerView
//        Query query = Utility.getCollectionReference().orderBy("timestamp", Query.Direction.ASCENDING);
//        FirestoreRecyclerOptions<SanPham> options = new FirestoreRecyclerOptions.Builder<SanPham>()
//                .setQuery(query, SanPham.class).build();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        sanPhamAdapter = new SanPhamAdapter(options, this);
//        recyclerView.setAdapter(sanPhamAdapter);

        // Thiết lập sự kiện khi chọn mục trong BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                if (item.getItemId() == R.id.quanLySanPham) {
                    fragment = new fragment_QuanLySanPham();
                }else if (item.getItemId() == R.id.Profile) {
                    fragment = new Profile();
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

//    @Override
//    public void onStart() {
//        super.onStart();
//        sanPhamAdapter.startListening();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        sanPhamAdapter.stopListening();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        sanPhamAdapter.notifyDataSetChanged();
//    }

//    private void updateCartIcon() {
//        if (cartIcon != null) {
//            if (soLuongSanPhamTrongGioHang > 0) {
//                cartIcon.setText(String.valueOf(soLuongSanPhamTrongGioHang));
//                cartIcon.setVisibility(View.VISIBLE);
//            } else {
//                cartIcon.setVisibility(View.GONE);
//            }
//        }
//    }
}
