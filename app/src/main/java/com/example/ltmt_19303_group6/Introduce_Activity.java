package com.example.ltmt_19303_group6;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ltmt_19303_group6.AdapterView.AdapterViewPager2;
import com.example.ltmt_19303_group6.AnimationViewPage2.ZoomOutPageTransformer;
import com.example.ltmt_19303_group6.Login_SingIn.Login_Activity;

public class Introduce_Activity extends AppCompatActivity {
    private static final String TAG = "zzzzzzzzzzzzzz";
    ViewPager2 viewPager2;
    AdapterViewPager2 pager2;
    Button btn_Next;
    TextView tv_Skip;
    Integer  myCurrent = 0;
    View index01, index02, index03;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_introduce);

        viewPager2 = findViewById(R.id.ViewPager2_Introduce);
        tv_Skip = findViewById(R.id.tv_Skip);
        pager2 = new AdapterViewPager2(this);
        index01 = findViewById(R.id.index01);
        index02 = findViewById(R.id.index02);
        index03 = findViewById(R.id.index03);


        btn_Next  = findViewById(R.id.btn_next);
//        viewPager2.setPageTransformer(new ZoomOutPageTransformer());

        viewPager2.setAdapter(pager2);
        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCurrent ++;
                viewPager2.setCurrentItem(myCurrent);
                if (myCurrent == 2){
                    clickNext();
                }
            }
        });

        tv_Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager2.setCurrentItem(3);
                clickNext();
            }
        });



    }

    public void clickNext(){
            btn_Next.setText("Get Started");
            tv_Skip.setVisibility(View.GONE);
            btn_Next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Introduce_Activity.this, Login_Activity.class));
                    finishAffinity();
                }
            });
    }
}