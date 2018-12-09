package com.company;

import src.com.company.db.DatabaseConnection;
import src.com.company.model.Exercise;
import src.com.company.model.Group;
import src.com.company.model.Solution;
import src.com.company.model.User;

import java.lang.management.GarbageCollectorMXBean;
import java.sql.SQLException;
import java.util.Arrays;

import static src.com.company.db.DatabaseConnection.getEfficientConnection;
import static src.com.company.model.Group.loadById;

public class Main {

    public static void main(String[] args) {
	// write your code here
    //System.out.println("test");

        Group group = new Group();
        src.com.company.model.User user = new User();
        src.com.company.model.Exercise exercise = new Exercise();
        src.com.company.model.Solution solution = new Solution();
        try {
            group.setId(0);
            group.setName("test5");
            group.saveToDB(getEfficientConnection());
            //exercise.delete(getEfficientConnection(),2);
            //solution.delete(getEfficientConnection(),2);
            //user.delete(getEfficientConnection(),3);
            //group.delete(getEfficientConnection(),3);
            System.out.println(Arrays.toString(Group.loadAllGroup(getEfficientConnection())));
            //System.out.println(Arrays.toString(User.loadAllUser(getEfficientConnection())));
            //System.out.println(Arrays.toString(Exercise.loadAllExercise(getEfficientConnection())));
            //System.out.println(Arrays.toString(Solution.loadAllSolution(getEfficientConnection())));
            //System.out.println(Group.loadById(DatabaseConnection.getEfficientConnection(),2));
            //System.out.println(User.loadById(getEfficientConnection(),3));
            //System.out.println(Exercise.loadById(getEfficientConnection(),1));
            //System.out.println(Solution.loadByID(getEfficientConnection(),2));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
