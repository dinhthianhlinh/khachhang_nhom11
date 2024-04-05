package com.example.khachhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khachhang.Adapter.SanPhamAdapter;
import com.example.khachhang.DTO.SanPham;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

public class DanhMucActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SanPhamAdapter sanPhamAdapter;
    ImageButton btnBackSP;
    TextView tvAddSP;
    //123
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc);
        tvAddSP = findViewById(R.id.tvAddSP);
        btnBackSP = findViewById(R.id.btnBack);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhMucActivity.this, ChiTietSanPhamActivity.class);
                startActivity(intent);
            }
        });
        btnBackSP.setOnClickListener(v -> {
            onBackPressed();
        });
        String text = getIntent().getStringExtra("text");

        // Thiết lập nội dung của TextView
        tvAddSP.setText(text);
        String targetTimestamp = null;
        if (text.equals("Điện Thoại iPhone")) {
            targetTimestamp = "iPhone";
        }else if(text.equals("Điện Thoại Xiaomi")){
            targetTimestamp = "Xiaomi";
        }else if(text.equals("Điện Thoại Oppo")){
            targetTimestamp = "Oppo";
        }else if(text.equals("Điện Thoại Asus")){
            targetTimestamp = "Asus";
        }else if(text.equals("Điện Thoại SamSung")){
            targetTimestamp = "SamSung";
        }else if(text.equals("Điện Thoại RealMe")){
            targetTimestamp = "RealMe";
        }
        Query query = Utility.getCollectionReference()
                .whereEqualTo("hangSP", targetTimestamp);
        FirestoreRecyclerOptions<SanPham> options = new FirestoreRecyclerOptions.Builder<SanPham>()
                .setQuery(query, SanPham.class)
                .build();


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sanPhamAdapter = new SanPhamAdapter(options, this);
        recyclerView.setAdapter(sanPhamAdapter);

    }
    @Override
    public void onStart(){
        super.onStart();
        sanPhamAdapter.startListening();
    }
    @Override
    public void onStop(){
        super.onStop();
        sanPhamAdapter.stopListening();
    }
    @Override
    public void onResume(){
        super.onResume();
        sanPhamAdapter.notifyDataSetChanged();
    }
}