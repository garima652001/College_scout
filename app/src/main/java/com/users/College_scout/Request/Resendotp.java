package com.users.College_scout.Request;

public class Resendotp {
    String email;

    public Resendotp(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
