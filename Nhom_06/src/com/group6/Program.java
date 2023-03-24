package com.group6;

import java.util.Scanner;

public class Program {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choose = 0;
        menuProgram();
        System.out.println("Chọn chức năng: ");
        choose = Validation.checkInputIntLimit(1, 5);
        while (choose != 1 && choose != 5) {
            System.out.println("Bạn chưa đăng nhập!");
            System.out.println("Chọn lại chức năng: ");
            choose = Validation.checkInputIntLimit(1, 5);
        }

        if (choose == 1) {
            System.out.println("Nhập username: ");
            String userName = scanner.nextLine();
            System.out.println("Nhập password: ");
            String password = scanner.nextLine();
            Admin admin = new Admin(userName, password);
            ManagementEmployee manageAccount = new ManagementEmployee(admin);
            boolean statusLogin = manageAccount.login();
            while (!statusLogin) {
                System.out.println("Đăng nhập lại");
                System.out.println("Nhập username: ");
                userName = scanner.nextLine();
                System.out.println("Nhập password: ");
                password = scanner.nextLine();
                admin = new Admin(userName, password);
                manageAccount = new ManagementEmployee(admin);
                statusLogin = manageAccount.login();
                if (statusLogin) {
                    break;
                }
            }
            if (statusLogin) {
                System.out.println("Đăng nhập thành công!");
                while (true) {
                    menuProgram();
                    System.out.println("Vui lòng nhập từ 2 đến 5 để chọn chức năng muốn sử dụng:");
                    choose = Validation.checkInputIntLimit(2, 5);
                    switch (choose) {
                        case 2:
                            while (true) {
                                System.out.println("\tQUẢN LÝ NHÂN VIÊN");
                                menuEmployee();
                                System.out.println("\tChọn chức năng quản lý nhân viên:");
                                int optionEmp = Validation.checkInputIntLimit(1, 6);
                                while (optionEmp < 1 || optionEmp > 6) {
                                    System.out.println("Chọn lại chức năng: ");
                                    optionEmp = Validation.checkInputIntLimit(1, 6);
                                }
                                if (optionEmp == 1) {
                                    FunctionEmployee.showListEmployee();
                                }
                                if (optionEmp == 2) {
                                    FunctionEmployee.addEmployee();
                                }
                                if (optionEmp == 3) {
                                    FunctionEmployee.updateEmployee();
                                }
                                if (optionEmp == 4) {
                                    FunctionEmployee.deleteEmployee();
                                }
                                if (optionEmp == 5) {
                                    FunctionEmployee.searchEmployee();
                                }
                                if (optionEmp == 6) {
                                    System.out.println("Thoát");
                                    break;
                                }
                            }
                            break;
                        case 3:
                            while (true) {
                                //ManagementDepartment mgtd = new ManagementDepartment();
                                System.out.println("\tQUẢN LÝ PHÒNG BAN");
                                menuDepartment();
                                System.out.println("\tChọn chức năng quản lý phòng ban:");
                                int optionDept = Validation.checkInputIntLimit(1, 8);
                                while (optionDept < 1 || optionDept > 8) {
                                    System.out.println("Chọn lại chức năng: ");
                                    optionDept = Validation.checkInputIntLimit(1, 8);
                                }
                                if (optionDept == 1) {
                                    FunctionDepartment.getListDepartment();
                                }
                                if (optionDept == 2) {
                                    FunctionDepartment.addDepartment();
                                }
                                if (optionDept == 3) {
                                    FunctionDepartment.updateDepartment();

                                }
                                if (optionDept == 4) {
                                    FunctionDepartment.deleteDepartment();

                                }
                                if (optionDept == 5) {
                                    FunctionDepartment.deleteEmployeeByDepartID();
                                }
                                if (optionDept == 6) {
                                    FunctionDepartment.transferDepartmentForEmployee();
                                }
                                if (optionDept == 7) {
                                    FunctionDepartment.addDepartmentForEmployee();
                                }
                                if (optionDept == 8) {
                                    System.out.println("Thoát");
                                    break;
                                }
                            }
                            break;
                        case 4:
                            System.out.println("\tTHỐNG KÊ");
                            menuStatistical();
                            System.out.println("\tChọn chức năng thống kê: ");
                            int optionStatistical = Validation.checkInputIntLimit(1, 4);
                            while (true) {
                                while (optionStatistical < 1 || optionStatistical > 4) {
                                    System.out.println("Chọn lại chức năng: ");
                                    optionStatistical = Validation.checkInputIntLimit(1, 4);
                                }
                                if (optionStatistical == 1) {
                                    System.out.println("Tìm top 5 nhân viên cao nhất, thấp nhất");
                                }
                                if (optionStatistical == 2) {
                                    System.out.println("Tìm top 5 nhân viên có lương cao nhất");
                                }
                                if (optionStatistical == 3) {
                                    System.out.println("Thống kê số lượng nhân viên mỗi phòng");
                                }
                                if (optionStatistical == 4) {
                                    System.out.println("Thống kê phòng ban có lương trung bình cao nhất");
                                }
                                if (optionStatistical == 5) {
                                    System.out.println("Thống kê mức lương theo từng năm");
                                }
                                if (optionStatistical == 6) {
                                    System.out.println("Thoát");
                                    break;
                                }
                            }
                            break;
                        case 5:
                            System.out.println("Thoát");
                            System.exit(0);

                    }
                }
            }

        }
    }

    public static void menuProgram() {
        System.out.println("MANAGEMENT RESOURCE EMPLOYEE");
        System.out.println("1. Đăng nhập");
        System.out.println("2. Quản lý nhân viên");
        System.out.println("3. Quản lý phòng ban");
        System.out.println("4. Thống kê ");
        System.out.println("5. Thoát");
    }

    public static void menuEmployee() {
        System.out.println("\t1.Danh sách nhân viên");
        System.out.println("\t2.Thêm nhân viên");
        System.out.println("\t3.Cập nhật thông tin nhân viên");
        System.out.println("\t4.Xoá nhân viên");
        System.out.println("\t5.Tìm kiếm nhân viên");
        System.out.println("\t6.Thoát");
    }

    public static void menuDepartment() {
        System.out.println("\t1.Danh sách phòng ban");
        System.out.println("\t2.Thêm phòng ban");
        System.out.println("\t3.Cập nhật thông tin phòng ban");
        System.out.println("\t4.Xoá phòng ban");//chưa làm được
        System.out.println("\t5.Xoá nhân viên từ phòng ban");
        System.out.println("\t6.Chuyển phòng ban");
        System.out.println("\t7.Thêm phòng ban cho nhân viên");
        System.out.println("\t8.Thoát");
    }

    public static void menuStatistical() {
        System.out.println("1. Tìm top 5 nhân viên cao nhất, thấp nhất");
        System.out.println("2. Tìm top 5 nhân viên có lương cao nhất");
        System.out.println("3. Thống kê số lượng nhân viên mỗi phòng");
        System.out.println("4. Thống kê phòng ban có lương trung bình cao nhất");
        System.out.println("5. Thống kê mức lương theo từng năm");
        System.out.println("6. Thoát");
    }

}
