package com.example.ltmt_19303_group6.Fragment;

import static androidx.recyclerview.widget.RecyclerView.*;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltmt_19303_group6.Activity.Activity_Add_Product;
import com.example.ltmt_19303_group6.AdapterView.Adapter_Category;
import com.example.ltmt_19303_group6.AdapterView.Adapter_Group;
import com.example.ltmt_19303_group6.AdapterView.Adapter_Product;
import com.example.ltmt_19303_group6.DAO.Category_DAO;
import com.example.ltmt_19303_group6.DAO.Group_Product_DAO;
import com.example.ltmt_19303_group6.DAO.Product_DAO;
import com.example.ltmt_19303_group6.Model.Category_Model;
import com.example.ltmt_19303_group6.Model.Group_Product;
import com.example.ltmt_19303_group6.Model.Product_Model;
import com.example.ltmt_19303_group6.R;
import com.example.ltmt_19303_group6.SpaceItemDecoration.HorizontalSpaceItemDecoration;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment_Home extends Fragment implements Adapter_Group.ListenClickItem, Adapter_Category.ListenClickItem {
    RecyclerView rc_lvProduct, rc_Group_product, rc_Category;
    Context context;

    FloatingActionButton btn_add;
    Group_Product_DAO groupProductDao;
    Category_DAO categoryDao;
    Product_DAO productDao;

    ArrayList<Group_Product> list_group_product;
    ArrayList<Category_Model> list_category;
    ArrayList<Product_Model> list_product;

    Adapter_Group adapterGroup;
    Adapter_Category adapterCategory;
    Adapter_Product adapterProduct;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container, false );
        context = getContext();

        list_product = new ArrayList<>();

        groupProductDao = new Group_Product_DAO(context);
        categoryDao = new Category_DAO(context);
        productDao = new Product_DAO(context);

        rc_lvProduct = view.findViewById(R.id.rc_product);
        rc_Group_product = view.findViewById(R.id.rc_group_product);
        rc_Category = view.findViewById(R.id.rc_category);
        btn_add = view.findViewById(R.id.btn_add);

        LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rc_lvProduct.setLayoutManager(layoutManager);

        LayoutManager layoutManager_rc_group_product = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        LayoutManager layoutManager_rc_category = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rc_Group_product.setLayoutManager(layoutManager_rc_group_product);
        rc_Category.setLayoutManager(layoutManager_rc_category);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.item_spacing);
        HorizontalSpaceItemDecoration itemDecoration = new HorizontalSpaceItemDecoration(spacingInPixels);

        list_group_product = groupProductDao.getList_Group_Product();

        adapterGroup = new Adapter_Group(context, list_group_product, this);
        rc_Group_product.setAdapter(adapterGroup);

        rc_Group_product.addItemDecoration(itemDecoration);
        rc_Category.addItemDecoration(itemDecoration);

        // set vị trí đầu tiên
        list_category = new ArrayList<>();

        if (groupProductDao.getId_Firt() != null){
            list_category = categoryDao.getLit_Category(groupProductDao.getId_Firt());
            if (list_category != null){
                adapterCategory = new Adapter_Category(context, list_category, this);
                rc_Category.setAdapter(adapterCategory);
            }
        }

        click_add();

        setDataList_Product(String.valueOf(list_category.get(0).getId()));
        return view;
    }


    @Override
    public void Click_Group(Group_Product groupProduct) {

        list_category.clear();
        list_category = categoryDao.getLit_Category(groupProduct.getId());

        adapterCategory = new Adapter_Category(context, list_category, this);
        rc_Category.setAdapter(adapterCategory);
    }

    public void click_add(){
        btn_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Activity_Add_Product.class);
                add_product.launch(intent);
            }
        });
    }

    ActivityResultLauncher<Intent> add_product = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            list_product.clear();
            list_product.addAll(productDao.getlist_product_one_image());
            adapterProduct.notifyDataSetChanged();
        }
    });

    public void setDataList_Product(String id_Category){
        list_product = productDao.getlist_product_one_image_from_category(id_Category);
        if (list_product != null){
            adapterProduct = new Adapter_Product(list_product, context);
            rc_lvProduct.setAdapter(adapterProduct);
        }
    }

    @Override
    public void click_category(Category_Model categoryModel) {
        setDataList_Product(String.valueOf(categoryModel.getId()));
    }
}
