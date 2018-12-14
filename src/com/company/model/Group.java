package src.com.company.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Group {

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }



    public void saveToDB (Connection conn) throws SQLException{
        if (this.id==0) {
            String sql = "INSERT INTO user_group(name) VALUES (?);";
            String[] genertetedColumns = {"ID"};
            PreparedStatement preparedStatement = conn.prepareStatement(sql,genertetedColumns);
            preparedStatement.setString(1,this.name);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
        } else  {
            String sql1 = "UPDATE user_group SET name=? WHERE id=?;";
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement1.setString(1,this.name);
            preparedStatement1.setInt(2,this.id);
            preparedStatement1.executeUpdate();
        }
    }



    static public Group[] loadAllGroup(Connection conn) throws SQLException {
        ArrayList<Group> group_users = new ArrayList<Group>();
        String sql = "SELECT * FROM user_group";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Group loadedGroup = new Group();
            loadedGroup.id = resultSet.getInt("id");
            loadedGroup.name = resultSet.getString("name");
            group_users.add(loadedGroup);}
        Group[] uArray = new Group[group_users.size()];
        uArray = group_users.toArray(uArray);
        return uArray;
    }

    public Group loadById(Connection conn, int id) throws SQLException{
        String sql = "SELECT * FROM user_group WHERE id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Group loadedGroup = new Group();
            loadedGroup.id =resultSet.getInt("id");
            loadedGroup.name = resultSet.getString("name");
            return loadedGroup;
        }
        return null;
    }

    public void delete(Connection conn) throws SQLException{
        if (this.id !=0) {
            String sql = "DELETE FROM user_group WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,this.id);
            preparedStatement.executeUpdate();
            this.id=0;
        }
    }




    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
