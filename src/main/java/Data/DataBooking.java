package main.java.Data;

import main.java.Entities.Booking;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DataBooking {

    private String query;

    public List<Booking> getAll() throws SQLException {
        LinkedList<Booking> bookings = new LinkedList<>();
        query = "select * from booking";
        Statement stmt = DbConnector.getInstance().getConn().createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            bookings.add(mapBooking(rs));
        }
        rs.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();

        return bookings;
    }

    public Booking getOne(int id) throws SQLException {
        Booking b = new Booking();
        query = "select * from booking where booking_id = ?";
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if(rs.next()) b = mapBooking(rs);

        rs.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();

        return b;
    }

    public void addBooking(Booking b) throws SQLException {
        query = "insert into booking(date_booking, date_cancel, date_from, date_to, room_id, status, total_price, user_id)" +
                " values(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement(
                query, PreparedStatement.RETURN_GENERATED_KEYS);
        mapStatement(b, stmt);
        stmt.executeUpdate();
        ResultSet keyRS = stmt.getGeneratedKeys();
        if(keyRS.next()){
            b.setBookingId(keyRS.getInt(1)); //TODO return b.getId() to reuse new object
        }

        keyRS.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    public void updateBooking(Booking b) throws SQLException{
        query = "update booking set date_booking = ?, date_cancel = ?, date_from = ?, date_to = ?," +
                " room_id = ?, status = ?, total_price = ?, user_id = ?" +
                " where booking_id = ?";
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement(query);
        mapStatement(b, stmt);
        stmt.setInt(9, b.getBookingId());
        stmt.executeUpdate();

        stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    public void deleteBooking(Booking b) throws SQLException {
        query = "delete from booking where booking_id = ?";
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement(query);
        stmt.setInt(1, b.getBookingId());
        stmt.executeUpdate();

        stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    private Booking mapBooking(ResultSet rs) throws SQLException {
        Booking b = new Booking();
        b.setBookingId(rs.getInt("booking_id"));
        b.setDateBooking(rs.getDate("date_booking").toLocalDate());
        b.setDateCancel(rs.getDate("date_cancel").toLocalDate());
        b.setDateFrom(rs.getDate("date_from").toLocalDate());
        b.setDateTo(rs.getDate("date_to").toLocalDate());
        b.setRoomId(rs.getInt("room_id"));
        b.setStatus(rs.getString("status"));
        b.setTotalPrice(rs.getBigDecimal("total_price"));
        b.setUserId(rs.getInt("user_id"));
        return b;
    }

    private void mapStatement(Booking b, PreparedStatement stmt) throws SQLException {
        stmt.setDate(1, Date.valueOf(b.getDateBooking()));
        stmt.setDate(2, Date.valueOf(b.getDateCancel()));
        stmt.setDate(3, Date.valueOf(b.getDateFrom()));
        stmt.setDate(4, Date.valueOf(b.getDateTo()));
        stmt.setInt(5, b.getRoomId());
        stmt.setString(6, b.getStatus());
        stmt.setBigDecimal(7, b.getTotalPrice());
        stmt.setInt(8, b.getUserId());
    }
}