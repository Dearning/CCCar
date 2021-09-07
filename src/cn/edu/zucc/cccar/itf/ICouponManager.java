package cn.edu.zucc.cccar.itf;

import cn.edu.zucc.cccar.model.Coupon;
import cn.edu.zucc.cccar.util.BaseException;

import java.util.List;

public interface ICouponManager {
    void delete(Coupon currentCoupon) throws BaseException;

    void update(Coupon currentCoupon) throws BaseException;

    List<Coupon> loadAll() throws BaseException;
}
