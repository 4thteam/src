package com.dao;

import com.entity.User;

/**
 * Created by 余文彪 on 2017/7/13.
 */
public interface UserDAO {
    public User findUserByUseridAndUser_passwod(String user_id,String user_password);
}
