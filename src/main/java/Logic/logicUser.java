package main.java.Logic;
import java.util.List;
import main.java.Data.*;
import main.java.Entities.*;
import java.sql.SQLException;


public class LogicUser {
    private DataUser dataUser;

    public LogicUser(){
        dataUser = new DataUser();
    }

    public List<User> getAll() throws SQLException {
        return dataUser.getAll();
    }

    public User getOne(int id) throws SQLException{
        return dataUser.getOne(id);
    }

    public void addUser(User user) throws SQLException{
        dataUser.addUser(user);
    }

    public void updateUser(User user) throws SQLException{
        dataUser.updateUser(user);
    }

    public void deleteUser(User user) throws SQLException{
        dataUser.deleteUser(user);
    }
}