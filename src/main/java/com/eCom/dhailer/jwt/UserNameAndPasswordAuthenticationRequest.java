package com.eCom.dhailer.jwt;

public class UserNameAndPasswordAuthenticationRequest {

    private String userEmail;
    private String password;

    public UserNameAndPasswordAuthenticationRequest() {
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String username) {
        this.userEmail = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
