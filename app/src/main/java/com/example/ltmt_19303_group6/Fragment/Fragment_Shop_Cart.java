package com.example.ltmt_19303_group6.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltmt_19303_group6.AdapterView.Adapter_item_shopcart;
import com.example.ltmt_19303_group6.DAO.Bill_Detail_DAO;
import com.example.ltmt_19303_group6.DAO.Customer_DAO;
import com.example.ltmt_19303_group6.DAO.Order_DAO;
import com.example.ltmt_19303_group6.DAO.Shop_Cart_DAO;
import com.example.ltmt_19303_group6.Model.Bill_Detail_Model;
import com.example.ltmt_19303_group6.Model.Order_Model;
import com.example.ltmt_19303_group6.Model.Shop_Cart_Model;
import com.example.ltmt_19303_group6.R;
import com.example.ltmt_19303_group6.SpaceItemDecoration.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.Calendar;

public class Fragment_Shop_Cart extends Fragment {
    private TextView tv01, tvAddress;
    private ImageView btnUpdateAddress;
    private RecyclerView rcShopCart;
    private Button btnXacNhan;
    Adapter_item_shopcart adapterItemShopcart;
    ArrayList<Shop_Cart_Model> list;
    Shop_Cart_DAO shopCartDao;
    Order_DAO orderDao;
    Customer_DAO customerDao;
    Bill_Detail_DAO billDetailDao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_cart,container, false );

        list = new ArrayList<>();
        tv01 = view.findViewById(R.id.tv_01);
        tvAddress = view.findViewById(R.id.tv_address);
        btnUpdateAddress = view.findViewById(R.id.btn_update_address);
        rcShopCart = view.findViewById(R.id.rc_shopcart);
        btnXacNhan = view.findViewById(R.id.btn_xacNhan);
        shopCartDao = new Shop_Cart_DAO(getContext());
        orderDao = new Order_DAO(getContext());
        billDetailDao = new Bill_Detail_DAO(getContext());
        customerDao = new Customer_DAO(getContext());

        list = shopCartDao.getList_Product_In_ShopCart();
        setDAta();

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean result_Bill = billDetailDao.add_detail_bill(new Bill_Detail_Model(null, list.size(), 100000));
                if (result_Bill){
                    Calendar calendar = Calendar.getInstance();
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0 nên cần cộng thêm 1
                    int year = calendar.get(Calendar.YEAR);
                    String created = ""+day +"/"+month+"/"+year;

                    Integer id_Customer = customerDao.getOne_LastIdex();

                    if (id_Customer == null || id_Customer == 0) {
                        id_Customer = 1;
                    }

                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("USER_LOGIN", Context.MODE_PRIVATE);
                    Integer id_Empolyee = sharedPreferences.getInt("id_empolyee", 0);

                    Integer id_Order = orderDao.getOne_LastIdex();
                    if (id_Order == null || id_Order == 0){
                        id_Order = 1;
                    }
                    boolean result = orderDao.add_Order(new Order_Model(null, created, 100000,id_Customer, id_Empolyee, id_Order ));
                    if (result){
                        Toast.makeText(getContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                        list.clear();
                        adapterItemShopcart.notifyDataSetChanged();
                    }
                }
            }
        });
        return view;
    }
    public void setDAta(){
        rcShopCart.addItemDecoration(new SpaceItemDecoration(10));
        if (list != null){
            adapterItemShopcart = new Adapter_item_shopcart(list, getContext());
            rcShopCart.setAdapter(adapterItemShopcart);
        }
    }
}
