package com.group6;

import java.util.List;
import java.util.Scanner;

public class Validation {
    static Scanner scanner = new Scanner(System.in);

    public static int checkInputInt() {
        while (true) {
            try {
                int result = Integer.parseInt(scanner.nextLine().trim());
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Phải nhập số nguyên. Nhập lại: ");
            }
        }
    }

    //NumberFormatException():bị ném ra khi một Chuỗi có định dạng sai và không thể chuyển đổi thành một số.
    public static int checkInputAge() {
        while (true) {
            try {
                int result = Integer.parseInt(scanner.nextLine().trim());
                if (result < 18) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Phải nhập số nguyên và tuổi phải lớn hơn 18.");
            }
        }
    }

    public static int checkInputIntLimit(int min, int max) {
        //loop until user input correct
        while (true) {
            try {
                int result = Integer.parseInt(scanner.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();

                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Nhập giá trị trong khoảng [" + min + ", " + max + "]: ");
            }
        }
    }

    //check user input yes/ no
    public static boolean checkInputYN() {
        //loop until user input correct
        while (true) {
            System.out.println("Bạn có muốn tiếp tục thực hiện (Yes/ No): ");
            String result = scanner.nextLine();
            //return true if user input y/Y
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            //return false if user input n/N
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Nhập y/Y hoặc n/N: ");
        }
    }

    //check employee exist
    public static boolean checkemployeeExist(List<Employee> list, String employeeId,
                                             String email) {
        //  int size = list.size();
        for (Employee employee : list) {
            if (employeeId.equals(employee.getEmployee_id())
                    && email.equalsIgnoreCase(employee.getEmail())) {
                return false;
            }
        }
        return true;
    }

    //check id and exist
    public static boolean checkEmployeeById(List<Employee> list, String employeeID, String fullName) {
        for (Employee employee : list) {
            if (employeeID.equalsIgnoreCase(employee.getEmployee_id())
                    && !fullName.equalsIgnoreCase(employee.getFullName())) {
                return false;
            }
        }
        return true;
    }

    //check employee change or not
    public static boolean checkChangeInfomation(Employee employee, String employeeId,
                                                String fullName, String position, String email,
                                                float salary, float tax,
                                                int deptId, String isManager) {
        if (employeeId.equalsIgnoreCase(employee.getEmployee_id())
                && fullName.equalsIgnoreCase(employee.getFullName())
                && position.equalsIgnoreCase(employee.getPosition())
                && email.equalsIgnoreCase(employee.getEmail())
                && salary == employee.getSalary()
                && tax == employee.getPerson_Income_Tax()
                && deptId == employee.getDepartment_id()
                && isManager.equals(employee.getIs_manager())
        ) {
            return false;
        }

        return true;
    }

    public static boolean checkDepartment(List<Department> list, int deptID, String departName) {
        for (Department department : list) {
            if (deptID == department.getDepartmentId()
                    && !departName.equalsIgnoreCase(department.getDepartmentName())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Kiểm tra dữ liệu nhập vào có bị trùng với giá trị cũ của department ko
     *
     * @param department      : giá trị của 1 bộ phận cũ
     * @param deptId          : mã phòng ban
     * @param deptNameUpdate: thay đổi tên phòng ban
     * @param addressUpdate   : thay đổi địa chỉ phòng ban
     * @return : true nếu giá trị không trùng với department cũ
     */

    public static boolean checkchangeInforDepartment(Department department, int deptId, String deptNameUpdate, String addressUpdate) {
        if (deptId == department.getDepartmentId()
                && deptNameUpdate.equalsIgnoreCase(department.getDepartmentName())
                && addressUpdate.equalsIgnoreCase(department.getAddress())
        ) {
            return false;
        }
        return true;
    }

    public static boolean checkChangeInfomation(Employee employee, String empID, int deptTransfer) {
        if (empID.equalsIgnoreCase(employee.getEmployee_id()) && deptTransfer == employee.getDepartment_id()) {
            return false;
        }
        return true;
    }

    public static String checkInputString() {
        //loop until user input true value
        while (true) {
            String result = scanner.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Không được để trống!");
            } else {
                return result;
            }
        }
    }

    public static float checkSalary() {
        //loop until user input correct
        while (true) {
            try {
                float result = Float.parseFloat(scanner.nextLine().trim());
                if (result < 0) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Lương phải lớn hơn 0.");
                System.out.print("Nhập lại: ");
            }
        }
    }
}
