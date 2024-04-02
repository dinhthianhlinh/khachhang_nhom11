package com.example.khachhang;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.khachhang.DTO.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;

public class DangKyActivity extends AppCompatActivity {
    Button btnDangKy;
    TextInputEditText edtPass,edtRePass,edtEmail,edtTen;
    TextInputLayout txtPass,txtRePass,txtEmail,txtTen;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        edtPass = findViewById(R.id.edtPass);
        txtPass = findViewById(R.id.txtPass);
        edtRePass = findViewById(R.id.edtRePass);
        txtRePass = findViewById(R.id.txtRePass);
        edtEmail = findViewById(R.id.edtEmail);
        txtEmail = findViewById(R.id.txtEmail);
        edtTen = findViewById(R.id.edtTen);
        txtTen = findViewById(R.id.txtTen);
        btnDangKy = findViewById(R.id.btnDangKy);
        TextView tvDangNhap = findViewById(R.id.tvDangNhap);
        getUserName();

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String pass = edtPass.getText().toString();
                String repass = edtRePass.getText().toString();
                String ten = edtTen.getText().toString();
                if(pass.equals("") || repass.equals("") || email.equals("") || ten.equals("")){

                    if(pass.equals("")){
                        txtPass.setError("Vui Lòng Nhập Mật Khẩu");
                    }else {
                        txtPass.setError(null);
                    }if(repass.equals("")){
                        txtRePass.setError("Vui Lòng Nhập Lại Mật Khẩu");
                    }else {
                        txtRePass.setError(null);
                    }if(email.equals("")){
                        txtEmail.setError("Vui Lòng Nhập Email");
                    }else if(!isValidEmail(email)){
                        txtEmail.setError("Email không đúng định dạng");
                    }else {
                        txtEmail.setError(null);
                    }if(ten.equals("")){
                        txtTen.setError("Vui Lòng Nhập Mật Khẩu");
                    }else {
                        txtTen.setError(null);
                    }
                }else{
//                    Intent intent = new Intent(DangKyActivity.this,LoginActivity.class);
//                    startActivity(intent);
                }
                createAccountInFireBase(email,pass);
                changeInprogress(true);
                if(user!= null){
                    user.setTen(ten);
                }else{
                    user = new User(email,pass,ten);
                }
                Utility.CurrentUserDetail().set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        changeInprogress(false);
                        if(task.isSuccessful()){

                        }
                    }
                });

            }
        });
        tvDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKyActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    txtPass.setError("Vui Lòng Nhập PassWord");
                }else if(s.length() <=6){
                    txtPass.setError("Vui lòng nhập lớn hơn 6 ký tự");
                }
                else {
                    txtPass.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtRePass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    txtRePass.setError("Vui Lòng Nhập Lại Mật Khẩu");
                }else {
                    txtRePass.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = edtEmail.getText().toString().trim();
                if (s.length() == 0)
                {
                    txtEmail.setError("Vui Lòng Nhập Email");
                }
                else if(!isValidEmail(email)){
                    txtEmail.setError("Email không đúng định dạng");
                }else{
                    txtEmail.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }
    public boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    void createAccountInFireBase(String email,String pass){
        changeInprogress(true);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(DangKyActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //tạo tài khoản thành công
                    Utility.showToast(DangKyActivity.this, "Đăng Ký Thành Công,Hãy Check Email Của Bạn");;
                    firebaseAuth.getCurrentUser().sendEmailVerification();
                    firebaseAuth.signOut();
                    finish();
                }else {
                    Utility.showToast(DangKyActivity.this, task.getException().getLocalizedMessage());
                }
            }
        });
    }
    void changeInprogress(boolean inProgress){
        if(inProgress){
            btnDangKy.setVisibility(View.GONE);
        }else{
            btnDangKy.setVisibility(View.VISIBLE);
        }
    }
    void getUserName(){
        changeInprogress(true);
        Utility.CurrentUserDetail().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                changeInprogress(false);
                if(task.isSuccessful()){
                    user = task.getResult().toObject(User.class);
                    if(user != null){
                        edtTen.setText(user.getTen());
                    }
                }
            }
        });
    }
}
