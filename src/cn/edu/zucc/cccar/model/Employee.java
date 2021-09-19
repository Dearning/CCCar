package cn.edu.zucc.cccar.model;

import java.io.Serializable;

public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer employeeId;
    private Integer netId;
    private String name;
    private String password;

    public static final String[] tableTitles={"员工编号","员工id","员工名","密码"};
    public String getCell(int col){
        if(col==0) return employeeId.toString();
        else if(col==1) {
            if (netId==null){
                return "管理员无网点id";
            }
            else return netId.toString();
        }
        else if(col==2) return name;
        else if(col==3) return password;
        else return "";
    }
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