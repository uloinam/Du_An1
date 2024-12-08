package com.example.ltmt_19303_group6.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltmt_19303_group6.Activity.Add_Empolyee_Activity;
import com.example.ltmt_19303_group6.AdapterView.Adapter_NhanVien;
import com.example.ltmt_19303_group6.DAO.Profile_DAO;
import com.example.ltmt_19303_group6.DAO.User_DAO;
import com.example.ltmt_19303_group6.Model.Profile_Model;
import com.example.ltmt_19303_group6.Model.User_Model;
import com.example.ltmt_19303_group6.R;
import com.example.ltmt_19303_group6.SpaceItemDecoration.SpaceItemDecoration;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment_QL_NhanVien extends Fragment {
    FloatingActionButton btn_add;
    Context context;
    RecyclerView rc_NhanVien;
    User_DAO userDao;
    Adapter_NhanVien adapterNhanVien;
    ArrayList<Profile_Model> list;
    Profile_DAO profileDao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_nv,container, false );
        context = getContext();
        userDao = new User_DAO(getContext());


        profileDao = new Profile_DAO(getContext());
        btn_add = view.findViewById(R.id.btn_add);
        rc_NhanVien = view.findViewById(R.id.rc_nhanVien);

        list = profileDao.getProfile();
        for (int  i =  0; i < list.size(); i++){
            if (list.get(i).getAvatar() == null){
                list.remove(i);
            }
        }
        int spaceInPixels = getResources().getDimensionPixelSize(R.dimen.item_spacing);
        rc_NhanVien.addItemDecoration(new SpaceItemDecoration(spaceInPixels));
        adapterNhanVien = new Adapter_NhanVien(list, getContext());

        rc_NhanVien.setAdapter(adapterNhanVien);

        set_Event_Click_Add();
        return view;
    }

    public void set_Event_Click_Add(){
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Add_Empolyee_Activity.class);
                reslut_Add_NhanVieen.launch(intent);

            }
        });
    }

    ActivityResultLauncher<Intent> reslut_Add_NhanVieen = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {

            list.clear();
            list.addAll(profileDao.getProfile());
            for (int  i =  0; i < list.size(); i++){
                if (list.get(i).getAvatar() == null){
                    list.remove(i);
                }
            }
            adapterNhanVien.notifyDataSetChanged();
        }
    });
}
