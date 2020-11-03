package com.users.College_scout.Request;

public class DetailRequest {
    String email,name,ShopName,college;
    boolean inCollege;

    public DetailRequest(String email, String name, String ShopName, String college, boolean inCollege) {
        this.email = email;
        this.name = name;
        this.ShopName = ShopName;
        this.college = college;
        this.inCollege = inCollege;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String ShopName) {
        this.ShopName = ShopName;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public boolean isInCollege() {
        return inCollege;
    }

    public void setInCollege(boolean inCollege) {
        this.inCollege = inCollege;
    }
}
