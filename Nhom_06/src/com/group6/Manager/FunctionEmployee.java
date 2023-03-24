package com.group6.Manager;

import com.group6.DAL.EmployeeDAO;
import com.group6.Entity.Employee;
import com.group6.Entity.Position;
import com.group6.Entity.Salary;

import java.text.SimpleDateFormat;
import java.util.*;

public class FunctionEmployee {
    static Scanner scanner = new Scanner(System.in);
    static EmployeeDAO managementEmployee = new EmployeeDAO();

    public static void showListEmployee() {
        System.out.println("Hiển thị danh sách nhân viên");
        List<Employee> employeeList = managementEmployee.getListEmployee();
        System.out.printf("%-15s%-20s%-15s%-10s%-16s%-25s%-20s%-20s%-25s%-26s%-20s%-10s\n", "EmployeeID", "FullName", "Position", "Age", "Phone", "Email", "Salary", "Tax", "Hire Date", "End Date", "DepartmentID", "IsManager");
        for (Employee employee : employeeList) {
            employee.showData();
        }
    }

    public static void addEmployee() {
        List<Employee> employeeList = managementEmployee.getListEmployee();
        while (true) {
            Employee employee = new Employee();
            System.out.println("Thêm nhân viên mới");
            System.out.println("Mã nhân viên: ");
            String employeeId = Validation.checkInputString();
            System.out.println("Họ và tên: ");
            String fullName = Validation.checkInputString();
            if (!Validation.checkEmployeeById(employeeList, employeeId, fullName)) {
                System.err.println("Nhân viên " + employeeId + " đã tồn tại. Nhập lại mã nhân viên: ");
                continue;
            }
            System.out.println("Vị trí: ");
            // cần sửa đoạn này
            System.out.println("1." + Position.manager.name());
            System.out.println("2. " + Position.employee);
            int choosePosition = Validation.checkInputIntLimit(1, 2);

            System.out.println("Email: ");
            String email = Validation.checkInputString();
            List<String> listCheckEmail = new ArrayList<>();
            for (int i = 0; i < employeeList.size(); i++) {
                listCheckEmail.add(employeeList.get(i).getEmail());
            }
            while (listCheckEmail.contains(email)) {
                System.out.println("Email " + email + " đã tồn tại. Nhập lại: ");
                email = Validation.checkInputString();
            }
            // Tính lương của nhân viên theo vị trí, và tính thuế theo lương
            String position = null;
            float salary = 0;
            if (choosePosition == 1) {
                position = Position.manager.name();
                salary = Salary.managerSalary;
                employee.setPerson_Income_Tax(salary);
            }
            if (choosePosition == 2) {
                position = Position.employee.name();
                salary = Salary.employeeSalary;
                employee.setPerson_Income_Tax(salary);
            }

            float tax = employee.getPerson_Income_Tax();
            // lấy currentDate
            Date date = new Date();
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String hireDate = formatDate.format(date);
            // nhập phòng ban cho nhân viên
            System.out.println("Chọn mã phòng ban:");
            int deptId = managementEmployee.checkInputDepartment(Integer.parseInt(scanner.nextLine()));
            // nhân viên này có phải là quản lý ko?
            System.out.println("Quản lý (Yes/No): ");
            String deptManager = Validation.checkInputString();
            String isManager = managementEmployee.checkIsManager(deptId, deptManager);
            employee = new Employee(employeeId, fullName, position, email, salary, tax, hireDate, deptId, isManager);
            if (Validation.checkemployeeExist(employeeList, employeeId, email)) {
                EmployeeDAO managementEmployee = new EmployeeDAO(employee);
                managementEmployee.addEmployee();
                break;
            }

            break;
        }

    }

