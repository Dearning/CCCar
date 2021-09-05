package cn.edu.zucc.cccar.model;

import java.io.Serializable;

public class NetInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer netId;
    private String netName;
    private String city;
    private String address;
    private String phone;

    public static final String[] tableTitles={"±àºÅ","ÍøµãÃû³Æ"};
    public NetInfo() {
    }
    public String getCell(int col){
        if(col==0) return netId.toString();
        else if(col==1) return netName;
        else if(col==2) return city;
        else if(col==3) return address;
        else if(col==4) return phone;
        else return "";
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