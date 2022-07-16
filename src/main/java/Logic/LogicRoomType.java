package main.java.Logic;
import java.sql.*;
import java.util.List;

import main.java.Data.*;
import main.java.Entities.*;

public class LogicRoomType {

    private DataRoomType drt;

    public LogicRoomType(){
        drt = new DataRoomType();
    }

    public List<RoomType> getAll() throws SQLException{
        return drt.getAll();
    }

    public RoomType getOne(int id) throws SQLException{
        return drt.getOne(id);
    }

    public void addRoomType(RoomType rt) throws SQLException{
        if(rt.getTypeName().length() > 0 && rt.getTypeName().length() < 40 && rt.getCapacity() > 0 && rt.getCapacity() < 5){
            drt.addRoomType(rt);
        }else{
            // TODO
        }
    }

    public void updateRoomType(RoomType rt) throws SQLException{
        drt.updateRoomType(rt);
    }

    public void deleteRoomType(RoomType rt) throws SQLException{
        drt.deleteRoomType(rt);
    }

}
