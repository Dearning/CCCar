package cn.edu.zucc.personplan.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CarType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * type_id
    */
    private Integer typeId;

    /**
    * category_id
    */
    private Integer categoryId;

    /**
    * type_name
    */
    private String typeName;

    /**
    * displacement
    */
    private BigDecimal displacement;

    /**
    * gear
    */
    private Integer gear;

    /**
    * seat_num
    */
    private Integer seatNum;

    /**
    * price
    */
    private BigDecimal price;

    /**
    * pic
    */
    private String pic;


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