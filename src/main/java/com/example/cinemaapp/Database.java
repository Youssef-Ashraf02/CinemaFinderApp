package com.example.cinemaapp;
import javafx.application.Platform;
import javafx.scene.control.ListView;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Database
{
    String url = "jdbc:mysql://localhost:3306/cinema_app_table";
    String user = "root";
    String password = "youssef1622";

    private static Database database;
    public static Connection connection = null;

    private Database()throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
        catch (SQLException e){
            System.out.println("not connected + "+ e.getMessage());
        }
    }

    public static synchronized Database getInstance() throws SQLException, ClassNotFoundException {
        if(database == null){
            database = new Database();
        } else if (Database.getConnection().isClosed()) {
            database = new Database();
        }
        return database;
    }
    public static synchronized Connection getConnection() throws SQLException
    {
        return connection;
    }

    public User SignIn(String username, String password) throws SQLException {
        Connection c = null;
        try {
            c = this.getConnection();
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            while(resultSet.next()){
                if(resultSet.getString("username").equals(username) &&
                        resultSet.getString("password").equals(password)){
                    return new User(username, password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public User SignUp(String username, String password, String email, String gender) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = this.getConnection();
            ps = c.prepareStatement("SELECT username FROM user WHERE username = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                throw new IllegalArgumentException("Username is already taken.");
            }

            ps = c.prepareStatement("insert into user (username, password, email, gender) values (?, ?, ?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, gender);
            ps.executeUpdate();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        }

        return null;
    }

    public ArrayList<Cinema> getcinema()
    {
        Connection c = null;
        ArrayList<Cinema> cinemas = new ArrayList<>();
        try{
            c = this.getConnection();
            Statement st = c.createStatement();
            ResultSet resultSet = st.executeQuery("select * from cinemas");
            while (resultSet.next())
            {
                    cinemas.add(new Cinema(resultSet.getString("cinemaname"),
                            resultSet.getDouble("latitude"),resultSet.getDouble("longitude"),
                            resultSet.getString("cinemalocation"),null));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cinemas;
    }

    public Cinema getcinemanamebylocation(String cinemalocation) throws SQLException {
        Connection c = null;
        ResultSet rs = null;
        Cinema cinema = null;
        try {
            c = this.getConnection();

            String sql = "SELECT * FROM cinemas WHERE cinemalocation = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cinemalocation);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                if (rs.getString("cinemalocation").equals(cinemalocation)) {
                    cinema = new Cinema(rs.getString("cinemaname"),0,0, cinemalocation, null);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                rs.close();
            if (c != null)
                c.close();
        }
        return cinema;
    }
    public Cinema getcinemaname(String cinemaname) throws SQLException {
        Connection c = null;
        ResultSet rs = null;
        Cinema cinema = null;
        try {
            c = this.getConnection();

            String sql = "SELECT * FROM cinemas WHERE cinemaname = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cinemaname);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                if (rs.getString("cinemaname").equals(cinemaname)) {
                    cinema = new Cinema(cinemaname,0,0, rs.getString("cinemalocation"), null);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                rs.close();
            if (c != null)
                c.close();
        }
        return cinema;
    }

    public seats getcinemaseats(int seatsToBuy,String cinemaname) throws SQLException {
        Connection c = null;
        seats seat = null;
        int newAvailableSeats;
        try {
            c = this.getConnection();

            // Check if there are enough available seats to buy
            PreparedStatement selectStatement = c.prepareStatement("SELECT avseats FROM cinemas WHERE cinemaname = ?");
            selectStatement.setString(1,cinemaname);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                int availableSeats = resultSet.getInt("avseats");

                if (availableSeats >= seatsToBuy) {
                    // Calculate new available seats after the purchase
                    newAvailableSeats = availableSeats - seatsToBuy;

                    // Update the database with new available seats
                    PreparedStatement updateStatement = c.prepareStatement("UPDATE cinemas SET avseats = ? WHERE cinemaname = ?");
                    updateStatement.setInt(1, newAvailableSeats);
                    updateStatement.setString(2, cinemaname);
                    seat = new seats(newAvailableSeats);
                    updateStatement.executeUpdate();
                } else {
                    throw new RuntimeException("Not enough available seats to buy.");
                }
            } else {
                throw new RuntimeException("Cinema not found.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return seat;
    }

    public ArrayList<Cinema> getCinemaWithDistance(double userLat, double userLon , ListView<String> cinemaListView) {
        ArrayList<Cinema> cinemaWithDistance = new ArrayList<>();
        ArrayList<String> cinemaNamesWithDistance = new ArrayList<>();
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT cinemaname, latitude, longitude FROM cinemas");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                // Calculate the distance using the Haversine formula
                double distance = DistanceCalculate.haversine(userLat, userLon, resultSet.getDouble("latitude"), resultSet.getDouble("longitude"));
                Cinema cinema = new Cinema(resultSet.getString("cinemaname"), resultSet.getDouble("latitude"), resultSet.getDouble("longitude"), distance);
                cinemaWithDistance.add(cinema);

                String cinemaInfo = cinema.getCinemaName() + " - Distance: " + cinema.getDistance() + " km";
                cinemaNamesWithDistance.add(cinemaInfo);
            }
            Collections.sort(cinemaWithDistance, Comparator.comparingDouble(Cinema::getDistance));
            Collections.sort(cinemaNamesWithDistance, (str1, str2) -> {
                double distance1 = Double.parseDouble(str1.split(" - Distance: ")[1].replace(" km", ""));
                double distance2 = Double.parseDouble(str2.split(" - Distance: ")[1].replace(" km", ""));
                return Double.compare(distance1, distance2);
            });
            Platform.runLater(() -> {
                cinemaListView.getItems().clear();
                cinemaListView.getItems().addAll(cinemaNamesWithDistance);
            });
            for (Cinema cinema : cinemaWithDistance) {

                System.out.println("Cinema: " + cinema.getCinemaName() + ", Distance: " + cinema.getDistance() + " km");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cinemaWithDistance;
    }
}
