package com.group6;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagementDepartment {
    Department department;

    public ManagementDepartment() {

    }

    public ManagementDepartment(Department dept) {
        this.department = dept;
    }

    public List<Department> getListDepartment() {
        List<Department> list = new ArrayList<>();
        Connection con = null;
        DBContext db = new DBContext();
        con = db.getConnection();
        Statement sm = null;
        try {
            sm = con.createStatement();
            ResultSet rs = sm.executeQuery("SELECT * FROM department");
            if (!rs.next()) {
                System.out.println("Data empty!");
            } else {
                do {
                    Department department = new Department(
                            rs.getInt("id"),
                            rs.getString("department_name"),
                            rs.getString("address")
                    );
                    list.add(department);
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void addDepartment() {
        try {
            Connection con = null;
            DBContext db = new DBContext();
            con = db.getConnection();
            PreparedStatement sm = con.prepareStatement("INSERT INTO department (department_name, address) VALUE (?,?)");
            sm.setString(1, department.getDepartmentName());
            sm.setString(2, department.getAddress());
            int count = sm.executeUpdate();
            if (count > 0) {
                System.out.println("Thêm phòng ban thành công!");
            } else {
                System.out.println("Thêm phòng ban thất bại!");
            }
            sm.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateDepartment() {
        Scanner scanner = new Scanner(System.in);
        String sql = "UPDATE department SET department_name = ?, address=? WHERE id = ?";
        try {
            Connection con = null;
            DBContext db = new DBContext();
            con = db.getConnection();
            PreparedStatement sm = con.prepareStatement(sql);

            sm.setString(1, department.getDepartmentName());
            sm.setString(2, department.getAddress());
            sm.setInt(3, department.getDepartmentId());
            sm.executeUpdate();
            int count = sm.executeUpdate();
            if (count > 0) {
                System.out.println("Cập nhật phòng ban thành công.");
            } else {
                System.out.println("Cập nhật phòng ban thất bại.");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void removeEmployeeByDeptID() {
        String sql = "DELETE from department WHERE id = ?";
        try {
            Connection con = null;
            DBContext db = new DBContext();
            con = db.getConnection();

            PreparedStatement sm = con.prepareStatement(sql);
            sm.setInt(1, department.getDepartmentId());
            sm.executeUpdate();
            System.out.println("Xoá phòng ban thành công!");

        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public List<Department> getListDepartmentById(List<Department> list, int deptID) {
        List<Department> getListDept = new ArrayList<>();
        for (Department department : list) {
            if (deptID == department.getDepartmentId()) {
                getListDept.add(department);
            }
        }
        return getListDept;
    }

    public List<Employee> listEmployeeDeptIsNull() {
        List<Employee> list = new ArrayList<>();
        Connection con = null;
        DBContext db = new DBContext();
        con = db.getConnection();
        try {
            Statement sm = con.createStatement();
            ResultSet rs = sm.executeQuery("SELECT * FROM employee\n" +
                    "WHERE department_id IS NULL");
            if (!rs.next()) {
                System.out.println("Không có dữ liệu!");
            } else {
                do {
                    Employee employee = new Employee(
                            rs.getString("employee_id"),
                            rs.getString("full_name"),
                            rs.getString("position"),
                            rs.getInt("age"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getFloat("salary"),
                            rs.getFloat("person_Income_Tax"),
                            rs.getString("hire_date"),
                            rs.getString("end_date"),
                            rs.getInt("department_id"),
                            rs.getString("is_manager")
                    );
                    list.add(employee);
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
