package com.example.datlichkhambenh.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class BacSi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mabs;

    private String tenbs;

    private Date ngaysinh;

    @ManyToOne
    private Khoa khoa;

    public BacSi() {
    }


    public BacSi(int mabs, String tenbs, Date ngaysinh, Khoa khoa) {
        this.mabs = mabs;
        this.tenbs = tenbs;
        this.ngaysinh = ngaysinh;
        this.khoa = khoa;
    }

    public Khoa getKhoa() {
        return khoa;
    }

    public void setKhoa(Khoa khoa) {
        this.khoa = khoa;
    }

    public int getMabs() {
        return mabs;
    }

    public void setMabs(int mabs) {
        this.mabs = mabs;
    }

    public String getTenbs() {
        return tenbs;
    }

    public void setTenbs(String tenbs) {
        this.tenbs = tenbs;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }


}