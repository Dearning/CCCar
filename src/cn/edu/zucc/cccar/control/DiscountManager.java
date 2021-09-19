package cn.edu.zucc.cccar.control;

import cn.edu.zucc.cccar.itf.IDiscountManager;
import cn.edu.zucc.cccar.model.CarInfo;
import cn.edu.zucc.cccar.model.DiscountInfo;
import cn.edu.zucc.cccar.util.BaseException;
import cn.edu.zucc.cccar.util.DBUtil;
import cn.edu.zucc.cccar.util.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DiscountManager implements IDiscountManager {
    @Override
    public void add(DiscountInfo discountInfo) throws BaseException {
        Connection connection =null;
        try {
            connection=DBUtil.getConnection();
            String sqlString;
            PreparedStatement preparedStatement;
            sqlString = "insert into discount_info " +
                    "(net_id, type_Id, discountAmount, discountNum, startDate, endDate) " +
                    "values (?,?,?,?,?,?);";
            preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setInt(1,discountInfo.getNetId());
            preparedStatement.setInt(2,discountInfo.getTypeId());
            preparedStatement.setInt(3,discountInfo.getDiscountamount());
            preparedStatement.setInt(4,discountInfo.getDiscountnum());
            preparedStatement.setTimestamp(5,new Timestamp(discountInfo.getStartdate().getTime()));
            preparedStatement.setTimestamp(6,new Timestamp(discountInfo.getEnddate().getTime()));
            preparedStatement.execute();
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
    public void delete(DiscountInfo currentDiscount) throws BaseException {
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString ="delete from discount_info where dicount_id = ?";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1,currentDiscount.getDicountId());
            pStatement.execute();
            pStatement.close();

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
    public void update(DiscountInfo currentDiscount) throws BaseException {

    }

    @Override
    public List<DiscountInfo> loadAll() throws BaseException {
        List<DiscountInfo> result = new ArrayList<DiscountInfo>();
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString ="select * from discount_info";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            java.sql.ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                DiscountInfo discountInfo = new DiscountInfo();
                discountInfo.setDicountId(resultSet.getInt(1));
                discountInfo.setNetId(resultSet.getInt(2));
                discountInfo.setTypeId(resultSet.getInt(3));
                discountInfo.setDiscountamount(resultSet.getInt(4));
                discountInfo.setDiscountnum(resultSet.getInt(5));
                discountInfo.setStartdate(resultSet.getTimestamp(6));
                discountInfo.setEnddate(resultSet.getTimestamp(7));
                result.add(discountInfo);
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
}
