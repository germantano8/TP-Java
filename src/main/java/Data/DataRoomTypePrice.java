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
        if(rs != null){
            while(rs.next()){
                rtp.add(mapRoomTypePrice(rs));
            }
        }
        if(rs!=null){rs.close();}
        if(stmt!=null){stmt.close();}
        DbConnector.getInstance().releaseConn();

        return rtp;
    }

    public RoomTypePrice getOne(int id) throws SQLException{
        RoomTypePrice rtp = new RoomTypePrice();
        String query = "select * from room_type_price where room_type_id = ?";
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if(rs!=null && rs.next()) rtp = mapRoomTypePrice(rs);

        if(rs!=null)rs.close();
        if(stmt!=null)stmt.close();
        DbConnector.getInstance().releaseConn();

        return rtp;
    }

    public void addRoomTypePrice(RoomTypePrice rtp) throws SQLException{
        PreparedStatement stmt = null;
        ResultSet keyResultSet = null;

        stmt=DbConnector.getInstance().getConn().prepareStatement(
                "insert into room_type_price(date_from, price, room_type_id) values(?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        );

        stmt.setDate(1, (Date)rtp.getDate_from());
        stmt.setDouble(2, rtp.getPrice());
        stmt.setInt(3, rtp.getRoomTypeId());
        stmt.executeUpdate();

        keyResultSet = stmt.getGeneratedKeys();
        if(keyResultSet != null && keyResultSet.next()){
            rtp.setDate_from(keyResultSet.getDate(1));
            rtp.setPrice(keyResultSet.getDouble(2));
            rtp.setRoomTypeId(keyResultSet.getInt(3));
        }
        if(keyResultSet!=null) keyResultSet.close();
        if(stmt!=null) stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    public void deleteRoomTypePrice(RoomTypePrice rtp)throws SQLException{
        PreparedStatement stmt=null;

        stmt = DbConnector.getInstance().getConn().prepareStatement("delete from room_type_price where room_type_id = ? and date_from = ?");

        stmt.setInt(1, rtp.getRoomTypeId());
        stmt.setDate(2, (Date)rtp.getDate_from());
        stmt.executeUpdate();

        if(stmt!=null) {stmt.close();}
        DbConnector.getInstance().releaseConn();
    }

}
