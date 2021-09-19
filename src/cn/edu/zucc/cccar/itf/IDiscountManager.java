package cn.edu.zucc.cccar.itf;

import cn.edu.zucc.cccar.model.DiscountInfo;
import cn.edu.zucc.cccar.util.BaseException;

import java.util.List;

public interface IDiscountManager {
    public List<DiscountInfo> loadAll() throws BaseException;

    void delete(DiscountInfo currentDiscount) throws BaseException;

    void update(DiscountInfo currentDiscount) throws BaseException;

    void add(DiscountInfo discountInfo) throws BaseException;
}
