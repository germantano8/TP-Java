package main.java.Logic;

import main.java.Data.DataBooking;
import main.java.Entities.Booking;

import java.sql.SQLException;
import java.util.List;

public class BookingLogic {
    private DataBooking data;

    public BookingLogic() {
        this.data = new DataBooking();
    }

    public List<Booking> getAll() throws SQLException {
        return data.getAll();
    }

    public Booking getOne(int id) throws SQLException {
        return data.getOne(id);
    }

    public void addBooking(Booking b) throws SQLException {
        if (b.getDateFrom().isAfter(b.getDateTo())) {
            //TODO: throw new IllegalArgumentException("Fecha desde no puede ser mayor a fecha hasta");
            return;
        }
        data.addBooking(b);
    }

    public void updateBooking(Booking b) throws SQLException {
        if (b.getDateFrom().isAfter(b.getDateTo())) {
            //TODO: throw new IllegalArgumentException("Fecha desde no puede ser mayor a fecha hasta");
            return;
        }
        data.updateBooking(b);
    }

    public void deleteBooking(Booking b) throws SQLException {
        data.deleteBooking(b);
    }
}
