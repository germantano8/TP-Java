package main.java.Data;
import main.java.Entities.*;
import java.sql.*;
import java.util.*;

public class DataRoomType {

    private RoomType mapRoomType(ResultSet rs) throws SQLException{
        RoomType rt = new RoomType();
        rt.setId(rs.getInt("room_type_id"));
        rt.setTypeName(rs.getString("type_name"));
        rt.setCapacity(rs.getInt("capacity"));
        return rt;
    }

    public List<RoomType> getAll() throws SQLException{
        LinkedList<RoomType> roomtypes = new LinkedList<>();
        String query = "select * from room_type";
        Statement stmt = DbConnector.getInstance().getConn().createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if(rs != null){
            while(rs.next()){
                roomtypes.add(mapRoomType(rs));
            }
        }
        if(rs!=null){rs.close();}
        if(stmt!=null){stmt.close();}
        DbConnector.getInstance().releaseConn();

        return roomtypes;
    }

    public RoomType getOne(int id) throws SQLException{
        RoomType rt = new RoomType();
        String query = "select * from room_type where room_type_id = ?";
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if(rs!=null && rs.next()) rt = mapRoomType(rs);

        if(rs!=null)rs.close();
        if(stmt!=null)stmt.close();
        DbConnector.getInstance().releaseConn();

        return rt;
    }

    public void addRoomType(RoomType rt) throws SQLException{
        PreparedStatement stmt = null;
        ResultSet keyResultSet = null;
        stmt=DbConnector.getInstance().getConn().prepareStatement(
                "insert into room_type(type_name, capacity) values(?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        );
        stmt.setString(1, rt.getTypeName());
        stmt.setInt(2, rt.getCapacity());
        stmt.executeUpdate();

        keyResultSet = stmt.getGeneratedKeys();
        if(keyResultSet != null && keyResultSet.next()){
            rt.setId(keyResultSet.getInt(1));
            rt.setTypeName(keyResultSet.getString(2));
        }

        if(keyResultSet!=null) keyResultSet.close();
        if(stmt!=null) stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    public void updateRoomType(RoomType rt) throws SQLException{
        PreparedStatement stmt =null;
        stmt = DbConnector.getInstance().getConn().prepareStatement("" +
                "update room_type set type_name=?, capacity=? where room_type_id=?");
        stmt.setString(1, rt.getTypeName());
        stmt.setInt(2, rt.getCapacity());
        stmt.setInt(3, rt.getId());
        stmt.executeUpdate();
        if(stmt!=null) stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    public void deleteRoomType(RoomType rt) throws SQLException{
        PreparedStatement stmt=null;
        stmt=DbConnector.getInstance().getConn().prepareStatement("delete from room_type where room_type_id=?");
        stmt.setInt(1, rt.getId());
        stmt.executeUpdate();

        if(stmt!=null)stmt.close();
        DbConnector.getInstance().releaseConn();
    }

}
