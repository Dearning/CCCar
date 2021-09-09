package cn.edu.zucc.cccar.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
public class TblOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String[] tableTitles={"�������","�û����","�Ż�ȯ���","�賵����","�����","��������",
            "�賵����","��������","�賵��ʱ��","����(Ԫ/ʱ)","��ʼ���","�ܽ��","����״̬"};
    public String getCell(int col){
        if(col==0) return orderId.toString();
        else if(col==1) return userId==null? "����Ա":userId.toString();
        else if(col==2) return couponId == 0? "δʹ���Ż�ȯ":couponId.toString();
        else if(col==3) return netBorrowId.toString();
        else if(col==4) return carId.toString();
        else if(col==5) return netReturnId.toString();
        else if(col==6) return borrowdate.toString();
        else if(col==7) return returndate==null? "δ����":returndate.toString();
        else if(col==8) return borrowduration==null? null:borrowduration;
        else if(col==9) return initialAmount==null? null:initialAmount.toString();
        else if(col==10) return originalAmount==null? null:originalAmount.toString();
        else if(col==11) return totalamount==null? null:totalamount.toString();
        else if(col==12) {
            if(orderStatus==0)return "δ���";
            else if(orderStatus == 1) return "�����";
            else if(orderStatus == 2) return "";
            else return"";
        }
        else return "";
    }
    private Integer orderId;
    private Integer couponId;
    private Integer netBorrowId;
    private Integer carId;
    private Integer netReturnId;
    private Integer userId;
    private Date borrowdate;
    private Date returndate;
    private String borrowduration;
    private BigDecimal initialAmount;

    private BigDecimal originalAmount;
    private BigDecimal totalamount;
    private Integer orderStatus;

    public BigDecimal getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(BigDecimal originalAmount) {
        this.originalAmount = originalAmount;
    }



    public String getBorrowduration() {
        return borrowduration;
    }

    public void setBorrowduration(String borrowduration) {
        this.borrowduration = borrowduration;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
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