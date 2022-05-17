package main.java.Entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Booking {
    private int bookingId;
    private LocalDate dateBooking;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private LocalDate dateCancel;
    private String status;
    private BigDecimal totalPrice;
    private int roomId;
    private int userId;

    private enum status{
        Canceled,
        Booked,
        InProgress,
        Finished
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getDateBooking() {
        return dateBooking;
    }

    public void setDateBooking(LocalDate dateBooking) {
        this.dateBooking = dateBooking;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public LocalDate getDateCancel() {
        return dateCancel;
    }

    public void setDateCancel(LocalDate dateCancel) {
        this.dateCancel = dateCancel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
