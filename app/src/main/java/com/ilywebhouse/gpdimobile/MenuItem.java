package com.ilywebhouse.gpdimobile;

public class MenuItem {
    private String menuLabel;
    private String menuCode;
    private int menuIcon;

    public MenuItem(String menuLabel, String menuCode, int menuIcon) {
        this.menuLabel = menuLabel;
        this.menuCode = menuCode;
        this.menuIcon = menuIcon;
    }

    public String getMenuLabel() {
        return menuLabel;
    }

    public void setMenuLabel(String menuLabel) {
        this.menuLabel = menuLabel;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public int getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(int menuIcon) {
        this.menuIcon = menuIcon;
    }
}
