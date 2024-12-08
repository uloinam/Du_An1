package com.example.ltmt_19303_group6.Fragment;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltmt_19303_group6.Activity.Activity_Add_Customer;
import com.example.ltmt_19303_group6.AdapterView.Adapter_Customer;
import com.example.ltmt_19303_group6.DAO.Customer_DAO;
import com.example.ltmt_19303_group6.Model.Customer_Model;
import com.example.ltmt_19303_group6.R;
import com.example.ltmt_19303_group6.SpaceItemDecoration.SpaceItemDecoration;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment_Customer extends Fragment {
    FloatingActionButton btn_add;
    RecyclerView rc_customer;
    Adapter_Customer adapterCustomer;
    ArrayList<Customer_Model> list;
    Customer_DAO customerDao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer,container, false );
        btn_add = view.findViewById(R.id.btn_add);
        rc_customer = view.findViewById(R.id.rc_customer);
        list = new ArrayList<>();
        customerDao = new Customer_DAO(getContext());

        rc_customer.addItemDecoration(new SpaceItemDecoration(10));

        list = customerDao.getList_Customer();

        if (list != null){
            adapterCustomer = new Adapter_Customer(list, getContext());
            rc_customer.setAdapter(adapterCustomer);
        }

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_Add_Customer.class);
                add_customer.launch(intent);
            }
        });


        return view;
    }

    ActivityResultLauncher<Intent> add_customer = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    list.clear();
                    list = customerDao.getList_Customer();

                    adapterCustomer = new Adapter_Customer(list, getContext());
                    rc_customer.setAdapter(adapterCustomer);
                    adapterCustomer.notifyDataSetChanged();
                }
            });
}
