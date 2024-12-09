package com.example.ltmt_19303_group6.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ltmt_19303_group6.DAO.Customer_DAO;
import com.example.ltmt_19303_group6.Model.Customer_Model;
import com.example.ltmt_19303_group6.R;

public class Activity_Add_Customer extends AppCompatActivity {

    private EditText nameEditText, ageEditText, addressEditText, phoneEditText;
    private Button addCustomerButton;
    Customer_DAO customerDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_customer);

        getUi();

        eventClickAdd();
    }

    public void getUi(){
        nameEditText = findViewById(R.id.name_add_Customer);
        ageEditText = findViewById(R.id.age_add_Customer);
        addressEditText = findViewById(R.id.address_add_Customer);
        phoneEditText = findViewById(R.id.phone_add_Customer);
        addCustomerButton = findViewById(R.id.btn_add_customer);

        customerDao = new Customer_DAO(Activity_Add_Customer.this);
    }

    public void eventClickAdd(){
        addCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String age = ageEditText.getText().toString();
                String address = addressEditText.getText().toString();
                String phone = phoneEditText.getText().toString();

                if (name.isEmpty() || age.isEmpty() || address.isEmpty() || phone.isEmpty()){
                    if (name.isEmpty()) {
                        nameEditText.setError("Tên không được để trống");

                    }

                    if (age.isEmpty()) {
                        ageEditText.setError("Tuổi không được để trống");

                    }

                    try {
                        int ageValue = Integer.parseInt(age);
                        if (ageValue <= 0) {
                            ageEditText.setError("Tuổi phải là số dương");

                        }
                    } catch (NumberFormatException e) {
                        ageEditText.setError("Tuổi phải là số hợp lệ");

                    }

                    if (address.isEmpty()) {
                        addressEditText.setError("Địa chỉ không được để trống");

                    }

                    if (phone.isEmpty()) {
                        phoneEditText.setError("Số điện thoại không được để trống");

                    }


                } else if (!phone.matches("\\d{10}")){
                    phoneEditText.setError("Số điện thoại không hợp lệ");
                }else{
                    boolean  reslut = customerDao.add_Customer(new Customer_Model(null, name, Integer.parseInt(age), address, phone));

                    if (reslut){
                        Toast.makeText(Activity_Add_Customer.this, "Thêm khách hàng thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}