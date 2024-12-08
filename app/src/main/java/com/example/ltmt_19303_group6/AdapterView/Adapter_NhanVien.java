package com.example.ltmt_19303_group6.AdapterView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltmt_19303_group6.Activity.Activity_Empolyee_Detail;
import com.example.ltmt_19303_group6.Model.Profile_Model;
import com.example.ltmt_19303_group6.Model.User_Model;
import com.example.ltmt_19303_group6.R;

import java.util.ArrayList;

public class Adapter_NhanVien extends RecyclerView.Adapter<Adapter_NhanVien.NhanVienViewHoDel> {
    ArrayList<Profile_Model> list;
    Context context;
    String status = "Đã nghỉ";

    public Adapter_NhanVien(ArrayList<Profile_Model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public NhanVienViewHoDel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nhanvien, parent, false);

        return new NhanVienViewHoDel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NhanVienViewHoDel holder, int position) {
        Profile_Model profile_Model = list.get(position);

        holder.tv_name.setText("Tên: "+profile_Model.getName_Empolyee());
        holder.tv_Age.setText("Tuổi: "+profile_Model.getAge());
        holder.tv_Email.setText("Email: "+profile_Model.getEmail_Empolyee());
        holder.tv_Phone_Number.setText("Số điện thoại: "+profile_Model.getPhone_Empolyee());

        holder.tv_Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Activity_Empolyee_Detail.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id_profile", profile_Model.getId_Profile());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        try{
            if (profile_Model.getAvatar() != null && profile_Model.getAvatar().length > 0) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(profile_Model.getAvatar(), 0, profile_Model.getAvatar().length);
                holder.image_avatar.setImageBitmap(bitmap);
            }
        }catch (Exception e){
            Log.d("Adapter_NhanVien", "Failed to decode avatar: " + e.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NhanVienViewHoDel extends RecyclerView.ViewHolder{
        TextView tv_name, tv_Age, tv_Email, tv_Phone_Number, tv_Status, tv_Details;
        ImageView image_avatar;

        public NhanVienViewHoDel(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.employeeName);
            tv_Age = itemView.findViewById(R.id.employeeAge);
            tv_Email = itemView.findViewById(R.id.employeeEmail);
            tv_Phone_Number = itemView.findViewById(R.id.employeePhone);
            tv_Status = itemView.findViewById(R.id.employeeStatus);
            tv_Details = itemView.findViewById(R.id.btn_employeeDetails);


            image_avatar = itemView.findViewById(R.id.employeeImage);
        }
    }
}
