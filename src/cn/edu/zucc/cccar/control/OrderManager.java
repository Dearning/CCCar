package cn.edu.zucc.cccar.control;

import cn.edu.zucc.cccar.CCCarUtil;
import cn.edu.zucc.cccar.itf.IOrderManager;
import cn.edu.zucc.cccar.model.TblOrder;
import cn.edu.zucc.cccar.util.BaseException;
import cn.edu.zucc.cccar.util.BusinessException;
import cn.edu.zucc.cccar.util.DBUtil;
import cn.edu.zucc.cccar.util.DbException;
import jdk.nashorn.internal.codegen.types.Type;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderManager implements IOrderManager {
    @Override
    public void completeOrder(Integer orderId) throws BaseException {
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString;
            sqlString ="select borrowDate,initial_amount,car_Id,net_borrow_id,net_return_id,coupon_id from tbl_order where order_id = ?\n";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1,orderId);
            ResultSet resultSet = pStatement.executeQuery();
            Timestamp borrowDate;
            BigDecimal moneyPerHour;
            int netBorrowId,netReturnId,carId;
            Integer couponId = null;
            if(resultSet.next()) {
                borrowDate = resultSet.getTimestamp(1);
                moneyPerHour = resultSet.getBigDecimal(2);
                carId = resultSet.getInt(3);
                netBorrowId = resultSet.getInt(4);
                netReturnId = resultSet.getInt(5);
                couponId= resultSet.getInt(6);
            }
            else throw new BusinessException("订单不存在");
            resultSet.close();
            pStatement.close();
            //选取typeId
            sqlString = "select type_Id from car_info where car_id = ?";
            pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1,carId);
            resultSet = pStatement.executeQuery();
            int typeId=0;
            if(resultSet.next()){
                typeId = resultSet.getInt(1);
            }
            resultSet.close();
            pStatement.close();
            // 选择打折力度，转换成小数
            sqlString="select discountAmount from discount_info where net_id = ? " +
                    "and type_Id = ? and startDate < ? and endDate > ?";
            pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1,netBorrowId);
            pStatement.setInt(2,typeId);
            pStatement.setTimestamp(3,borrowDate);
            pStatement.setTimestamp(4,borrowDate);
            resultSet = pStatement.executeQuery();
            double discountAmount = 100;
            if(resultSet.next()){
                discountAmount = resultSet.getDouble(1);
            }
            // 优惠方式这里随便定的，优惠力度更新

            if(discountAmount>10)discountAmount =discountAmount/100;
            else if(discountAmount<10) discountAmount = discountAmount/10;
            System.out.println(discountAmount);
            resultSet.close();
            pStatement.close();
            // 找到优惠券信息

            BigDecimal couponAmount=null;
            //总金额需要根据是否使用优惠券判断，使用优惠券的话，需要更新优惠券使用情况，或者直接删除，然后需需要根据当前的折扣判断进行修改
//            couponAmount = ;
            sqlString ="select creditAmount from coupon where coupon_id = ?";
            pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1,couponId);
            resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                couponAmount = resultSet.getBigDecimal(1);
            }
            //更新订单的订单状态，和还车日期，借车时长，计算总金额
            sqlString ="update tbl_order set order_status = 1, returnDate=?,borrowDuration=?,original_amount=?,totalAmount=? " +
                    "where order_id = ?";
            pStatement = connection.prepareStatement(sqlString);
            Timestamp returnDate = new Timestamp(System.currentTimeMillis());
            //参考了代码
            String borrowDuration = getTimeDifference(returnDate,borrowDate);
            pStatement.setTimestamp(1,returnDate);
            pStatement.setString(2,borrowDuration);
            //封装了下s
            int hours = getTimeDifferenceHours(returnDate,borrowDate);



            BigDecimal totalMoney = moneyPerHour.multiply(new BigDecimal(hours)).multiply
                    (new BigDecimal(discountAmount)).subtract(couponAmount);

