package main.java.Data;

import main.java.Entities.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DataService {

    public List<Service> getAll() throws SQLException {
        LinkedList<Service> Servicios = new LinkedList<>();
        String query = "select * from service";
        Statement stmt = DbConnector.getInstance().getConn().createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            Servicios.add(mapService(rs));
        }
        rs.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();

        return Servicios;
    }

    private Service mapService(ResultSet rs) throws SQLException {
        Service s = new Service();
        s.setId(rs.getInt("service_id"));
        s.setService_name(rs.getString("service_name"));

        return s;
    }

    public Service getOne(int id) throws SQLException{
        Service oneService = new Service();
        String query = "select * from service where service_id = ?";
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) oneService = mapService(rs);
        rs.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();

        return oneService;
    }

    public void addService(Service s) throws SQLException{
        PreparedStatement stmt=DbConnector.getInstance().getConn().prepareStatement(
                "insert into service(service_id, service_name) values(?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        );
        stmt.setInt(1, s.getId());
        stmt.setString(2, s.getService_name());
        stmt.executeUpdate();

        ResultSet keyResultSet = stmt.getGeneratedKeys();
        if(keyResultSet.next()){
            s.setId(keyResultSet.getInt(1));
        }
        keyResultSet.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    public void updateService(Service s) throws SQLException{
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement("" +
                "update service_price set service_name=? where service_id=?");
        stmt.setDouble(1, s.getId());
        stmt.setString(2, s.getService_name());
        stmt.executeUpdate();

        if(stmt!=null) stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    public void deleteService(Service s) throws SQLException{
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement("delete from service where service_id=?");
        stmt.setInt(1, s.getId());
        stmt.executeUpdate();
        stmt.close();
        DbConnector.getInstance().releaseConn();
    }

}
