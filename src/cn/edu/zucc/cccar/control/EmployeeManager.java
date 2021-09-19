package cn.edu.zucc.cccar.control;

import cn.edu.zucc.cccar.itf.IEmployeeManager;
import cn.edu.zucc.cccar.model.Employee;
import cn.edu.zucc.cccar.model.UserInfo;
import cn.edu.zucc.cccar.util.BaseException;
import cn.edu.zucc.cccar.util.BusinessException;
import cn.edu.zucc.cccar.util.DBUtil;
import cn.edu.zucc.cccar.util.DbException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager implements IEmployeeManager {
    @Override
    public void update(Employee employee) throws BaseException {
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString ="update employee set net_id =?,name =?,password = ?where employee_id = ?\n";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            pStatement.setString(2,employee.getName());
            pStatement.setInt(1,employee.getNetId());
            pStatement.setString(3,employee.getPassword());
            pStatement.setInt(4,employee.getEmployeeId());
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
    public List<Employee> loadAll() throws BaseException {
        List<Employee> result = new ArrayList<Employee>();
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString ="select * from employee;";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            java.sql.ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt(1));
                employee.setNetId((Integer) resultSet.getObject(2));
                employee.setName(resultSet.getString(3));
                employee.setPassword(resultSet.getString(4));
                result.add(employee);
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
    public Employee reg(String userid, String pwd, String pwd2) throws BaseException {
        return null;
    }

    @Override
    public Employee login(String account, String pwd) throws BaseException {

        if(account==null || "".equals(account)) throw new BusinessException("�˺Ų���Ϊ��");
        if(pwd==null || "".equals(pwd)) throw new BusinessException("���벻��Ϊ��");
        Connection connection =null;
        try {

            connection= DBUtil.getConnection();
            // �û�����id ������ͬ
            String sqlString ="select * from employee where employee_id = ? or name = ?";
            //׼��ִ�е����
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            pStatement.setString(1, account);
            pStatement.setString(2,account);
            //ִ�����
            java.sql.ResultSet rSet = pStatement.executeQuery();

            Employee employee  =null;
            if(rSet.next()) {
                //����һ��,���س�������Ա��Ϣ
                String name  = null;
                String password = rSet.getString(4);

                if(password.equals(pwd)){
                    employee = new Employee();
                    employee.setEmployeeId(rSet.getInt(1));
                    employee.setNetId((Integer)rSet.getObject(2));
                    employee.setName(rSet.getString(3));
                    employee.setPassword(rSet.getString(4));
                } else {
                    throw new BusinessException("�������");
                }
                rSet.close();
                pStatement.close();
                return employee;
            }else {
                throw new BusinessException("Ա����Ϣ�����ڻ����������");
            }
            //�ر�ǰ������ݿ�ִ�����,���½��������,����ִ��

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
