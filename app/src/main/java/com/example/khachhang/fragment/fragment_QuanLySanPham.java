package com.example.khachhang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.khachhang.Adapter.BannerAdapter;
import com.example.khachhang.Adapter.SanPhamAdapter;
import com.example.khachhang.DTO.SanPham;
import com.example.khachhang.DanhMucActivity;
import com.example.khachhang.R;
import com.example.khachhang.Utility;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.Query;

public class fragment_QuanLySanPham extends Fragment {
    FloatingActionButton btnAdd;
    RecyclerView recyclerView;
    SanPhamAdapter sanPhamAdapter;
    private TextView cartIcon;
    private int soLuongSanPhamTrongGioHang = 0;
    ImageView iPhone,Oppo,SamSung,Xiaomi,Asus,RealMe;
    private ViewPager2 mViewPager;
    private ImageButton btnPrevious;
    private ImageButton btnNext;
    private int[] images = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4};
    private int currentPosition = 0;
    private Handler autoScrollHandler;
    private final long AUTO_SCROLL_DELAY = 3000;
    private BannerAdapter adapter1;

    //123
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__quan_ly_san_pham, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        cartIcon = view.findViewById(R.id.cartIcon); // Initialize cartIcon here
        int[] images = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4}; // Thay đổi thành các ảnh của bạn

        mViewPager = view.findViewById(R.id.viewPager);
        btnPrevious = view.findViewById(R.id.btnPrevious);
        btnNext = view.findViewById(R.id.btnNext);

        adapter1 = new BannerAdapter(getContext(), images);
        mViewPager.setAdapter(adapter1);

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition > 0) {
                    currentPosition--;
                    mViewPager.setCurrentItem(currentPosition);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition < images.length - 1) {
                    currentPosition++;
                    mViewPager.setCurrentItem(currentPosition);
                }
            }
        });

        autoScrollHandler = new Handler(Looper.getMainLooper());
        startAutoScroll();

        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPosition = position;
            }
        });

        // Initialize and update the shopping cart icon
        updateCartIcon();
//        iPhone = (ImageView) view.findViewById(R.id.imgApple);
//        Oppo = (ImageView) view.findViewById(R.id.imgOppo);
//        SamSung = (ImageView) view.findViewById(R.id.imgSamSung);
//        Xiaomi = (ImageView) view.findViewById(R.id.imgXiaoMi);
//        Asus = (ImageView) view.findViewById(R.id.imgAsus);
//        RealMe = (ImageView) view.findViewById(R.id.imgRealMe);
//        iPhone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), DanhMucActivity.class);
//                intent.putExtra("text", "Điện Thoại iPhone");
//                getActivity().startActivity(intent);
//            }
//        });
//        Asus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), DanhMucActivity.class);
//                intent.putExtra("text", "Điện Thoại Asus");
//                getActivity().startActivity(intent);
//            }
//        });
//        Oppo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), DanhMucActivity.class);
//                intent.putExtra("text", "Điện Thoại Oppo");
//                getActivity().startActivity(intent);
//            }
//        });
//        Xiaomi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), DanhMucActivity.class);
//                intent.putExtra("text", "Điện Thoại Xiaomi");
//                getActivity().startActivity(intent);
//            }
//        });
//        SamSung.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), DanhMucActivity.class);
//                intent.putExtra("text", "Điện Thoại SamSung");
//                getActivity().startActivity(intent);
//            }
//        });
//        RealMe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), DanhMucActivity.class);
//                intent.putExtra("text", "Điện Thoại RealMe");
//                getActivity().startActivity(intent);
//            }
//        });

        Query query = Utility.getCollectionReference().orderBy("timestamp", Query.Direction.ASCENDING).whereEqualTo("banchay","co");
        FirestoreRecyclerOptions<SanPham> options = new FirestoreRecyclerOptions.Builder<SanPham>()
                .setQuery(query, SanPham.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sanPhamAdapter = new SanPhamAdapter(options, getContext());
        recyclerView.setAdapter(sanPhamAdapter);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        sanPhamAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        sanPhamAdapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        sanPhamAdapter.notifyDataSetChanged();
    }

    private void themSanPhamVaoGioHang() {
        soLuongSanPhamTrongGioHang++;
        updateCartIcon();
    }

    private void xoaSanPhamKhoiGioHang() {
        if (soLuongSanPhamTrongGioHang > 0) {
            soLuongSanPhamTrongGioHang--;
        }
        updateCartIcon();
    }

    private void updateCartIcon() {
        if (cartIcon != null) {
            if (soLuongSanPhamTrongGioHang > 0) {
                cartIcon.setText(String.valueOf(soLuongSanPhamTrongGioHang));
                cartIcon.setVisibility(View.VISIBLE);
            } else {
                cartIcon.setVisibility(View.GONE);
            }
        }
    }
    private void startAutoScroll() {
        autoScrollHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentPosition < images.length - 1) {
                    currentPosition++;
                } else {
                    currentPosition = 0;
                }
                mViewPager.setCurrentItem(currentPosition);
                autoScrollHandler.postDelayed(this, AUTO_SCROLL_DELAY);
            }
        }, AUTO_SCROLL_DELAY);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        autoScrollHandler.removeCallbacksAndMessages(null);
    }
}
