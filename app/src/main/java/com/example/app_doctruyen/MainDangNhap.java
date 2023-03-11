package com.example.app_doctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.app_doctruyen.database.databasedoctruyen;

public class MainDangNhap extends AppCompatActivity {

    //khai báo biến
    EditText edtTaiKhoan,edtMatKhau;
    Button btnDangNhap,btnDangKy;

    //tạo đối tượng
    databasedoctruyen databasedoctruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_nhap);
        AnhXa();
        //dối tượng database
        databasedoctruyen = new databasedoctruyen(this);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tạo intent để chuyển đổi giữa các màn hình
                Intent intent = new Intent(MainDangNhap.this,MainDangKy.class);
                startActivity(intent);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gán cho các biến là giá trị nhập từ edittext
                String tentaikhoan=edtTaiKhoan.getText().toString();
                String matkhau = edtMatKhau.getText().toString();

                //sử dụng con trỏ chuột để lấy dữ liệu,gọi tới getdata() để lấy tất cả tải khoản ở database
                Cursor cursor= databasedoctruyen.getData();

                //thực hiện vòng lặp để lấy dữ liệu từ cursor với moveTonext() di chuyển tiếp
                while(cursor.moveToNext()){
                    //lấy dữ liệu và gán vào biến,dữ liệu tài khoản ở 1 và mật khẩu ở ô 2,ô 0 là idtaikhoan,ô 3 là email,ô 4 là phanquyen
                    String datatentaikhoan = cursor.getString(1);
                    String datamatkhau=cursor.getString(2);

                    //Nếu tài khoản và mật khẩu nhập vào từ bàn phím khớp với dữ liệu ở database
                    if(datatentaikhoan.equals(tentaikhoan) && datamatkhau.equals(matkhau)){
                        //lấy dữ liệu phanquyen và id
                        int phanquyen = cursor.getInt(4);
                        int idd=cursor.getInt(0);
                        String email=cursor.getString(3);
                        String tentk=cursor.getString(1);

                        //chuyển qua màn MainActivity
                        Intent intent = new Intent(MainDangNhap.this,MainActivity.class);

                        //gửi dữ liệu qua màn Activity
                        intent.putExtra("phanquyen",phanquyen);
                        intent.putExtra("idd",idd);
                        intent.putExtra("email",email);
                        intent.putExtra("tentaikhoan",tentk);

                        startActivity(intent);
                    }
                }
                //thực hiện trả cursor về đầu
                cursor.moveToFirst();
                //đóng khi k đúng
                cursor.close();
            }
        });
    }

    private void AnhXa() {
        edtTaiKhoan=findViewById(R.id.taikhoan);
        edtMatKhau=findViewById(R.id.matkhau);
        btnDangNhap=findViewById(R.id.dangnhap);
        btnDangKy=findViewById(R.id.dangky);

    }
}