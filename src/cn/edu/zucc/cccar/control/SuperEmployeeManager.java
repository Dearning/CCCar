package cn.edu.zucc.cccar.control;

import cn.edu.zucc.cccar.itf.IEmployeeManager;
import cn.edu.zucc.cccar.model.Employee;
import cn.edu.zucc.cccar.util.BaseException;
import cn.edu.zucc.cccar.util.BusinessException;
import cn.edu.zucc.cccar.util.DBUtil;
import cn.edu.zucc.cccar.util.DbException;

import java.sql.Connection;

public class SuperEmployeeManager implements IEmployeeManager {
    @Override
    public Employee reg(String employeeid, String pwd, String pwd2) throws BaseException {
        return null;
    }

    @Override
    public Employee login(String account, String pwd) throws BaseException {

        if(account==null || "".equals(account)) throw new BusinessException("账号不能为空");
        if(pwd==null || "".equals(pwd)) throw new BusinessException("密码不能为空");
        Connection connection =null;
        try {

            connection= DBUtil.getConnection();
            // 用户名和id 不能相同
            String sqlString ="select * from employee where employee_id = ? or name = ?";
            //准备执行的语句
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            pStatement.setString(1, account);
            pStatement.setString(2,account);
            //执行语句
            java.sql.ResultSet rSet = pStatement.executeQuery();

            Employee employee  =null;
            if(rSet.next()) {
                //密码一致,返回超级管理员信息
                String name  = null;
                String password = rSet.getString(4);
                if(rSet.getObject(2)!= null) {
                    throw new BusinessException("不是超级管理员");
                } else {
                    if(password.equals(pwd)){
                        employee = new Employee();
                        employee.setEmployeeId(rSet.getInt(1));
                        employee.setNetId(null);
                        employee.setName(rSet.getString(3));
                        employee.setPassword(rSet.getString(4));
                    } else {
                        throw new BusinessException("密码错误");
                    }
                }
                rSet.close();
                pStatement.close();
                return employee;
            }else {
                throw new BusinessException("超级管理员不存在或者密码错误");
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
    public void changePwd(Employee employee, String oldPwd, String newPwd, String newPwd2) throws BaseException {

    }
}
