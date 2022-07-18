package main.java.Data;

import main.java.Entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DataUser {

    private User mapUser(ResultSet rs) throws SQLException {
        User u = new User();
        u.setUserId(rs.getInt("user_id"));
        u.setPersonId(rs.getInt("person_id"));
        u.setPassword(rs.getString("password"));
        u.setUsername(rs.getString("username"));

        return u;
    }

    public List<User> getAll() throws SQLException {
        LinkedList<User> usuarios = new LinkedList<>();
        String query = "select * from user";
        Statement stmt = DbConnector.getInstance().getConn().createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            usuarios.add(mapUser(rs));
        }
        rs.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();

        return usuarios;
    }


    public User getOne(int id) throws SQLException{
        User oneUser = new User();
        String query = "select * from user where user_id = ?";
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) oneUser = mapUser(rs);
        rs.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();

        return oneUser;
    }

    public void addUser(User u) throws SQLException{
        PreparedStatement stmt=DbConnector.getInstance().getConn().prepareStatement(
                "insert into user(username, password, person_id) values(?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        );
        stmt.setString(1, u.getUsername());
        stmt.setString(2, u.getPassword());
        stmt.setInt(3,u.getPersonId());
        stmt.executeUpdate();

        ResultSet keyResultSet = stmt.getGeneratedKeys();
        if(keyResultSet.next()){
            u.setUserId(keyResultSet.getInt(1));
        }
        keyResultSet.close();
        stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    public void updateUser(User u) throws SQLException{
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement("" +
                "update user set username=?, password=?, person_id=? where user_id=?");
        stmt.setString(1, u.getUsername());
        stmt.setString(2, u.getPassword());
        stmt.setInt(3, u.getPersonId());
        stmt.executeUpdate();

        if(stmt!=null) stmt.close();
        DbConnector.getInstance().releaseConn();
    }

    public void deleteUser(User u) throws SQLException{
        PreparedStatement stmt = DbConnector.getInstance().getConn().prepareStatement("delete from user where user_id=?");
        stmt.setInt(1, u.getUserId());
        stmt.executeUpdate();
        stmt.close();
        DbConnector.getInstance().releaseConn();
    }

}
