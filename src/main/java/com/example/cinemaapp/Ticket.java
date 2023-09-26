package com.example.cinemaapp;

public class Ticket
{
    public int ticket_num;

    public int getTicket_num() {
        return ticket_num;
    }

    public void setTicket_num(int ticket_num) {
        this.ticket_num = ticket_num;
    }

    public Ticket() { }
    public Ticket(int ticket_num)
    {
        this.ticket_num = ticket_num;
    }

    public String ToString()
{
    return " " + ticket_num;
}
}
