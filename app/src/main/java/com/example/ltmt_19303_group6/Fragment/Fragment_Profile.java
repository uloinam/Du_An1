package com.example.ltmt_19303_group6.Fragment;

import android.os.Bundle;
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

import java.util.ArrayList;

public class Fragment_Profile extends Fragment {
    ImageView ivImg;
    TextView tvEmail, tvName, tv_age, phoneNumber;

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

        // Lấy dữ liệu từ DAO
        Profile_DAO profile_dao = new Profile_DAO(requireContext());
        ArrayList<Profile_Model> profileList = profile_dao.getProfile();

        if (profileList != null && !profileList.isEmpty()) {
            Profile_Model profile = profileList.get(0);

            // Set dữ liệu vào view
            tvName.setText(profile.getName_Empolyee());
            tvEmail.setText(profile.getEmail_Empolyee());
            tv_age.setText(String.valueOf(profile.getAge()));
            phoneNumber.setText(profile.getPhone_Empolyee());

            // Hiển thị ảnh đại diện (nếu có)
            if (profile.getAvatar() != null && profile.getAvatar().length > 0) {
                ivImg.setImageBitmap(DbHelper.convertByteArrayToBitmap(profile.getAvatar()));
            } else {
                ivImg.setImageResource(R.drawable.ic_user); // Ảnh mặc định nếu không có
            }
        }

        return view;
    }
}
