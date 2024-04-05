package com.example.khachhang.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khachhang.Adapter.HoaDonAdapter;
import com.example.khachhang.DTO.HoaDon;
import com.example.khachhang.R;
import com.example.khachhang.Utility;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

public class fragment_QuanLyDonHang extends Fragment {

    RecyclerView recyclerView;
    HoaDonAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__quan_ly_don_hang,container,false);
        recyclerView = view.findViewById(R.id.rcyView);

        Query query = Utility.HoaDon();
        FirestoreRecyclerOptions<HoaDon> options = new FirestoreRecyclerOptions.Builder<HoaDon>()
                .setQuery(query, HoaDon.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HoaDonAdapter(options, getContext());
        recyclerView.setAdapter(adapter);
//123
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}