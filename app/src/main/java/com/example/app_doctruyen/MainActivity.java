package com.example.app_doctruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.example.app_doctruyen.adapter.adapterTruyen;
import com.example.app_doctruyen.database.databasedoctruyen;
import com.example.app_doctruyen.model.Truyen;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listView,listViewNew,listViewThongTin;
    DrawerLayout drawerLayout;

    String email;
    String tentaikhoan;

    ArrayList<Truyen> TruyenArraylist;
    adapterTruyen adapterTruyen;
    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databasedoctruyen =new databasedoctruyen(this);

        //nhận dữ liệu ở mà đăng nhập gửi
        Intent intentpq=getIntent();
        int i = intentpq.getIntExtra("phanq",0);
        int idd = intentpq.getIntExtra("idd",0);
        email = intentpq.getStringExtra("email");
        tentaikhoan = intentpq.getStringExtra("tentaikhoan");
        Anhxa();
        ActionBar();
        ActionViewFlipper();

        listViewNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent  intent = new Intent(MainActivity.this,ManNoiDung.class);
                String tent = TruyenArraylist.get(position).getTenTruyen();
                String noidung = TruyenArraylist.get(position).getNoiDung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidung);
                startActivity(intent);
            }
        });
    }

    //Thanh actionbar với toolbar
    private void ActionBar(){
        //Hàm hỗ trợ toolbar
        setSupportActionBar(toolbar);
        //set nút cho actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //tạo icon cho toolbar
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    //phương thức cho chạy slide với Viewfilipper
    private void ActionViewFlipper() {

        //mảng chứa tấm ảnh cho slide
        ArrayList<String> mangquangcao = new ArrayList<>();
        //add ảnh vào slide
        mangquangcao.add("https://toplist.vn/images/800px/rua-va-tho-230179.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/deo-chuong-cho-meo-230180.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/cu-cai-trang-230181.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/de-den-va-de-trang-230182.jpg");

        //Thực hiện vòng lặp gán ảnh vào imageview,rồi từ imgview lên app
        for(int i=0;i<mangquangcao.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            //sử dụng hàm thư viện piscasso để load ảnh lên imageView
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            //Phương thức chỉnh tấm hình vừa khung slide
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            //Thêm ảnh từ imageview vào ViewFlipper
            viewFlipper.addView(imageView);
        }
        //Thiết lập tự động chạy cho ViewFlipper chạy trong 3s
        viewFlipper.setFlipInterval(3000);
        //run auto ViewFlipper
        viewFlipper.setAutoStart(true);

        //gọi amitation cho vào và ra
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);

        //gọi Animation vào viewFlipper
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);
    }

    private void Anhxa() {
        toolbar=findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        listViewNew = findViewById(R.id.listviewNew);
        listView = findViewById(R.id.listviewmanhinhchinh);
        listViewThongTin = findViewById(R.id.listviewthongtin);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerlayout);

        TruyenArraylist = new ArrayList<>();
        Cursor cursor1 = databasedoctruyen.getDatal();
        while (cursor1.moveToNext()){
            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            TruyenArraylist.add(new Truyen(id,tentruyen,noidung,anh,id_tk));
            adapterTruyen = new adapterTruyen(getApplicationContext(),TruyenArraylist);
            listViewNew.setAdapter(adapterTruyen);
        }
        cursor1.moveToFirst();
        cursor1.close();
    }

    //nạp một menu tìm kiếm vào actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        //nếu click chuột vào icon tìm kiếm sẽ chuyển quan màn hình tìm kiếm
        switch (item.getItemId()){
            case R.id.menu1:
                Intent intent = new Intent(MainActivity.this,ManTimKiem.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}