package com.example.demo.entity;

public class Emp {
    private Integer id;
    private String empName;
    private String sex;
    private Integer age;
    private String deptName;
    private String empDegreeName;

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", empName='" + empName + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", deptName='" + deptName + '\'' +
                ", empDegreeName='" + empDegreeName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getEmpDegreeName() {
        return empDegreeName;
    }

    public void setEmpDegreeName(String empDegreeName) {
        this.empDegreeName = empDegreeName;
    }

    public Emp() {
    }

    public Emp(Integer id, String empName, String sex, Integer age, String deptName, String empDegreeName) {
        this.id = id;
        this.empName = empName;
        this.sex = sex;
        this.age = age;
        this.deptName = deptName;
        this.empDegreeName = empDegreeName;
    }
}
