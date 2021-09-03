package cn.edu.zucc.personplan.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description coupon
 * @author zhengkai.blog.csdn.net
 * @date 2021-09-03
 */
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * coupon_id
    */
    private Integer couponId;

    /**
    * content
    */
    private String content;

    /**
    * creditamount
    */
    private Integer creditamount;

    /**
    * startdate
    */
    private Date startdate;

    /**
    * enddate
    */
    private Date enddate;


    public Coupon() {
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCreditamount() {
        return creditamount;
    }

    public void setCreditamount(Integer creditamount) {
        this.creditamount = creditamount;
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