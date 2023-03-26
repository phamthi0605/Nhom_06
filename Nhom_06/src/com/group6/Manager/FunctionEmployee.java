package com.group6.Manager;

import com.group6.DAL.DepartmentDAO;
import com.group6.DAL.EmployeeDAO;
import com.group6.Entity.Department;
import com.group6.Entity.Employee;
import com.group6.Entity.Position;
import com.group6.Entity.Salary;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class FunctionEmployee {
    static Scanner scanner = new Scanner(System.in);
    static EmployeeDAO managementEmployee = new EmployeeDAO();
    static DepartmentDAO mangementDerpartment = new DepartmentDAO();

    public static void addEmployee() {
        List<Employee> employeeList = managementEmployee.getListEmployee();
        while (true) {
            Employee employee = new Employee();
            System.out.println("Thêm nhân viên mới");
//            System.out.println("Mã nhân viên: ");
//            String employeeId = Validation.checkInputString();
//            if (!Validation.checkEmployeeById(employeeList, employeeId)) {
//                System.err.println("Nhân viên " + employeeId + " đã tồn tại. Nhập lại mã nhân viên: ");
//                continue;
//            }
            System.out.println("Họ và tên: ");
            String fullName = Validation.checkInputString();
            // nhập phòng ban cho nhân viên
            List<Department> departmentList = mangementDerpartment.getListDepartment();
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.printf("%-5s", "|");
            System.out.printf("%-15s", "Department ID");
            System.out.printf("%-12s", "|");
            System.out.printf("%-20s", "Department Name");
            System.out.printf("%-10s", "|");
            System.out.printf("%-20s", "Address");
            System.out.printf("%-8s\n", "|");
            System.out.println("-----------------------------------------------------------------------------------");
            for (Department department : departmentList) {
                System.out.printf("%-5s", "|");
                System.out.printf("%-15s", department.getDepartmentId());
                System.out.printf("%-12s", "|");
                System.out.printf("%-20s", department.getDepartmentName());
                System.out.printf("%-10s", "|");
                System.out.printf("%-20s", department.getAddress());
                System.out.printf("%-8s\n", "|");
            }
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("Chọn mã phòng ban:");
            int deptId = Validation.checkInputInt();
            List<Integer> listCheckDeptId = new ArrayList<>();

            for (int i = 0; i < departmentList.size(); i++) {
                listCheckDeptId.add(departmentList.get(i).getDepartmentId());
            }
            while (!listCheckDeptId.contains(deptId)) {
                System.err.println("Bạn chỉ được phép chọn những bộ phận trên!");
                deptId = Validation.checkInputInt();
            }
            // int deptId = managementEmployee.checkInputDepartment(Integer.parseInt(scanner.nextLine()));
            // nhân viên này có phải là quản lý ko?
            System.out.println("Quản lý (Yes/No): ");
            String deptManager = Validation.checkInputString();
            String isManager = managementEmployee.checkIsManager(deptId, deptManager);//y/n

            System.out.println("Vị trí: ");

            System.out.println("1." + Position.manager.name());
            System.out.println("2. " + Position.employee.name());
            int choosePosition = Validation.checkInputIntLimit(1, 2);
            // Tính lương của nhân viên theo vị trí, và tính thuế theo lương
            String position = null;
            float salary = 0;
            if (choosePosition == 1) {
                if (managementEmployee.isManager(deptId, deptManager)) {
                    position = Position.employee.name();
                    salary = Salary.employeeSalary;
                    employee.setPerson_Income_Tax(salary);
                } else {
                    position = Position.manager.name();
                    salary = Salary.managerSalary;
                    employee.setPerson_Income_Tax(salary);
                }

            }
            if (choosePosition == 2) {
                if (managementEmployee.isManager(deptId, deptManager)) {
                    position = Position.employee.name();
                    salary = Salary.employeeSalary;
                    employee.setPerson_Income_Tax(salary);
                } else {
                    position = Position.manager.name();
                    salary = Salary.managerSalary;
                    employee.setPerson_Income_Tax(salary);
                }

            }

            float tax = employee.getPerson_Income_Tax();
            System.out.println("Email: ");
            String email = Validation.checkInputString();
            List<String> listCheckEmail = new ArrayList<>();
            for (int i = 0; i < employeeList.size(); i++) {
                listCheckEmail.add(employeeList.get(i).getEmail());
            }
            while (listCheckEmail.contains(email)) {
                System.err.println("Email " + email + " đã tồn tại. Nhập lại: ");
                email = Validation.checkInputString();
            }
            // lấy currentDate
            Date date = new Date();
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String hireDate = formatDate.format(date);

            employee = new Employee(fullName, position, email, salary, tax, hireDate, deptId, isManager);
            // confirm xác nhận tiếp tục thực hiện thêm nhân viên hay ko
            if (Validation.checkInputYN()) {
                EmployeeDAO managementEmployee = new EmployeeDAO(employee);
                managementEmployee.addEmployee();
                break;
            }

            break;
        }

    }

    public static String format(float value) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        return decimalFormat.format(value);
    }

    public static void displayList() {
        List<Employee> employeeList = managementEmployee.getListEmployee();
        System.out.println("Danh sách nhân viên");
        if (employeeList.isEmpty()) {
            System.err.println("Không có dữ liệu.");
            return;
        }
//        for (Employee employee : employeeList) {
//            employee.showData();
//        }
        // Danh sách tất cả các nhân viên
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
        for (Employee employee : employeeList) {
            String id = employee.getEmployee_id();
            String fullName = employee.getFullName();
            if (fullName == null) {
                fullName = "--";
            }
            String position = employee.getPosition();
            if (position == null) {
                position = "--";
            }
            int age = employee.getAge();
            String ageStr = String.valueOf(age);
            if (age == 0) {
                ageStr = "--";
            }
            String phone = employee.getPhoneNumber();
            if (phone == null) {
                phone = "--";
            }
            String email = employee.getEmail();
            if (email == null) {
                email = "--";
            }
            float salary = employee.getSalary();
            String displaySalary = String.valueOf(salary);
            if (salary == 0) {
                displaySalary = "--";
            }
            if (salary > 0) {
                displaySalary = format(salary);
            }
            // displaySalary = format(salary);

            float tax = employee.getPerson_Income_Tax();
            String convertTax = String.valueOf(tax);
            if (tax == 0) {
                convertTax = "--";
            }
            if (tax > 0) {
                convertTax = format(tax);
            }

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
            System.out.printf("%-10s", ageStr);
            System.out.printf("%-12s", "|");
            System.out.printf("%-20s", phone);
            System.out.printf("%-19s", "|");
            System.out.printf("%-32s", email);
            System.out.printf("%-13s", "|");
            System.out.printf("%-23s", displaySalary);
            System.out.printf("%-13s", "|");
            System.out.printf("%-23s", convertTax);
            System.out.printf("%-13s", "|");
            System.out.printf("%-23s", hireDate);
            System.out.printf("%-10s", "|");
            System.out.printf("%-20s", deptID);
            System.out.printf("%-10s", "|");
            System.out.printf("%-23s", isManager);
            System.out.printf("%-8s", "|");
            System.out.println();
        }

        System.out.println("------------------------------------------------------------------------------" +
                "------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------" +
                "-------------------------------------------------------------------------------------------" +
                "--------------");
    }

    public static void updateEmployee() {
        List<Employee> employeeList = managementEmployee.getListEmployee();
        System.out.println("Cập nhật thông tin nhân viên");
        displayList();
        while (true) {
            Employee employeeUpdate = new Employee();
            System.out.println("Nhập mã nhân viên cần cập nhật: ");
            String employeeId = Validation.checkInputString();
            List<Employee> listFindEmployee = managementEmployee.getListEmployeeById(employeeList, employeeId);
            if (listFindEmployee.isEmpty()) {
                System.err.println("Không tìm thấy nhân viên. Nhập lại: ");
                continue;
            } else {
                System.out.println("Kết quả tìm kiếm: ");
//                System.out.printf("%-15s%-25s%-15s%-10s%-16s%-25s%-20s%-20s%-25s%-26s%-20s%-10s\n", "EmployeeID", "FullName", "Position", "Age", "Phone", "Email", "Salary", "Tax", "Hire Date", "End Date", "DepartmentID", "IsManager");
//                for (Employee employee : listFindEmployee) {
//                    employee.showData();
//                }
                // Thông tin nhân viên tìm thấy theo employeeID
                System.out.println("------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "-------------------------------------------------------------------------------------------" +
                        "--------------");
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
                for (Employee emp : listFindEmployee) {
                    String id = emp.getEmployee_id();
                    String fullName = emp.getFullName();
                    if (fullName == null) {
                        fullName = "--";
                    }
                    String position = emp.getPosition();
                    if (position == null) {
                        position = "--";
                    }
                    int age = emp.getAge();
                    String ageStr = String.valueOf(age);
                    if (age == 0) {
                        ageStr = "--";
                    }
                    String phone = emp.getPhoneNumber();
                    if (phone == null) {
                        phone = "--";
                    }
                    String email = emp.getEmail();
                    if (email == null) {
                        email = "--";
                    }
                    float salary = emp.getSalary();
                    String displaySalary = String.valueOf(salary);
                    if (salary == 0) {
                        displaySalary = "--";
                    }
                    if (salary > 0) {
                        displaySalary = format(salary);
                    }
                    // displaySalary = format(salary);

                    float tax = emp.getPerson_Income_Tax();
                    String convertTax = String.valueOf(tax);
                    if (tax == 0) {
                        convertTax = "--";
                    }
                    if (tax > 0) {
                        convertTax = format(tax);
                    }

                    String isManager = emp.getIs_manager();
                    if (isManager == null) {
                        isManager = "--";
                    }
                    String hireDate = emp.getHire_date();

                    int deptID = emp.getDepartment_id();
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
                    System.out.printf("%-10s", ageStr);
                    System.out.printf("%-12s", "|");
                    System.out.printf("%-20s", phone);
                    System.out.printf("%-19s", "|");
                    System.out.printf("%-32s", email);
                    System.out.printf("%-13s", "|");
                    System.out.printf("%-23s", displaySalary);
                    System.out.printf("%-13s", "|");
                    System.out.printf("%-23s", convertTax);
                    System.out.printf("%-13s", "|");
                    System.out.printf("%-23s", hireDate);
                    System.out.printf("%-10s", "|");
                    System.out.printf("%-20s", deptID);
                    System.out.printf("%-10s", "|");
                    System.out.printf("%-23s", isManager);
                    System.out.printf("%-8s", "|");
                    System.out.println();
                }

                System.out.println("------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "-------------------------------------------------------------------------------------------" +
                        "--------------");
                System.out.println("Họ và tên: ");
                String fullName = Validation.checkInputString();

                // nhập phòng ban cho nhân viên
                List<Department> departmentList = mangementDerpartment.getListDepartment();
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.printf("%-5s", "|");
                System.out.printf("%-15s", "Department ID");
                System.out.printf("%-12s", "|");
                System.out.printf("%-20s", "Department Name");
                System.out.printf("%-10s", "|");
                System.out.printf("%-20s", "Address");
                System.out.printf("%-8s\n", "|");
                System.out.println("-----------------------------------------------------------------------------------");
                for (Department department : departmentList) {
                    System.out.printf("%-5s", "|");
                    System.out.printf("%-15s", department.getDepartmentId());
                    System.out.printf("%-12s", "|");
                    System.out.printf("%-20s", department.getDepartmentName());
                    System.out.printf("%-10s", "|");
                    System.out.printf("%-20s", department.getAddress());
                    System.out.printf("%-8s\n", "|");
                }
                System.out.println("-----------------------------------------------------------------------------------");

                System.out.println("Chọn mã phòng ban:");
                int deptId = Validation.checkInputInt();
                List<Integer> listCheckDeptId = new ArrayList<>();

                for (int i = 0; i < departmentList.size(); i++) {
                    listCheckDeptId.add(departmentList.get(i).getDepartmentId());
                }
                while (!listCheckDeptId.contains(deptId)) {
                    System.err.println("Bạn chỉ được phép chọn những bộ phận có trong danh sách!");
                    deptId = Validation.checkInputInt();
                }
                // int deptId = managementEmployee.checkInputDepartment(Integer.parseInt(scanner.nextLine()));
                // nhân viên này có phải là quản lý ko?
                System.out.println("Quản lý (Yes/No): ");
                String deptManager = scanner.nextLine();
                String isManager = managementEmployee.checkIsManager(deptId, deptManager);//y/n

                System.out.println("Vị trí: ");

                System.out.println("1." + Position.manager.name());
                System.out.println("2. " + Position.employee.name());
                int choosePosition = Validation.checkInputIntLimit(1, 2);
                // Tính lương của nhân viên theo vị trí, và tính thuế theo lương
                String position = null;
                float salary = 0;
                if (choosePosition == 1) {
                    if (managementEmployee.isManager(deptId, deptManager)) {
                        position = Position.employee.name();
                        salary = Salary.employeeSalary;
                        employeeUpdate.setPerson_Income_Tax(salary);
                    } else {
                        position = Position.manager.name();
                        salary = Salary.managerSalary;
                        employeeUpdate.setPerson_Income_Tax(salary);
                    }

                }
                if (choosePosition == 2) {
                    if (managementEmployee.isManager(deptId, deptManager)) {
                        position = Position.employee.name();
                        salary = Salary.employeeSalary;
                        employeeUpdate.setPerson_Income_Tax(salary);
                    } else {
                        position = Position.manager.name();
                        salary = Salary.managerSalary;
                        employeeUpdate.setPerson_Income_Tax(salary);
                    }

                }
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
                    System.err.println("Số điện thoại " + phoneNumber + " đã tồn tại. Nhập lại: ");
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
                    System.err.println("Email " + email + " đã tồn tại. Nhập lại: ");
                    email = Validation.checkInputString();
                }
                float tax = employeeUpdate.getPerson_Income_Tax();
                // get currentDate
                Date date = new Date();
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String hireDate = formatDate.format(date);

                employeeUpdate = new Employee(employeeId, fullName, position, age, phoneNumber, email, salary, tax, hireDate, deptId, isManager);
                if (Validation.checkEmployeeExist(employeeList, email)) {
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
        displayList();
        List<Employee> employeeList = managementEmployee.getListEmployee();
        System.out.println("Nhập mã nhân viên cần xoá: ");
        String employeeId = Validation.checkInputString();
        List<String> listCheckEmpId = new ArrayList<>();

        for (int i = 0; i < employeeList.size(); i++) {
            listCheckEmpId.add(employeeList.get(i).getEmployee_id());
        }
        while (!listCheckEmpId.contains(employeeId)) {
            System.err.println("Bạn chỉ được phép chọn những nhân viên có trong danh sách!");
            employeeId = Validation.checkInputString();
        }
        Employee employee = new Employee();
        employee.setEmployee_id(employeeId);
        EmployeeDAO manage = new EmployeeDAO(employee);
        //confirm nếu muốn tiếp tục xoá nhân viên hay ko
        if (Validation.checkInputYN()) {
            manage.removeEmployee();
        }

    }

    public static void searchEmployee() {
        System.out.println("Tìm kiếm nhân viên theo bất kì mã, tên, số điện thoại hoặc email: ");
        Employee searchEmp = new Employee();
        System.out.println("Nhập mã nhân viên muốn tìm kiếm: ");
        String employeeID = Validation.checkInputString();
        searchEmp.setEmployee_id(employeeID);
        System.out.println("Nhập tên nhân viên muốn tìm kiếm: ");
        String fullName = Validation.checkInputString();
        searchEmp.setFullName(fullName);
        System.out.println("Nhập số điện thoại muốn tìm kiếm: ");
        String phone = Validation.checkInputString();
        searchEmp.setPhoneNumber(phone);
        System.out.println("Nhập email muốn tìm kiếm: ");
        String email = Validation.checkInputString();
        searchEmp.setEmail(email);
        EmployeeDAO managementEmployee = new EmployeeDAO(searchEmp);
        if (Validation.checkInputYN()) {
            managementEmployee.searchEmployee();
        }

    }
}
