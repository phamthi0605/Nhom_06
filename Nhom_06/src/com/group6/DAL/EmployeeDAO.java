package com.group6.DAL;

import com.group6.Entity.Admin;
import com.group6.Entity.Employee;
import com.group6.DB.DBContext;
import com.group6.Manager.Validation;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDAO {
    private Employee employee;
    private Admin admin;

    public EmployeeDAO() {

    }

    public EmployeeDAO(Admin admin) {
        this.admin = admin;
    }

    public EmployeeDAO(Employee employee) {
        this.employee = employee;
    }

    /**
     * Login in to the program
     *
     * @return true: login successfully and return false login failed
     */
    public boolean login() {
        Connection con = null;
        DBContext db = new DBContext();
        con = db.getConnection();
        boolean status = false;
        try {
            PreparedStatement ptm = con.prepareStatement("select username,password from admin where username = ? and password = ? ");
            ptm.setString(1, admin.getUserName());
            ptm.setString(2, admin.getPassword());

            ResultSet rs = ptm.executeQuery();
            status = rs.next();


        } catch (SQLException e) {
            e.getMessage();
        }
        return status;

    }

    /**
     * Add new employees to the database
     */
    public void addEmployee() {
        try {
            Connection con = null;
            DBContext db = new DBContext();
            con = db.getConnection();
            String sql = "INSERT INTO employee ( full_name, position, email, salary, person_Income_Tax, hire_date, " +
                    "department_id, is_manager) " +
                    "VALUES ('" + employee.getFullName() + "', '" + employee.getPosition() + "'," +
                    "'" + employee.getEmail() + "', '" + employee.getSalary() + "', '" + employee.getPerson_Income_Tax() + "', '" + employee.getHire_date() + "'," +
                    " '" + employee.getDepartment_id() + "', " + employee.getIs_manager() + ")";

            PreparedStatement sm = con.prepareStatement(sql);
            int count = sm.executeUpdate();
            if (count > 0) {
                System.out.println("Thêm nhân viên thành công!");
            } else {
                System.out.println("Thêm nhân viên thất bại!");
            }
            sm.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Update employee
     */
    public void updateEmployee() {
        String sql = "UPDATE employee SET full_name = ?, position=?, age=?, phone =?, email = ?, " +
                "salary =?, person_Income_Tax=?, hire_date=?, department_id=?, is_manager=?" +
                " WHERE id = ?";
        try {
            Connection con = null;
            DBContext db = new DBContext();
            con = db.getConnection();

            PreparedStatement psmt = con.prepareStatement(sql);

            // set value for parameter of sql statement
            psmt.setString(1, employee.getFullName());
            psmt.setString(2, employee.getPosition());
            psmt.setInt(3, employee.getAge());
            psmt.setString(4, employee.getPhoneNumber());
            psmt.setString(5, employee.getEmail());
            psmt.setFloat(6, employee.getSalary());
            psmt.setFloat(7, employee.getPerson_Income_Tax());
            psmt.setString(8, employee.getHire_date());
            psmt.setInt(9, employee.getDepartment_id());
            psmt.setString(10, employee.getIs_manager());

            psmt.setString(11, employee.getEmployee_id());

            psmt.executeUpdate();
            int count = psmt.executeUpdate();
            if (count > 0) {
                System.out.println("------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "-------------------------------------------------------------------------------------------" +
                        "--------------");
                System.out.printf("%-8s", "|");
                System.out.printf("%-20s", "Employee ID");
                System.out.printf("%-10s", "|");
                System.out.printf("%-20s", "Full Name");
                System.out.printf("%-12s", "|");
                System.out.printf("%-20s", "Postion");
                System.out.printf("%-8s", "|");
                System.out.printf("%-10s", "Age");
                System.out.printf("%-12s", "|");
                System.out.printf("%-20s", "Phone");
                System.out.printf("%-19s", "|");
                System.out.printf("%-32s", "Email");
                System.out.printf("%-13s", "|");
                System.out.printf("%-23s", "Salary");
                System.out.printf("%-13s", "|");
                System.out.printf("%-23s", "Tax");
                System.out.printf("%-13s", "|");
                System.out.printf("%-23s", "Hire Date");
                System.out.printf("%-10s", "|");
                System.out.printf("%-20s", "Department ID");
                System.out.printf("%-10s", "|");
                System.out.printf("%-23s", "Manager");
                System.out.printf("%-16s\n", "|");
                //System.out.printf("%-8s", "|");
                System.out.println("------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "-------------------------------------------------------------------------------------------" +
                        "--------------");
                String id = employee.getEmployee_id();
                String fullName = employee.getFullName();
                String position = employee.getPosition();
                int age = employee.getAge();
                String phone = employee.getPhoneNumber();
                if (phone == null) {
                    phone = "--";
                }
                String email = employee.getEmail();
                if (email == null) {
                    email = "--";
                }
                float salary = employee.getSalary();


                float tax = employee.getPerson_Income_Tax();

                String isManager = employee.getIs_manager();
                if (isManager == null) {
                    isManager = "--";
                }
                String hireDate = employee.getHire_date();

                int deptID = employee.getDepartment_id();
                if (isManager == null) {
                    isManager = "--";
                }
                System.out.printf("%-8s", "|");
                System.out.printf("%-20s", id);
                System.out.printf("%-10s", "|");
                System.out.printf("%-20s", fullName);
                System.out.printf("%-12s", "|");
                System.out.printf("%-20s", position);
                System.out.printf("%-8s", "|");
                System.out.printf("%-10s", age);
                System.out.printf("%-12s", "|");
                System.out.printf("%-20s", phone);
                System.out.printf("%-19s", "|");
                System.out.printf("%-32s", email);
                System.out.printf("%-13s", "|");
                System.out.printf("%-23s", format(salary));
                System.out.printf("%-13s", "|");
                System.out.printf("%-23s", format(tax));
                System.out.printf("%-13s", "|");
                System.out.printf("%-23s", hireDate);
                System.out.printf("%-10s", "|");
                System.out.printf("%-20s", deptID);
                System.out.printf("%-10s", "|");
                System.out.printf("%-23s", isManager);
                System.out.printf("%-8s", "|");
                System.out.println();

                System.out.println("------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "-------------------------------------------------------------------------------------------" +
                        "--------------");
            } else {
                System.out.println("cập nhật nhân viên thất bại!");
            }
            psmt.close();
            con.close();

        } catch (SQLException e) {
            e.getMessage();
        }
    }

    /**
     * Remove employee by employee id
     */
    public void removeEmployee() {
        String sql = "DELETE from employee WHERE id = ?";
        try {
            Connection con = null;
            DBContext db = new DBContext();
            con = db.getConnection();

            PreparedStatement sm = con.prepareStatement(sql);
            sm.setString(1, employee.getEmployee_id());
            sm.executeUpdate();
            System.out.println("Xoá nhân viên thành công!");
        } catch (SQLException e) {
            e.getMessage();
        }
    }


    public ResultSet searchEmployee() {
        Connection con = null;
        DBContext db = new DBContext();
        con = db.getConnection();
        ResultSet rs = null;
        try {
            PreparedStatement ptm = con.prepareStatement("select * from employee where id = ? or full_name like ? or phone like ? or email like ? ");
            ptm.setString(1, employee.getEmployee_id());
            ptm.setString(2, "%" + employee.getFullName());
            ptm.setString(3, "%" + employee.getPhoneNumber());
            ptm.setString(4, "%" + employee.getEmail());
            rs = ptm.executeQuery();
            if (rs.next() == false) {
                System.out.println("Không có dữ liệu!");
            } else {
                System.out.println("------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "----------------------------------");
                //   System.out.printf("|\t" + "%-21s%-20s%-20s%-20s%-20s%-30s%-20s%-20s%-20s\n", "Employee ID\t|", "Full Name\t\t|", "Position\t|", "Age\t|", "Phone\t|", "Email\t|", "Salary\t|", "Manager\t|", "Department Name\t|");
                System.out.printf("%-8s", "|");
                System.out.printf("%-20s", "Employee ID");
                System.out.printf("%-10s", "|");
                System.out.printf("%-20s", "Full Name");
                System.out.printf("%-12s", "|");
                System.out.printf("%-20s", "Postion");
                System.out.printf("%-8s", "|");
                System.out.printf("%-10s", "Age");
                System.out.printf("%-12s", "|");
                System.out.printf("%-20s", "Phone");
                System.out.printf("%-19s", "|");
                System.out.printf("%-32s", "Email");
                System.out.printf("%-13s", "|");
                System.out.printf("%-23s", "Salary");
                System.out.printf("%-10s", "|");
                System.out.printf("%-23s", "Manager");
                System.out.printf("%-10s\n", "|");

                System.out.println("------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "----------------------------------");
                do {
                    String id = rs.getString("id");
                    String fullName = rs.getString("full_name");
                    String position = rs.getString("position");
                    int age = rs.getInt("age");
                    String phone = rs.getString("phone");
                    if (phone == null) {
                        phone = "--";
                    }
                    String email = rs.getString("email");
                    if (email == null) {
                        email = "--";
                    }
                    float salary = rs.getFloat("salary");
                    String isManager = rs.getString("is_manager");
                    if (isManager == null) {
                        isManager = "--";
                    }
                    System.out.printf("%-8s", "|");
                    System.out.printf("%-20s", id);
                    System.out.printf("%-10s", "|");
                    System.out.printf("%-20s", fullName);
                    System.out.printf("%-12s", "|");
                    System.out.printf("%-20s", position);
                    System.out.printf("%-8s", "|");
                    System.out.printf("%-10s", age);
                    System.out.printf("%-12s", "|");
                    System.out.printf("%-20s", phone);
                    System.out.printf("%-19s", "|");
                    System.out.printf("%-32s", email);
                    System.out.printf("%-13s", "|");
                    System.out.printf("%-23s", format(salary));
                    System.out.printf("%-10s", "|");
                    System.out.printf("%-23s", isManager);
                    System.out.printf("%-10s", "|");
                    System.out.println();
                } while (rs.next());
                System.out.println("------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "----------------------------------");
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return rs;

    }

    public static ResultSet getList() {
        Connection con = null;
        DBContext db = new DBContext();
        con = db.getConnection();
        Statement sm = null;
        ResultSet rs = null;
        try {
            sm = con.createStatement();
            rs = sm.executeQuery("SELECT employee.id, employee.full_name ,employee.`position`,employee.age, employee.phone,\n" +
                    "employee.email, employee.salary, employee.is_manager,\n" +
                    "department.department_name\n" +
                    "FROM employee JOIN department ON employee.department_id = department.id");
            if (!rs.next()) {
                System.out.println("Không có dữ liệu!");
            } else {
                System.out.println("------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "----------------------------------");
                //   System.out.printf("|\t" + "%-21s%-20s%-20s%-20s%-20s%-30s%-20s%-20s%-20s\n", "Employee ID\t|", "Full Name\t\t|", "Position\t|", "Age\t|", "Phone\t|", "Email\t|", "Salary\t|", "Manager\t|", "Department Name\t|");
                System.out.printf("%-8s", "|");
                System.out.printf("%-20s", "Employee ID");
                System.out.printf("%-10s", "|");
                System.out.printf("%-20s", "Full Name");
                System.out.printf("%-12s", "|");
                System.out.printf("%-20s", "Postion");
                System.out.printf("%-8s", "|");
                System.out.printf("%-10s", "Age");
                System.out.printf("%-12s", "|");
                System.out.printf("%-20s", "Phone");
                System.out.printf("%-19s", "|");
                System.out.printf("%-32s", "Email");
                System.out.printf("%-13s", "|");
                System.out.printf("%-23s", "Salary");
                System.out.printf("%-10s", "|");
                System.out.printf("%-23s", "Manager");
                System.out.printf("%-10s", "|");
                System.out.printf("%-20s", "Department Name");
                System.out.printf("%-16s\n", "|");
                //System.out.printf("%-8s", "|");
                System.out.println("------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "----------------------------------");
                do {
                    String id = rs.getString("id");
                    String fullName = rs.getString("full_name");
                    String position = rs.getString("position");
                    int age = rs.getInt("age");
                    String phone = rs.getString("phone");
                    if (phone == null) {
                        phone = "--";
                    }
                    String email = rs.getString("email");
                    if (email == null) {
                        email = "--";
                    }
                    float salary = rs.getFloat("salary");
                    String isManager = rs.getString("is_manager");
                    if (isManager == null) {
                        isManager = "--";
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
                    System.out.printf("%-8s", "|");
                    System.out.printf("%-10s", age);
                    System.out.printf("%-12s", "|");
                    System.out.printf("%-20s", phone);
                    System.out.printf("%-19s", "|");
                    System.out.printf("%-32s", email);
                    System.out.printf("%-13s", "|");
                    System.out.printf("%-23s", format(salary));
                    System.out.printf("%-10s", "|");
                    System.out.printf("%-23s", isManager);
                    System.out.printf("%-10s", "|");
                    System.out.printf("%-20s", department_name);
                    System.out.printf("%-8s", "|");
                    System.out.println();
                    //  System.out.printf("|\t" + "%-20s%" + "-25s%-20s%-20d%-20s%-30s%-20s%-20s%-20s\n", id, fullName, position, age, phone, email, format(salary), isManager, department_name + "|");
                    // System.out.printf("%-5s", "|");
                    //System.out.printf("%-20s", id);
                    //System.out.printf("%-5s", "|");


                } while (rs.next());
                System.out.println("------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }

    public static String format(float value) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        return decimalFormat.format(value);
    }

    public List getListEmployee() {
        List<Employee> list = new ArrayList<>();
        Connection con = null;
        DBContext db = new DBContext();
        con = db.getConnection();
        Statement sm = null;
        try {
            sm = con.createStatement();
            ResultSet rs = sm.executeQuery("select * from employee");
            if (!rs.next()) {
                System.out.println("Không có dữ liệu!");
            } else {
                do {
                    Employee employee = new Employee(
                            rs.getString("id"),
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

    public List<Employee> getListEmployeeByDeptId() {
        List<Employee> list = new ArrayList<>();
        Connection con = null;
        DBContext db = new DBContext();
        con = db.getConnection();
        ResultSet rs = null;
        try {
            PreparedStatement ptm = con.prepareStatement("SELECT * FROM employee\n" +
                    "WHERE department_id =?");
            // set value for parameter of sql statement
            ptm.setInt(1, employee.getDepartment_id());
            rs = ptm.executeQuery();
            if (rs.next() == false) {
                System.out.println("Không có dữ liệu!");
            } else {
                do {
                    Employee employee = new Employee(
                            rs.getString("id"),
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
                            rs.getString("is_manager"));
                    list.add(employee);
                } while (rs.next());
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    //Get list employee find by id
    public List<Employee> getListEmployeeById(List<Employee> ls, String employeeID) {
        List<Employee> getList = new ArrayList<>();
        for (Employee employee : ls) {
            if (employeeID.equalsIgnoreCase(employee.getEmployee_id())) {
                getList.add(employee);
            }
        }
        return getList;
    }

    public void transferDepartmentForEmployee() {
        String sql = "UPDATE employee SET full_name = ?, position=?, age=?, phone =?, email = ?, " +
                "salary =?, person_Income_Tax=?, hire_date=?, department_id=?, is_manager=?" +
                " WHERE id = ?";
        try {
            Connection con = null;
            DBContext db = new DBContext();
            con = db.getConnection();

            PreparedStatement psmt = con.prepareStatement(sql);

            // set value for parameter of sql statement
            psmt.setString(1, employee.getFullName());
            psmt.setString(2, employee.getPosition());
            psmt.setInt(3, employee.getAge());
            psmt.setString(4, employee.getPhoneNumber());
            psmt.setString(5, employee.getEmail());
            psmt.setFloat(6, employee.getSalary());
            psmt.setFloat(7, employee.getPerson_Income_Tax());
            psmt.setString(8, employee.getHire_date());
            psmt.setInt(9, employee.getDepartment_id());
            psmt.setString(10, employee.getIs_manager());

            psmt.setString(11, employee.getEmployee_id());

            psmt.executeUpdate();
            int count = psmt.executeUpdate();
            System.out.println("Thông tin của nhân viên " + employee.getEmployee_id() + " sau khi điều chuyển phòng ban: ");
            if (count > 0) {
                System.out.println("------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "-------------------------------------------------------------------------------------------" +
                        "--------------");
                System.out.printf("%-8s", "|");
                System.out.printf("%-20s", "Employee ID");
                System.out.printf("%-10s", "|");
                System.out.printf("%-20s", "Full Name");
                System.out.printf("%-12s", "|");
                System.out.printf("%-20s", "Postion");
                System.out.printf("%-8s", "|");
                System.out.printf("%-10s", "Age");
                System.out.printf("%-12s", "|");
                System.out.printf("%-20s", "Phone");
                System.out.printf("%-19s", "|");
                System.out.printf("%-32s", "Email");
                System.out.printf("%-13s", "|");
                System.out.printf("%-23s", "Salary");
                System.out.printf("%-13s", "|");
                System.out.printf("%-23s", "Tax");
                System.out.printf("%-13s", "|");
                System.out.printf("%-23s", "Hire Date");
                System.out.printf("%-10s", "|");
                System.out.printf("%-20s", "Department ID");
                System.out.printf("%-10s", "|");
                System.out.printf("%-23s", "Manager");
                System.out.printf("%-16s\n", "|");
                //System.out.printf("%-8s", "|");
                System.out.println("------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "-------------------------------------------------------------------------------------------" +
                        "--------------");
                String id = employee.getEmployee_id();
                String fullName = employee.getFullName();
                String position = employee.getPosition();
                int age = employee.getAge();
                String phone = employee.getPhoneNumber();
                if (phone == null) {
                    phone = "--";
                }
                String email = employee.getEmail();
                if (email == null) {
                    email = "--";
                }
                float salary = employee.getSalary();


                float tax = employee.getPerson_Income_Tax();

                String isManager = employee.getIs_manager();
                if (isManager == null) {
                    isManager = "--";
                }
                String hireDate = employee.getHire_date();

                int deptID = employee.getDepartment_id();
                if (isManager == null) {
                    isManager = "--";
                }
                System.out.printf("%-8s", "|");
                System.out.printf("%-20s", id);
                System.out.printf("%-10s", "|");
                System.out.printf("%-20s", fullName);
                System.out.printf("%-12s", "|");
                System.out.printf("%-20s", position);
                System.out.printf("%-8s", "|");
                System.out.printf("%-10s", age);
                System.out.printf("%-12s", "|");
                System.out.printf("%-20s", phone);
                System.out.printf("%-19s", "|");
                System.out.printf("%-32s", email);
                System.out.printf("%-13s", "|");
                System.out.printf("%-23s", format(salary));
                System.out.printf("%-13s", "|");
                System.out.printf("%-23s", format(tax));
                System.out.printf("%-13s", "|");
                System.out.printf("%-23s", hireDate);
                System.out.printf("%-10s", "|");
                System.out.printf("%-20s", deptID);
                System.out.printf("%-10s", "|");
                System.out.printf("%-23s", isManager);
                System.out.printf("%-8s", "|");
                System.out.println();

                System.out.println("------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "-------------------------------------------------------------------------------------------" +
                        "--------------");
            } else {
                System.out.println("Điều chuyển phòng ban cho nhân viên thất bại!");
            }
            psmt.close();
            con.close();

        } catch (SQLException e) {
            e.getMessage();
        }

    }
    
    public void addDepartForEmployee() {
        String sql = "UPDATE employee SET full_name = ?, position=?, age=?, phone =?, email = ?, " +
                "salary =?, person_Income_Tax=?, hire_date=?, department_id=?, is_manager=?" +
                " WHERE id = ?";
        try {
            Connection con = null;
            DBContext db = new DBContext();
            con = db.getConnection();

            PreparedStatement stmt = con.prepareStatement(sql);

            // set value for parameter of sql statement
            stmt.setString(1, employee.getFullName());
            stmt.setString(2, employee.getPosition());
            stmt.setInt(3, employee.getAge());
            stmt.setString(4, employee.getPhoneNumber());
            stmt.setString(5, employee.getEmail());
            stmt.setFloat(6, employee.getSalary());
            stmt.setFloat(7, employee.getPerson_Income_Tax());
            stmt.setString(8, employee.getHire_date());
            stmt.setInt(9, employee.getDepartment_id());
            stmt.setString(10, employee.getIs_manager());

            stmt.setString(11, employee.getEmployee_id());

            stmt.executeUpdate();
            int count = stmt.executeUpdate();
            if (count > 0) {
                System.out.println("------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "-------------------------------------------------------------------------------------------" +
                        "--------------");
                System.out.printf("%-8s", "|");
                System.out.printf("%-20s", "Employee ID");
                System.out.printf("%-10s", "|");
                System.out.printf("%-20s", "Full Name");
                System.out.printf("%-12s", "|");
                System.out.printf("%-20s", "Postion");
                System.out.printf("%-8s", "|");
                System.out.printf("%-10s", "Age");
                System.out.printf("%-12s", "|");
                System.out.printf("%-20s", "Phone");
                System.out.printf("%-19s", "|");
                System.out.printf("%-32s", "Email");
                System.out.printf("%-13s", "|");
                System.out.printf("%-23s", "Salary");
                System.out.printf("%-13s", "|");
                System.out.printf("%-23s", "Tax");
                System.out.printf("%-13s", "|");
                System.out.printf("%-23s", "Hire Date");
                System.out.printf("%-10s", "|");
                System.out.printf("%-20s", "Department ID");
                System.out.printf("%-10s", "|");
                System.out.printf("%-23s", "Manager");
                System.out.printf("%-16s\n", "|");
                //System.out.printf("%-8s", "|");
                System.out.println("------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "-------------------------------------------------------------------------------------------" +
                        "--------------");
                String id = employee.getEmployee_id();
                String fullName = employee.getFullName();
                String position = employee.getPosition();
                int age = employee.getAge();
                String phone = employee.getPhoneNumber();
                if (phone == null) {
                    phone = "--";
                }
                String email = employee.getEmail();
                if (email == null) {
                    email = "--";
                }
                float salary = employee.getSalary();


                float tax = employee.getPerson_Income_Tax();

                String isManager = employee.getIs_manager();
                if (isManager == null) {
                    isManager = "--";
                }
                String hireDate = employee.getHire_date();

                int deptID = employee.getDepartment_id();
                if (isManager == null) {
                    isManager = "--";
                }
                System.out.printf("%-8s", "|");
                System.out.printf("%-20s", id);
                System.out.printf("%-10s", "|");
                System.out.printf("%-20s", fullName);
                System.out.printf("%-12s", "|");
                System.out.printf("%-20s", position);
                System.out.printf("%-8s", "|");
                System.out.printf("%-10s", age);
                System.out.printf("%-12s", "|");
                System.out.printf("%-20s", phone);
                System.out.printf("%-19s", "|");
                System.out.printf("%-32s", email);
                System.out.printf("%-13s", "|");
                System.out.printf("%-23s", format(salary));
                System.out.printf("%-13s", "|");
                System.out.printf("%-23s", format(tax));
                System.out.printf("%-13s", "|");
                System.out.printf("%-23s", hireDate);
                System.out.printf("%-10s", "|");
                System.out.printf("%-20s", deptID);
                System.out.printf("%-10s", "|");
                System.out.printf("%-23s", isManager);
                System.out.printf("%-8s", "|");
                System.out.println();

                System.out.println("------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "-------------------------------------------------------------------------------------------" +
                        "--------------");
            } else {
                System.out.println("Thêm phòng ban cho nhân viên thất bại!");
            }
            stmt.close();
            con.close();

        } catch (SQLException e) {
            e.getMessage();
        }

    }

    // Check (departmentID, isManager): unique
    // Kiểm tra xem nhân viên thuộc phòng ban này có là trưởng phòng ko
    public String checkIsManager(int deptID, String deptManager) {
        Connection con = null;
        DBContext db = new DBContext();
        con = db.getConnection();
        String isManager = null;// kết quả trả về
        if (deptManager.equals("y")) {
            Map<Integer, String> map = new HashMap<>();
            try {
                Statement sm = con.createStatement();
                ResultSet rs = sm.executeQuery("SELECT department_id,is_manager FROM employee");
                if (!rs.next()) {
                    System.out.println("Không có dữ liệu!");
                } else {
                    do {
                        map.put(rs.getInt("department_id"), rs.getString("is_manager"));

                    } while (rs.next());
                }
            } catch (SQLException e) {
                e.getMessage();
            }
            for (Map.Entry<Integer, String> isManagerDept : map.entrySet()) {
                while (deptID == isManagerDept.getKey() && isManagerDept.getValue().equals("1")) {
                    System.err.println("Phòng ban: " + deptID + " đã có trưởng phòng!");
                    System.out.println("Nhập lại (Yes/No): ");
                    deptManager = Validation.checkInputString();
                    if (deptManager.equals("n")) {
                        return null; // 1-1: 1 phòng ban chỉ có 1 quản lý ,1-0: dân đen
                    }
                }

            }
            return "1";
        }
        return null;
    }

    public boolean isManager(int deptID, String deptManager) {
        Connection con = null;
        DBContext db = new DBContext();
        con = db.getConnection();
        String isManager = null;// kết quả trả về
        if (deptManager.equals("y")) {
            Map<Integer, String> map = new HashMap<>();
            try {
                Statement sm = con.createStatement();
                ResultSet rs = sm.executeQuery("SELECT department_id,is_manager FROM employee");
                if (!rs.next()) {
                    System.out.println("Không có dữ liệu!");
                } else {
                    do {
                        map.put(rs.getInt("department_id"), rs.getString("is_manager"));

                    } while (rs.next());
                }
            } catch (SQLException e) {
                e.getMessage();
            }
            for (Map.Entry<Integer, String> isManagerDept : map.entrySet()) {
                while (deptID == isManagerDept.getKey() && isManagerDept.getValue().equals("1")) {
                    System.err.println("Phòng ban: " + deptID + " đã có trưởng phòng!");
                    System.err.println("Chỉ được nhập N(No):");
                    deptManager = Validation.checkInputString();
                    if (deptManager.equals("n")) {
                        return true;
                    }
                }

            }
            return false;
        }
        return true;
    }

}
