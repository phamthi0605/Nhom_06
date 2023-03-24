package com.group6.DAL;

import com.group6.DB.DBContext;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatisticalDAO {
    public static ResultSet top5MinSalary() {
        Connection con = null;
        DBContext db = new DBContext();
        con = db.getConnection();
        ResultSet rs = null;
        try {
            Statement sm = con.createStatement();
            rs = sm.executeQuery("SELECT a.id, a.employee_id, a.full_name, a.position, a.salary, b.department_name FROM employee a\n" +
                    "INNER JOIN department b ON b.id = a.department_id\n" +
                    "ORDER BY salary ASC\n" +
                    "LIMIT 5");
            if (rs.next() == false) {
                System.out.println("Data empty!");
            } else {
                System.out.printf("%-15s%-21s%-20s%-15s%-17s\n", "Employee ID", "Fullname", "Position", "Salary", "Department Name");
                do {
                    String id = rs.getString("employee_id");
                    String full_name = rs.getString("full_name");
                    String position = rs.getString("position");
                    float salary = rs.getFloat("salary");
                    String department_name = rs.getString("department_name");

                    System.out.printf("%-15s%-21s%-20s%-15.2f%-17s\n", id, full_name, position, salary, department_name);
                }
                while (rs.next());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet top5MaxSalary() {
        Connection con = null;
        DBContext db = new DBContext();
        con = db.getConnection();
        ResultSet rs = null;
        try {
            Statement sm = con.createStatement();
            rs = sm.executeQuery("SELECT a.id, a.employee_id, a.full_name, a.position, a.salary, b.department_name FROM employee a\n" +
                    "INNER JOIN department b ON b.id = a.department_id\n" +
                    "ORDER BY salary Desc\n" +
                    "LIMIT 5\n");
            if (rs.next() == false) {
                System.out.println("Data empty!");
            } else {
                System.out.printf("%-15s%-21s%-20s%-15s%-17s\n", "Employee ID", "Fullname", "Position", "Salary", "Department Name");
                do {
                    String id = rs.getString("employee_id");
                    String full_name = rs.getString("full_name");
                    String position = rs.getString("position");
                    float salary = rs.getFloat("salary");
                    String department_name = rs.getString("department_name");

                    System.out.printf("%-15s%-21s%-20s%-15.2f%-17s\n", id, full_name, position, salary, department_name);
                }
                while (rs.next());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet countEmployeeInDept() {
        Connection con = null;
        DBContext db = new DBContext();
        con = db.getConnection();
        ResultSet rs = null;
        try {
            Statement sm = con.createStatement();
            rs = sm.executeQuery("SELECT b.department_name, COUNT(*) AS Total FROM employee a\n" +
                    "INNER JOIN department b ON b.id = a.department_id\n" +
                    "GROUP BY department_id");
            if (rs.next() == false) {
                System.out.println("Không có dữ liệu!");
            } else {
                System.out.println("DepartmentName\t\tTotal");
                do {
                    System.out.printf("%-15s%-21d\n", rs.getString("department_name"), rs.getInt("Total"));
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet avgSalaryMaxInDept() {
        Connection con = null;
        DBContext db = new DBContext();
        con = db.getConnection();
        ResultSet rs = null;
        try {
            Statement sm = con.createStatement();
            rs = sm.executeQuery("SELECT b.department_name, AVG(SALARY) AS MAX_AVG_SALARY\n" +
                    "FROM employee a\n" +
                    "INNER JOIN department b ON b.id = a.department_id\n" +
                    "GROUP BY b.department_name\n" +
                    "ORDER BY AVG(SALARY) DESC\n" +
                    "LIMIT 1");
            if (rs.next() == false) {
                System.out.println("Không có dữ liệu!");
            } else {
                System.out.println("Phòng ban\t\tLương trung bình cao nhất");
                do {
                    System.out.printf("%-15s%-20.2f\n", rs.getString("department_name"), rs.getFloat("MAX_AVG_SALARY"));
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet sumSalaryByYear() {
        Connection con = null;
        DBContext db = new DBContext();
        con = db.getConnection();
        ResultSet rs = null;
        try {
            Statement sm = con.createStatement();
            rs = sm.executeQuery("SELECT YEAR(hire_date) AS Year, SUM(salary) AS Total FROM employee \n" +
                    "GROUP BY YEAR(hire_date)");
            if (rs.next() == false) {
                System.out.println("Không có dữ liệu!");
            } else {
                System.out.println("Năm\t\t\t\tTổng lương");
                do {
                    System.out.printf("%-15s%-20.2f\n", rs.getString("Year"), rs.getFloat("Total"));
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
