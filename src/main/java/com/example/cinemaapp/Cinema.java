package com.example.cinemaapp;

import javafx.scene.control.ListView;

import java.sql.SQLException;
import java.util.ArrayList;

public class Cinema
{
    private String cinemaName;
    private double latitude;
    private double longitude;
    private String cinemaLocation;
    double distance;

    private ArrayList<String> movies = new ArrayList<>();

    public Cinema(String cinemaName, double latitude, double longitude, String cinemaLocation, ArrayList<String> movies) {
        this.cinemaName = cinemaName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cinemaLocation = cinemaLocation;
        this.movies = movies;
    }

    public Cinema() {

    }

    public Cinema(String cinemaName, double latitude, double longitude,double distance) {
        this.cinemaName = cinemaName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
    }
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getCinemaLocation() {
        return cinemaLocation;
    }

    public void setCinemaLocation(String cinemaLocation) {
        this.cinemaLocation = cinemaLocation;
    }

    public static ArrayList<Cinema> getCinema() throws SQLException, ClassNotFoundException
    {
        Database db = Database.getInstance();
        return db.getcinema();
    }
    public static Cinema getName(String Location) throws SQLException, ClassNotFoundException
    {
        Database db = Database.getInstance();
        return db.getcinemanamebylocation(Location);
    }

    public static ArrayList<Cinema> getcinemawithdistance(double userLat, double userLon, ListView<String> listview) throws SQLException, ClassNotFoundException
    {
        Database db = Database.getInstance();
        return db.getCinemaWithDistance(userLat,userLon,listview);
    }

    public static Cinema getnearestcinema(double userLat, double userLon) throws SQLException, ClassNotFoundException {
        Database db = Database.getInstance();
        return db.getNearestCinema(userLat,userLon);
    }
    public static Cinema getName2(String name) throws SQLException, ClassNotFoundException
    {
        Database db = Database.getInstance();
        return db.getcinemaname(name);
    }
}
