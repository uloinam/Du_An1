package com.example.ltmt_19303_group6.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ltmt_19303_group6.DAO.Profile_DAO;
import com.example.ltmt_19303_group6.DbHelper;
import com.example.ltmt_19303_group6.Model.Profile_Model;
import com.example.ltmt_19303_group6.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Fragment_Profile extends Fragment {
    ImageView ivImg;
    TextView tvEmail, tvName, tv_age, phoneNumber;
    Profile_DAO profile_dao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Ánh xạ các view
        ivImg = view.findViewById(R.id.ivImg);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvName = view.findViewById(R.id.tvName);
        tv_age = view.findViewById(R.id.tv_age);
        phoneNumber = view.findViewById(R.id.tv_phoneNumber);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("USER_LOGIN", Context.MODE_PRIVATE);
        Integer id = sharedPreferences.getInt("id_empolyee", 0);
                // Lấy dữ liệu từ DAO
        profile_dao = new Profile_DAO(getContext());



            Profile_Model profile = profile_dao.getProfile(id);

            // Set dữ liệu vào view
            if (profile != null){
                tvName.setText(profile.getName_Empolyee());
                tvEmail.setText(profile.getEmail_Empolyee());
                tv_age.setText(String.valueOf(profile.getAge()));
                phoneNumber.setText(profile.getPhone_Empolyee());
                if (profile.getAvatar() != null && profile.getAvatar().length > 0) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(profile.getAvatar(), 0, profile.getAvatar().length);
                    ivImg.setImageBitmap(bitmap);
                } else {
                    ivImg.setImageResource(R.drawable.ic_user);
                }
            }

        return view;
    }

}
