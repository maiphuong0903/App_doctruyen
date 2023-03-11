package com.example.app_doctruyen.model;

public class Truyen {
    private  int ID;

    public int getID() {
        return ID;
    }

    public Truyen(String tenTruyen, String noiDung, String anh, int ID_TK) {
        TenTruyen = tenTruyen;
        NoiDung = noiDung;
        Anh = anh;
        this.ID_TK = ID_TK;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Truyen(int ID, String tenTruyen, String noiDung, String anh, int ID_TK) {
        this.ID = ID;
        TenTruyen = tenTruyen;
        NoiDung = noiDung;
        Anh = anh;
        this.ID_TK = ID_TK;
    }

    public String getTenTruyen() {
        return TenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        TenTruyen = tenTruyen;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }

    public int getID_TK() {
        return ID_TK;
    }

    public void setID_TK(int ID_TK) {
        this.ID_TK = ID_TK;
    }

    private String TenTruyen;
    private  String NoiDung;
    private String Anh;
    private int ID_TK;
}
