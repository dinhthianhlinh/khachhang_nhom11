package com.example.khachhang.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.khachhang.Adapter.SanPhamAdapter;
import com.example.khachhang.DTO.SanPham;
import com.example.khachhang.R;
import com.example.khachhang.Utility;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class thongbao extends Fragment {

    private Spinner brandSpinner;
    SanPhamAdapter adapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_thongbao, container, false);

        brandSpinner = root.findViewById(R.id.brand_spinner);
        recyclerView = root.findViewById(R.id.recyclerView);

        List<String> brands = new ArrayList<>();
        brands.add("iPhone");
        brands.add("Oppo");
        brands.add("Xiaomi");
        brands.add("SamSung");
        brands.add("RealMe");
        brands.add("Asus");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, brands);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brandSpinner.setAdapter(spinnerAdapter);

        brandSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedBrand = brands.get(position);
                Query query = Utility.getCollectionReference().whereEqualTo("hangSP", selectedBrand);
                FirestoreRecyclerOptions<SanPham> options = new FirestoreRecyclerOptions.Builder<SanPham>()
                        .setQuery(query, SanPham.class).build();
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new SanPhamAdapter(options, getContext());
                recyclerView.setAdapter(adapter);
                adapter.startListening();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        return root;
    }
}
