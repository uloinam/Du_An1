package com.example.ltmt_19303_group6.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltmt_19303_group6.AdapterView.Adapter_item_rc_product;
import com.example.ltmt_19303_group6.DAO.Bill_Detail_DAO;
import com.example.ltmt_19303_group6.DAO.Brand_DAO;
import com.example.ltmt_19303_group6.DAO.Customer_DAO;
import com.example.ltmt_19303_group6.DAO.Image_DAO;
import com.example.ltmt_19303_group6.DAO.Order_DAO;
import com.example.ltmt_19303_group6.DAO.Product_DAO;
import com.example.ltmt_19303_group6.DAO.Shop_Cart_DAO;
import com.example.ltmt_19303_group6.Model.Brand_Model;
import com.example.ltmt_19303_group6.Model.Image_product_Model;
import com.example.ltmt_19303_group6.Model.Order_Model;
import com.example.ltmt_19303_group6.Model.Product_Model;
import com.example.ltmt_19303_group6.Model.Shop_Cart_Model;
import com.example.ltmt_19303_group6.R;
import com.example.ltmt_19303_group6.SpaceItemDecoration.HorizontalSpaceItemDecoration;

import java.util.ArrayList;
import java.util.Calendar;

public class Activity_Product_Detail extends AppCompatActivity implements Adapter_item_rc_product.ListenClickItem_Image {
    private ImageView btnBack, btnUpdate, imageProduct, btnRemoveCountProduct, btnAddCountProduct;
    private RecyclerView rcItemProduct;
    private TextView tvNameProduct, tvPriceProduct, tvBrandProduct, tvDimensionsProduct, tvDescriptionProduct;
    private EditText edtCountProduct;
    private Button btnAddShopCart;
    private ArrayList<Image_product_Model> list_image;
    Product_DAO productDao;
    Image_DAO imageDao;
    Brand_DAO brandDao;
    Integer count_product ;
    Adapter_item_rc_product adapterItemRcProduct;
    Shop_Cart_DAO shopCartDao;
    Product_Model productModel;
    Bill_Detail_DAO billDetailDao;
    Order_DAO orderDao ;
    Customer_DAO customerDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);

        getUi();


        setData_product();

        evenClickback();

        btnRemoveCountProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer count = Integer.parseInt(edtCountProduct.getText().toString().trim());
                if (count > 1){
                    edtCountProduct.setText(""+(count - 1));
                }else {
                    Toast.makeText(Activity_Product_Detail.this, "Không thể giảm thêm", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnAddCountProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer count = Integer.parseInt(edtCountProduct.getText().toString().trim());
                if (count < count_product){
                    edtCountProduct.setText(""+(count + 1));
                }else {
                    Toast.makeText(Activity_Product_Detail.this, "Số hàng trong kho không đủ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnAddShopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Integer idCart, int quantity, int unitPrice, int idProduct, int subtotal
                Integer quantity_product_shop_cart = Integer.parseInt(edtCountProduct.getText().toString().trim());
                Integer price_Product = quantity_product_shop_cart * productModel.getPrice_product();

                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0 nên cần cộng thêm 1
                int year = calendar.get(Calendar.YEAR);
                String created = ""+day +"/"+month+"/"+year;

                Integer id_Bill_detail = billDetailDao.getOne_LastIdex();

                Integer id_Customer = customerDao.getOne_LastIdex();

                if (id_Bill_detail == null || id_Bill_detail == 0) {
                    id_Bill_detail = 1;
                }
                SharedPreferences sharedPreferences = getSharedPreferences("USER_LOGIN", Context.MODE_PRIVATE);
                Integer id_Empolyee = sharedPreferences.getInt("id_empolyee", 0);
                if (id_Customer == null || id_Customer == 0) {
                    id_Customer = 1;
                }

                boolean reslut_Order = orderDao.add_Order(new Order_Model(null, created, price_Product, id_Customer, id_Empolyee, id_Bill_detail));

                if (reslut_Order){
                    Integer id_Order = orderDao.getOne_LastIdex();
                    if (id_Order == null || id_Order == 0 ){
                        id_Order = 1;
                    }
                    boolean reslut_shopvart = shopCartDao.add_product_to_Shopcar(new Shop_Cart_Model(null, quantity_product_shop_cart, price_Product, productModel.getId_productl(), productModel.getPrice_product(), id_Order));
                    if (reslut_shopvart){
                        Toast.makeText(Activity_Product_Detail.this, "Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                        //Integer idCart, int quantity, int unitPrice, int idProduct, int subtotal, Integer id_Order

                }
            }
        });
    }

    private void setData_product() {
        Intent intent = getIntent();
        Integer id = intent.getIntExtra("id_product", 0);
        if (id != null){
            productModel = productDao.getProduct_detail(id);
            count_product = productModel.getQuantity_Product();
            if (productModel != null){
                Brand_Model brandModel = brandDao.get_OneBrand(productModel.getID_Brand());
                tvBrandProduct.setText(""+brandModel.getName());
                tvPriceProduct.setText(""+productModel.getPrice_product());
                tvDimensionsProduct.setText("("+productModel.getHeight()+"/"+productModel.getWeight()+")");
                tvDescriptionProduct.setText("Chi tiết: "+productModel.getDescrible());
                tvNameProduct.setText(""+productModel.getName());

                Bitmap bitmap = BitmapFactory.decodeByteArray(productModel.getImage(), 0, productModel.getImage().length);

                imageProduct.setImageBitmap(bitmap);
            }

            list_image = imageDao.get_list_Image_fromProduct(id);
            adapterItemRcProduct = new Adapter_item_rc_product(list_image, Activity_Product_Detail.this, this);
            rcItemProduct.setAdapter(adapterItemRcProduct);
        }
    }

    private void evenClickback() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 finish();
            }
        });
    }

    public void getUi(){
        btnBack = findViewById(R.id.btn_back);
        btnUpdate = findViewById(R.id.btn_update);
        imageProduct = findViewById(R.id.image_product);

        rcItemProduct = findViewById(R.id.rc_item_Product);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rcItemProduct.setLayoutManager(layoutManager);
        rcItemProduct.addItemDecoration(new HorizontalSpaceItemDecoration(15));

        tvNameProduct = findViewById(R.id.tv_Name_Product);
        tvPriceProduct = findViewById(R.id.tv_Price_Product);
        tvBrandProduct = findViewById(R.id.tv_Brand_Product);
        tvDimensionsProduct = findViewById(R.id.tv_Dimensions_Product);
        tvDescriptionProduct = findViewById(R.id.tv_Descrption_Product);
        btnRemoveCountProduct = findViewById(R.id.btn_remove_Count_Product);
        edtCountProduct = findViewById(R.id.id_edt_Count_Product);
        btnAddCountProduct = findViewById(R.id.btn_add_Count_Product);
        btnAddShopCart = findViewById(R.id.btn_add_Shop_Cart);

        list_image = new ArrayList<>();

        brandDao = new Brand_DAO(Activity_Product_Detail.this);
        imageDao = new Image_DAO(Activity_Product_Detail.this);
        productDao = new Product_DAO(Activity_Product_Detail.this);
        shopCartDao = new Shop_Cart_DAO(Activity_Product_Detail.this);
        billDetailDao = new Bill_Detail_DAO(Activity_Product_Detail.this);
        orderDao = new Order_DAO(Activity_Product_Detail.this);
        customerDao = new Customer_DAO(Activity_Product_Detail.this);
    }


    @Override
    public void setImage(Image_product_Model productModel) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(productModel.getImage(), 0, productModel.getImage().length);
        imageProduct.setImageBitmap(bitmap);
    }
}