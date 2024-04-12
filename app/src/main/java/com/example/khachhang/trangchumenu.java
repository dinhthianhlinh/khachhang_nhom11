package com.example.khachhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.khachhang.fragment.Profile;
import com.example.khachhang.fragment.fragment_QuanLyDonHang;
import com.example.khachhang.fragment.fragment_QuanLySanPham;
import com.example.khachhang.fragment.thongbao;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class trangchumenu extends AppCompatActivity {


    BottomNavigationView bottom;
    fragment_QuanLySanPham fragmentQuanLySanPham;
    fragment_QuanLyDonHang fragmentQuanLyDonHang;
    thongbao getThongbao;
    thongbao thongbao;
    TextView tvHl;
    Profile profile;
    EditText searchView;
    ImageView cart;
    private List<String> namesList = new ArrayList<>();


    //    private RecyclerView recyclerView;
//    private SanPhamAdapter sanPhamAdapter;
//    private TextView cartIcon;
    private int soLuongSanPhamTrongGioHang = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchumenu);
        fragmentQuanLySanPham = new fragment_QuanLySanPham();
        profile = new Profile();
        fragmentQuanLyDonHang = new fragment_QuanLyDonHang();
        getThongbao = new thongbao();
        cart = findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(trangchumenu.this,GioHangActivity.class);
                startActivity(intent);
            }
        });
        DocumentReference userDocumentRef = Utility.currentUserDetails();
        CollectionReference userDocumentRef1 = Utility.ThemSanPhamVaoGiohHang();
        userDocumentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Lấy dữ liệu từ tài liệu người dùng
                    String userData = documentSnapshot.getString("ten"); // Thay "fieldName" bằng tên trường cần lấy dữ liệu

                    // Kiểm tra nếu userData không null và không rỗng
                    if (userData != null && !userData.isEmpty()) {
                        // Hiển thị dữ liệu trong TextView
                        tvHl.setText("Xin Chào  " + userData);
                    } else {
                        // userData là null hoặc rỗng, set text là "Xin Chào"
                        tvHl.setText("Xin Chào");
                    }
                } else {
                    // Tài liệu không tồn tại
                    tvHl.setText("Xin Chào");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Xử lý lỗi khi truy vấn dữ liệu
                tvHl.setText("Xin Chào");
            }
        });
        // Initialize TextView
        tvHl = findViewById(R.id.tvHl);

        // Read data from file and populate the namesList
        readDataFromFile("your_file.txt");

        // Update TextView with the list of names
        updateTextView();

        // Ánh xạ các view

        bottom = findViewById(R.id.botom);

        BottomNavigationView bottomNavigationView = findViewById(R.id.botom);
        bottom.setSelectedItemId(R.id.quanLySanPham);
        searchView = findViewById(R.id.searchView);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(trangchumenu.this, SearchSanPhamActivity.class);
                startActivity(intent);
            }
        });


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.quanLySanPham) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentQuanLySanPham).commit();
                }
                if (item.getItemId() == R.id.Profile) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, profile).commit();
                }
                if (item.getItemId() == R.id.hoahon) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentQuanLyDonHang).commit();
                }if (item.getItemId() == R.id.Thongbao) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, getThongbao).commit();
                }

                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.quanLySanPham);
    }
    private void readDataFromFile(String fileName) {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = getAssets().open(fileName);
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                namesList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void updateTextView() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String name : namesList) {
            stringBuilder.append(name).append("\n");
        }
        tvHl.setTextSize(20); // Set text size to 20sp (adjust the value as needed)

    }

}
