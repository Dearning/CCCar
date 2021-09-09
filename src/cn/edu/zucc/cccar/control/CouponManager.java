package cn.edu.zucc.cccar.control;

import cn.edu.zucc.cccar.itf.ICouponManager;
import cn.edu.zucc.cccar.model.Coupon;
import cn.edu.zucc.cccar.model.DiscountInfo;
import cn.edu.zucc.cccar.model.UserInfo;
import cn.edu.zucc.cccar.util.BaseException;
import cn.edu.zucc.cccar.util.DBUtil;
import cn.edu.zucc.cccar.util.DbException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CouponManager implements ICouponManager {
    @Override
    public List<Coupon> loadUser(UserInfo currentLoginUser) throws BaseException {
        List<Coupon> result = new ArrayList<Coupon>();
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString ="select * from coupon where user_id = ?";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1,currentLoginUser.getUserId());
            java.sql.ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                Coupon coupon = new Coupon();
                coupon.setCouponId(resultSet.getInt(1));
                coupon.setContent(resultSet.getString(2));
                coupon.setCreditamount(resultSet.getInt(3));
                coupon.setStartdate(resultSet.getDate(4));
                coupon.setEnddate(resultSet.getDate(5));
                coupon.setUserId(resultSet.getInt(6));
                coupon.setUsed(resultSet.getBoolean(7));
                result.add(coupon);
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException(e);
        }finally {
            if(connection!=null) {
                try {
                    connection.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Coupon> loadAll() throws BaseException {
        List<Coupon> result = new ArrayList<Coupon>();
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString ="select * from coupon";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            java.sql.ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                Coupon coupon = new Coupon();
                coupon.setCouponId(resultSet.getInt(1));
                coupon.setContent(resultSet.getString(2));
                coupon.setCreditamount(resultSet.getInt(3));
                coupon.setStartdate(resultSet.getDate(4));
                coupon.setEnddate(resultSet.getDate(5));
                coupon.setUserId(resultSet.getInt(6));
                coupon.setUsed(resultSet.getBoolean(7));
                result.add(coupon);
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException(e);
        }finally {
            if(connection!=null) {
                try {
                    connection.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(Coupon currentCoupon) throws BaseException {

    }

    @Override
    public void delete(Coupon currentCoupon) throws BaseException {

    }
}
