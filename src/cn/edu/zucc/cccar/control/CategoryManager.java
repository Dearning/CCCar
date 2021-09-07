package cn.edu.zucc.cccar.control;

import cn.edu.zucc.cccar.itf.ICategoryManager;
import cn.edu.zucc.cccar.model.CarCategory;
import cn.edu.zucc.cccar.model.CarInfo;
import cn.edu.zucc.cccar.model.CarType;
import cn.edu.zucc.cccar.util.BaseException;
import cn.edu.zucc.cccar.util.BusinessException;
import cn.edu.zucc.cccar.util.DBUtil;
import cn.edu.zucc.cccar.util.DbException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager implements ICategoryManager {
    @Override
    public List<CarCategory> loadAll() throws BaseException {
        List<CarCategory> result = new ArrayList<CarCategory>();
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString ="select * from car_category ";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            java.sql.ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                CarCategory carCategory = new CarCategory();
                carCategory.setCategoryId(resultSet.getInt(1));
                carCategory.setCategoryName(resultSet.getString(2));
                carCategory.setCategoryDescription(resultSet.getString(3));
                result.add(carCategory);
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
    public void deleteCategory(CarCategory currentCategory) throws BaseException {
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString =null;
            java.sql.PreparedStatement pStatement = null;
            ResultSet resultSet =null;

            sqlString ="select * from car_type where category_Id = ?";
            pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1,currentCategory.getCategoryId());
            resultSet = pStatement.executeQuery();
            if(resultSet.next()) throw new BusinessException("存在属于当前车类的车型,无法删除,请先删除相关车型和车辆");
            pStatement.close();
            resultSet.close();

            sqlString ="select * from car_category where category_Id = ?";
            pStatement = connection.prepareStatement(sqlString);
            resultSet = pStatement.executeQuery();
            if(resultSet.next()) {
                resultSet.close();

                pStatement.close();
                sqlString ="delete from car_category where category_Id = ?";
                pStatement = connection.prepareStatement(sqlString);
                pStatement.setInt(1,currentCategory.getCategoryId());
                pStatement.execute();
                pStatement.close();
            } else  {
                System.out.println("未找到需要删除网点");
            }

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
    public void add(CarCategory carCategory) throws BaseException {
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString =null;
            sqlString ="select count(*) from car_category where category_Name=? ";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);

            pStatement.setString(1,carCategory.getCategoryName());
//            pStatement.setString(2, carCategory.getCategoryDescription());
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                if(resultSet.getInt(1) > 0) throw new BusinessException("相同类型已存在");
            }
            pStatement.close();
            resultSet.close();

            sqlString ="insert into car_category (category_Name, category_Description) values(?,?)";
            pStatement = connection.prepareStatement(sqlString);
            pStatement.setString(1,carCategory.getCategoryName());
            pStatement.setString(2,carCategory.getCategoryDescription());
            System.out.println(pStatement);
            pStatement.execute();

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
    public List<CarCategory> loadTypes(Integer netId) throws BaseException {
        List<CarCategory> result = new ArrayList<CarCategory>();
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString ="select category_id,category_name from carinfo where net_id = ? group by category_id";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1,netId);
            java.sql.ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                CarCategory carCategory = new CarCategory();
                carCategory.setCategoryId(resultSet.getInt(1));
                carCategory.setCategoryName(resultSet.getString(2));
                result.add(carCategory);
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
