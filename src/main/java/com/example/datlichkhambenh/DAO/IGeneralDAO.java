package com.example.datlichkhambenh.DAO;

import java.util.List;

public interface IGeneralDAO<T> {
    List<T> findAll();

    void save(T t);

    T findById(int id);

    void delete(int id);

    void update(int id, T t);
}