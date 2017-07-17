package com.entity;

import java.io.Serializable;

public class Menus{
    private String power_name;
    private String power_path;
    private String menu_tag;

    public String getMenu_tag() {
        return menu_tag;
    }

    public void setMenu_tag(String menu_tag) {
        this.menu_tag = menu_tag;
    }

    public String getPower_name() {
        return power_name;
    }

    public void setPower_name(String power_name) {
        this.power_name = power_name;
    }

    public String getPower_path() {
        return power_path;
    }

    public void setPower_path(String power_path) {
        this.power_path = power_path;
    }
}
