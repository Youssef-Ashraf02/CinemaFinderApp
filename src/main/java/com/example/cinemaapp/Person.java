package com.example.cinemaapp;

public class Person
{
    private String username;
    private String gender;
    private String email;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Person()
    {

    }

    public Person(String username, String gender, String email, String password) {
        this.username = username;
        this.gender = gender;
        this.email = email;
        this.password = password;
    }
    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
