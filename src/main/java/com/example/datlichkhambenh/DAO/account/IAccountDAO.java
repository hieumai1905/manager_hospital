package com.example.datlichkhambenh.DAO.account;

import com.example.datlichkhambenh.models.Account;

public interface IAccountDAO {
    Account login(String username, String password);

    boolean register(String username, String password);
}
