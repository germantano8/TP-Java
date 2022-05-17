package main.java.Data;

import main.java.Entities.BookingPerson;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DataBookingPerson {
    private String query;

    public List<BookingPerson> getAll() throws SQLException {
        LinkedList<BookingPerson> bpList = new LinkedList<>();
        query = "select * from booking_person";
        Statement stmt = DbConnector.getInstance().getConn().createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            bpList.add(mapBooking(rs));
        }
        rs.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();

        return bpList;
    }

    public BookingPerson getOne(int bId, int pId) throws SQLException {
        BookingPerson bp = new BookingPerson();
        query = "select * from booking_person where booking_id = ? and person_id = ?";
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement(query);
        stmt.setInt(1, bId);
        stmt.setInt(2, pId);
        ResultSet rs = stmt.executeQuery();

        if(rs.next()) bp = mapBooking(rs);

        rs.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();

        return bp;
    }

    public void addBookingPerson(BookingPerson bp) throws SQLException {
        query = "insert into booking_person(booking_id, person_id) values(?, ?)";
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement(query);
        mapStatement(bp, stmt);
        stmt.executeUpdate();

        stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    public void updateBookingPerson(BookingPerson bp) throws SQLException{
        query = "update booking_person set booking_id = ?, person_id = ?" +
                " where booking_id = ? and person_id = ?";
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement(query);
        mapStatement(bp, stmt);
        stmt.executeUpdate();

        stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    public void deleteBookingPerson(BookingPerson bp) throws SQLException {
        query = "delete from booking_person where booking_id = ? and person_id = ?";
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement(query);
        mapStatement(bp, stmt);
        stmt.executeUpdate();

        stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    private BookingPerson mapBooking(ResultSet rs) throws SQLException {
        BookingPerson bp = new BookingPerson();
        bp.setBookingId(rs.getInt("booking_id"));
        bp.setPersonId(rs.getInt("person_id"));
        return bp;
    }

    private void mapStatement(BookingPerson bp, PreparedStatement stmt) throws SQLException {
        stmt.setInt(1, bp.getBookingId());
        stmt.setInt(2, bp.getPersonId());
    }
}
