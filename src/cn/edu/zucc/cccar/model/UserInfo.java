package cn.edu.zucc.cccar.model;

import java.io.Serializable;
import java.util.Date;
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * user_id
    */
    private Integer userId;

    /**
    * name
    */
    private String name;

    /**
    * sex
    */
    private String sex;

    /**
    * password
    */
    private String password;

    /**
    * phone
    */
    private String phone;

    /**
    * email
    */
    private String email;

    /**
    * city
    */
    private String city;

    /**
    * registrationtime
    */
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