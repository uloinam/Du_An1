package com.example.ltmt_19303_group6.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ltmt_19303_group6.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Profile extends Fragment {
    // Khai báo các Views
    private EditText etName, etPhone, etAddress;
    private TextView tvUserName, tvUserPhone, tvUserAddress;
    private CheckBox cbTerms;
    private Button btnSave;

    // Khai báo SharedPreferences
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String PREFS_NAME = "UserInfoPrefs";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_ADDRESS = "address";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Ánh xạ các Views
        etName = view.findViewById(R.id.etName);
        etPhone = view.findViewById(R.id.etPhone);
        etAddress = view.findViewById(R.id.etAddress);
        tvUserName = view.findViewById(R.id.tvUserName);
        tvUserPhone = view.findViewById(R.id.tvUserPhone);
        tvUserAddress = view.findViewById(R.id.tvUserAddress);
        cbTerms = view.findViewById(R.id.cbTerms);
        btnSave = view.findViewById(R.id.btnSave);

        // Khởi tạo SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, requireContext().MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Lấy dữ liệu từ SharedPreferences khi mở lại Fragment
        loadUserInfo();

        // Khi người dùng nhấn nút "Lưu"
        btnSave.setOnClickListener(v -> saveUserInfo());

        return view;
    }

    // Hàm lưu thông tin người dùng
    private void saveUserInfo() {
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        String address = etAddress.getText().toString();

        if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
            return;
        }

        editor.putString(KEY_NAME, name);
        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_ADDRESS, address);
        editor.apply();

        tvUserName.setText(name);
        tvUserPhone.setText(phone);
        tvUserAddress.setText(address);

        if (cbTerms.isChecked()) {
            Toast.makeText(getActivity(), "Thông tin đã lưu thành công.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Vui lòng đồng ý với điều khoản.", Toast.LENGTH_SHORT).show();
        }

        etName.setText("");
        etPhone.setText("");
        etAddress.setText("");
        cbTerms.setChecked(false);
    }

    private void loadUserInfo() {
        String name = sharedPreferences.getString(KEY_NAME, "");
        String phone = sharedPreferences.getString(KEY_PHONE, "");
        String address = sharedPreferences.getString(KEY_ADDRESS, "");

        if (!name.isEmpty()) {
            tvUserName.setText(name);
        }
        if (!phone.isEmpty()) {
            tvUserPhone.setText(phone);
        }
        if (!address.isEmpty()) {
            tvUserAddress.setText(address);
        }
    }
}
