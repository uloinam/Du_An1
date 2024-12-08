package com.example.ltmt_19303_group6;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
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

import com.example.ltmt_19303_group6.AdapterView.Adapter_Group_Product;
import com.example.ltmt_19303_group6.DAO.Brand_DAO;
import com.example.ltmt_19303_group6.DAO.Category_DAO;
import com.example.ltmt_19303_group6.DAO.Group_Product_DAO;
import com.example.ltmt_19303_group6.DAO.Profile_DAO;
import com.example.ltmt_19303_group6.Fragment.Fragment_Customer;
import com.example.ltmt_19303_group6.Fragment.Fragment_Doi_Password;
import com.example.ltmt_19303_group6.Fragment.Fragment_HoaDon;
import com.example.ltmt_19303_group6.Fragment.Fragment_Home;
import com.example.ltmt_19303_group6.Fragment.Fragment_Ls_XuatNhap;
import com.example.ltmt_19303_group6.Fragment.Fragment_Profile;
import com.example.ltmt_19303_group6.Fragment.Fragment_QL_NhanVien;
import com.example.ltmt_19303_group6.Fragment.Fragment_Shop_Cart;
import com.example.ltmt_19303_group6.Fragment.Fragment_ThongKe_DoanhThu;
import com.example.ltmt_19303_group6.Login_SingIn.Login_Activity;
import com.example.ltmt_19303_group6.Model.Brand_Model;
import com.example.ltmt_19303_group6.Model.Category_Model;
import com.example.ltmt_19303_group6.Model.Group_Product;
import com.example.ltmt_19303_group6.Model.Profile_Model;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

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
    public static final int DIALOG_ADD_CATEGORY = 9;
    ImageView btn_search;
    EditText edt_Search;
    Integer myFragMent = 0;
    DrawerLayout drawerLayout;
    NavigationView nav;
    Toolbar toolbar;
    BottomNavigationView bottomNav;
    FragmentManager fm;
    FragmentContainerView fm_Container;
    Category_DAO categoryDao;
    Group_Product_DAO groupProductDao;
    Integer id_Group_Product;
    Brand_DAO brandDao ;
    Profile_DAO profileDao;

    ImageView image_dialog_add_brand;
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
        evenClick_Menu_Toolbar();

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myFragMent == FRAGMENT_QUANLY_NHANVIEN){
                    String keyword = edt_Search.getText().toString().trim();

                    // Tạo Bundle để gửi dữ liệu
                    Bundle bundle = new Bundle();
                    bundle.putString("search_keyword", keyword);

                    // Tạo Fragment và truyền dữ liệu
                    Fragment_QL_NhanVien fragmentQlNhanVien = new Fragment_QL_NhanVien();
                    fragmentQlNhanVien.setArguments(bundle);

                    setFragment(fragmentQlNhanVien);
                }
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("USER_LOGIN", MODE_PRIVATE);
        Integer id = sharedPreferences.getInt("id_empolyee", 0);

        Profile_Model profileModel = profileDao.getProfile(id);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(MainActivity.this
                , drawerLayout
                , toolbar
                , R.string.Open_Nav
                ,R.string.Close);

        // thay thế icon hamburg default thành icon avartar
        drawerToggle.setDrawerIndicatorEnabled(false); // Disable default icon
        Drawable drawable = null;
        if (profileModel != null){
           if (profileModel.getAvatar() != null){
               Bitmap bitmap = BitmapFactory.decodeByteArray(profileModel.getAvatar(), 0, profileModel.getAvatar().length);
               int desiredWidth = 100; // Kích thước mong muốn
               int desiredHeight = 100;
               Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, desiredWidth, desiredHeight, false);

               Bitmap circularBitmap = getCircularBitmap(resizedBitmap);
               drawable = new BitmapDrawable(getResources(), circularBitmap);
           }
        }
        if (drawable != null) {
            toolbar.setNavigationIcon(drawable);
        }else {
            toolbar.setNavigationIcon(R.drawable.account_ic);
        }
        // Set custom icon
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
                } else if (item.getItemId() == R.id.nav_category) {
                    ShowDiaLog_Add_Category();
                } else if (item.getItemId() == R.id.nav_brand) {
                    shoadiaLog_Add_Brand();
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
                        Fragment_Profile fragmentProfile = new Fragment_Profile();
                        Bundle bundle = new Bundle();
                        bundle.putInt("id_user", id);

                        fragmentProfile.setArguments(bundle);
                        setFragment(fragmentProfile);
                        myFragMent = FRAGMENT_PROFILE;
                        setAction_Search();
                    }
                }
                return true;
            }
        });
    }

    private void shoadiaLog_Add_Brand() {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_add_brand, null, false);

        image_dialog_add_brand = view.findViewById(R.id.img_branch);
        EditText edt_name = view.findViewById(R.id.edt_Name);
        Button btn_XacNhan = view.findViewById(R.id.btn_xacNhan);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(view);

        Dialog dialog = builder.create();

        image_dialog_add_brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                openGarellye_Launcher.launch(intent);
            }
        });

        btn_XacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_brand = edt_name.getText().toString().trim();
                if (name_brand.isEmpty()){
                    edt_name.setError("Chưa nhập dữ liệu");
                }else{
                    byte[] image = coverImage_to_Byte(image_dialog_add_brand);
                    if (image != null){
                        boolean reslut = brandDao.add_brand(new Brand_Model(null, name_brand,image ));
                        if (reslut){
                            Toast.makeText(MainActivity.this, "Thêm hãng thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "Bạn chưa chọn ảnh", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        dialog.show();
    }

    private void ShowDiaLog_Add_Category() {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_add_category, null, false);

        EditText edt_Name_Category;
        Button btn_XacNhan;
        Spinner sp_group_product;

        btn_XacNhan = view.findViewById(R.id.btn_xacNhan);
        edt_Name_Category = view.findViewById(R.id.edt_Name);
        sp_group_product = view.findViewById(R.id.sp_group_pruct);
        ArrayList<Group_Product> list = groupProductDao.getList_Group_Product();

        ArrayList<String> Name_Group_Product = new ArrayList<>();
        if (list != null){
            for (Group_Product list_name_group : list){
                Name_Group_Product.add(list_name_group.getName());
            }
            Adapter_Group_Product arrayAdapter =  new Adapter_Group_Product(this, R.layout.item_one_group_product,list );
            sp_group_product.setAdapter(arrayAdapter);
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(view);

        sp_group_product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_Group_Product = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                id_Group_Product = 0;
            }
        });
        Dialog dialog = builder.create();
        btn_XacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_category = edt_Name_Category.getText().toString().trim();

                if (name_category.isEmpty()){
                    edt_Name_Category.setError("Chưa nhập tên loại sản phẩm mới");
                }else {
                    boolean result = categoryDao.add_Category(new Category_Model(null, name_category, id_Group_Product));
                    if (result){
                        Toast.makeText(MainActivity.this, "Đã thêm thể loại mới thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });

        dialog.show();

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

        categoryDao = new Category_DAO(MainActivity.this);
        groupProductDao = new Group_Product_DAO(MainActivity.this);
        brandDao = new Brand_DAO(MainActivity.this);

        profileDao = new Profile_DAO(MainActivity.this);
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

    public void evenClick_Menu_Toolbar(){
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.add_group_product){
                    ShowDiaLog_Add_Group_Product();
                }
                return true;
            }
        });
    }

    public void ShowDiaLog_Add_Group_Product(){
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_add_group_product, null, false);

        EditText edt_name_group_product = view.findViewById(R.id.edt_Name);
        Button btn_XacNhan = view.findViewById(R.id.btn_xacNhan);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(view);

        Dialog dialog = builder.create();

        btn_XacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_group_product = edt_name_group_product.getText().toString().trim();
                if (name_group_product != null){
                    Group_Product groupProduct = new Group_Product(null, name_group_product);
                    boolean reslut = groupProductDao.add_group_product(groupProduct);
                    if (reslut){
                        Toast.makeText(MainActivity.this, "Tạo nhóm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });
        dialog.show();
    }

    ActivityResultLauncher<Intent> openGarellye_Launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getData() != null){
                        try {
                            if (result.getResultCode() == RESULT_OK) {
                                Intent data = result.getData();
                                Uri imageUri = data.getData();
                                try {
                                    InputStream inputStream = getContentResolver().openInputStream(imageUri);
                                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                                    image_dialog_add_brand.setImageBitmap(bitmap);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }

                        }catch (Exception e){

                        }
                    }

                }
            });


    public byte[] coverImage_to_Byte(ImageView imageView){
        byte[] imageInByte = null;
        try {
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            imageInByte = baos.toByteArray();
        }catch (Exception e){
            Log.d("zzzzzz", "coverImage_to_Byte: Lỗi vclll");
        }
        return imageInByte;
    }

    private Bitmap getCircularBitmap(Bitmap bitmap) {
        int size = Math.min(bitmap.getWidth(), bitmap.getHeight());
        Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, size, size);
        RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);

        // Vẽ hình tròn
        paint.setColor(Color.BLACK);
        canvas.drawOval(rectF, paint);

        // Cắt bitmap thành hình tròn
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, null, rect, paint);

        return output;
    }

}