//            totalMoney = totalMoney.multiply(new BigDecimal(discountAmount));
            System.out.println(moneyPerHour.multiply(new BigDecimal(hours)));
            pStatement.setBigDecimal(3,moneyPerHour.multiply(new BigDecimal(hours)));
            pStatement.setBigDecimal(4,totalMoney);
            pStatement.setInt(5,orderId);
            pStatement.execute();
            pStatement.close();
            //更新优惠券使用状态
            if( couponId != 0){
                sqlString = "update coupon set isUsed = true where coupon_id = ?\n";
                pStatement = connection.prepareStatement(sqlString);
                pStatement.setInt(1,couponId);
                pStatement.execute();
                pStatement.close();
                sqlString = "update tbl_order set coupon_id = ? where order_id = ?";
                pStatement = connection.prepareStatement(sqlString);
                pStatement.setInt(1,couponId);
                pStatement.setInt(2,orderId);
                pStatement.execute();

            }

            //更新车辆信息车辆状态为在库
            sqlString ="update car_info set car_status = 1 where car_id = ?\n";
            pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1,carId);
            pStatement.execute();
            pStatement.close();
            if(netBorrowId!=netReturnId) {
                // 如果还车网点和借车网点不同 则产生车辆调拨表
                sqlString = "insert into allocation(allocateTime, car_id, net_in_id, net_out_id) VALUES (?,?,?,?)";
                pStatement = connection.prepareStatement(sqlString);
                pStatement.setTimestamp(1,new Timestamp(System.currentTimeMillis()));
                pStatement.setInt(2,carId);
                pStatement.setInt(3,netReturnId);
                pStatement.setInt(4,netBorrowId);
                pStatement.execute();
                pStatement.close();
                //更新车的网点id
                sqlString = "update car_info set net_id = ? where car_id = ?";
                pStatement = connection.prepareStatement(sqlString);
                pStatement.setInt(1,netReturnId);
                pStatement.setInt(2,carId);
                pStatement.execute();
                pStatement.close();
            }


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

    public int getTimeDifferenceHours(Timestamp endTime, Timestamp startTime) {
        SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
        long t1 = endTime.getTime();
        long t2 = startTime.getTime();
        int hours= ((t1 - t2)%(1000*60*60))==0?(int) ((t1 - t2)/(1000*60*60)): (int) (((t1 - t2) / (1000 * 60 * 60)) + 1);
        return hours;
    }
    //网上的代码 https://blog.csdn.net/yuzheh521/article/details/106216680
    public String getTimeDifference(Timestamp endTime, Timestamp startTime) {
        SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
        long t1 = endTime.getTime();
        long t2 = startTime.getTime();
        int hours=(int) ((t1 - t2)/(1000*60*60));
        int minutes=(int) (((t1 - t2)/1000-hours*(60*60))/60);
        int second=(int) ((t1 - t2)/1000-hours*(60*60)-minutes*60);
        return ""+hours+"小时"+minutes+"分"+second+"秒";
    }
    @Override
    public void deleteOrder(Integer orderId) throws BaseException {
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString ="delete from tbl_order where order_id = ?";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1,orderId);
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
    public void addOrder(TblOrder order) throws BaseException {
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString;
            java.sql.PreparedStatement pStatement = null;
            sqlString = "select car_status from car_info where car_id= ?";
            pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1,order.getCarId());
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                if(resultSet.getInt(1) == 0) throw new BusinessException("被组借中");
            }
            sqlString ="insert into tbl_order(coupon_id, net_borrow_id, car_Id, net_return_id, user_id," +
                    " borrowDate, returnDate, borrowDuration, initial_amount, totalAmount, order_status)" +
                    "values (?,?,?,?,?,?,null,null,?,null,0)";
            pStatement = connection.prepareStatement(sqlString);
            if(order.getCouponId()!=null){
                pStatement.setInt(1, order.getCouponId());
            } else pStatement.setNull(1, Types.INTEGER);
            pStatement.setInt(2,order.getNetBorrowId());

            pStatement.setInt(3,order.getCarId());
            pStatement.setInt(4, CCCarUtil.CurrentReturnNet.getNetId());
            if(order.getUserId()!=null) pStatement.setInt(5,order.getUserId());
            else pStatement.setNull(5, Types.INTEGER);
            pStatement.setTimestamp(6,new Timestamp(System.currentTimeMillis()));
            pStatement.setBigDecimal(7,order.getInitialAmount());
            pStatement.execute();
            pStatement.close();
            sqlString = "update car_info set car_status = 0 where car_id = ?";
            pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1,order.getCarId());
            pStatement.execute();



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
    public List<TblOrder> loadAll() throws BaseException {
        List<TblOrder> result = new ArrayList<TblOrder>();
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString ="select * from tbl_order order by order_status ASC";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            java.sql.ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                TblOrder tblOrder = new TblOrder();
//                System.out.println(carType);
                tblOrder.setOrderId(resultSet.getInt(1));
                tblOrder.setCouponId(resultSet.getInt(2));
                tblOrder.setNetBorrowId(resultSet.getInt(3));
                tblOrder.setCarId(resultSet.getInt(4));
                tblOrder.setNetReturnId(resultSet.getInt(5));
                tblOrder.setUserId(resultSet.getInt(6));
                tblOrder.setBorrowdate(resultSet.getDate(7));
                tblOrder.setReturndate(resultSet.getTimestamp(8));
                tblOrder.setBorrowduration(resultSet.getString(9));
                tblOrder.setInitialAmount(resultSet.getBigDecimal(10));
                tblOrder.setOriginalAmount(resultSet.getBigDecimal(11));
                tblOrder.setTotalamount(resultSet.getBigDecimal(12));
                tblOrder.setOrderStatus(resultSet.getInt(13));
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
