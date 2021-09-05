package cn.edu.zucc.cccar.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
public class TblOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * order_id
    */
    private Integer orderId;

    /**
    * coupon_id
    */
    private Integer couponId;

    /**
    * net_borrow_id
    */
    private Integer netBorrowId;

    /**
    * net_return_id
    */
    private Integer netReturnId;

    /**
    * user_id
    */
    private Integer userId;

    /**
    * borrowdate
    */
    private Date borrowdate;

    /**
    * returndate
    */
    private Date returndate;

    /**
    * borrowduration
    */
    private Date borrowduration;

    /**
    * initial_amount
    */
    private BigDecimal initialAmount;

    /**
    * totalamount
    */
    private BigDecimal totalamount;

    /**
    * order_status
    */
    private Integer orderStatus;


    public TblOrder() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getNetBorrowId() {
        return netBorrowId;
    }

    public void setNetBorrowId(Integer netBorrowId) {
        this.netBorrowId = netBorrowId;
    }

    public Integer getNetReturnId() {
        return netReturnId;
    }

    public void setNetReturnId(Integer netReturnId) {
        this.netReturnId = netReturnId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getBorrowdate() {
        return borrowdate;
    }

    public void setBorrowdate(Date borrowdate) {
        this.borrowdate = borrowdate;
    }

    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }

    public Date getBorrowduration() {
        return borrowduration;
    }

    public void setBorrowduration(Date borrowduration) {
        this.borrowduration = borrowduration;
    }

    public BigDecimal getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(BigDecimal initialAmount) {
        this.initialAmount = initialAmount;
    }

    public BigDecimal getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

}