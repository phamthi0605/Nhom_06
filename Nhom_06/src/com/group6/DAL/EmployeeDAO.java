package com.group6.DAL;

import com.group6.Entity.Admin;
import com.group6.Entity.Employee;
import com.group6.DB.DBContext;
import com.group6.Manager.Validation;

import java.sql.*;
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
            String sql = "INSERT INTO employee (employee_id, full_name, position, email, salary, person_Income_Tax, hire_date, " +
                    "department_id, is_manager) " +
                    "VALUES ('" + employee.getEmployee_id() + "', '" + employee.getFullName() + "', '" + employee.getPosition() + "'," +
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
                " WHERE employee_id = ?";
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
                employee.showData();
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
        String sql = "DELETE from employee WHERE employee_id = ?";
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
        ResultSet result = null;
        try {
            PreparedStatement ptm = con.prepareStatement("select * from employee where employee_id = ? or full_name =? or phone=? or email=? ");
            ptm.setString(1, employee.getEmployee_id());
            ptm.setString(2, "%" + employee.getFullName());
            ptm.setString(3, "%" + employee.getPhoneNumber());
            ptm.setString(4, "%" + employee.getEmail());
            result = ptm.executeQuery();
            if (result.next() == false) {
                System.out.println("Không có dữ liệu!");
            } else {
                System.out.printf("%-8s%-15s%-20s%-15s%-10s%-16s%-25s%-20s%-20s%-10s\n", "ID", "EmployeeID", "FullName", "Position", "Age", "Phone", "Email", "Salary", "Tax", "DepartmentID");
                do {
                    int id = result.getInt("id");
                    String employeeId = result.getString("employee_id");
                    String fullName = result.getString("full_name");
                    String position = result.getString("position");
                    int age = result.getInt("age");
                    String phoneNumber = result.getString("phone");
                    String email = result.getString("email");
                    float salary = result.getFloat("salary");
                    float tax = result.getFloat("person_Income_Tax");
//                    Date hireDate = result.getDate("hire_date");
//                    Date endDate = result.getDate("end_date");
                    int deptID = result.getInt("department_id");
                    System.out.printf("%-8s%-15s%-20s%-15s%-10d%-16s%-25s%-20f%-20f%-10d\n", id, employeeId, fullName, position, age, phoneNumber, email, salary, tax, deptID);

                }
                while (result.next());
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return result;

    }

    public List<Employee> getListEmployee() {
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
                " WHERE employee_id = ?";
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
                employee.showData();
            } else {
                System.out.println("Cập nhật dữ liệu thất bại!");
            }
            psmt.close();
            con.close();

        } catch (SQLException e) {
            e.getMessage();
        }

    }

    public static int checkInputDepartment(int check) {
        List<Integer> listDeptId = new ArrayList<>();
        Connection con = null;
        DBContext db = new DBContext();
        con = db.getConnection();
        try {
            Statement sm = con.createStatement();
            ResultSet resultSet = sm.executeQuery("SELECT id FROM department");
            while (resultSet.next()) {
                listDeptId.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (check < 1 || check > listDeptId.size()) {
            System.out.println("Nhập từ 1 - " + listDeptId.size() + "!");
            System.out.print("Nhập lại mã phòng ban: ");
            check = Validation.checkInputInt();
        }
        return check;
    }


    public void addDepartForEmployee() {
        String sql = "UPDATE employee SET full_name = ?, position=?, age=?, phone =?, email = ?, " +
                "salary =?, person_Income_Tax=?, hire_date=?, department_id=?, is_manager=?" +
                " WHERE employee_id = ?";
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
                employee.showData();
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
                        return null;
                    }
                }

            }
            return "1";
        }
        return isManager;
    }


}
