package main.java.Logic;
import java.sql.*;
import java.util.List;

import main.java.Data.*;
import main.java.Entities.*;

public class LogicRoomTypePrice {

    private DataRoomTypePrice drtp;

    public LogicRoomTypePrice(){
        drtp = new DataRoomTypePrice();
    }

    public List<RoomTypePrice> getAll() throws SQLException{
        return drtp.getAll();
    }

    public RoomTypePrice getOne(int id) throws SQLException{
        return drtp.getOne(id);
    }

    public void addRoomTypePrice(RoomTypePrice rtp) throws SQLException{
        drtp.addRoomTypePrice(rtp);
    }

    public void deleteRoomTypePrice(RoomTypePrice rtp) throws SQLException{
        drtp.deleteRoomTypePrice(rtp);
    }

}
