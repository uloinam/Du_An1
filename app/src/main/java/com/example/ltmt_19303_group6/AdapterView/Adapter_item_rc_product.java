package com.example.ltmt_19303_group6.AdapterView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltmt_19303_group6.Model.Image_product_Model;
import com.example.ltmt_19303_group6.R;

import java.util.ArrayList;

public class Adapter_item_rc_product extends RecyclerView.Adapter<Adapter_item_rc_product.Adapter_ViewHodel>{
    ArrayList<Image_product_Model> list;
    Context context;
    ListenClickItem_Image listenClickItemImage;

    public interface ListenClickItem_Image{
        void setImage(Image_product_Model productModel);
    }
    public Adapter_item_rc_product(ArrayList<Image_product_Model> list, Context context, ListenClickItem_Image listenClickItemImage) {
        this.list = list;
        this.context = context;
        this.listenClickItemImage = listenClickItemImage;
    }

    @NonNull
    @Override
    public Adapter_ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rc_product_detail, parent, false);

        return new Adapter_ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_ViewHodel holder, int position) {
        Image_product_Model imageProductModel = list.get(0);

        if (imageProductModel != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageProductModel.getImage(), 0, imageProductModel.getImage().length);
            holder.image_product.setImageBitmap(bitmap);
        }
        holder.image_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenClickItemImage.setImage(imageProductModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Adapter_ViewHodel extends RecyclerView.ViewHolder{
    ImageView image_product;
        public Adapter_ViewHodel(@NonNull View itemView) {
            super(itemView);

            image_product = itemView.findViewById(R.id.image_product);
        }
    }
}
