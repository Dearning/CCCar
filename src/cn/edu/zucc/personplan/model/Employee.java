package cn.edu.zucc.personplan.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * employee_id
    */
    private Integer employeeId;

    /**
    * net_id
    */
    private Integer netId;

    /**
    * name
    */
    private String name;

    /**
    * password
    */
    private String password;


    public Employee() {
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getNetId() {
        return netId;
    }

    public void setNetId(Integer netId) {
        this.netId = netId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}