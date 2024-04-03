package com.example.khachhang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khachhang.ChiTietSanPhamActivity;
import com.example.khachhang.DTO.GioHang;
import com.example.khachhang.DTO.SanPham;
import com.example.khachhang.GioHangActivity;
import com.example.khachhang.R;
import com.example.khachhang.Thanhtaon;
import com.example.khachhang.Utility;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class GioHangAdapter extends FirestoreRecyclerAdapter<GioHang, GioHangAdapter.GioHangViewHolder> {
    private final Context context;
    private TextView tv_tongtien;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    int totalCost = 0;
    private OnItemClickListener listener;

    public GioHangAdapter(@NonNull FirestoreRecyclerOptions<GioHang> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull GioHangViewHolder holder, int position, @NonNull GioHang model) {
        int tongTien = model.getGiaSP() * model.getSoLuongSP();
        holder.tongTienSP.setText(String.valueOf(tongTien));
        totalCost += tongTien;

        holder.tenSP.setText(model.getTenSP());
        holder.soluongSP.setText(String.valueOf(model.getSoLuongSP()));
        holder.giaSP.setText(String.valueOf(model.getGiaSP()));
        holder.tongTienSP.setText(String.valueOf(model.tinhTongTien()));
        holder.btnCongSoLuong_cart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int currentQuantity = Integer.parseInt(holder.soluongSP.getText().toString());
                currentQuantity++;
                holder.soluongSP.setText(String.valueOf(currentQuantity));
                // Cập nhật tổng tiền khi số lượng thay đổi
                int updatedTongTien = model.getGiaSP() * currentQuantity;
                holder.tongTienSP.setText(String.valueOf(updatedTongTien));
                // Cập nhật tổng tiền
                totalCost -= tongTien;
                totalCost += updatedTongTien;
//                Intent intent = new Intent(context, GioHangActivity.class);
//                // Đặt dữ liệu cần truyền vào Intent
//                intent.putExtra("totalCost", totalCost);
//                // Gửi Intent từ Adapter sang Activity
//                context.startActivity(intent);
                String docID = getSnapshots().getSnapshot(position).getId();
                GioHang model1 = new GioHang(model.getTenSP(),model.getGiaSP(),currentQuantity );
                DocumentReference documentReference;
                documentReference = Utility.ThemSanPhamVaoGiohHang().document(docID);
                documentReference.set(model1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Thay newData bằng dữ liệu mới bạn muốn cập nhật
                            // Hoặc nếu dữ liệu là biến public, bạn có thể cập nhật trực tiếp:
                            // adapter.data = newData;
                            // Sau đó, thông báo cho Adapter cập nhật giao diện
                            notifyDataSetChanged();
                        }
                    }
                });



                if (listener != null) {
                    listener.onItemClick(model);
                }
            }
        });

        holder.btnTruSoLuong_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = Integer.parseInt(holder.soluongSP.getText().toString());
                currentQuantity = Math.max(0, currentQuantity - 1);
                holder.soluongSP.setText(String.valueOf(currentQuantity));
                // Cập nhật tổng tiền khi số lượng thay đổi
                int updatedTongTien = model.getGiaSP() * currentQuantity;
                holder.tongTienSP.setText(String.valueOf(updatedTongTien));
                // Cập nhật tổng tiền
                totalCost -= tongTien;
                totalCost += updatedTongTien;
                String docID = getSnapshots().getSnapshot(position).getId();
//                Intent intent = new Intent(context, GioHangActivity.class);
//                // Đặt dữ liệu cần truyền vào Intent
//                intent.putExtra("totalCost", totalCost);
//                // Gửi Intent từ Adapter sang Activity
//                context.startActivity(intent);
                GioHang model1 = new GioHang(model.getTenSP(),model.getGiaSP(),currentQuantity );
                DocumentReference documentReference;
                documentReference = Utility.ThemSanPhamVaoGiohHang().document(docID);
                documentReference.set(model1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Thay newData bằng dữ liệu mới bạn muốn cập nhật
                            // Hoặc nếu dữ liệu là biến public, bạn có thể cập nhật trực tiếp:
                            // adapter.data = newData;
                            // Sau đó, thông báo cho Adapter cập nhật giao diện
                            notifyDataSetChanged();
                        }
                    }
                });
                if (listener != null) {
                    listener.onItemClick(model);
                }
            }
        });
        Intent intent = null;

        // Chuyển dữ liệu của sản phẩm được chọn qua Intent
//        intent.putExtra("Gio Hang", model);
//
//        // Mở Activity chi tiết sản phẩm
//        context.startActivity(intent);


//        holder.imgDel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Thêm logic xóa sản phẩm khỏi giỏ hàng ở đây
//            }
//        });
    }

    @NonNull
    @Override
    public GioHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gio_hang_item, parent, false);
        return new GioHangViewHolder(view);
    }

    public static class GioHangViewHolder extends RecyclerView.ViewHolder {
        TextView tenSP, giaSP, soluongSP, sizeSP, btnTruSoLuong_cart, btnCongSoLuong_cart,tongTienSP;
        // Khai báo các thành phần giao diện còn lại ở đây

        public GioHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tenSP = itemView.findViewById(R.id.tv_tensp);
            giaSP = itemView.findViewById(R.id.tv_giatien);
            soluongSP = itemView.findViewById(R.id.tv_sl);
            tongTienSP = itemView.findViewById(R.id.tv_tongtien2);
            btnTruSoLuong_cart = itemView.findViewById(R.id.btnTruSoLuong_cart);
            btnCongSoLuong_cart = itemView.findViewById(R.id.btnCongSoLuong_cart);

            // Khởi tạo các thành phần giao diện còn lại ở đây
        }
    }
    public interface OnItemClickListener {
        void onItemClick(GioHang gioHang);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
