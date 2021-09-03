package cn.edu.zucc.personplan.model;

import java.io.Serializable;

public class CarCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * category_id
     */
    private Integer categoryId;

    /**
     * category_name
     */
    private String categoryName;

    /**
     * category_description
     */
    private String categoryDescription;


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