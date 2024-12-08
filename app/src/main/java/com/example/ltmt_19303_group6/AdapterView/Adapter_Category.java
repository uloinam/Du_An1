package com.example.ltmt_19303_group6.AdapterView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltmt_19303_group6.Model.Category_Model;
import com.example.ltmt_19303_group6.Model.Group_Product;
import com.example.ltmt_19303_group6.R;

import java.util.ArrayList;

public class Adapter_Category extends RecyclerView.Adapter<Adapter_Category.Adapter_GroupViewHoDel>{
    Context context;
    ArrayList<Category_Model> list;

    public Adapter_Category(Context context, ArrayList<Category_Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Adapter_GroupViewHoDel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);

        return new Adapter_GroupViewHoDel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_GroupViewHoDel holder, int position) {
        Category_Model category_Model = list.get(position);
        holder.tv_name.setText(category_Model.getName_category());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class Adapter_GroupViewHoDel extends RecyclerView.ViewHolder{
        TextView tv_name;
        CardView cardView;
        public Adapter_GroupViewHoDel(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_Name);
            cardView = itemView.findViewById(R.id.backgroud_item);
        }
    }


}
