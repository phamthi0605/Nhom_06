package com.group6.DAL;

import com.group6.DB.DBContext;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

public class StatisticalDAO {
    public static String format(float value) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        return decimalFormat.format(value);
    }

    public static ResultSet top5MinSalary() {
        Connection con = null;
        DBContext db = new DBContext();
        con = db.getConnection();
        ResultSet rs = null;
        try {
            Statement sm = con.createStatement();
            rs = sm.executeQuery("SELECT a.id,  a.full_name, a.position, a.salary, b.department_name FROM employee a\n" +
                    "INNER JOIN department b ON b.id = a.department_id\n" +
                    "ORDER BY salary ASC\n" +
                    "LIMIT 5");
            if (rs.next() == false) {
                System.out.println("Không có dữ liệu!");
            } else {
                System.out.println("------------------------------------------------------------------------------" +
                        "---------------------------------------------------------------------------------");
                System.out.printf("%-8s", "|");
                System.out.printf("%-20s", "Employee ID");
                System.out.printf("%-10s", "|");
                System.out.printf("%-20s", "Full Name");
                System.out.printf("%-12s", "|");
                System.out.printf("%-20s", "Postion");
                System.out.printf("%-12s", "|");
                System.out.printf("%-23s", "Salary");
                System.out.printf("%-10s", "|");
                System.out.printf("%-23s", "Department Name");
                System.out.printf("%-10s\n", "|");
                System.out.println("------------------------------------------------------------------------------" +
                        "---------------------------------------------------------------------------------");
                do {
                    String id = rs.getString("id");
                    String fullName = rs.getString("full_name");
                    String position = rs.getString("position");

                    float salary = rs.getFloat("salary");
                    String displaySalary = String.valueOf(salary);
                    if (salary == 0) {
                        displaySalary = "--";
                    }
                    if (salary > 0) {
                        displaySalary = format(salary);
                    }
                    String department_name = rs.getString("department_name");
                    if (department_name == null) {
                        department_name = "--";
                    }
                    System.out.printf("%-8s", "|");
                    System.out.printf("%-20s", id);
                    System.out.printf("%-10s", "|");
                    System.out.printf("%-20s", fullName);
                    System.out.printf("%-12s", "|");
                    System.out.printf("%-20s", position);
                    System.out.printf("%-12s", "|");
                    System.out.printf("%-23s", displaySalary);
                    System.out.printf("%-10s", "|");
                    System.out.printf("%-23s", department_name);
                    System.out.printf("%-10s\n", "|");
                    System.out.println("------------------------------------------------------------------------------" +
                            "---------------------------------------------------------------------------------");
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
            rs = sm.executeQuery("SELECT a.id, a.full_name, a.position, a.salary, b.department_name FROM employee a\n" +
                    "INNER JOIN department b ON b.id = a.department_id\n" +
                    "ORDER BY salary Desc\n" +
                    "LIMIT 5\n");
            if (rs.next() == false) {
                System.out.println("Data empty!");
            } else {
                System.out.println("------------------------------------------------------------------------------" +
                        "---------------------------------------------------------------------------------");
                System.out.printf("%-8s", "|");
                System.out.printf("%-20s", "Employee ID");
                System.out.printf("%-10s", "|");
                System.out.printf("%-20s", "Full Name");
                System.out.printf("%-12s", "|");
                System.out.printf("%-20s", "Postion");
                System.out.printf("%-12s", "|");
                System.out.printf("%-23s", "Salary");
                System.out.printf("%-10s", "|");
                System.out.printf("%-23s", "Department Name");
                System.out.printf("%-10s\n", "|");
                System.out.println("------------------------------------------------------------------------------" +
                        "---------------------------------------------------------------------------------");
                do {
                    String id = rs.getString("id");
                    String fullName = rs.getString("full_name");
                    String position = rs.getString("position");

                    float salary = rs.getFloat("salary");
                    String displaySalary = String.valueOf(salary);
                    if (salary == 0) {
                        displaySalary = "--";
                    }
                    if (salary > 0) {
                        displaySalary = format(salary);
                    }
                    String department_name = rs.getString("department_name");
                    if (department_name == null) {
                        department_name = "--";
                    }
                    System.out.printf("%-8s", "|");
                    System.out.printf("%-20s", id);
                    System.out.printf("%-10s", "|");
                    System.out.printf("%-20s", fullName);
                    System.out.printf("%-12s", "|");
                    System.out.printf("%-20s", position);
                    System.out.printf("%-12s", "|");
                    System.out.printf("%-23s", displaySalary);
                    System.out.printf("%-10s", "|");
                    System.out.printf("%-23s", department_name);
                    System.out.printf("%-10s\n", "|");
                    System.out.println("------------------------------------------------------------------------------" +
                            "---------------------------------------------------------------------------------");

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
                System.out.println("----------------------------------------------------------");
                System.out.printf("%-8s", "|");
                System.out.printf("%-20s", "Phòng ban");
                System.out.printf("%-10s", "|");
                System.out.printf("%-20s", "Số lượng nhân viên");
                System.out.printf("%-12s\n", "|");
                System.out.println("----------------------------------------------------------");
                do {
                    String depatName = rs.getString("department_name");
                    int count = rs.getInt("Total");
                    System.out.printf("%-8s", "|");
                    System.out.printf("%-20s", depatName);
                    System.out.printf("%-10s", "|");
                    System.out.printf("%-20s", count);
                    System.out.printf("%-12s\n", "|");
                    System.out.println("----------------------------------------------------------");
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
                System.out.println("--------------------------------------------------------------------");
                System.out.printf("%-8s", "|");
                System.out.printf("%-20s", "Phòng ban");
                System.out.printf("%-10s", "|");
                System.out.printf("%-30s", "Lương trung bình/tháng");
                System.out.printf("%-12s\n", "|");
                System.out.println("--------------------------------------------------------------------");
                do {
                    String dept = rs.getString("department_name");
                    float avgSalary = rs.getFloat("MAX_AVG_SALARY");
                    System.out.printf("%-8s", "|");
                    System.out.printf("%-20s", dept);
                    System.out.printf("%-10s", "|");
                    String displaySalary = String.valueOf(avgSalary);
                    if (avgSalary == 0) {
                        displaySalary = "--";
                    }
                    if (avgSalary > 0) {
                        displaySalary = format(avgSalary);
                    }
                    System.out.printf("%-30s", displaySalary);
                    System.out.printf("%-12s\n", "|");
                    System.out.println("--------------------------------------------------------------------");
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
                System.out.println("--------------------------------------------------------------------");
                System.out.printf("%-8s", "|");
                System.out.printf("%-20s", "Năm");
                System.out.printf("%-10s", "|");
                System.out.printf("%-30s", "Tổng lương/Tháng");
                System.out.printf("%-12s\n", "|");
                System.out.println("--------------------------------------------------------------------");
                do {
                    String year = rs.getString("Year");
                    if (year == null) {
                        year = "--";
                    }
                    float salary = rs.getFloat("Total");
                    System.out.printf("%-8s", "|");
                    System.out.printf("%-20s", year);
                    System.out.printf("%-10s", "|");
                    String displaySalary = String.valueOf(salary);
                    if (salary == 0) {
                        displaySalary = "--";
                    }
                    if (salary > 0) {
                        displaySalary = format(salary);
                    }
                    System.out.printf("%-30s", displaySalary);
                    System.out.printf("%-12s\n", "|");
                    System.out.println("--------------------------------------------------------------------");
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
