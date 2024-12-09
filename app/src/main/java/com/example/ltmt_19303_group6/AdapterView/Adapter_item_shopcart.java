package com.example.ltmt_19303_group6.AdapterView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltmt_19303_group6.DAO.Shop_Cart_DAO;
import com.example.ltmt_19303_group6.Model.Shop_Cart_Model;
import com.example.ltmt_19303_group6.R;

import java.util.ArrayList;

public class Adapter_item_shopcart extends RecyclerView.Adapter<Adapter_item_shopcart.Adapter_ViewHodel> {
    ArrayList<Shop_Cart_Model> list;
    Context context;
    Shop_Cart_DAO shopCartDao;

    public Adapter_item_shopcart(ArrayList<Shop_Cart_Model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter_ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop_cart, parent, false);
        shopCartDao = new Shop_Cart_DAO(context);
        return new Adapter_ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_ViewHodel holder, int position) {
        Shop_Cart_Model shopCartModel = list.get(position);

        holder.tvNameProduct.setText(""+shopCartModel.getName_Product());
        holder.tvPriceProduct.setText(""+shopCartModel.getPrice_product());
        holder.edtCountProduct.setText(""+shopCartModel.getQuantity());

        Bitmap bitmap = BitmapFactory.decodeByteArray(shopCartModel.getImage(), 0, shopCartModel.getImage().length);
        holder.imageProduct.setImageBitmap(bitmap);

        holder.btnAddCountProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.edtCountProduct.setText(""+(shopCartModel.getQuantity() + 1));
            }
        });
        holder.btnRemoveCountProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shopCartModel.getQuantity() > 1){
                    holder.edtCountProduct.setText(""+(shopCartModel.getQuantity() - 1));
                }
            }
        });
        holder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean reslut = shopCartDao.delete(shopCartModel.getIdProduct());
                if (reslut){
                    list.clear();
                    list.addAll(shopCartDao.getList_Product_In_ShopCart());
                    notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Adapter_ViewHodel extends RecyclerView.ViewHolder{
        ImageView imageProduct, btnRemoveCountProduct, btnAddCountProduct, btnCancel;
        TextView tvNameProduct, tvPriceProduct;
        EditText edtCountProduct;
        CardView cardView;
        public Adapter_ViewHodel(@NonNull View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.image_product);
            tvNameProduct = itemView.findViewById(R.id.tv_Name_Product);
            tvPriceProduct = itemView.findViewById(R.id.tv_Price_Product);
            btnRemoveCountProduct = itemView.findViewById(R.id.btn_remove_Count_Product);
            btnAddCountProduct = itemView.findViewById(R.id.btn_add_Count_Product);
            edtCountProduct = itemView.findViewById(R.id.id_edt_Count_Product);
            btnCancel = itemView.findViewById(R.id.btn_Cancel);
            cardView = itemView.findViewById(R.id.carview);
        }
    }
}
