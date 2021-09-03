package cn.edu.zucc.personplan.model;

import java.io.Serializable;

/**
 * @description car_info
 * @author zhengkai.blog.csdn.net
 * @date 2021-09-03
 */
public class CarInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * car_id
    */
    private Integer carId;

    /**
    * net_id
    */
    private Integer netId;

    /**
    * type_id
    */
    private Integer typeId;

    /**
    * license
    */
    private String license;

    /**
    * car_status
    */
    private Integer carStatus;


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