package main.java.Data;

import main.java.Entities.BookingService;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DataBookingService {
    private String query;

    public List<BookingService> getAll() throws SQLException {
        LinkedList<BookingService> bServices = new LinkedList<>();
        query = "select * from booking_service";
        Statement stmt = DbConnector.getInstance().getConn().createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            bServices.add(mapBooking(rs));
        }
        rs.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();

        return bServices;
    }

    public BookingService getOne(int bId, int sId) throws SQLException {
        BookingService bs = new BookingService();
        query = "select * from booking_service where booking_id = ? and service_id = ?";
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement(query);
        stmt.setInt(1, bId);
        stmt.setInt(2, sId);
        ResultSet rs = stmt.executeQuery();

        if(rs.next()) bs = mapBooking(rs);

        rs.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();

        return bs;
    }

    public void addBookingService(BookingService bs) throws SQLException {
        query = "insert into booking_service(booking_id, service_id) values(?, ?)";
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement(query);
        mapStatement(bs, stmt);
        stmt.executeUpdate();

        stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    public void updateBookingService(BookingService bs) throws SQLException{
        query = "update booking_service set booking_id = ?, service_id = ?" +
                " where booking_id = ? and service_id = ?";
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement(query);
        mapStatement(bs, stmt);
        stmt.executeUpdate();

        stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    public void deleteBookingService(BookingService bs) throws SQLException {
        query = "delete from booking_service where booking_id = ? and service_id = ?";
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement(query);
        mapStatement(bs, stmt);
        stmt.executeUpdate();

        stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    private BookingService mapBooking(ResultSet rs) throws SQLException {
        BookingService bs = new BookingService();
        bs.setBookingId(rs.getInt("booking_id"));
        bs.setServiceId(rs.getInt("service_id"));
        return bs;
    }

    private void mapStatement(BookingService bs, PreparedStatement stmt) throws SQLException {
        stmt.setInt(1, bs.getBookingId());
        stmt.setInt(2, bs.getServiceId());
    }
}