    // check lại
    public static void updateEmployee() {
        List<Employee> employeeList = managementEmployee.getListEmployee();
        if (employeeList.isEmpty()) {
            System.err.println("Không có dữ liệu.");
            return;
        }
        System.out.println("Cập nhật thông tin nhân viên");
        for (Employee employee : employeeList) {
            employee.showData();
        }
        while (true) {
            Employee employeeUpdate = new Employee();
            System.out.println("Nhập mã nhân viên cần cập nhật: ");
            String employeeId = scanner.nextLine();
            List<Employee> listFindEmployee = managementEmployee.getListEmployeeById(employeeList, employeeId);
            if (listFindEmployee.isEmpty()) {
                System.err.println("Không tìm thấy nhân viên");
                continue;
            } else {
                System.out.println("Kết quả tìm kiếm: ");
                System.out.printf("%-15s%-20s%-15s%-10s%-16s%-25s%-20s%-20s%-25s%-26s%-20s%-10s\n", "EmployeeID", "FullName", "Position", "Age", "Phone", "Email", "Salary", "Tax", "Hire Date", "End Date", "DepartmentID", "IsManager");
                for (Employee employee : listFindEmployee) {
                    employee.showData();
                }
                System.out.println("Họ và tên: ");
                String fullName = Validation.checkInputString();
                System.out.println("Vị trí: ");
                System.out.println("1." + Position.manager.name());
                System.out.println("2. " + Position.employee);
                int choosePosition = Validation.checkInputIntLimit(1, 2);

                System.out.println("Tuổi: ");
                int age = Validation.checkInputAge();
                // phone number is unique
                System.out.println("Số điện thoại: ");
                String phoneNumber = Validation.checkInputString();
                List<String> listCheckPhone = new ArrayList<>();
                for (int i = 0; i < employeeList.size(); i++) {
                    listCheckPhone.add(employeeList.get(i).getPhoneNumber());
                }
                while (listCheckPhone.contains(phoneNumber)) {
                    System.out.println("Số điện thoại " + phoneNumber + " đã tồn tại. Nhập lại: ");
                    phoneNumber = Validation.checkInputString();
                }
                //email is unique
                System.out.println("Email: ");
                String email = Validation.checkInputString();
                List<String> listCheckEmail = new ArrayList<>();
                for (int i = 0; i < employeeList.size(); i++) {
                    listCheckEmail.add(employeeList.get(i).getEmail());
                }
                while (listCheckEmail.contains(email)) {
                    System.out.println("Email " + email + " đã tồn tại. Nhập lại: ");
                    email = Validation.checkInputString();
                }
                String position = null;
                float salary = 0;
                if (choosePosition == 1) {
                    position = Position.manager.name();
                    salary = Salary.managerSalary;
                    employeeUpdate.setPerson_Income_Tax(salary);
                }
                if (choosePosition == 2) {
                    position = Position.employee.name();
                    salary = Salary.employeeSalary;
                    employeeUpdate.setPerson_Income_Tax(salary);
                }
                float tax = employeeUpdate.getPerson_Income_Tax();
                // get currentDate
                Date date = new Date();
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String hireDate = formatDate.format(date);

                // select department for employee
                System.out.println("Mã phòng ban:");
                int deptId = managementEmployee.checkInputDepartment(Integer.parseInt(scanner.nextLine()));

                // Are you manager?Yes/No?
                System.out.println("Quản lý của nhân viên: ");
                String deptManagerID = Validation.checkInputString();
                String isManager = managementEmployee.checkIsManager(deptId, deptManagerID);

                employeeUpdate = new Employee(employeeId, fullName, position, age, phoneNumber, email, salary, tax, hireDate, deptId, isManager);
                if (Validation.checkemployeeExist(employeeList, employeeId, email)) {
                    EmployeeDAO managementEmployee = new EmployeeDAO(employeeUpdate);
                    managementEmployee.updateEmployee();
                    break;
                }
            }
            break;
        }
    }

    public static void deleteEmployee() {
        System.out.println("Xoá nhân viên");
        System.out.println("Nhập mã nhân viên cần xoá: ");
        String employeeId = scanner.nextLine();
        Employee employee = new Employee();
        employee.setEmployee_id(employeeId);
        EmployeeDAO manage = new EmployeeDAO(employee);
        manage.removeEmployee();
    }

    public static void searchEmployee() {
        System.out.println("Tìm kiếm nhân viên theo bất kì mã, tên, số điện thoại hoặc email: ");
        Employee searchEmp = new Employee();
        System.out.println("Nhập mã nhân viên muốn tìm kiếm: ");
        String employeeID = scanner.nextLine();
        searchEmp.setEmployee_id(employeeID);
        System.out.println("Nhập tên nhân viên muốn tìm kiếm: ");
        String fullName = scanner.nextLine();
        searchEmp.setFullName(fullName);
        System.out.println("Nhập số điện thoại muốn tìm kiếm: ");
        String phone = scanner.nextLine();
        searchEmp.setPhoneNumber(phone);
        System.out.println("Nhập email muốn tìm kiếm: ");
        String email = scanner.nextLine();
        searchEmp.setEmail(email);
        EmployeeDAO managementEmployee = new EmployeeDAO(searchEmp);
        managementEmployee.searchEmployee();
    }
}
