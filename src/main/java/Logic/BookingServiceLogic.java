package main.java.Logic;

import main.java.Data.DataBookingService;
import main.java.Entities.BookingService;

import java.sql.SQLException;
import java.util.List;

public class BookingServiceLogic {
    private DataBookingService data;

    public BookingServiceLogic() {
        this.data = new DataBookingService();
    }

    public List<BookingService> getAll() throws SQLException {
        return data.getAll();
    }

    public BookingService getOne(int bId, int sId) throws SQLException {
        return data.getOne(bId, sId);
    }

    public void addBookingService(BookingService bs) throws SQLException {
        data.addBookingService(bs);
    }

    public void updateBookingService(BookingService bs) throws SQLException {
        data.updateBookingService(bs);
    }

    public void deleteBookingService(BookingService bs) throws SQLException {
        data.deleteBookingService(bs);
    }
}
