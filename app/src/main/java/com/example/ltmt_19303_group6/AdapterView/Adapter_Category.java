package com.example.ltmt_19303_group6.AdapterView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltmt_19303_group6.Model.Category_Model;
import com.example.ltmt_19303_group6.Model.Group_Product;
import com.example.ltmt_19303_group6.R;

import java.util.ArrayList;

public class Adapter_Category extends RecyclerView.Adapter<Adapter_Category.Adapter_GroupViewHoDel>{
    Context context;
    ArrayList<Category_Model> list;
    private int selectedPosition = RecyclerView.NO_POSITION;
    ListenClickItem listenClickItem;

    public interface ListenClickItem{
        void click_category(Category_Model categoryModel);
    }
    public Adapter_Category(Context context, ArrayList<Category_Model> list, ListenClickItem listenClickItem) {
        this.context = context;
        this.list = list;
        this.listenClickItem = listenClickItem;
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

        if (selectedPosition == position){
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.blueIndex));
            holder.tv_name.setTextColor(ContextCompat.getColor(context, R.color.white));
        }else {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white));
            holder.tv_name.setTextColor(ContextCompat.getColor(context, R.color.blueIndex));
        }

        holder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenClickItem.click_category(category_Model);
                int previousPosition = selectedPosition;
                selectedPosition = holder.getAdapterPosition();

                // Cập nhật lại các item
                notifyItemChanged(previousPosition);
                notifyItemChanged(selectedPosition);
            }
        });
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
