package cn.edu.zucc.cccar.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @description coupon
 * @author zhengkai.blog.csdn.net
 * @date 2021-09-03
 */
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String[] tableTitles={"�Ż�ȯ���","�Ƿ�ʹ��","�û����","�Ż�ȯ����","��ʼʱ��","����ʱ��"};

    public String getCell(int col){
        if(col==0) return couponId.toString();
        else if(col==1) {
            if(isUsed)return "�ѱ�ʹ��";
            else return "��δ��ʹ��";
        }
        else if(col==2) return userId.toString();
        else if(col==3) return creditamount.toString();
        else if(col==4) return startdate.toString();
        else if(col==5) return enddate.toString();
        else return "";
    }
    private Integer couponId;
    private Boolean isUsed;
    private Integer userId;
    private Integer creditamount;
    private Date startdate;
    private Date enddate;

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    private String content;

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