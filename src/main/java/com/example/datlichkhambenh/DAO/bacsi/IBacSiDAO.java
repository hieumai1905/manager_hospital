package com.example.datlichkhambenh.DAO.bacsi;

import com.example.datlichkhambenh.DAO.IGeneralDAO;
import com.example.datlichkhambenh.models.BacSi;

import java.util.List;

public interface IBacSiDAO extends IGeneralDAO<BacSi> {
    List<BacSi> layBacSiTheoKhoa(int maKhoa);
}
