package cn.edu.zucc.cccar.model;

import java.io.Serializable;
import java.util.Date;
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String[] tableTitles={"用户编号","用户名","性别","密码","电话","邮箱","城市","注册时间"};
    public String getCell(int col){
        if(col==0) return userId.toString();
        else if(col==1) return name;
        else if(col==2) return sex;
        else if(col==3) return password;
        else if(col==4) return phone;
        else if(col==5) return email;
        else if(col==6) return city;
        else if(col==7) return registrationtime.toString();
        else return "";
    }
    private Integer userId;
    private String name;
    private String sex;
    private String password;
    private String phone;
    private String email;
    private String city;
    private Date registrationtime;


    public UserInfo() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getRegistrationtime() {
        return registrationtime;
    }

    public void setRegistrationtime(Date registrationtime) {
        this.registrationtime = registrationtime;
    }

}