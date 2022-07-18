package main.java.Logic;
import java.util.List;
import main.java.Data.*;
import main.java.Entities.*;
import java.sql.SQLException;


public class LogicServicePrice {
    private DataServicePrice datasp;

    public LogicServicePrice(){
        datasp = new DataServicePrice();
    }

    public List<ServicePrice> getAll() throws SQLException {
        return datasp.getAll();
    }

    public ServicePrice getOne(int id) throws SQLException{
        return datasp.getOne(id);
    }

    public void addServicePrice(ServicePrice sp) throws SQLException{
        datasp.addServicePrice(sp);
    }

    public void updateServicePrice(ServicePrice sp) throws SQLException{
        datasp.updateServicePrice(sp);
    }

    public void deleteServicePrice(ServicePrice sp) throws SQLException{
        datasp.deleteServicePrice(sp);
    }
}