package src.com.company.model;

import java.sql.*;
import java.util.ArrayList;

public class Solution {

    private int id;
    private java.util.Date created;
    private java.util.Date updated;
    private String description;

    public Object getExercise() {
        return exercise;
    }

    public void setExercise(Object exercise) {
        this.exercise = exercise;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    private Object exercise;
    private Object user;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.util.Date getCreated() {
        return created;
    }

    public void setCreated(java.util.Date created) {
        this.created = (Date) created;
    }

    public java.util.Date getUpdated() {
        return updated;
    }

    public void setUpdated(java.util.Date updated) {
        this.updated = (Date) updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void saveToDB(Connection conn) throws SQLException{
        if (this.id==0) {
            String sql = "INSERT INTO solution(created, updates, description, exercise_id, users_id) VALUES (?,?,?,?,?);";
            String[] generatedColumns = {"ID"};
            PreparedStatement preparedStatement = conn.prepareStatement(sql, generatedColumns);
            preparedStatement.setDate(1, (Date) this.created);
            preparedStatement.setDate(2, (Date) this.updated);
            preparedStatement.setString(3,this.description);
            preparedStatement.setObject(4,this.exercise);
            preparedStatement.setObject(5,this.user);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
        } else  {
            String sql1 = "UPDATE solution SET created=?, updates=?, description=?, exercise_id=?, users_id=? WHERE id=?;";
            PreparedStatement preparedStatement =conn.prepareStatement(sql1);
            preparedStatement.setDate(1, (Date) this.created);
            preparedStatement.setDate(2, (Date) this.updated);
            preparedStatement.setString(3,this.description);
            preparedStatement.setObject(4,this.exercise);
            preparedStatement.setObject(5,this.user);
            preparedStatement.setInt(6,this.id);
            preparedStatement.executeUpdate();
        }
    }


    static public Solution[] loadAllSolution(Connection conn) throws SQLException {
        ArrayList<src.com.company.model.Solution> solutions = new ArrayList<Solution>();
        String sql = "SELECT * FROM solution";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            src.com.company.model.Solution loadedSolution = new src.com.company.model.Solution();
            loadedSolution.id = resultSet.getInt("id");
            loadedSolution.created = resultSet.getDate("created");
            loadedSolution.updated = resultSet.getDate("updates");
            loadedSolution.description = resultSet.getString("description");
            loadedSolution.exercise = resultSet.getObject("exercise_id");
            loadedSolution.user = resultSet.getObject("users_id");
            solutions.add(loadedSolution);}
        src.com.company.model.Solution[] uArray = new src.com.company.model.Solution[solutions.size()];
        uArray = solutions.toArray(uArray);
        return uArray;
    }

    public Solution loadByID(Connection conn) throws SQLException {
        String sql = "SELECT * FROM solution WHERE id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,this.id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            Solution loadedSolution = new Solution();
            loadedSolution.id = resultSet.getInt("id");
            loadedSolution.created = resultSet.getDate("created");
            loadedSolution.updated = resultSet.getDate("updates");
            loadedSolution.description = resultSet.getString("description");
            loadedSolution.exercise = resultSet.getObject("exercise_id");
            loadedSolution.user = resultSet.getObject("users_id");
            return loadedSolution;
        }
        return null;
    }


    public void  delete (Connection conn) throws SQLException {
        if (this.id !=0){
            String sql = "DELETE FROM solution WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,this.id);
            preparedStatement.executeUpdate();
            this.id=0;
        }


    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", description='" + description + '\'' +
                ", exercise=" + exercise +
                ", user=" + user +
                '}';
    }
}
