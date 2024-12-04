package com.example.ltmt_19303_group6.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltmt_19303_group6.R;

public class Fragment_Home extends Fragment {
    RecyclerView rc_lvProduct;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container, false );


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rc_lvProduct.setLayoutManager(layoutManager);

        return view;
    }
}
