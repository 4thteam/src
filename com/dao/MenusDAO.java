package com.dao;

import com.entity.Menus;

import java.util.List;

public interface MenusDAO {
    public List<Menus> findMenusById(String id);
}
