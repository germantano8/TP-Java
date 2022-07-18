package main.java.Logic;
import java.util.List;
import main.java.Data.*;
import main.java.Entities.*;
import java.sql.SQLException;


public class LogicPerson {
    private DataPerson dataPerson;

    public LogicPerson(){
        dataPerson = new DataPerson();
    }

    public List<Person> getAll() throws SQLException {
        return dataPerson.getAll();
    }

    public Person getOne(int id) throws SQLException{
        return dataPerson.getOne(id);
    }

    public void addPerson(Person person) throws SQLException{
        dataPerson.addPerson(person);
    }

    public void updatePerson(Person person) throws SQLException{
        dataPerson.updatePerson(person);
    }

    public void deletePerson(Person person) throws SQLException{
        dataPerson.deletePerson(person);
    }
}