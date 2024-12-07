package com.example.ltmt_19303_group6.Login_SingIn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ltmt_19303_group6.DAO.User_DAO;
import com.example.ltmt_19303_group6.MainActivity;
import com.example.ltmt_19303_group6.Model.User_Model;
import com.example.ltmt_19303_group6.R;

public class Login_Activity extends AppCompatActivity {
    EditText edt_userName, edt_passWord;
    Button btn_Login;
    User_DAO userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Ánh xạ
        getUi();
        Listener_Event();

    }

    public void getUi(){
        edt_userName = findViewById(R.id.edt_user);
        edt_passWord = findViewById(R.id.edt_password);

        btn_Login = findViewById(R.id.btn_login);

        userDao = new User_DAO(Login_Activity.this);
    }

    public void Listener_Event(){
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventLogin();
            }
        });
    }

    public void eventLogin(){
        String user_Name = edt_userName.getText().toString().trim();
        String passWord = edt_passWord.getText().toString().trim();

        User_Model userModel = userDao.get_OneUser(user_Name, passWord);
        if (userModel != null){
            if (user_Name.equals(userModel.getUser_Name()) && passWord.equals(userModel.getUser_Password())){
                SharedPreferences sharedPreferences = getSharedPreferences("USER_LOGIN", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                editor.putInt("id_empolyee", userModel.getId_User());
                editor.apply();
                startActivity(intent);
            }else {
                Toast.makeText(this, "Sai mật khẩu hoặc password", Toast.LENGTH_SHORT).show();
            }
        }
    }
}