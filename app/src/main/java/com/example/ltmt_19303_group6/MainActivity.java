package com.example.ltmt_19303_group6;

import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import com.example.ltmt_19303_group6.Fragment.Fragment_Customer;
import com.example.ltmt_19303_group6.Fragment.Fragment_Doi_Password;
import com.example.ltmt_19303_group6.Fragment.Fragment_HoaDon;
import com.example.ltmt_19303_group6.Fragment.Fragment_Home;
import com.example.ltmt_19303_group6.Fragment.Fragment_Ls_XuatNhap;
import com.example.ltmt_19303_group6.Fragment.Fragment_Profile;
import com.example.ltmt_19303_group6.Fragment.Fragment_QL_NhanVien;
import com.example.ltmt_19303_group6.Fragment.Fragment_Shop_Cart;
import com.example.ltmt_19303_group6.Fragment.Fragment_ThongKe_DoanhThu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_CUSTOMER = 1;
    public static final int FRAGMENT_SHOP_CART = 2;
    public static final int FRAGMENT_PROFILE = 3;
    public static final int FRAGMENT_TK_DOANHTHU = 4;
    public static final int FRAGMENT_HOA_DON = 5;
    public static final int FRAGMENT_QUANLY_NHANVIEN = 6;
    public static final int FRAGMENT_LICH_SU_NHAP_XUAT = 7;
    public static final int FRAGMENT_DOI_PASSWORD = 8;

    ImageView btn_search;
    EditText edt_Search;
    Integer myFragMent = 0;
    DrawerLayout drawerLayout;
    NavigationView nav;
    Toolbar toolbar;
    BottomNavigationView bottomNav;
    FragmentManager fm;
    FragmentContainerView fm_Container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Ánh xạ
        getUi();

        // Xét nút ba chấm của toolbar
        toolbar.getOverflowIcon().setTint(getResources().getColor(R.color.black));
        edt_Search = findViewById(R.id.edt_search);
        // set actionbar
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(MainActivity.this
                , drawerLayout
                , toolbar
                , R.string.Open_Nav
                ,R.string.Close);

        // thay thế icon hamburg default thành icon avartar
        drawerToggle.setDrawerIndicatorEnabled(false); // Disable default icon
        toolbar.setNavigationIcon(R.drawable.account_ic); // Set custom icon
        toolbar.setNavigationOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));

        // set sự kiện lắng nghe của drawToggle
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        // setFragment đầu tiên
        setFragment(new Fragment_Home());

        // sét sự kiện lắng nghe của Navigation View
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.nav_thongKe){
                    // check Fragment hiện tại có phải Fragment home
                    // Tránh việc khởi tạo lại Fragment
                    if (myFragMent  != FRAGMENT_TK_DOANHTHU){

                        setFragment(new Fragment_ThongKe_DoanhThu());
                        myFragMent = FRAGMENT_TK_DOANHTHU;
                        setAction_Search();
                    }
                }else if(item.getItemId() == R.id.nav_HoaDon){
                    if (myFragMent  != FRAGMENT_HOA_DON){

                        setFragment(new Fragment_HoaDon());
                        myFragMent = FRAGMENT_HOA_DON;
                        setAction_Search();
                    }
                }
                else if(item.getItemId() == R.id.nav_QLNV){
                    if (myFragMent  != FRAGMENT_QUANLY_NHANVIEN){

                        setFragment(new Fragment_QL_NhanVien());
                        myFragMent = FRAGMENT_QUANLY_NHANVIEN;
                        setAction_Search();
                    }
                }
                else if(item.getItemId() == R.id.nav_History){
                    if (myFragMent  != FRAGMENT_LICH_SU_NHAP_XUAT){

                        setFragment(new Fragment_Ls_XuatNhap());
                        myFragMent = FRAGMENT_LICH_SU_NHAP_XUAT;
                        setAction_Search();
                    }
                }
                else if(item.getItemId() == R.id.nav_DoiPassword){
                    if (myFragMent  != FRAGMENT_DOI_PASSWORD){

                        setFragment(new Fragment_Doi_Password());
                        myFragMent = FRAGMENT_DOI_PASSWORD;
                        setAction_Search();
                    }
                }
                drawerLayout.close();
                return true;
            }
        });

        // Xét sự kiện lắng nghe của bottom Navigation
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // check nút người dùng gọi nhấn
                // Trả lại 1 item.getId đã xét bên menu Bottom nav
                if (item.getItemId() == R.id.nav_home){
                    // check Fragment hiện tại có phải Fragment home
                    // Tránh việc khởi tạo lại Fragment Lãng phí dung lươn
                    if (myFragMent  != FRAGMENT_HOME){

                        setFragment(new Fragment_Home());
                        myFragMent = FRAGMENT_HOME;
                        setAction_Search();
                    }
                }
                else if(item.getItemId() == R.id.nav_customer){
                    if (myFragMent  != FRAGMENT_CUSTOMER){

                        setFragment(new Fragment_Customer());
                        myFragMent = FRAGMENT_CUSTOMER;
                        setAction_Search();
                    }
                }
                else if(item.getItemId() == R.id.nav_shop_cart){
                    if (myFragMent  != FRAGMENT_SHOP_CART){

                        setFragment(new Fragment_Shop_Cart());
                        myFragMent = FRAGMENT_SHOP_CART;
                        setAction_Search();
                    }
                }
                else if(item.getItemId() == R.id.nav_profile){
                    if (myFragMent  != FRAGMENT_PROFILE){

                        setFragment(new Fragment_Profile());
                        myFragMent = FRAGMENT_PROFILE;
                        setAction_Search();
                    }
                }
                return true;
            }
        });
    }

    // Ánh xạ
    public void getUi(){
        drawerLayout = findViewById(R.id.main);
        nav = findViewById(R.id.nav);
        toolbar = findViewById(R.id.toobar);

        bottomNav = findViewById(R.id.nav_bottom);
        btn_search= findViewById(R.id.btn_search);

        fm = getSupportFragmentManager();

        // Fragment Conatiner view
        fm_Container = findViewById(R.id.Fragment_Container);
    }

    // set sự kiện nhấn nút 3 chấm của toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    // Xét Fragment vào Fragment_Container
    public void setFragment(Fragment fragment){
        fm.beginTransaction().replace(R.id.Fragment_Container, fragment).commit();
    }

    // Sử lý ẩn /Hiện edt_search và button search
    public void setAction_Search(){
        if (btn_search.getVisibility() == View.VISIBLE && myFragMent == FRAGMENT_PROFILE){
            btn_search.setVisibility(View.GONE);
            edt_Search.setVisibility(View.GONE);
        }else if (btn_search.getVisibility() == View.VISIBLE && myFragMent == FRAGMENT_TK_DOANHTHU){
            btn_search.setVisibility(View.GONE);
            edt_Search.setVisibility(View.GONE);
        }else{
            btn_search.setVisibility(View.VISIBLE);
            edt_Search.setVisibility(View.VISIBLE);
        }
    }


}