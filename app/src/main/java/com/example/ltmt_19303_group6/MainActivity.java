package com.example.ltmt_19303_group6;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
import com.example.ltmt_19303_group6.Fragment.Fragment_Home;
import com.example.ltmt_19303_group6.Fragment.Fragment_Profile;
import com.example.ltmt_19303_group6.Fragment.Fragment_Shop_Cart;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_CUSTOMER = 1;
    public static final int FRAGMENT_SHOP_CART = 2;
    public static final int FRAGMENT_PROFILE = 3;

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
                Integer id = item.getItemId();


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
                    }
                }
                else if(item.getItemId() == R.id.nav_customer){
                    if (myFragMent  != FRAGMENT_CUSTOMER){
                        setFragment(new Fragment_Customer());
                        myFragMent = FRAGMENT_CUSTOMER;
                    }
                }
                else if(item.getItemId() == R.id.nav_shop_cart){
                    if (myFragMent  != FRAGMENT_SHOP_CART){
                        setFragment(new Fragment_Shop_Cart());
                        myFragMent = FRAGMENT_SHOP_CART;
                    }
                }
                else if(item.getItemId() == R.id.nav_profile){
                    if (myFragMent  != FRAGMENT_PROFILE){
                        setFragment(new Fragment_Profile());
                        myFragMent = FRAGMENT_PROFILE;
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
}