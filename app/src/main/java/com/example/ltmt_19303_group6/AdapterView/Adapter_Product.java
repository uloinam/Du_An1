package com.example.ltmt_19303_group6.AdapterView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltmt_19303_group6.Model.Product_Model;
import com.example.ltmt_19303_group6.R;

import java.util.ArrayList;

public class Adapter_Product extends RecyclerView.Adapter<Adapter_Product.Product_ViewHodel>{
    ArrayList<Product_Model> list;
    Context context;

    public Adapter_Product(ArrayList<Product_Model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Product_ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);

        return new Product_ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Product_ViewHodel holder, int position) {

        Product_Model productModel = list.get(position);
        holder.tv_quantity.setText("Kho: "+productModel.getQuantity_Product());
        holder.tv_name.setText(""+productModel.getName());
        holder.tv_price.setText(""+productModel.getPrice_product());

        Bitmap bitmap = BitmapFactory.decodeByteArray(productModel.getImage(), 0, productModel.getImage().length);

        holder.image_product.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Product_ViewHodel extends RecyclerView.ViewHolder{
        ImageView image_product;
        TextView tv_name, tv_price, tv_quantity;
        public Product_ViewHodel(@NonNull View itemView) {
            super(itemView);
            image_product = itemView.findViewById(R.id.image_product);
            tv_name = itemView.findViewById(R.id.tv_Name);
            tv_price = itemView.findViewById(R.id.tv_Price_Product);
            tv_quantity = itemView.findViewById(R.id.tv_quantity);

        }

    }
}
