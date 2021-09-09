package cn.edu.zucc.cccar.model;

import java.io.Serializable;
import java.util.Date;
public class Scrap implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String[] tableTitles={"报废编号","报废员工id","车辆id","报废时间","描述"};
    public String getCell(int col){
        if(col==0) return scrapId.toString();
        else if(col==1) return employeeId.toString();
        else if(col==2) return carId.toString();
        else if(col==3) return scraptime.toString();
        else if(col==4) return description;

        else return "";
    }
    private Integer scrapId;
    private Integer employeeId;
    private Integer carId;
    private Date scraptime;
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