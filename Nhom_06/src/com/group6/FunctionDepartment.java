package com.group6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FunctionDepartment {
    static Scanner scanner = new Scanner(System.in);
    static ManagementDepartment managementDepartment = new ManagementDepartment();

    public static void getListDepartment() {
        System.out.println("Danh sách phòng ban");
        List<Department> departmentList = managementDepartment.getListDepartment();
        System.out.printf("%-10s%-21s%-13s\n", "ID", "Department Name", "Address");
        for (Department department : departmentList) {
            department.showData();
        }
    }

    public static void addDepartment() {
        List<Department> departmentList = managementDepartment.getListDepartment();
        while (true) {
            System.out.println("Thêm phòng ban");
            Department department = new Department();
            System.out.println("Nhập id phòng ban: ");
            int deptID = Integer.parseInt(scanner.nextLine());
            System.out.println("Tên phòng ban: ");
            String deptName = scanner.nextLine();
            if (!Validation.checkDepartment(departmentList, deptID, deptName)) {
                System.err.println("Department id has exist . Pleas re-input.");
                continue;
            }
            System.out.println("Địa chỉ: ");
            String address = scanner.nextLine();

            department.setDepartmentId(deptID);
            department.setDepartmentName(deptName);
            department.setAddress(address);
            //add department
            ManagementDepartment management = new ManagementDepartment(department);
            management.addDepartment();
            break;

        }
    }

    public static void updateDepartment() {
        List<Department> departmentList = managementDepartment.getListDepartment();
        System.out.println("Cập nhật phòng ban");
        System.out.println("Nhập mã phòng ban cần cập nhật: ");
        int deptId = Integer.parseInt(scanner.nextLine());
        List<Department> listFindDeptByID = managementDepartment.getListDepartmentById(departmentList, deptId);
        if (listFindDeptByID.isEmpty()) {
            System.err.println("Không có bộ phận này!");
        } else {
            System.out.println("Kết quả tìm kiếm là: ");
            System.out.printf("%-20s%-21s%-13s\n", "DepartmentId", "DepartmentName", "Address");
            for (Department department : listFindDeptByID) {
                department.showData();
            }
            System.out.print("Bạn có muốn cập nhật nhân viên(Yes/No): ");
            if (Validation.checkInputYN()) {
                System.out.println("Tên phòng ban:");
                String deptNameUpdate = scanner.nextLine();
                System.out.println("Địa chỉ: ");
                String addressUpdate = scanner.nextLine();
                //check employee change or not
                if (!Validation.checkchangeInforDepartment(listFindDeptByID.get(0), deptId, deptNameUpdate, addressUpdate)) {
                    System.err.println("Dữ liệu không thay đổi");
                }
                System.out.println("Dữ liệu department trước khi update: ");
                System.out.printf("%-20s%-21s%-13s\n", "DepartmentId", "DepartmentName", "Address");
                for (Department department : listFindDeptByID) {
                    department.showData();
                }
                //update department
                Department department = new Department(deptId, deptNameUpdate, addressUpdate);
                ManagementDepartment managementEmployee = new ManagementDepartment(department);
                managementEmployee.updateDepartment();
                System.out.println("Dữ liệu department sau khi update: ");
                department.showData();
            }
        }
    }

    public static void deleteDepartment() {
        System.out.println("Xoá phòng ban");
        //Hiển thị danh sách tất cả nhân viên trong 1 phòng ban : trả về 1 list
        // rồi kiểm tra xem list đó có nhân viên ko : nếu có thì in ra ko xoá department dc
        // nếu list ko có nhân viên thì thực hiện xoá
        System.out.println("Danh sách các phòng ban");
        List<Department> listDept = managementDepartment.getListDepartment();
        System.out.printf("%-10s%-31s%-13s\n", "ID", "Department Name", "Address");
        for (Department department : listDept) {
            department.showData();
        }
        Employee employee = new Employee();
        System.out.println("Nhập mã phòng ban: ");
        int deptId = ManagementEmployee.checkInputDepartment(Validation.checkInputInt());
        employee.setDepartment_id(deptId);
        ManagementEmployee managementEmployee = new ManagementEmployee(employee);
        List<Employee> employeeList = managementEmployee.getListEmployeeByDeptId();
        if (employeeList.size() > 0) {
            System.out.println(employeeList.size());
            System.out.println("Phòng ban " + deptId + " đang tồn tại " + employeeList.size() + " viên nhân. Không thể xoá!");
            System.out.printf("%-15s%-20s%-15s%-10s%-16s%-25s%-20s%-20s%-25s%-26s%-20s%-10s\n", "EmployeeID", "FullName", "Position", "Age", "Phone", "Email", "Salary", "Tax", "Hire Date", "End Date", "DepartmentID", "IsManager");
            for (Employee obj : employeeList) {
                obj.showData();
            }
        } else {
            System.out.println("Phòng ban này có thể xoá!");
            Department department = new Department();
            department.setDepartmentId(deptId);
            ManagementDepartment managementDepartment = new ManagementDepartment(department);
            managementDepartment.removeEmployeeByDeptID();
            List<Department> departmentList = managementDepartment.getListDepartment();
            System.out.printf("%-10s%-21s%-13s\n", "ID", "Department Name", "Address");
            for (Department show : departmentList) {
                show.showData();
            }
        }
    }

    public static void deleteEmployeeByDepartID() {
        System.out.println("Xoá nhân viên từ phòng ban");
        // Tìm kiếm những nhân viên cùng 1 phòng ban
        Employee employee = new Employee();
        System.out.println("Danh sách các phòng ban");
        List<Department> departmentList = managementDepartment.getListDepartment();
        System.out.printf("%-10s%-31s%-13s\n", "ID", "Department Name", "Address");
        for (Department department : departmentList) {
            department.showData();
        }
        System.out.println("Nhập mã phòng ban: ");
        int deptId = Validation.checkInputInt();
        employee.setDepartment_id(deptId);
        // kiểm tra chỉ cho phép nhập giá trị trong List departmentID của department

        ManagementEmployee managementEmployee = new ManagementEmployee(employee);
        List<Employee> listEmployee = managementEmployee.getListEmployeeByDeptId();
        if (listEmployee.size() > 0) {
            System.out.printf("%-15s%-20s%-15s%-10s%-16s%-25s%-20s%-20s%-25s%-26s%-20s%-10s\n", "EmployeeID", "FullName", "Position", "Age", "Phone", "Email", "Salary", "Tax", "Hire Date", "End Date", "DepartmentID", "IsManager");
            for (Employee emp : listEmployee) {
                emp.showData();
            }
            System.out.println("Nhập mã nhân viên mà bạn muốn xoá");
            String empID = scanner.nextLine();
            employee.setEmployee_id(empID);
            ManagementEmployee deleteEmployeeId = new ManagementEmployee(employee);
            deleteEmployeeId.removeEmployee();
        } else {
            System.out.println("Phòng ban " + deptId + " chưa có nhân viên nào!");
        }

        // xoá những nhân viên cùng phòng ban đó đi và hiển thị lại kết quả
    }

    public static void transferDepartmentForEmployee() {
        // Có trong thực hiện update thông tin nhân viên
        Employee employee = new Employee();
        System.out.println("Chuyển phòng ban cho nhân viên");
        System.out.println("Danh sách các phòng ban");
        List<Department> departmentList = managementDepartment.getListDepartment();
        System.out.printf("%-10s%-31s%-13s\n", "ID", "Department Name", "Address");
        for (Department department : departmentList) {
            department.showData();
        }
        System.out.println("Nhập mã phòng ban: ");
        int deptId = Validation.checkInputInt();
        employee.setDepartment_id(deptId);
        ManagementEmployee managementEmployee = new ManagementEmployee(employee);
        List<Employee> listEmployeeInDept = managementEmployee.getListEmployeeByDeptId();
        System.out.println("Danh sách nhân viên trong phòng ban " + deptId + " là ");
        if (listEmployeeInDept.size() > 0) {
            System.out.printf("%-15s%-20s%-15s%-10s%-16s%-25s%-20s%-20s%-25s%-26s%-20s%-10s\n", "EmployeeID", "FullName", "Position", "Age", "Phone", "Email", "Salary", "Tax", "Hire Date", "End Date", "DepartmentID", "IsManager");
            for (Employee emp : listEmployeeInDept) {
                emp.showData();
            }
            System.out.println("Nhập mã nhân viên muốn chuyển: ");
            String empIdTransfer = Validation.checkInputString();
            List<String> listCheckId = new ArrayList<>();

            for (int i = 0; i < listEmployeeInDept.size(); i++) {
                listCheckId.add(listEmployeeInDept.get(i).getEmployee_id());
            }
            while (!listCheckId.contains(empIdTransfer)) {
                System.out.println("Bạn chỉ được phép chọn employee id có trong danh sách bên trên!");
                empIdTransfer = Validation.checkInputString();
            }
            System.out.println("Thông tin của nhân viên " + empIdTransfer + " trước khi chuyển: ");
            //list all employee from database
            List<Employee> listEmployee = managementEmployee.getListEmployee();
            // get employee by id
            List<Employee> listFindEmployee = managementEmployee.getListEmployeeById(listEmployee, empIdTransfer);
            for (Employee emp : listFindEmployee) {
                emp.showData();
                if (Validation.checkInputYN()) {
                    System.out.println("Nhập mã phòng ban muốn chuyển: ");
                    int deptIdTransfer = managementEmployee.checkInputDepartment(Integer.parseInt(scanner.nextLine()));
                    emp.setDepartment_id(deptIdTransfer);
                    ManagementEmployee manageUpdate = new ManagementEmployee(emp);
                    manageUpdate.transferDepartmentForEmployee();
                    //emp.showData();

                }
            }


        } else {
            System.out.println("Phòng ban " + deptId + " chưa có nhân viên nào!");
        }
    }

    public static void addDepartmentForEmployee() {
        //Hiển thị danh sách những nhân viên chưa có phòng ban nào
        // Thêm phòng ban cho nhân viên
        // Xác nhận update thông tin
        // Update thông tin cho nhân viên đó
        System.out.println("Danh sách nhân viên chưa có phòng ban nào: ");
        List<Employee> listEmployeeDeptIsNull = managementDepartment.listEmployeeDeptIsNull();
        System.out.printf("%-15s%-20s%-15s%-10s%-16s%-25s%-20s%-20s%-25s%-26s%-20s%-10s\n", "EmployeeID", "FullName", "Position", "Age", "Phone", "Email", "Salary", "Tax", "Hire Date", "End Date", "DepartmentID", "IsManager");
        for (Employee emp : listEmployeeDeptIsNull) {
            emp.showData();
        }
        System.out.println("Nhập mã nhân viên: ");
        String empID = Validation.checkInputString();
        List<String> listCheckId = new ArrayList<>();

        for (int i = 0; i < listEmployeeDeptIsNull.size(); i++) {
            listCheckId.add(listEmployeeDeptIsNull.get(i).getEmployee_id());
        }
        while (!listCheckId.contains(empID)) {
            System.out.println("Bạn chỉ được phép chọn employee id có trong danh sách bên trên!");
            empID = Validation.checkInputString();
        }
        System.out.println("Thông tin của nhân viên trước khi update: ");
        Employee employee = new Employee();
        employee.setEmployee_id(empID);
        ManagementEmployee managementEmployee = new ManagementEmployee(employee);
        List<Employee> list = managementEmployee.getListEmployee();
        List<Employee> employeeList = managementEmployee.getListEmployeeById(list, empID);
        for (Employee obj : employeeList) {
            obj.showData();
            System.out.println("Nhập phòng ban muốn thêm nhân viên: " + empID + ":");
            int deptId = Validation.checkInputInt();
            obj.setDepartment_id(deptId);
            ManagementEmployee manageUpdate = new ManagementEmployee(obj);
            manageUpdate.addDepartForEmployee();
        }

    }
}
