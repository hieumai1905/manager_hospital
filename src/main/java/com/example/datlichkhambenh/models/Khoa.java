package com.example.datlichkhambenh.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Khoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maK;

    private String tenK;

    public Khoa() {
    }

    public Khoa(String tenK) {
        this.tenK = tenK;
    }

    public Khoa(int maK, String tenK) {
        this.maK = maK;
        this.tenK = tenK;
    }

    public int getMaK() {
        return maK;
    }

    public void setMaK(int maK) {
        this.maK = maK;
    }

    public String getTenK() {
        return tenK;
    }

    public void setTenK(String tenK) {
        this.tenK = tenK;
    }
}