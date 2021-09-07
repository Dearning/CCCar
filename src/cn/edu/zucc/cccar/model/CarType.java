package cn.edu.zucc.cccar.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CarType implements Serializable {

    private static final long serialVersionUID = 1L;

//    public static final String[] tableTitles={"���","����","���","Ʒ��","����","�ŵ�","��λ��","�۸�","ͼƬ"};

    public static final String[] tableTitles={"���ͱ��","��������"};
    public String getCell(int col){
        if(col==0) return typeId.toString();
        else if(col==1) return typeName;
        else if(col==2) return categoryId.toString();
        else if(col==3) return displacement.toString();
        else if(col==4) return gear.toString();
        else if(col==5) return seatNum.toString();
        else if(col==6) return price.toString();
        else if(col==7) return pic;//TODO ͼƬ��ô��
        else return "";
    }
    private Integer typeId;
    private Integer categoryId;
    private String typeName;

    private String brand;
    private BigDecimal displacement;
    private Integer gear;
    private Integer seatNum;
    private BigDecimal price;
    private String pic;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public CarType() {
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public BigDecimal getDisplacement() {
        return displacement;
    }

    public void setDisplacement(BigDecimal displacement) {
        this.displacement = displacement;
    }

    public Integer getGear() {
        return gear;
    }

    public void setGear(Integer gear) {
        this.gear = gear;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

}