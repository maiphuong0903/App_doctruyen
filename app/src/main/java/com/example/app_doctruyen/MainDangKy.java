package com.example.app_doctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_doctruyen.database.databasedoctruyen;
import com.example.app_doctruyen.model.TaiKhoan;

public class MainDangKy extends AppCompatActivity {
    EditText edtDKTaiKhoan,edtDKMatKhau,edtDKEmail;
    Button btnDKDangKy;
    TextView txtDangNhap;
    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_ky);

        databasedoctruyen = new databasedoctruyen(this);

        AnhXa();
        btnDKDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taikhoan = edtDKTaiKhoan.getText().toString();
                String matkhau = edtDKTaiKhoan.getText().toString();
                String email = edtDKEmail.getText().toString();
                
                TaiKhoan taikhoan1 = CreateTaiKhoan();
                if(taikhoan.equals("") || matkhau.equals("") || email.equals("")){
                    Log.e("Thông báo : ","Chưa nhập đầy đủ thông tin");
                }
                //nếu đầy đủ thông tin thì add tài khoản vào database
                else{
                    databasedoctruyen.AddTaiKhoan(taikhoan1);
                    Toast.makeText(MainDangKy.this,"Đăng ký thành công", Toast.LENGTH_LONG).show();
                }
            }
        });
        //trở về đăng nhập
        txtDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    //phương thức tạo tk
    private TaiKhoan CreateTaiKhoan(){
        String taikhoan = edtDKTaiKhoan.getText().toString();
        String matkhau = edtDKTaiKhoan.getText().toString();
        String email = edtDKEmail.getText().toString();
        int phanquyen = 1;
        TaiKhoan tk = new TaiKhoan(taikhoan,matkhau,email,phanquyen);
        return tk;
    }

    private void AnhXa() {
        edtDKTaiKhoan = findViewById(R.id.dktaikhoan);
        edtDKMatKhau = findViewById(R.id.dkmatkhau);
        edtDKEmail = findViewById(R.id.dkemail);
        btnDKDangKy = findViewById(R.id.dkdangky);
        txtDangNhap = findViewById(R.id.dkdangnhap);
    }
}