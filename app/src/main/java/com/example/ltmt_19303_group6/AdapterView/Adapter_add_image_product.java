package com.example.ltmt_19303_group6.AdapterView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltmt_19303_group6.Model.Image_product_Model;
import com.example.ltmt_19303_group6.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class Adapter_add_image_product extends RecyclerView.Adapter<Adapter_add_image_product.Adapter_ViewHodel>{
    ArrayList<Uri> list;
    Context context;
    GetArrayByte getArrayByte;
    public interface GetArrayByte{
        void getBitmapFavtory(byte[] list_byte_Image);
    }


    public Adapter_add_image_product(ArrayList<Uri> list, Context context, GetArrayByte getArrayByte) {
        this.list = list;
        this.context = context;
        this.getArrayByte = getArrayByte;
    }

    @NonNull
    @Override
    public Adapter_ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image_add_product, parent, false);

        return new Adapter_ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_ViewHodel holder, int position) {
        Uri image_Uri = list.get(position);

        try {
            InputStream is = context.getContentResolver().openInputStream(image_Uri);

            Bitmap bitmap = BitmapFactory.decodeStream(is);

            holder.image_product.setImageBitmap(bitmap);

            byte[] byte_image = coverImage_to_Byte(holder.image_product);
            if (byte_image != null){

                getArrayByte.getBitmapFavtory(byte_image);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
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

    public byte[] coverImage_to_Byte(ImageView imageView){
        byte[] imageInByte = null;
        try {
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            imageInByte = baos.toByteArray();
        }catch (Exception e){
            Log.d("zzzzzz", "coverImage_to_Byte: Lỗi vclll");
        }
        return imageInByte;
    }
}
