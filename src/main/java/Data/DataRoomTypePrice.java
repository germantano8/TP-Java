package main.java.Data;
import main.java.Entities.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DataRoomTypePrice {

    private RoomTypePrice mapRoomTypePrice(ResultSet rs) throws SQLException{
        RoomTypePrice p = new RoomTypePrice();
        p.setPrice(rs.getDouble("price"));
        p.setRoomTypeId(rs.getInt("room_type_id"));
        p.setDate_from(rs.getDate("date_from"));
        return p;
    }

    public List<RoomTypePrice> getAll() throws SQLException{
        LinkedList<RoomTypePrice> rtp = new LinkedList<>();
        String query = "select * from room_type_price";
        Statement stmt = DbConnector.getInstance().getConn().createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            rtp.add(mapRoomTypePrice(rs));
        }
        rs.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();

        return rtp;
    }

    public RoomTypePrice getOne(int id) throws SQLException{
        RoomTypePrice rtp = new RoomTypePrice();
        String query = "select * from room_type_price where room_type_id = ?";
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) rtp = mapRoomTypePrice(rs);

        rs.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();

        return rtp;
    }

    public void addRoomTypePrice(RoomTypePrice rtp) throws SQLException{
        PreparedStatement stmt=DbConnector.getInstance().getConn().prepareStatement(
                "insert into room_type_price(date_from, price, room_type_id) values(?, ?, ?)"
        );

        stmt.setDate(1, (Date)rtp.getDate_from());
        stmt.setDouble(2, rtp.getPrice());
        stmt.setInt(3, rtp.getRoomTypeId());
        stmt.executeUpdate();

        stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    public void deleteRoomTypePrice(RoomTypePrice rtp)throws SQLException{
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement("delete from room_type_price where room_type_id = ? and date_from = ?");

        stmt.setInt(1, rtp.getRoomTypeId());
        stmt.setDate(2, (Date)rtp.getDate_from());
        stmt.executeUpdate();

        stmt.close();
        DbConnector.getInstance().releaseConn();
    }

}
