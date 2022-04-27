package main.java.Entities;

import java.util.Date;

public class RoomTypePrice {
    private double price;
    private Date dateFrom;
    private int RoomTypeId;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate_from() {
        return dateFrom;
    }

    public void setDate_from(Date date_from) {
        this.dateFrom = date_from;
    }

    public int getRoomTypeId() {
        return RoomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        RoomTypeId = roomTypeId;
    }
}
