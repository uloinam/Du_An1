package com.example.ltmt_19303_group6.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ltmt_19303_group6.DAO.Profile_DAO;
import com.example.ltmt_19303_group6.DAO.User_DAO;
import com.example.ltmt_19303_group6.Model.Profile_Model;
import com.example.ltmt_19303_group6.Model.User_Model;
import com.example.ltmt_19303_group6.R;

public class Activity_Empolyee_Detail extends AppCompatActivity {
    EditText edt_UserName, edt_Age, edt_PhoneNumber, edt_Email;
    TextView tv_name;
    Button btn_XacNhan;
    ImageView btn_back;
    Integer id_profile, Status;
    RadioButton rdo_DangLam, rdo_NghiLam;
    User_DAO userDao;
    Profile_DAO profileDao;
    ImageView image_avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_empolyee_detail);

        getUi();
        setData();
        event_Back();
    }


    public void getUi(){
        edt_UserName = findViewById(R.id.edt_Username);
        edt_Age = findViewById(R.id.edt_Age);
        edt_PhoneNumber = findViewById(R.id.edt_PhoneNumber);
        edt_Email = findViewById(R.id.edt_Email);
        btn_back = findViewById(R.id.btn_back);
        tv_name = findViewById(R.id.tv_Name);
        rdo_DangLam= findViewById(R.id.rdo_dangLam);
        rdo_NghiLam = findViewById(R.id.rdo_daNghi);

        image_avatar = findViewById(R.id.profile_image);
        btn_XacNhan = findViewById(R.id.btn_change);

        userDao = new User_DAO(Activity_Empolyee_Detail.this);
        profileDao = new Profile_DAO(Activity_Empolyee_Detail.this);
    }

    public void setData(){
        Intent intent = getIntent();

        id_profile = intent.getIntExtra("id_profile", 0);
        Profile_Model profileModel = profileDao.getProfile(id_profile);
        User_Model userModel = userDao.getOneEmpolyee(id_profile);
        edt_UserName.setText(""+userModel.getUser_Name());
        edt_Age.setText(""+profileModel.getAge());
        edt_PhoneNumber.setText(""+profileModel.getPhone_Empolyee());
        edt_Email.setText(""+profileModel.getEmail_Empolyee());
        tv_name.setText(""+profileModel.getName_Empolyee());

        if (userModel.getStatus() == 1){
            rdo_DangLam.setChecked(true);
        }else {
            rdo_NghiLam.setChecked(true);
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(profileModel.getAvatar(), 0, profileModel.getAvatar().length);
        image_avatar.setImageBitmap(bitmap);
    }

    public void event_Back(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}