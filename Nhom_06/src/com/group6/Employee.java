package com.group6;

public class Employee {
    // private int id;
    private String employee_id;
    private String fullName;
    private String position;
    private int age;
    private String phoneNumber;
    private String email;
    private float salary;
    private float person_Income_Tax;
    private String hire_date;
    private String endDate;
    private int department_id;
    private String is_manager;

    public Employee() {

    }

    public Employee(String employee_id, String fullName, String position, int age, String phoneNumber, String email, float salary, float person_Income_Tax,
                    String hire_date, String endDate, int department_id, String is_manager) {
        this.employee_id = employee_id;
        this.fullName = fullName;
        this.position = position;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.salary = salary;
        this.person_Income_Tax = person_Income_Tax;
        this.hire_date = hire_date;
        this.endDate = endDate;
        this.department_id = department_id;
        this.is_manager = is_manager;
    }

    public Employee(String employeeId, String fullName, String position, String email,
                    float salary, float tax, String hireDate, int deptID, String isManager) {
        this.employee_id = employeeId;
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.salary = salary;
        this.person_Income_Tax = tax;
        this.hire_date = hireDate;
        this.department_id = deptID;
        this.is_manager = isManager;
    }


    public Employee(String employee_id, String fullName, String position, int age,
                    String phoneNumber, String email, float salary, float person_Income_Tax,
                    String hire_date, int department_id, String is_manager) {
        this.employee_id = employee_id;
        this.fullName = fullName;
        this.position = position;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.salary = salary;
        this.person_Income_Tax = person_Income_Tax;
        this.hire_date = hire_date;
        this.department_id = department_id;
        this.is_manager = is_manager;
    }


    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getPerson_Income_Tax() {
        return person_Income_Tax;
    }

    public void setPerson_Income_Tax(float salary) {
        if (salary <= 5000000) {
            salary = (float) (salary * 0.05);
        } else if (salary >= 5000000 && salary <= 10000000) {
            salary = (float) (salary * 0.1);
        } else if (salary >= 10000000 && salary <= 18000000) {
            salary = (float) (salary * 0.15);
        } else {
            salary = (float) (salary * 0.2);
        }
        this.person_Income_Tax = salary;
    }


    public String getHire_date() {
        return hire_date;
    }

    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getIs_manager() {
        return is_manager;
    }

    public void setIs_manager(String is_manager) {
        this.is_manager = is_manager;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public void showData() {
        System.out.printf("%-15s%-20s%-15s%-10d%-16s%-25s%-20.2f%-20.2f%-25s%-31s%-16d%-12s\n", employee_id, fullName, position, age, phoneNumber, email, salary, person_Income_Tax, hire_date, endDate, department_id, is_manager);
    }
}