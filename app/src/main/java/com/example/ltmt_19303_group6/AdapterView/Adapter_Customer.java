package com.example.ltmt_19303_group6.AdapterView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltmt_19303_group6.DAO.Customer_DAO;
import com.example.ltmt_19303_group6.Model.Customer_Model;
import com.example.ltmt_19303_group6.R;


import java.util.ArrayList;

public class Adapter_Customer extends RecyclerView.Adapter<Adapter_Customer.Adapter_ViewHodel>{
    ArrayList<Customer_Model> list;
    Context context;
    Customer_DAO customerDao;

     public Adapter_Customer(ArrayList<Customer_Model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter_ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_customer, parent, false);

        return new Adapter_ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_ViewHodel holder, int position) {
        customerDao = new Customer_DAO(context);
        Customer_Model customerModel = list.get(position);

        holder.idCustomer.setText(""+customerModel.getId_Customer());
        holder.nameCustomer.setText(""+customerModel.getName_Customer());
        holder.ageCustomer.setText(""+customerModel.getAge_Customer()); // Ép kiểu tuổi về chuỗi
        holder.addressCustomer.setText(""+customerModel.getAddress_Customer());
        holder.phoneNumberCustomer.setText(""+customerModel.getPhoneNumber_Customer());

        holder.line_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_update_customer, null, false);

                EditText nameEditCustomer = view.findViewById(R.id.name_edit_Customer);
                EditText ageEditCustomer = view.findViewById(R.id.age_edit_Customer);
                EditText addressEditCustomer = view.findViewById(R.id.address_edit_Customer);
                EditText phoneEditCustomer = view.findViewById(R.id.phone_edit_Customer);
                Button btnEditCustomer = view.findViewById(R.id.btn_edit_customer);

                builder.setView(view);
                Dialog dialog = builder.create();

                btnEditCustomer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = nameEditCustomer.getText().toString();
                        String age = ageEditCustomer.getText().toString();
                        String address = addressEditCustomer.getText().toString();
                        String phone = phoneEditCustomer.getText().toString();

                        // Xử lý dữ liệu (ví dụ: lưu trữ hoặc kiểm tra hợp lệ)
                        if (name.isEmpty() || age.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        } else {

                            boolean reslut = customerDao.update_Customer(new Customer_Model(customerModel.getId_Customer(), name, Integer.parseInt(age), address, phone));

                            if (reslut){
                                list.clear();
                                list.addAll(customerDao.getList_Customer());
                                notifyDataSetChanged();
                                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Adapter_ViewHodel extends RecyclerView.ViewHolder{
        TextView idCustomer;
        TextView nameCustomer;
        TextView ageCustomer;
        TextView addressCustomer;
        TextView phoneNumberCustomer;
        LinearLayout line_01;
        public Adapter_ViewHodel(@NonNull View itemView) {
            super(itemView);
            idCustomer = itemView.findViewById(R.id.ID_Customer);
            nameCustomer = itemView.findViewById(R.id.Name_Customer);
            ageCustomer = itemView.findViewById(R.id.Age_Customer);
            addressCustomer = itemView.findViewById(R.id.Address_Customer);
            phoneNumberCustomer = itemView.findViewById(R.id.phoneNumber_Customer);
            line_01 = itemView.findViewById(R.id.line_01);
        }
    }
}
