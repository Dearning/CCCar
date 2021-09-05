package cn.edu.zucc.cccar.model;


import java.io.Serializable;
import java.util.Date;
public class DiscountInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * dicount_id
    */
    private Integer dicountId;

    /**
    * net_id
    */
    private Integer netId;

    /**
    * type_id
    */
    private Integer typeId;

    /**
    * discountamount
    */
    private Integer discountamount;

    /**
    * discountnum
    */
    private Integer discountnum;

    /**
    * startdate
    */
    private Date startdate;

    /**
    * enddate
    */
    private Date enddate;


    public DiscountInfo() {
    }

    public Integer getDicountId() {
        return dicountId;
    }

    public void setDicountId(Integer dicountId) {
        this.dicountId = dicountId;
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

    public Integer getDiscountamount() {
        return discountamount;
    }

    public void setDiscountamount(Integer discountamount) {
        this.discountamount = discountamount;
    }

    public Integer getDiscountnum() {
        return discountnum;
    }

    public void setDiscountnum(Integer discountnum) {
        this.discountnum = discountnum;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

}