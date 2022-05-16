package main.java.Data;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import main.java.Entities.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DataRoom {

    private Room mapRoom(ResultSet rs) throws SQLException{
        Room r = new Room();
        r.setRoomId(rs.getInt("room_id"));
        r.setRoomTypeId(rs.getInt("room_type_id"));
        r.setStatus(rs.getString("status"));
        r.setRoomNumber(rs.getInt("room_number"));
        return r;
    }

    public List<Room> getAll() throws SQLException{
        LinkedList<Room> rooms = new LinkedList<>();
        String query = "select * from room";
        Statement stmt = DbConnector.getInstance().getConn().createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            rooms.add(mapRoom(rs))
        }
        rs.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();

        return rooms;
    }

    public Room getOne(int id) throws SQLException{
        Room oneRoom = new Room();
        String query = "select * from room where room_id = ?";
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) oneRoom = mapRoom(rs);

        rs.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();

        return oneRoom;
    }

    public void addRoom(Room r) throws SQLException{
        PreparedStatement stmt=DbConnector.getInstance().getConn().prepareStatement(
                "insert into room(room_number, status, room_type_id) values(?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        );
        stmt.setInt(1, r.getRoomNumber());
        stmt.setString(2, r.getStatus());
        stmt.setInt(3, r.getRoomId());
        stmt.executeUpdate();

        ResultSet keyResultSet = stmt.getGeneratedKeys();
        if(keyResultSet.next()){
            r.setRoomNumber(keyResultSet.getInt(1));
            r.setStatus(keyResultSet.getString(2));
            r.setRoomTypeId(keyResultSet.getInt(3));
        }
        keyResultSet.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    public void updateRoom(Room r) throws SQLException{
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement("" +
                "update room set room_number=?, status=?, room_type_id=? where room_id=?");
        stmt.setInt(1, r.getRoomNumber());
        stmt.setString(2, r.getStatus());
        stmt.setInt(3, r.getRoomTypeId());
        stmt.setInt(4, r.getRoomId());
        stmt.executeUpdate();

        if(stmt!=null) stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    public void deleteRoom(Room r) throws SQLException{
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement("delete from room where room_id=?");
        stmt.setInt(1, r.getRoomId());
        stmt.executeUpdate();
        stmt.close();
        DbConnector.getInstance().releaseConn();
    }

}
