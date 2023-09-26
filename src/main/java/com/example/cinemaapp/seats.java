package com.example.cinemaapp;

import java.sql.SQLException;

public class seats implements seatsInterface
{
    public int availableSeats  ;


    public seats()
    {
    }
    public seats(int availableSeats)
    {
        this.availableSeats = availableSeats;
    }

    @Override
    public int bookingseats(int numseats) {
        if (numseats > availableSeats) {
            return 0; // Not enough available seats
        }
        availableSeats -= numseats;
        return availableSeats;
    }
    public seats bookingseatss(int numseats,String cinemaname) throws SQLException, ClassNotFoundException {
        Database db = Database.getInstance();
        return db.getcinemaseats(numseats,cinemaname);
    }

}
