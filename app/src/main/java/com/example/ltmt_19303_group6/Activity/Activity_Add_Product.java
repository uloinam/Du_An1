package com.example.ltmt_19303_group6.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltmt_19303_group6.AdapterView.Adapter_Category;
import com.example.ltmt_19303_group6.AdapterView.Adapter_Group;
import com.example.ltmt_19303_group6.AdapterView.Adapter_Group_Product;
import com.example.ltmt_19303_group6.AdapterView.Adapter_Spinner_Brand;
import com.example.ltmt_19303_group6.AdapterView.Adapter_Spinner_Category;
import com.example.ltmt_19303_group6.AdapterView.Adapter_add_image_product;
import com.example.ltmt_19303_group6.DAO.Brand_DAO;
import com.example.ltmt_19303_group6.DAO.Category_DAO;
import com.example.ltmt_19303_group6.DAO.Group_Product_DAO;
import com.example.ltmt_19303_group6.DAO.Image_DAO;
import com.example.ltmt_19303_group6.DAO.Product_DAO;
import com.example.ltmt_19303_group6.Model.Brand_Model;
import com.example.ltmt_19303_group6.Model.Category_Model;
import com.example.ltmt_19303_group6.Model.Group_Product;
import com.example.ltmt_19303_group6.Model.Image_product_Model;
import com.example.ltmt_19303_group6.Model.Product_Model;
import com.example.ltmt_19303_group6.R;
import com.example.ltmt_19303_group6.SpaceItemDecoration.SpaceItemDecoration;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Activity_Add_Product extends AppCompatActivity implements Adapter_add_image_product.GetArrayByte {
    EditText edt_name, edt_height, edt_weight, edt_wight, edt_Price, edt_Quantity, edt_supiler, edt_descript;
    Spinner sp_Group_Product, sp_Category, sp_Brand;
    RecyclerView rc_image_product;
    Adapter_Spinner_Brand adapterSpinnerBrand;
    ImageView image_back;

    Brand_DAO brandDao;
    Group_Product_DAO groupProductDao;
    Category_DAO categoryDao;
    Product_DAO productDao;
    RadioButton rdo_dangBan, rdo_hetHang, rdo_dungban;
    Image_DAO imageDao;

    Adapter_Group_Product groupProduct;
    Adapter_Spinner_Category adapterSpinnerCategory;
    Adapter_add_image_product adapterAddImageProduct;

    ArrayList<Brand_Model> list_brand ;
    ArrayList<Group_Product> list_Group;
    ArrayList<Category_Model> list_category;
    ArrayList<byte[]> list_array_byte_image;
    ArrayList<Product_Model> list_product;

    Integer id_brand = null, id_category = null, status = 0;

    Button btn_Select_imag, btn_xacNhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_product);

        getUi();
        setData_Spinner();

        event_clickSpinner();

        even_ClickSelectImage();

        event_clickAdd_Product();

        evenClick_back();
        getStSatus();
    }

    private void evenClick_back() {
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void event_clickAdd_Product() {
        btn_xacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_product = edt_name.getText().toString().trim();
                String height_product = edt_height.getText().toString().trim();
                String weight_product = edt_weight.getText().toString().trim();
                String wight_product = edt_wight.getText().toString().trim();
                String price_product = edt_Price.getText().toString().trim();
                String quantity_product = edt_Quantity.getText().toString().trim();
                String Suplier_product = edt_supiler.getText().toString().trim();
                String descript_product = edt_descript.getText().toString().trim();
                if (name_product.isEmpty() || height_product.isEmpty() || wight_product.isEmpty() || weight_product.isEmpty() || price_product.isEmpty() || quantity_product.isEmpty() || Suplier_product.isEmpty() ||descript_product.isEmpty()){
                    if (name_product.isEmpty()){
                        edt_name.setError("Không để trống trong tin");
                    }
                    if (height_product.isEmpty()){
                        edt_name.setError("Không để trống trong tin");
                    }
                    if (wight_product.isEmpty()){
                        edt_name.setError("Không để trống trong tin");
                    }
                    if (weight_product.isEmpty()){
                        edt_name.setError("Không để trống trong tin");
                    }
                    if (price_product.isEmpty()){
                        edt_name.setError("Không để trống trong tin");
                    }
                    if (quantity_product.isEmpty()){
                        edt_name.setError("Không để trống trong tin");
                    }
                    if (Suplier_product.isEmpty()){
                        edt_name.setError("Không để trống trong tin");
                    }
                    if (descript_product.isEmpty()){
                        edt_name.setError("Không để trống trong tin");
                    }
                }else if (rc_image_product == null){
                    Toast.makeText(Activity_Add_Product.this, "Bạn chưa chọn ảnh", Toast.LENGTH_SHORT).show();
                }else{

                    Integer quantity = Integer.parseInt(quantity_product);
                    Integer price = Integer.parseInt(price_product);
                    Integer height = Integer.parseInt(height_product);
                    Integer weight = Integer.parseInt(weight_product);
                    Integer wight = Integer.parseInt(wight_product);

                    Calendar calendar = Calendar.getInstance();
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0 nên cần cộng thêm 1
                    int year = calendar.get(Calendar.YEAR);
                    String created = ""+day +"/"+month+"/"+year;
                    if (list_array_byte_image.size() > 0){
                        boolean reslut_text = productDao.add_Product(new Product_Model(null,quantity, price, height, weight, status, wight, id_brand, id_category, name_product, descript_product, created,  created, Suplier_product));

                        Integer id_product_new  = productDao.get_List_Product();
                        if (reslut_text){
                            for (int i = 0; i < list_array_byte_image.size(); i++){
                                boolean reslut = imageDao.addImage_Product(new Image_product_Model(null, id_product_new,list_array_byte_image.get(i)));
                            }
                            Toast.makeText(Activity_Add_Product.this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
            }
        });
    }


    public void getUi(){
        edt_name = findViewById(R.id.edt_Name);
        edt_height = findViewById(R.id.edt_height);
        edt_weight = findViewById(R.id.edt_weight);
        edt_wight = findViewById(R.id.edt_wight);
        edt_Price = findViewById(R.id.edt_Price);
        edt_Quantity = findViewById(R.id.edt_quantity);
        edt_supiler = findViewById(R.id.edt_Supiler);
        edt_descript = findViewById(R.id.edt_descrip);
        image_back = findViewById(R.id.btn_back);

        rdo_dangBan = findViewById(R.id.rdo_dangBan);
        rdo_hetHang = findViewById(R.id.rdo_hetHang);
        rdo_dungban = findViewById(R.id.rdo_dungban);

        rc_image_product = findViewById(R.id.rc_image_product);
        GridLayoutManager layoutManager = new GridLayoutManager(Activity_Add_Product.this, 2);
        rc_image_product.setLayoutManager(layoutManager);
        rc_image_product.addItemDecoration(new SpaceItemDecoration(16));

        sp_Group_Product = findViewById(R.id.sp_group_pruct);
        sp_Category = findViewById(R.id.sp_Category);
        sp_Brand = findViewById(R.id.sp_brand);

        brandDao = new Brand_DAO(Activity_Add_Product.this);
        groupProductDao = new Group_Product_DAO(Activity_Add_Product.this);
        categoryDao = new Category_DAO(Activity_Add_Product.this);
        productDao = new Product_DAO(Activity_Add_Product.this);
        imageDao = new Image_DAO(Activity_Add_Product.this);

        btn_Select_imag = findViewById(R.id.btn_Select_imag);
        btn_xacNhan = findViewById(R.id.btn_xacNhan);

        list_brand = new ArrayList<>();
        list_Group = new ArrayList<>();
        list_category = new ArrayList<>();
        list_array_byte_image = new ArrayList<>();
        list_product = new ArrayList<>();
    }

    public void setData_Spinner(){
        list_brand = brandDao.get_list_Brand();
        if (list_brand != null){
            adapterSpinnerBrand = new Adapter_Spinner_Brand(Activity_Add_Product.this, R.layout.item_brand, list_brand);
            sp_Brand.setAdapter(adapterSpinnerBrand);

        }

        list_Group = groupProductDao.getList_Group_Product();

        if (list_Group != null && !list_Group.isEmpty()){
            groupProduct = new Adapter_Group_Product(Activity_Add_Product.this, R.layout.item_one_group_product, list_Group);

            sp_Group_Product.setAdapter(groupProduct);

            list_category = categoryDao.getLit_Category(list_Group.get(0).getId());

            adapterSpinnerCategory = new Adapter_Spinner_Category(Activity_Add_Product.this, R.layout.item_spinner_category,list_category );

            sp_Category.setAdapter(adapterSpinnerCategory);
        }

    }


    public void event_clickSpinner(){
        sp_Group_Product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                list_category = categoryDao.getLit_Category(position + 1);

                adapterSpinnerCategory = new Adapter_Spinner_Category(Activity_Add_Product.this, R.layout.item_spinner_category,list_category );

                sp_Category .setAdapter(adapterSpinnerCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_category = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_Brand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_brand = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void even_ClickSelectImage() {
        btn_Select_imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK)
                        .setType("image/*")
                        .putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                open_garellye_select_mutil_Image.launch(intent);
            }
        });
    }

    ActivityResultLauncher<Intent> open_garellye_select_mutil_Image = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        ArrayList<Uri> imageUris = new ArrayList<>(); // Danh sách để lưu trữ các URI hình ảnh

                        if (data.getClipData() != null) { // Khi người dùng chọn nhiều hình ảnh

                            for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                                Uri imageUri = data.getClipData().getItemAt(i).getUri();
                                imageUris.add(imageUri); // Thêm URI vào danh sách
                            }
                        } else if (data.getData() != null) { // Khi người dùng chọn một hình ảnh
                            Uri imageUri = data.getData();
                            imageUris.add(imageUri); // Thêm URI vào danh sách
                        }
                        adapterAddImageProduct = new Adapter_add_image_product(imageUris, Activity_Add_Product.this, Activity_Add_Product.this);
                        rc_image_product.setAdapter(adapterAddImageProduct);
                        // Xử lý danh sách ảnh (ví dụ: hiển thị trong RecyclerView hoặc log thông tin)
                    }
                }
            }
    );

    public void getStSatus(){
        rdo_dangBan.setChecked(true);

        rdo_dangBan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    status = 0;
                }
            }
        });

        rdo_hetHang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    status = 1;
                }
            }
        });

        rdo_dungban.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    status = 2;
                }
            }
        });
    }


    @Override
    public void getBitmapFavtory(byte[] list_byte_Image) {
        list_array_byte_image.add(list_byte_Image);
    }
}