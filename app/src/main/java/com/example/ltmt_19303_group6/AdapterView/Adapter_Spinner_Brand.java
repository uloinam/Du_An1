package com.example.ltmt_19303_group6.AdapterView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ltmt_19303_group6.Model.Brand_Model;
import com.example.ltmt_19303_group6.R;

import java.util.List;

public class Adapter_Spinner_Brand extends ArrayAdapter<Brand_Model> {
    public Adapter_Spinner_Brand(@NonNull Context context, int resource, @NonNull List<Brand_Model> objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_brand, parent, false);
        ImageView image_Brand = convertView.findViewById(R.id.image_brand);
        TextView tv_name = convertView.findViewById(R.id.tv_Name);

        Brand_Model brandModel = this.getItem(position);

        if (brandModel != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(brandModel.getImage(), 0, brandModel.getImage().length);

            image_Brand.setImageBitmap(bitmap);

            tv_name.setText("Hãng: "+brandModel.getName());
        }


        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_brand, parent, false);
        ImageView image_Brand = convertView.findViewById(R.id.image_brand);
        TextView tv_name = convertView.findViewById(R.id.tv_Name);

        Brand_Model brandModel = this.getItem(position);

        Bitmap bitmap = BitmapFactory.decodeByteArray(brandModel.getImage(), 0, brandModel.getImage().length);

        image_Brand.setImageBitmap(bitmap);

        tv_name.setText("Hãng: "+brandModel.getName());

        return convertView;
    }
}
