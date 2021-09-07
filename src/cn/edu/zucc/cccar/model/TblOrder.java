package cn.edu.zucc.cccar.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
public class TblOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String[] tableTitles={"订单编号","优惠券编号","借车网点","还车网点","借车日期","还车日期","借车总时长","初始金额","总金额","订单状态"};
    public String getCell(int col){
        if(col==0) return orderId.toString();
        else if(col==1) return couponId == 0? "未使用优惠券":couponId.toString();
        else if(col==2) return netBorrowId.toString();
        else if(col==3) return netReturnId.toString();
        else if(col==4) return borrowdate.toString();
        else if(col==5) return returndate==null? "未还车":returndate.toString();
        else if(col==6) return borrowduration==null? null:borrowduration.toString();
        else if(col==7) return returndate==null? null:initialAmount.toString();
        else if(col==8) return returndate==null? null:totalamount.toString();
        else if(col==9) {
            if(orderStatus==0)return "未完成";
            else if(orderStatus == 1) return "已完成";
            else if(orderStatus == 2) return "";
            else return"";
        }
        else return "";
    }
    private Integer orderId;
    private Integer couponId;
    private Integer netBorrowId;
    private Integer carTypeId;
    private Integer netReturnId;
    private Integer userId;
    private Date borrowdate;
    private Date returndate;
    private Date borrowduration;
    private BigDecimal initialAmount;
    private BigDecimal totalamount;
    private Integer orderStatus;


    public Integer getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(Integer carTypeId) {
        this.carTypeId = carTypeId;
    }

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