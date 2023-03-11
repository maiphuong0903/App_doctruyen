package com.example.app_doctruyen.model;

public class TaiKhoan {

    //tạo biến
    private  int mId;
    private String mTenTaiKhoan;
    private String mMatKhau;
    private String mEmail;
    private int mPhanQuyen;

    //hàm tạo
    public TaiKhoan(String mTenTaiKhoan, String mEmail) {
        this.mTenTaiKhoan = mTenTaiKhoan;
        this.mEmail = mEmail;
    }

    public TaiKhoan(String mTenTaiKhoan, String mMatKhau, String mEmail, int mPhanQuyen) {
        this.mTenTaiKhoan = mTenTaiKhoan;
        this.mMatKhau = mMatKhau;
        this.mEmail = mEmail;
        this.mPhanQuyen = mPhanQuyen;
    }


    //hàm get set
    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmTenTaiKhoan() {
        return mTenTaiKhoan;
    }

    public void setmTenTaiKhoan(String mTenTaiKhoan) {
        this.mTenTaiKhoan = mTenTaiKhoan;
    }

    public String getmMatKhau() {
        return mMatKhau;
    }

    public void setmMatKhau(String mMatKhau) {
        this.mMatKhau = mMatKhau;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public int getmPhanQuyen() {
        return mPhanQuyen;
    }

    public void setmPhanQuyen(int mPhanQuyen) {
        this.mPhanQuyen = mPhanQuyen;
    }
}
