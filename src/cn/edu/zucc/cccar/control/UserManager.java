package cn.edu.zucc.cccar.control;

import cn.edu.zucc.cccar.itf.IUserManager;
import cn.edu.zucc.cccar.model.Coupon;
import cn.edu.zucc.cccar.model.Employee;
import cn.edu.zucc.cccar.model.UserInfo;
import cn.edu.zucc.cccar.util.BaseException;
import cn.edu.zucc.cccar.util.BusinessException;
import cn.edu.zucc.cccar.util.DBUtil;
import cn.edu.zucc.cccar.util.DbException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UserManager implements IUserManager {
    @Override
    public UserInfo reg(String userid, String pwd, String pwd2) throws BaseException {
        return null;
    }

    @Override
    public List<UserInfo> loadAll() throws BaseException {
        List<UserInfo> result = new ArrayList<UserInfo>();
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString ="select * from user_info;";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            java.sql.ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(resultSet.getInt(1));
                userInfo.setName(resultSet.getString(2));
                userInfo.setSex(resultSet.getString(3));
                userInfo.setPassword(resultSet.getString(4));
                userInfo.setPhone(resultSet.getString(5));
                userInfo.setEmail(resultSet.getString(6));
                userInfo.setCity(resultSet.getString(7));
                userInfo.setRegistrationtime(resultSet.getTimestamp(8));
                result.add(userInfo);
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
    public void update(UserInfo carInfo) throws BaseException {
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString ="update user_info set name = ?,sex = ?,password=?,phone=?,email = ?,city = ? where user_id = ?";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            pStatement.setString(1,carInfo.getName());
            pStatement.setString(2,carInfo.getSex());
            pStatement.setString(3,carInfo.getPassword());
            pStatement.setString(4,carInfo.getPhone());
            pStatement.setString(5,carInfo.getEmail());
            pStatement.setString(6,carInfo.getCity());
            pStatement.setInt(7,carInfo.getUserId());
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
    public UserInfo login(String account, String pwd) throws BaseException {

        if(account==null || "".equals(account)) throw new BusinessException("账号不能为空");
        if(pwd==null || "".equals(pwd)) throw new BusinessException("密码不能为空");
        Connection connection =null;
        try {

            connection= DBUtil.getConnection();
            // 用户名和id 不能相同
            String sqlString ="select *from user_info where user_id=? or name = ?;\n";
            //准备执行的语句
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            pStatement.setString(1, account);
            pStatement.setString(2,account);
            //执行语句
            java.sql.ResultSet rSet = pStatement.executeQuery();
            UserInfo userInfo = null;
            if(rSet.next()) {
                //密码一致,返回超级管理员信息
                String name  = null;
                String password = rSet.getString(4);

                if(password.equals(pwd)){
                    userInfo = new UserInfo();
                    userInfo.setUserId(rSet.getInt(1));
                    userInfo.setName(rSet.getString(2));
                    userInfo.setSex(rSet.getString(3));
                    userInfo.setPassword(rSet.getString(4));
                    userInfo.setPhone(rSet.getString(5));
                    userInfo.setEmail(rSet.getString(6));
                    userInfo.setCity(rSet.getString(7));
                    userInfo.setRegistrationtime(rSet.getTimestamp(8));
                } else {
                    throw new BusinessException("密码错误");
                }
                rSet.close();
                pStatement.close();
                return userInfo;
            }else {
                throw new BusinessException("员工信息不存在或者密码错误");
            }
            //关闭前面的数据库执行语句,并新建插入语句,进行执行

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new DbException(e);
        }finally {
            if(connection!=null) {
                try {
                    connection.close();
                } catch (Exception e2) {
                    // TODO: handle exception
                    e2.printStackTrace();
                }
            }
        }
    }

    @Override
    public void changePwd(UserInfo user, String oldPwd, String newPwd, String newPwd2) throws BaseException {

    }
}
