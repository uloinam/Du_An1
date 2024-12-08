package com.example.ltmt_19303_group6.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltmt_19303_group6.AdapterView.Adapter_item_shopcart;
import com.example.ltmt_19303_group6.DAO.Shop_Cart_DAO;
import com.example.ltmt_19303_group6.Model.Shop_Cart_Model;
import com.example.ltmt_19303_group6.R;
import com.example.ltmt_19303_group6.SpaceItemDecoration.SpaceItemDecoration;

import java.util.ArrayList;

public class Fragment_Shop_Cart extends Fragment {
    private TextView tv01, tvAddress;
    private ImageView btnUpdateAddress;
    private RecyclerView rcShopCart;
    private Button btnXacNhan;
    Adapter_item_shopcart adapterItemShopcart;
    ArrayList<Shop_Cart_Model> list;
    Shop_Cart_DAO shopCartDao;
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

        list = shopCartDao.getList_Product_In_ShopCart();
        setDAta();

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
