package com.dao;

import com.entity.User;

public interface UserDAO {
    public User findUserByUseridAndUser_passwod(String user_id, String user_password);
}
