package cn.edu.zucc.personplan.model;

import java.io.Serializable;
import java.util.Date;

public class Allocation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * allocatetime
    */
    private Date allocatetime;

    /**
    * allocation_id
    */
    private Integer allocationId;

    /**
    * car_id
    */
    private Integer carId;

    /**
    * net_in_id
    */
    private Integer netInId;

    /**
    * net_out_id
    */
    private Integer netOutId;


    public Allocation() {
    }

    public Date getAllocatetime() {
        return allocatetime;
    }

    public void setAllocatetime(Date allocatetime) {
        this.allocatetime = allocatetime;
    }

    public Integer getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(Integer allocationId) {
        this.allocationId = allocationId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getNetInId() {
        return netInId;
    }

    public void setNetInId(Integer netInId) {
        this.netInId = netInId;
    }

    public Integer getNetOutId() {
        return netOutId;
    }

    public void setNetOutId(Integer netOutId) {
        this.netOutId = netOutId;
    }

}