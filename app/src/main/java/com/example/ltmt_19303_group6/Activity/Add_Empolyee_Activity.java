package com.example.ltmt_19303_group6.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ltmt_19303_group6.DAO.Profile_DAO;
import com.example.ltmt_19303_group6.DAO.User_DAO;
import com.example.ltmt_19303_group6.Model.Profile_Model;
import com.example.ltmt_19303_group6.Model.User_Model;
import com.example.ltmt_19303_group6.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Add_Empolyee_Activity extends AppCompatActivity {
    EditText edt_UserName, edt_Password, edt_Name, edt_Age, edt_PhoneNumber, edt_Email;
    Button btn_XacNhan;
    RadioButton rdo_DangLam, rdo_DaNghi;
    ImageView image_Avatar;
    User_DAO userDao;
    Integer status = 1;
    ImageView btn_back;
    Profile_DAO profileDao;
    private int REQUES_Choose_PHOTO = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_empolyee);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getUi();
        event_ClickImage_Avatar();
        event_Select_Checked_rdo();

        event_XacNhan();
    }

    private void event_Select_Checked_rdo() {
        rdo_DangLam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    status = 1;
                }
            }
        });
        rdo_DaNghi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    status = 0;
                }
            }
        });
    }


    public void getUi (){
        edt_UserName = findViewById(R.id.edt_Username);
        edt_Password = findViewById(R.id.edt_Password);
        edt_Name = findViewById(R.id.edt_Name);
        edt_Age = findViewById(R.id.edt_Age);
        edt_PhoneNumber = findViewById(R.id.edt_PhoneNumber);
        edt_Email = findViewById(R.id.edt_Email);
        btn_back = findViewById(R.id.btn_back);

        btn_XacNhan = findViewById(R.id.btn_xacNhan);
        rdo_DangLam = findViewById(R.id.rdo_dangLam);
        rdo_DaNghi = findViewById(R.id.rdo_daNghi);
        image_Avatar = findViewById(R.id.image_avatar);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        userDao = new User_DAO(Add_Empolyee_Activity.this);
        profileDao = new Profile_DAO(Add_Empolyee_Activity.this);
    }

    public void event_ClickImage_Avatar(){
        image_Avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission()){
                    open_Garelly();
                }
            }
        });
    }

    public void open_Garelly (){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUES_Choose_PHOTO);
    }

    public boolean checkPermission() {
        String[] permissions = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        };

        // Kiểm tra nếu bất kỳ quyền nào chưa được cấp
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                // Yêu cầu quyền nếu chưa được cấp
                ActivityCompat.requestPermissions(this, permissions, 1);
                return false;
            }
        }

        // Tất cả quyền đã được cấp
        return true;
    }


    private void event_XacNhan() {
        btn_XacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_Name = edt_UserName.getText().toString().trim();
                String pass_word = edt_Password.getText().toString().trim();
                String name = edt_Name.getText().toString().trim();
                String age = edt_Age.getText().toString().trim();
                String phone_number = edt_PhoneNumber.getText().toString().trim();
                String email = edt_Email.getText().toString().trim();

                if (user_Name.isEmpty() || pass_word.isEmpty() || name.isEmpty() || age.isEmpty() || phone_number.isEmpty() || email.isEmpty()){
                    if (user_Name.isEmpty()){
                        edt_UserName.setError("Điền đầy đủ thông tin");
                        edt_UserName.requestFocus();
                    }
                    if (pass_word.isEmpty()){
                        edt_Password.setError("Điền đầy đủ thông tin");
                        edt_Password.requestFocus();
                    }
                    if (name.isEmpty()){
                        edt_Name.setError("Điền đầy đủ thông tin");
                        edt_Name.requestFocus();
                    }
                    if (age.isEmpty()){
                        edt_Age.setError("Điền đầy đủ thông tin");
                        edt_Age.requestFocus();
                    }
                    if (phone_number.isEmpty()){
                        edt_PhoneNumber.setError("Điền đầy đủ thông tin");
                        edt_PhoneNumber.requestFocus();
                    }
                    if (email.isEmpty()){
                        edt_Email.setError("Điền đầy đủ thông tin");
                        edt_Email.requestFocus();
                    }
                }else if (pass_word.length() < 6){
                    edt_Password.setError("Mật khẩu yếu");
                }else if(!pass_word.matches("^.*[A-Z]{1}.*$")){
                    edt_Password.setError("Mật khẩu phải chứa 1 kí tự viết hoa");
                }else if(!pass_word.matches("^.*[#$@!%&*?]{1}.*$")){
                    edt_Password.setError("Mật khẩu phải chứa 1 kí tự đặc biệt");
                }else if(!phone_number.matches("^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$")){
                    edt_PhoneNumber.setError("Sai định dạng số điện thoại");
                } else if (!email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")) {
                    edt_Email.setError("Sai định dạng email");
                }
                else{
                    Integer Age = Integer.parseInt(age);

                    if (Age < 0 || Age > 70){
                        edt_Age.setText("Số tuổi không nhỏ hơn 0 hoặc lớn hơn 70");
                    }else {
                        User_Model userModel = userDao.get_OneUser(user_Name, pass_word);

                        if (userModel != null){
                            Toast.makeText(Add_Empolyee_Activity.this, "Tên người dùng đã được sử dụng vui lòng sử dụng tên khác", Toast.LENGTH_SHORT).show();
                        }else{

                            if (coverImage_to_Byte() == null){
                                Toast.makeText(Add_Empolyee_Activity.this, "Bạn chưa chọn ảnh", Toast.LENGTH_SHORT).show();
                            }else {
                                byte[] image_byte_avatar = coverImage_to_Byte();
                                Log.d("zzzzz", "onClick: "+image_byte_avatar);
                                boolean reslut = profileDao.addProfile(new Profile_Model(null, Age, name, email, phone_number, image_byte_avatar));
                                if (reslut){
                                    Integer index_last_Profile = profileDao.getProfile().size();
                                    boolean check = userDao.addEmpolyee(new User_Model(null, index_last_Profile, status, user_Name, pass_word, "NhanVien"));

                                    if (check){
                                        Toast.makeText(Add_Empolyee_Activity.this, "Đăng ký nhân viên thành công", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }
                            }

                        }
                    }
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                open_Garelly();
            } else {
                Toast.makeText(this, "Bạn chưa cấp quyền truy cập cho ứng dụng", Toast.LENGTH_SHORT).show();
                checkPermission();
            }
        }
    }

    ActivityResultLauncher<Intent> openGarellye_Launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getData() != null){
                        try {
                            if (result.getResultCode() == RESULT_OK) {
                                Intent data = result.getData();
                                Uri imageUri = data.getData();
                                try {
                                    InputStream inputStream = getContentResolver().openInputStream(imageUri);
                                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                    Log.d("zzzzzzz", "onActivityResult: "+bitmap);
                                    image_Avatar.setImageBitmap(bitmap);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }

                        }catch (Exception e){

                        }
                    }else{

                    }

                }
            });

    public byte[] coverImage_to_Byte(){
        byte[] imageInByte = null;
        try {
            Bitmap bitmap = ((BitmapDrawable) image_Avatar.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            imageInByte = baos.toByteArray();
        }catch (Exception e){
            Log.d("zzzzzz", "coverImage_to_Byte: Lỗi vclll");
        }
        return imageInByte;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == REQUES_Choose_PHOTO){
                Uri image_Uri = data.getData();
                try {
                    InputStream is = getContentResolver().openInputStream(image_Uri);

                    Bitmap bitmap = BitmapFactory.decodeStream(is);

                    image_Avatar.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}