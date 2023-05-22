package com.example.datlichkhambenh.DAO.lichkham;

import com.example.datlichkhambenh.DAO.IGeneralDAO;
import com.example.datlichkhambenh.models.LichKham;

import java.util.List;

public interface ILichKhamDAO extends IGeneralDAO<LichKham> {
    List<LichKham> layDanhSachLichKhamCuaBacSi(int maBS);
    List<LichKham> layDanhSachLichKhamCuaBenhNhan(int maBN);
    List<LichKham> layDanhSachLichKhamTrongKhoang(String timeStart, String timeEnd);
}
