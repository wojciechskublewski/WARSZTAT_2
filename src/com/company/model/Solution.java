package src.com.company.model;

import java.sql.*;
import java.util.ArrayList;

public class Solution {

    private int id;
    private Date created;
    private Date updated;
    private String description;
    private Object exercise;
    private Object user;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    static public Solution loadByID (Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM solution WHERE id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,id);
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


    public void  delete (Connection conn, int id) throws SQLException {
        if (id !=0){
            String sql = "DELETE FROM solution WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            id=0;
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
