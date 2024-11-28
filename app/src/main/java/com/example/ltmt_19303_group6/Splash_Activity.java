package com.example.ltmt_19303_group6;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ltmt_19303_group6.Login_SingIn.Login_Activity;

public class Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        SharedPreferences sharedPreferences = getSharedPreferences("MY_FIRT_INSTALL_APP", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Handler handler = new Handler();
        boolean check = sharedPreferences.getBoolean("MY_FIRT_INSTALL_APP", false);
        Log.d("zzzzzzz", "onCreate: "+check);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (sharedPreferences.getBoolean("MY_FIRT_INSTALL_APP", false)){
                    startActivity(new Intent(Splash_Activity.this, Login_Activity.class));
                    finishAffinity();
                }else{
                    startActivity(new Intent(Splash_Activity.this, Introduce_Activity.class));
                    editor.putBoolean("MY_FIRT_INSTALL_APP", true);
                    editor.apply();
                    finishAffinity();
                }
            }
        }, 3000);
    }
}