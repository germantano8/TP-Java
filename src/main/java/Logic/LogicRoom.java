package main.java.Logic;
import java.util.List;

import main.java.Data.*;
import main.java.Entities.*;
import java.sql.SQLException;

public class LogicRoom {

    private DataRoom dr;

    public LogicRoom(){
        dr = new DataRoom();
    }

    public List<Room> getAll() throws SQLException {
        return dr.getAll();
    }

    public Room getOne(int id) throws SQLException{
        return dr.getOne(id);
    }

    public void addRoom(Room r) throws SQLException{
        RoomType rt = new RoomType();
        rt = new DataRoomType().getOne(r.getRoomTypeId());
        if(r.getRoomNumber() > 0 && r.getRoomNumber() < 1000 && rt != null && new DataRoom().getOneByNumber(r.getRoomId()) == null){
            dr.addRoom(r);
        }else{
            throw new SQLException("Número de habitación incorrecto o ya existente");
        }
    }

    public void updateRoom(Room r) throws SQLException{
        RoomType rt = new RoomType();
        rt = new DataRoomType().getOne(r.getRoomTypeId());
        if(r.getRoomNumber() > 0 && r.getRoomNumber() < 1000 && rt != null){
            dr.addRoom(r);
        }else{
            throw new SQLException("Número de habitación incorrecto o tipo de habitación incorrecto");
        }
    }

    public void deleteRoom(Room r) throws SQLException{
        dr.deleteRoom(r);
    }
}
