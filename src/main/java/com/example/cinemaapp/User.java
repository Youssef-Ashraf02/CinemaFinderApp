package com.example.cinemaapp;

import java.sql.SQLException;

public class User extends Person
{
    public User()
    {

    }
    public User(String username, String gender, String email, String password)
    {
        super(username, gender, email, password);
    }
    public User(String username, String password) throws SQLException, ClassNotFoundException {
        super(username,password);
        /*Database jdbc = Database.getInstance();
        jdbc.SignIn(username, password);*/
    }
    public static User logincheck(String username, String password ) throws SQLException, ClassNotFoundException
    {
        Database db = Database.getInstance();
        return db.SignIn(username,password);
    }

    public static User signupcheck(String username, String password, String email,String gender) throws SQLException, ClassNotFoundException {
        Database db = Database.getInstance();
        return db.SignUp(username, password, email,gender);
    }



}
