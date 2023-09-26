package com.example.cinemaapp;

public class DistanceCalculate
{
    public static double haversine(double lat1, double lon1,double lat2, double lon2)
    {
        // convert to radians
        lat1 = Math.toRadians(lat1);
        lon1 =Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 =Math.toRadians(lon2);

        // distance between latitudes and longitudes
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        double distance =  rad * c;
        return distance;
    }
}
