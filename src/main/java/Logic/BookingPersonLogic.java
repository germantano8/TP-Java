package main.java.Logic;

import main.java.Data.DataBookingPerson;
import main.java.Entities.Booking;
import main.java.Entities.BookingPerson;

import java.sql.SQLException;
import java.util.List;

public class BookingPersonLogic {
    private DataBookingPerson data;

    public BookingPersonLogic() {
        this.data = new DataBookingPerson();
    }

    public List<BookingPerson> getAll() throws SQLException {
        return data.getAll();
    }

    public BookingPerson getOne(int bId, int pId) throws SQLException {
        return data.getOne(bId, pId);
    }

    public void addBookingPerson(BookingPerson bp) throws SQLException {
        data.addBookingPerson(bp);
    }

    public void updateBookingPerson(BookingPerson bp) throws SQLException {
        data.updateBookingPerson(bp);
    }

    public void deleteBookingPerson(BookingPerson bp) throws SQLException {
        data.deleteBookingPerson(bp);
    }
}
