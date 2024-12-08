package com.example.ltmt_19303_group6.AdapterView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltmt_19303_group6.Model.Group_Product;
import com.example.ltmt_19303_group6.R;

import java.util.ArrayList;

public class Adapter_Group extends RecyclerView.Adapter<Adapter_Group.Adapter_GroupViewHoDel>{
    Context context;
    ArrayList<Group_Product> list;
    private int selectedPosition = RecyclerView.NO_POSITION;

    private  ListenClickItem ListenClickItem;
    public interface ListenClickItem{
        void Click_Group(Group_Product groupProduct);
    }
    public Adapter_Group(Context context, ArrayList<Group_Product> list, ListenClickItem listenClickItem) {
        this.context = context;
        this.list = list;
        this.ListenClickItem = listenClickItem;
    }

    @NonNull
    @Override
    public Adapter_GroupViewHoDel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_group_product, parent, false);

        return new Adapter_GroupViewHoDel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_GroupViewHoDel holder, int position) {
        Group_Product groupProduct = list.get(position);
        holder.tv_name.setText(groupProduct.getName());


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
                ListenClickItem.Click_Group(groupProduct);
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

    public class Adapter_GroupViewHoDel extends RecyclerView.ViewHolder{
        TextView tv_name;
        CardView cardView;
        public Adapter_GroupViewHoDel(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_Name);
            cardView = itemView.findViewById(R.id.backgroud_item);
        }
    }

}
