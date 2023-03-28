package com.group6.Manager;

import com.group6.DAL.DepartmentDAO;
import com.group6.DAL.EmployeeDAO;
import com.group6.Entity.Department;
import com.group6.Entity.Employee;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FunctionDepartment {
    static DepartmentDAO managementDepartment = new DepartmentDAO();

    public static void getListDepartment() {
        System.out.println("Danh sách phòng ban");
        List<Department> departmentList = managementDepartment.getListDepartment();
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.printf("%-8s", "|");
        System.out.printf("%-20s", "Department ID");
        System.out.printf("%-10s", "|");
        System.out.printf("%-20s", "Department Name");
        System.out.printf("%-12s", "|");
        System.out.printf("%-20s", "Address");
        System.out.printf("%-8s\n", "|");
        System.out.println("-------------------------------------------------------------------------------------------");
        for (Department department : departmentList) {
            int id = department.getDepartmentId();
            String department_name = department.getDepartmentName();
            if (department_name == null) {
                department_name = "--";
            }
            String address = department.getAddress();
            if (address == null) {
                address = "--";
            }
            System.out.printf("%-8s", "|");
            System.out.printf("%-20s", id);
            System.out.printf("%-10s", "|");
            System.out.printf("%-20s", department_name);
            System.out.printf("%-12s", "|");
            System.out.printf("%-20s", address);
            System.out.printf("%-8s\n", "|");
            System.out.println("-------------------------------------------------------------------------------------------");
        }
    }

    public static void addDepartment() {
        List<Department> departmentList = managementDepartment.getListDepartment();
        System.out.println("Thêm phòng ban");
        int deptID;
        while (true) {
            Department department = new Department();
            System.out.println("Nhập mã phòng ban: ");
            deptID = Validation.checkInputInt();
            if (!Validation.checkDepartment(departmentList, deptID)) {
                System.err.println("Mã phòng ban " + deptID + " đã tồn tại. Nhập lại: ");
                continue;
            }
            System.out.println("Tên phòng ban: ");
            String deptName = Validation.checkInputString();
            System.out.println("Địa chỉ: ");
            String address = Validation.checkInputString();

            department.setDepartmentId(deptID);
            department.setDepartmentName(deptName);
            department.setAddress(address);
            //add department
            if (Validation.checkInputYN()) {
                DepartmentDAO management = new DepartmentDAO(department);
                management.addDepartment();
                break;
            }
        }

    }

    public static void updateDepartment() {
        List<Department> departmentList = managementDepartment.getListDepartment();
        System.out.println("Cập nhật phòng ban");
        getListDepartment();
        while (true) {
            System.out.println("Nhập mã phòng ban cần cập nhật: ");
            int depID = Validation.checkInputInt();
            List<Integer> listCheck = new ArrayList<>();
            for (int i = 0; i < departmentList.size(); i++) {
                listCheck.add(departmentList.get(i).getDepartmentId());
            }
            while (!listCheck.contains(depID)) {
                System.err.println("Bạn chỉ được phép chọn những bộ phận có trong danh sách!");
                depID = Validation.checkInputInt();
            }
            List<Department> listFindDeptByID = managementDepartment.getListDepartmentById(departmentList, depID);
            if (listFindDeptByID.isEmpty()) {
                System.err.println("Không có bộ phận này!");
                continue;
            } else {
                System.out.println("Kết quả tìm kiếm là: ");
                System.out.println("-------------------------------------------------------------------------------------------");
                System.out.printf("%-8s", "|");
                System.out.printf("%-20s", "Department ID");
                System.out.printf("%-10s", "|");
                System.out.printf("%-20s", "Department Name");
                System.out.printf("%-12s", "|");
                System.out.printf("%-20s", "Address");
                System.out.printf("%-8s\n", "|");
                System.out.println("-------------------------------------------------------------------------------------------");
                for (Department department : listFindDeptByID) {
                    int id = department.getDepartmentId();
                    String department_name = department.getDepartmentName();
                    if (department_name == null) {
                        department_name = "--";
                    }
                    String address = department.getAddress();
                    if (address == null) {
                        address = "--";
                    }
                    System.out.printf("%-8s", "|");
                    System.out.printf("%-20s", id);
                    System.out.printf("%-10s", "|");
                    System.out.printf("%-20s", department_name);
                    System.out.printf("%-12s", "|");
                    System.out.printf("%-20s", address);
                    System.out.printf("%-8s\n", "|");
                    System.out.println("-------------------------------------------------------------------------------------------");
                }
                System.out.println("Tên phòng ban:");
                String deptNameUpdate = Validation.checkInputString();
                System.out.println("Địa chỉ: ");
                String addressUpdate = Validation.checkInputString();
                //check employee change or not
                if (!Validation.checkchangeInforDepartment(listFindDeptByID.get(0), depID, deptNameUpdate, addressUpdate)) {
                    System.err.println("Dữ liệu không thay đổi");
                }
                if (Validation.checkInputYN()) {
                    //update department
                    Department department = new Department(depID, deptNameUpdate, addressUpdate);
                    DepartmentDAO managementEmployee = new DepartmentDAO(department);
                    managementEmployee.updateDepartment();
                }
            }
            break;
        }

    }

    public static String format(float value) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        return decimalFormat.format(value);
    }

    public static void deleteDepartment() {
        System.out.println("Xoá phòng ban");
        //Hiển thị danh sách tất cả nhân viên trong 1 phòng ban : trả về 1 list
        // rồi kiểm tra xem list đó có nhân viên ko : nếu có thì in ra ko xoá department dc
        // nếu list ko có nhân viên thì thực hiện xoá
        //System.out.println("Danh sách các phòng ban");
        //  List<Department> listDept = managementDepartment.getListDepartment();
        List<Department> departmentList = managementDepartment.getListDepartment();
        getListDepartment();
        Employee employee = new Employee();
        System.out.println("Nhập mã phòng ban: ");
        int deptId = Validation.checkInputInt();
        // check departmentID exist?
        List<Integer> listCheckDeptID = new ArrayList<>();
        for (int i = 0; i < departmentList.size(); i++) {
            listCheckDeptID.add(departmentList.get(i).getDepartmentId());
        }
        while (!listCheckDeptID.contains(deptId)) {
            System.err.println("Bạn chỉ được phép chọn những bộ phận có trong danh sách!");
            deptId = Validation.checkInputInt();
        }
        employee.setDepartment_id(deptId);
        EmployeeDAO managementEmployee = new EmployeeDAO(employee);
        List<Employee> employeeList = managementEmployee.getListEmployeeByDeptId();
        if (employeeList.size() > 0) {
            //  System.out.println(employeeList.size());
            System.err.println("Phòng ban: " + deptId + " có " + employeeList.size() + " nhân viên.");
            System.err.println("Không thể xoá.");
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
            System.out.println("------------------------------------------------------------------------------" +
                    "------------------------------------------------------------------------------------------" +
                    "-----------------------------------------------------------------------------------------" +
                    "-------------------------------------------------------------------------------------------" +
                    "--------------");
            for (Employee emp : employeeList) {
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
        } else {
            System.out.println("Phòng ban này có thể xoá!");
            Department department = new Department();
            department.setDepartmentId(deptId);
            if (Validation.checkInputYN()) {
                DepartmentDAO managementDepartment = new DepartmentDAO(department);
                managementDepartment.removeEmployeeByDeptID();
            }
            getListDepartment();

        }
    }

    public static void deleteEmployeeByDepartID() {
        System.out.println("Xoá nhân viên từ phòng ban");
        // Tìm kiếm những nhân viên cùng 1 phòng ban
        Employee employee = new Employee();
        List<Department> departmentList = managementDepartment.getListDepartment();
        getListDepartment();
        System.out.println("Nhập mã phòng ban: ");
        int deptId = Validation.checkInputInt();
        // check departmentID exist?
        List<Integer> listCheckDeptID = new ArrayList<>();
        for (int i = 0; i < departmentList.size(); i++) {
            listCheckDeptID.add(departmentList.get(i).getDepartmentId());
        }
        while (!listCheckDeptID.contains(deptId)) {
            System.err.println("Bạn chỉ được phép chọn những bộ phận có trong danh sách!");
            deptId = Validation.checkInputInt();
        }
        employee.setDepartment_id(deptId);
        // kiểm tra chỉ cho phép nhập giá trị trong List departmentID của department

        EmployeeDAO managementEmployee = new EmployeeDAO(employee);
        List<Employee> listEmployee = managementEmployee.getListEmployeeByDeptId();
        if (listEmployee.size() > 0) {
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
            System.out.println("------------------------------------------------------------------------------" +
                    "------------------------------------------------------------------------------------------" +
                    "-----------------------------------------------------------------------------------------" +
                    "-------------------------------------------------------------------------------------------" +
                    "--------------");
            for (Employee emp : listEmployee) {
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

            System.out.println("Nhập mã nhân viên mà bạn muốn xoá");
            String empID = Validation.checkInputString();
            // check
            List<String> listId = new ArrayList<>();
            for (int i = 0; i < listEmployee.size(); i++) {
                listId.add(listEmployee.get(i).getEmployee_id());
            }
            while (!listId.contains(empID)) {
                System.err.println("Bạn chỉ được phép chọn những nhân viên có trong danh sách!");
                empID = Validation.checkInputString();
            }
            employee.setEmployee_id(empID);
            EmployeeDAO deleteEmployeeId = new EmployeeDAO(employee);
            if (Validation.checkInputYN()) {
                deleteEmployeeId.removeEmployee();
            }

        } else {
            System.out.println("Phòng ban " + deptId + " chưa có nhân viên nào!");
        }

    }

    public static void transferDepartmentForEmployee() {
        Employee employee = new Employee();
        System.out.println("Điều chuyển phòng ban cho nhân viên");
        List<Department> departmentList = managementDepartment.getListDepartment();
        getListDepartment();

        System.out.println("Nhập mã phòng ban: ");
        int deptId = Validation.checkInputInt();
        List<Integer> listCheckDeptID = new ArrayList<>();
        for (int i = 0; i < departmentList.size(); i++) {
            listCheckDeptID.add(departmentList.get(i).getDepartmentId());
        }
        while (!listCheckDeptID.contains(deptId)) {
            System.err.println("Bạn chỉ được phép chọn những bộ phận có trong danh sách!Nhập lại:");
            deptId = Validation.checkInputInt();
        }
        employee.setDepartment_id(deptId);

        EmployeeDAO managementEmployee = new EmployeeDAO(employee);
        List<Employee> listEmployeeInDept = managementEmployee.getListEmployeeByDeptId();
        System.out.println("Danh sách nhân viên trong phòng ban " + deptId + " là: ");
        if (listEmployeeInDept.size() > 0) {
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
            System.out.println("------------------------------------------------------------------------------" +
                    "------------------------------------------------------------------------------------------" +
                    "-----------------------------------------------------------------------------------------" +
                    "-------------------------------------------------------------------------------------------" +
                    "--------------");
            for (Employee emp : listEmployeeInDept) {
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
            System.out.println("Nhập mã nhân viên muốn chuyển: ");
            String empIdTransfer = Validation.checkInputString();
            List<String> listCheckId = new ArrayList<>();

            for (int i = 0; i < listEmployeeInDept.size(); i++) {
                listCheckId.add(listEmployeeInDept.get(i).getEmployee_id());
            }
            while (!listCheckId.contains(empIdTransfer)) {
                System.out.println("Bạn chỉ được phép chọn nhân viên có trong danh sách bên trên!");
                empIdTransfer = Validation.checkInputString();
            }
            System.out.println("Thông tin của nhân viên " + empIdTransfer + " trước khi chuyển: ");
            //list all employee from database
            List<Employee> listEmployee = managementEmployee.getListEmployee();
            // get employee by id
            List<Employee> listFindEmployee = managementEmployee.getListEmployeeById(listEmployee, empIdTransfer);
            for (Employee emp : listFindEmployee) {
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
                System.out.println("------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "-------------------------------------------------------------------------------------------" +
                        "--------------");
                for (Employee findEmployee : listFindEmployee) {
                    String id = findEmployee.getEmployee_id();
                    String fullName = findEmployee.getFullName();
                    if (fullName == null) {
                        fullName = "--";
                    }
                    String position = findEmployee.getPosition();
                    if (position == null) {
                        position = "--";
                    }
                    int age = findEmployee.getAge();
                    String ageStr = String.valueOf(age);
                    if (age == 0) {
                        ageStr = "--";
                    }
                    String phone = findEmployee.getPhoneNumber();
                    if (phone == null) {
                        phone = "--";
                    }
                    String email = findEmployee.getEmail();
                    if (email == null) {
                        email = "--";
                    }
                    float salary = findEmployee.getSalary();
                    String displaySalary = String.valueOf(salary);
                    if (salary == 0) {
                        displaySalary = "--";
                    }
                    if (salary > 0) {
                        displaySalary = format(salary);
                    }
                    // displaySalary = format(salary);

                    float tax = findEmployee.getPerson_Income_Tax();
                    String convertTax = String.valueOf(tax);
                    if (tax == 0) {
                        convertTax = "--";
                    }
                    if (tax > 0) {
                        convertTax = format(tax);
                    }

                    String isManager = findEmployee.getIs_manager();
                    if (isManager == null) {
                        isManager = "--";
                    }
                    String hireDate = findEmployee.getHire_date();

                    int deptID = findEmployee.getDepartment_id();
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
                getListDepartment();
                System.out.println("Nhập mã phòng ban muốn chuyển: ");
                int deptIdTransfer = Validation.checkInputInt();
                List<Integer> listCheck = new ArrayList<>();
                for (int i = 0; i < departmentList.size(); i++) {
                    listCheck.add(departmentList.get(i).getDepartmentId());
                }
                while (!listCheck.contains(deptIdTransfer)) {
                    System.err.println("Bạn chỉ được phép chọn những bộ phận có trong danh sách!");
                    deptIdTransfer = Validation.checkInputInt();
                }
                emp.setDepartment_id(deptIdTransfer);
                EmployeeDAO manageUpdate = new EmployeeDAO(listFindEmployee.get(0));
                if (Validation.checkInputYN()) {
                    manageUpdate.transferDepartmentForEmployee();
                    System.out.println("Thông tin của nhân viên sau khi điều chuyển: ");
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
                    System.out.println("------------------------------------------------------------------------------" +
                            "------------------------------------------------------------------------------------------" +
                            "-----------------------------------------------------------------------------------------" +
                            "-------------------------------------------------------------------------------------------" +
                            "--------------");
                    for (Employee findEmployee : listFindEmployee) {
                        String id = findEmployee.getEmployee_id();
                        String fullName = findEmployee.getFullName();
                        if (fullName == null) {
                            fullName = "--";
                        }
                        String position = findEmployee.getPosition();
                        if (position == null) {
                            position = "--";
                        }
                        int age = findEmployee.getAge();
                        String ageStr = String.valueOf(age);
                        if (age == 0) {
                            ageStr = "--";
                        }
                        String phone = findEmployee.getPhoneNumber();
                        if (phone == null) {
                            phone = "--";
                        }
                        String email = findEmployee.getEmail();
                        if (email == null) {
                            email = "--";
                        }
                        float salary = findEmployee.getSalary();
                        String displaySalary = String.valueOf(salary);
                        if (salary == 0) {
                            displaySalary = "--";
                        }
                        if (salary > 0) {
                            displaySalary = format(salary);
                        }
                        // displaySalary = format(salary);

                        float tax = findEmployee.getPerson_Income_Tax();
                        String convertTax = String.valueOf(tax);
                        if (tax == 0) {
                            convertTax = "--";
                        }
                        if (tax > 0) {
                            convertTax = format(tax);
                        }

                        String isManager = findEmployee.getIs_manager();
                        if (isManager == null) {
                            isManager = "--";
                        }
                        String hireDate = findEmployee.getHire_date();

                        int deptID = findEmployee.getDepartment_id();
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
            }
        }
    }

    public static void addDepartmentForEmployee() {
        //Hiển thị danh sách những nhân viên chưa có phòng ban nào
        // Thêm phòng ban cho nhân viên
        // Xác nhận update thông tin
        // Update thông tin cho nhân viên đó
        System.out.println("Danh sách nhân viên chưa có phòng ban nào: ");
        List<Employee> listEmployeeDeptIsNull = managementDepartment.listEmployeeDeptIsNull();
        if (listEmployeeDeptIsNull.isEmpty()) {
            System.err.println("Tất cả phòng ban đều có nhân viên! ");
        } else {
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
            System.out.println("------------------------------------------------------------------------------" +
                    "------------------------------------------------------------------------------------------" +
                    "-----------------------------------------------------------------------------------------" +
                    "-------------------------------------------------------------------------------------------" +
                    "--------------");
            for (Employee findEmployee : listEmployeeDeptIsNull) {
                String id = findEmployee.getEmployee_id();
                String fullName = findEmployee.getFullName();
                if (fullName == null) {
                    fullName = "--";
                }
                String position = findEmployee.getPosition();
                if (position == null) {
                    position = "--";
                }
                int age = findEmployee.getAge();
                String ageStr = String.valueOf(age);
                if (age == 0) {
                    ageStr = "--";
                }
                String phone = findEmployee.getPhoneNumber();
                if (phone == null) {
                    phone = "--";
                }
                String email = findEmployee.getEmail();
                if (email == null) {
                    email = "--";
                }
                float salary = findEmployee.getSalary();
                String displaySalary = String.valueOf(salary);
                if (salary == 0) {
                    displaySalary = "--";
                }
                if (salary > 0) {
                    displaySalary = format(salary);
                }
                // displaySalary = format(salary);

                float tax = findEmployee.getPerson_Income_Tax();
                String convertTax = String.valueOf(tax);
                if (tax == 0) {
                    convertTax = "--";
                }
                if (tax > 0) {
                    convertTax = format(tax);
                }

                String isManager = findEmployee.getIs_manager();
                if (isManager == null) {
                    isManager = "--";
                }
                String hireDate = findEmployee.getHire_date();

                int deptID = findEmployee.getDepartment_id();
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
                System.out.println("------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "-------------------------------------------------------------------------------------------" +
                        "--------------");
            }
            System.out.println("Nhập mã nhân viên: ");
            String empID = Validation.checkInputString();
            List<String> listCheckId = new ArrayList<>();

            for (int i = 0; i < listEmployeeDeptIsNull.size(); i++) {
                listCheckId.add(listEmployeeDeptIsNull.get(i).getEmployee_id());
            }
            while (!listCheckId.contains(empID)) {
                System.out.println("Bạn chỉ được phép chọn nhân viên có trong danh sách bên trên!");
                empID = Validation.checkInputString();
            }
            System.out.println("Thông tin của nhân viên trước khi cập nhật: ");
            Employee employee = new Employee();
            employee.setEmployee_id(empID);
            EmployeeDAO managementEmployee = new EmployeeDAO(employee);
            List<Employee> list = managementEmployee.getListEmployee();
            List<Employee> employeeList = managementEmployee.getListEmployeeById(list, empID);
            for (Employee findEmployee : employeeList) {
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
                System.out.println("------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "-------------------------------------------------------------------------------------------" +
                        "--------------");
                String id = findEmployee.getEmployee_id();
                String fullName = findEmployee.getFullName();
                if (fullName == null) {
                    fullName = "--";
                }
                String position = findEmployee.getPosition();
                if (position == null) {
                    position = "--";
                }
                int age = findEmployee.getAge();
                String ageStr = String.valueOf(age);
                if (age == 0) {
                    ageStr = "--";
                }
                String phone = findEmployee.getPhoneNumber();
                if (phone == null) {
                    phone = "--";
                }
                String email = findEmployee.getEmail();
                if (email == null) {
                    email = "--";
                }
                float salary = findEmployee.getSalary();
                String displaySalary = String.valueOf(salary);
                if (salary == 0) {
                    displaySalary = "--";
                }
                if (salary > 0) {
                    displaySalary = format(salary);
                }
                // displaySalary = format(salary);

                float tax = findEmployee.getPerson_Income_Tax();
                String convertTax = String.valueOf(tax);
                if (tax == 0) {
                    convertTax = "--";
                }
                if (tax > 0) {
                    convertTax = format(tax);
                }

                String isManager = findEmployee.getIs_manager();
                if (isManager == null) {
                    isManager = "--";
                }
                String hireDate = findEmployee.getHire_date();

                int deptID = findEmployee.getDepartment_id();
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
                System.out.println("------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "-------------------------------------------------------------------------------------------" +
                        "--------------");
                System.out.println("Nhập phòng ban muốn thêm nhân viên: " + empID + ":");
                getListDepartment();
                List<Department> departmentList = managementDepartment.getListDepartment();
                int depID = Validation.checkInputInt();
                List<Integer> listCheck = new ArrayList<>();
                for (int i = 0; i < departmentList.size(); i++) {
                    listCheck.add(departmentList.get(i).getDepartmentId());
                }
                while (!listCheck.contains(depID)) {
                    System.err.println("Bạn chỉ được phép chọn những bộ phận có trong danh sách!");
                    depID = Validation.checkInputInt();
                }
                findEmployee.setDepartment_id(depID);
                EmployeeDAO manageUpdate = new EmployeeDAO(findEmployee);
                if (Validation.checkInputYN()) {
                    manageUpdate.addDepartForEmployee();
                }
            }
        }
    }

}

