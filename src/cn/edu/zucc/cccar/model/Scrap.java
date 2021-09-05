package cn.edu.zucc.cccar.model;

import java.io.Serializable;
import java.util.Date;
public class Scrap implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * scrap_id
    */
    private Integer scrapId;

    /**
    * employee_id
    */
    private Integer employeeId;

    /**
    * car_id
    */
    private Integer carId;

    /**
    * scraptime
    */
    private Date scraptime;

    /**
    * description
    */
    private String description;


    public Scrap() {
    }

    public Integer getScrapId() {
        return scrapId;
    }

    public void setScrapId(Integer scrapId) {
        this.scrapId = scrapId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Date getScraptime() {
        return scraptime;
    }

    public void setScraptime(Date scraptime) {
        this.scraptime = scraptime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}