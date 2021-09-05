package cn.edu.zucc.cccar.model;

import java.io.Serializable;

/**
 * @description car_info
 * @author zhengkai.blog.csdn.net
 * @date 2021-09-03
 */
public class CarInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String[] tableTitles={"编号","网点编号","车型","牌照号","车俩状态"};

    private Integer carId;
    private Integer netId;
    private Integer typeId;
    private String license;
    private Integer carStatus; //0 不在网点,工作中, 1 在网点空闲

    public String getCell(int col){
        if(col==0) return carId.toString();
        else if(col==1) return netId.toString();
        else if(col==2) return typeId.toString();
        else if(col==3) return license;
        else if(col==4) {
            if(carStatus==1) return "在库";
            else return "租借中";
        }
        else return "";
    }
    public CarInfo() {
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getNetId() {
        return netId;
    }

    public void setNetId(Integer netId) {
        this.netId = netId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Integer getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(Integer carStatus) {
        this.carStatus = carStatus;
    }


}