package src.com.company.model;

import java.io.InputStream;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class User {
    private String username;
    private String password;
    private String email;
    private long id;

    public Object getGroup() {
        return group;
    }

    public void setGroup(Object group) {
        this.group = group;
    }

    private Object group;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void saveToDB(Connection conn) throws SQLException {
        if (this.id ==0) {
            String sql = "INSERT INTO users(username, password, email, user_group_id) VALUES (?,?,?,?);";
            String[] generatedColumns = {"ID"};
            PreparedStatement preparedStatement = conn.prepareStatement(sql,generatedColumns);
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2,this.password);
            preparedStatement.setString(3,this.email);
            preparedStatement.setObject(4,this.group);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getLong(1);
            }
        } else {
            String sql1 = "UPDATE users SET username=?, password=?, email=?, user_group_id=? WHERE id=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql1);
            preparedStatement.setString(1,this.username);
            preparedStatement.setString(2,this.password);
            preparedStatement.setString(3,this.email);
            preparedStatement.setObject(4,this.group);
            preparedStatement.setLong(5, this.id);
            preparedStatement.executeUpdate();
        }
    }


    static public src.com.company.model.User[] loadAllUser(Connection conn) throws SQLException {
        ArrayList<src.com.company.model.User> users = new ArrayList<src.com.company.model.User>();
        String sql = "SELECT * FROM users";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            src.com.company.model.User loadedUser = new src.com.company.model.User();
            loadedUser.id = resultSet.getLong("id");
            loadedUser.username = resultSet.getString("username");
            loadedUser.password = resultSet.getString("password");
            loadedUser.email = resultSet.getString("email");
            loadedUser.group = resultSet.getObject("user_group_id");
            users.add(loadedUser);}
        src.com.company.model.User[] uArray = new src.com.company.model.User[users.size()];
        uArray = users.toArray(uArray);
        return uArray;
    }

    public User loadById(Connection conn) throws SQLException{
        String sql = "SELECT * FROM users WHERE id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setLong(1, this.id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            User loadedUser = new User();
            loadedUser.id = resultSet.getLong("id");
            loadedUser.username = resultSet.getString("username");
            loadedUser.password = resultSet.getString("password");
            loadedUser.email = resultSet.getString("email");
            loadedUser.group = resultSet.getObject("user_group_id");
            return loadedUser;
        }
        return null;
    }

    public void delete(Connection conn, int id) throws SQLException{
        if (this.id !=0) {
            String sql = "DELETE FROM users WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1,this.id);
            preparedStatement.executeUpdate();
            this.id=0;
        }
    }



    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", group=" + group +
                '}';
    }
}
