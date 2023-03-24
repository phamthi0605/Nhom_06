package com.group6;

public class Department {
    private int departmentId;
    private String departmentName;
    private String address;

    public Department() {

    }

    public Department(int departmentId, String departmentName, String address) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.address = address;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public void showData() {
        System.out.printf("%-20d%-21s%-13s\n", departmentId, departmentName, address);
    }
}
