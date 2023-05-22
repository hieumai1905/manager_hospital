package com.example.datlichkhambenh.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
@Entity
public class BenhNhan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mabn;

    private String tenbn;

    private Date ngaysinh;

    private int gioitinh;

    public BenhNhan() {
    }

    public BenhNhan(int mabn, String tenbn, Date ngaysinh, int gioitinh) {
        this.mabn = mabn;
        this.tenbn = tenbn;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
    }

    public int getMabn() {
        return mabn;
    }

    public void setMabn(int mabn) {
        this.mabn = mabn;
    }

    public String getTenbn() {
        return tenbn;
    }

    public void setTenbn(String tenbn) {
        this.tenbn = tenbn;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }
}