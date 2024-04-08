package com.example.khachhang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.khachhang.DTO.User;
import com.example.khachhang.LoginActivity;
import com.example.khachhang.R;
import com.example.khachhang.Utility;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentSnapshot;
public class Profile extends Fragment {
    //123
    ImageView profilePic;
    TextInputEditText usernameInput;
    TextInputEditText edtPhone,edtAdress;
    Button updateProfileBtn;
    TextView logoutBtn;
    User user;
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        profilePic = view.findViewById(R.id.profile_image_view);
        usernameInput = view.findViewById(R.id.profile_username);
        edtPhone = view.findViewById(R.id.edtPhone);
        edtAdress = view.findViewById(R.id.edtAdress);
        updateProfileBtn = view.findViewById(R.id.profle_update_btn);
        logoutBtn = view.findViewById(R.id.logout_btn);
        getUserName();
        logoutBtn.setOnClickListener((v)-> {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                });
        updateProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenUser = usernameInput.getText().toString();
                String phoneUser = edtPhone.getText().toString();
                String Adress= edtAdress.getText().toString();
                changeInprogress(true);
                if(user!= null){
                    user.setTen(tenUser);
                    user.setPhone(phoneUser);
                    user.setAdress(Adress);
                }else{
                    user = new User(tenUser, Utility.CurrentUserID(),phoneUser,Adress,user.getEmail());
                }
                Utility.currentUserDetails().set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        changeInprogress(false);
                        if(task.isSuccessful()){

                        }
                    }
                });
            }
        });

        return view;
    }
    void getUserName(){
        changeInprogress(true);
        Utility.currentUserDetails().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                changeInprogress(false);
                if(task.isSuccessful()){
                    user = task.getResult().toObject(User.class);
                    if(user != null){
                        usernameInput.setText(user.getTen());
                        edtPhone.setText(user.getPhone());
                        edtAdress.setText(user.getAdress());
                    }
                }
            }
        });
    }
    void changeInprogress(boolean inProgress){
        if(inProgress){
            updateProfileBtn.setVisibility(View.GONE);
        }else{
            updateProfileBtn.setVisibility(View.VISIBLE);
        }
    }
}