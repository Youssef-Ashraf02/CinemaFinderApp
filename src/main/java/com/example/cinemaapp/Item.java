package com.example.cinemaapp;

public class Item extends Ticket
{
    public String name;
    public int price ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Item(String name,int price,int ticket_num)
    {
        super(ticket_num);
        this.name = name;
        this.price = price;
    }
    public Item(String name,int price)
    {
        this.name = name;
        this.price = price;
    }


    public String ToString()
{
    return " " + price;
}
}
