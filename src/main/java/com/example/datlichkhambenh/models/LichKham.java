package com.example.datlichkhambenh.models;

import javax.persistence.*;
import java.sql.Date;
@Entity
public class LichKham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date ngaykham;

    private String noidung;

    @ManyToOne
    private BacSi bacSi;

    @ManyToOne
    private BenhNhan benhNhan;

    public LichKham() {
    }

    public LichKham(int id, Date ngaykham, String noidung, BacSi bacSi, BenhNhan benhNhan) {
        this.id = id;
        this.ngaykham = ngaykham;
        this.noidung = noidung;
        this.bacSi = bacSi;
        this.benhNhan = benhNhan;
    }
    public LichKham(Date ngaykham, String noidung, BacSi bacSi, BenhNhan benhNhan) {
        this.ngaykham = ngaykham;
        this.noidung = noidung;
        this.bacSi = bacSi;
        this.benhNhan = benhNhan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgaykham() {
        return ngaykham;
    }

    public void setNgaykham(Date ngaykham) {
        this.ngaykham = ngaykham;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public BacSi getBacSi() {
        return bacSi;
    }

    public void setBacSi(BacSi bacSi) {
        this.bacSi = bacSi;
    }

    public BenhNhan getBenhNhan() {
        return benhNhan;
    }

    public void setBenhNhan(BenhNhan benhNhan) {
        this.benhNhan = benhNhan;
    }
}