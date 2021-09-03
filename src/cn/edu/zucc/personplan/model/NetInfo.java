package cn.edu.zucc.personplan.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
public class NetInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * net_id
    */
    private Integer netId;

    /**
    * net_name
    */
    private String netName;

    /**
    * city
    */
    private String city;

    /**
    * address
    */
    private String address;

    /**
    * phone
    */
    private String phone;


    public NetInfo() {
    }

    public Integer getNetId() {
        return netId;
    }

    public void setNetId(Integer netId) {
        this.netId = netId;
    }

    public String getNetName() {
        return netName;
    }

    public void setNetName(String netName) {
        this.netName = netName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}