package com.example.ltmt_19303_group6.AdapterView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ltmt_19303_group6.Model.Group_Product;
import com.example.ltmt_19303_group6.R;

import java.util.List;

public class Adapter_Group_Product extends ArrayAdapter<Group_Product> {
    public Adapter_Group_Product(@NonNull Context context, int resource, @NonNull List<Group_Product> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_one_group_product, parent, false);
        TextView tv_name = convertView.findViewById(R.id.tv_Name);

        Group_Product groupProduct = this.getItem(position);

        if (groupProduct != null){
            tv_name.setText(groupProduct.getName());
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_one_group_product, parent, false);
        TextView tv_name = convertView.findViewById(R.id.tv_Name);

        Group_Product groupProduct = this.getItem(position);

        if (groupProduct != null){
            tv_name.setText(groupProduct.getName());
        }

        return convertView;
    }
}
