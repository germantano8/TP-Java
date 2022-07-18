package main.java.Data;

import main.java.Entities.Person;
import main.java.Entities.Room;
import main.java.Entities.Service;
import main.java.Entities.ServicePrice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DataServicePrice {

    private ServicePrice mapServicePrice(ResultSet rs) throws SQLException {
        ServicePrice sp = new ServicePrice();
        sp.setServiceId(rs.getInt("service_id"));
        sp.setPrice(rs.getDouble("price"));
        sp.setDate_from(rs.getDate("price_from"));

        return sp;
    }
    public List<ServicePrice> getAll() throws SQLException {
        LinkedList<ServicePrice> preciosServicios = new LinkedList<>();
        String query = "select * from service_price";
        Statement stmt = DbConnector.getInstance().getConn().createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            preciosServicios.add(mapServicePrice(rs));
        }
        rs.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();

        return preciosServicios;
    }

    public ServicePrice getOne(int id) throws SQLException{
        ServicePrice oneServicePrice = new ServicePrice();
        String query = "select * from service_price where service_id = ?";
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) oneServicePrice = mapServicePrice(rs);
        rs.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();

        return oneServicePrice;
    }

    public void addServicePrice(ServicePrice sp) throws SQLException{
        PreparedStatement stmt=DbConnector.getInstance().getConn().prepareStatement(
                "insert into service_price(service_id, price) values(?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        );
        stmt.setInt(1, sp.getServiceId());
        stmt.setDouble(2, sp.getPrice());
        stmt.executeUpdate();

        ResultSet keyResultSet = stmt.getGeneratedKeys();
        if(keyResultSet.next()){
            sp.setServiceId(keyResultSet.getInt(1));
        }
        keyResultSet.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    public void updateServicePrice(ServicePrice sp) throws SQLException{
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement("" +
                "update service_price set price=? where service_id=?");
        stmt.setDouble(1, sp.getPrice());
        stmt.setInt(2, sp.getServiceId());
        stmt.executeUpdate();

        if(stmt!=null) stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    public void deleteServicePrice(ServicePrice sp) throws SQLException{
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement("delete from service_price where service_id=?");
        stmt.setInt(1, sp.getServiceId());
        stmt.executeUpdate();
        stmt.close();
        DbConnector.getInstance().releaseConn();
    }

}
