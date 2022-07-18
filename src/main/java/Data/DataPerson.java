package main.java.Data;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import main.java.Entities.*;

public class DataPerson {

    private Person mapPerson(ResultSet rs) throws SQLException {
        Person p = new Person();
        p.setId(rs.getInt("person_id"));
        p.setDni(rs.getString("person_dni")); //en la base de datos no aparece el DNI.
        p.setFirstName(rs.getString("first_name"));
        p.setLastName(rs.getString("last_name"));
        p.setBirthdate(rs.getDate("birthdate"));
        p.setEmail(rs.getString("email"));
        p.setPhone(rs.getString("phone"));
        p.setPersonType(rs.getString("person_type"));

        return p;
    }
    public LinkedList<Person> getAll() throws SQLException {
        Statement stmt=null;
        ResultSet rs=null;
        LinkedList<Person> personas= new LinkedList<>();

        stmt= DbConnector.getInstance().getConn().createStatement();
        rs= stmt.executeQuery("select first_name from person");

        while(rs.next()){
            Person p = new Person();
        }

        return personas;
    }

    public Person getOne(int id) throws SQLException{
        Person onePerson = new Person();
        String query = "select * from person where person_id = ?";
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) onePerson = mapPerson(rs);
        rs.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();

        return onePerson;
    }

    public void addPerson(Person p) throws SQLException{
        PreparedStatement stmt=DbConnector.getInstance().getConn().prepareStatement(
                "insert into person(person_dni, person_type) values(?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        );
        stmt.setString(1, p.getDni());
        stmt.setString(2, p.getPersonType());
        stmt.executeUpdate();

        ResultSet keyResultSet = stmt.getGeneratedKeys();
        if(keyResultSet.next()){
            p.setId(keyResultSet.getInt(1));
        }
        keyResultSet.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    public void updatePerson(Person p) throws SQLException{
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement("" +
                "update person set person_dni=?, person_type=? where person_id=?");
        stmt.setString(1, p.getDni());
        stmt.setString(2, p.getPersonType());
        stmt.setInt(3, p.getId());
        stmt.executeUpdate();

        if(stmt!=null) stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    public void deletePerson(Person p) throws SQLException{
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement("delete from room where person_id=?");
        stmt.setInt(1, p.getId());
        stmt.executeUpdate();
        stmt.close();
        DbConnector.getInstance().releaseConn();
    }

}
