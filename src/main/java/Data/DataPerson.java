package main.java.Data;
import java.sql.*;
import java.util.LinkedList;
import main.java.Entities.*;

public class DataPerson {

    public LinkedList<Person> getAll(){
        Statement stmt=null;
        ResultSet rs=null;
        LinkedList<Person> personas= new LinkedList<>();

        stmt= DbConnector.getInstance().getConn().createStatement();
        rs= stmt.executeQuery("select first_name from person");

        while(rs.next()){
            Person p = new Person();

        }
    }

}
