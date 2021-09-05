package cn.edu.zucc.cccar.model;

import java.io.Serializable;

public class CarCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String[] tableTitles={"车类编号","车类名称"};
    private Integer categoryId;
    private String categoryName;
    private String categoryDescription;

    public String getCell(int col){
        if(col==0) return categoryId.toString();
        else if(col==1) return categoryName;
        else if(col==2) return categoryDescription;
        else return "";
    }
    public CarCategory() {
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

}