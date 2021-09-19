package cn.edu.zucc.cccar.control;

import cn.edu.zucc.cccar.CCCarUtil;
import cn.edu.zucc.cccar.itf.IScrapManager;
import cn.edu.zucc.cccar.model.CarType;
import cn.edu.zucc.cccar.model.Scrap;
import cn.edu.zucc.cccar.util.BaseException;
import cn.edu.zucc.cccar.util.BusinessException;
import cn.edu.zucc.cccar.util.DBUtil;
import cn.edu.zucc.cccar.util.DbException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ScrapManager implements IScrapManager {
    @Override
    public void addDescription(String netIdText,int carId) throws BaseException {
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString =null;
            java.sql.PreparedStatement pStatement = null;
            sqlString ="update scrap set description = ? where car_id = ?";
            pStatement=connection.prepareStatement(sqlString);
            pStatement.setString(1,netIdText);
            pStatement.setInt(2,carId);
            pStatement.execute();
            pStatement.close();
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
    public void add(Scrap scrap) throws BaseException {
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString =null;
            java.sql.PreparedStatement pStatement = null;
            sqlString ="insert into scrap(employee_id, car_id, scrapTime, description) VALUES (?,?,?,?)";
            pStatement=connection.prepareStatement(sqlString);
            pStatement.setInt(1,CCCarUtil.currentLoginEmployee.getEmployeeId());
            pStatement.setInt(2,scrap.getCarId());
            pStatement.setTimestamp(3,new Timestamp(System.currentTimeMillis()));
            pStatement.setString(4,scrap.getDescription());
            pStatement.execute();
            pStatement.close();
            sqlString = "update car_info set car_status = -1 where car_id = ?";
            pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1,scrap.getCarId());
            pStatement.execute();
            pStatement.close();

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
    public List<Scrap> loadAll() throws BaseException {
        List<Scrap>result = new ArrayList<Scrap>();
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString ="select scrap_id, employee_id, car_id, scrapTime, description from scrap";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                Scrap scrap = new Scrap();
                scrap.setScrapId(resultSet.getInt(1));
                scrap.setCarId(resultSet.getInt(3));
                scrap.setEmployeeId(resultSet.getInt(2));
                scrap.setScraptime(resultSet.getTimestamp(4));
                scrap.setDescription(resultSet.getString(5));
                result.add(scrap);
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
