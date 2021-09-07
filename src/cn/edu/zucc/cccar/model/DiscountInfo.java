package cn.edu.zucc.cccar.model;


import java.io.Serializable;
import java.util.Date;
public class DiscountInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String[] tableTitles={"促销编号","网点编号","车类编号","促销折扣","促销数量","开始时间","结束时间"};

    public String getCell(int col){
        if(col==0) return dicountId.toString();
        else if(col==1) return netId.toString();
        else if(col==2) return typeId.toString();
        else if(col==3) return discountamount.toString();
        else if(col==4) return discountnum.toString();
        else if(col==5) return startdate.toString();
        else if(col==6) return enddate.toString();
        else return "";
    }
    private Integer dicountId;
    private Integer netId;
    private Integer typeId;
    private Integer discountamount;
    private Integer discountnum;
    private Date startdate;
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