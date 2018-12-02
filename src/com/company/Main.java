package com.company;

import src.com.company.db.DatabaseConnection;
import src.com.company.model.Group;

import java.lang.management.GarbageCollectorMXBean;
import java.sql.SQLException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
    System.out.println("test");

        Group group = new Group();

        try {
            System.out.println(Arrays.toString(Group.loadAllGroup(DatabaseConnection.getEfficientConnection())));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
