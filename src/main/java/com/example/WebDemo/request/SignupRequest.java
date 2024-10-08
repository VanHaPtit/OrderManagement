package com.example.WebDemo.request;

import jakarta.validation.constraints.*;

import java.util.Set;

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String userName;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String passWord;

    public @NotBlank @Size(min = 3, max = 20) String getUserName() {
        return userName;
    }

    public void setUserName(@NotBlank @Size(min = 3, max = 20) String userName) {
        this.userName = userName;
    }

    public @NotBlank @Size(min = 6, max = 40) String getPassWord() {
        return passWord;
    }

    public void setPassWord(@NotBlank @Size(min = 6, max = 40) String passWord) {
        this.passWord = passWord;
    }

    //    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}