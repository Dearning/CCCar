package cn.edu.zucc.cccar.control;

import cn.edu.zucc.cccar.itf.IOrderManager;
import cn.edu.zucc.cccar.model.CarType;
import cn.edu.zucc.cccar.model.TblOrder;
import cn.edu.zucc.cccar.util.BaseException;
import cn.edu.zucc.cccar.util.DBUtil;
import cn.edu.zucc.cccar.util.DbException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class OrderManager implements IOrderManager {
    @Override
    public List<TblOrder> loadAll() throws BaseException {
        List<TblOrder> result = new ArrayList<TblOrder>();
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString ="select * from tbl_order";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            java.sql.ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                TblOrder tblOrder = new TblOrder();
//                System.out.println(carType);
                tblOrder.setOrderId(resultSet.getInt(1));
                tblOrder.setCouponId(resultSet.getInt(2));
                tblOrder.setNetBorrowId(resultSet.getInt(3));
                tblOrder.setCarTypeId(resultSet.getInt(4));
                tblOrder.setNetReturnId(resultSet.getInt(5));
                tblOrder.setUserId(resultSet.getInt(6));
                tblOrder.setBorrowdate(resultSet.getDate(7));
                tblOrder.setReturndate(resultSet.getTimestamp(8));
                tblOrder.setBorrowduration(resultSet.getDate(9));
                tblOrder.setInitialAmount(BigDecimal.valueOf(resultSet.getDouble(10)));
                tblOrder.setTotalamount(BigDecimal.valueOf(resultSet.getDouble(11)));
                tblOrder.setOrderStatus(resultSet.getInt(12));
                result.add(tblOrder);
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
