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
        dr.addRoom(r);
    }

    public void deleteRoom(Room r) throws SQLException{
        dr.deleteRoom(r);
    }
}
