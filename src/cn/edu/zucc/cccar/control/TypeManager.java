package cn.edu.zucc.cccar.control;

import cn.edu.zucc.cccar.itf.ITypeManager;
import cn.edu.zucc.cccar.model.CarType;
import cn.edu.zucc.cccar.util.BaseException;
import cn.edu.zucc.cccar.util.BusinessException;
import cn.edu.zucc.cccar.util.DBUtil;
import cn.edu.zucc.cccar.util.DbException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TypeManager implements ITypeManager {
    @Override
    public List<CarType> loadTypesByCategoryOnly(Integer categoryId) throws BaseException {
        List<CarType> result = new ArrayList<CarType>();
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString ="select type_id,type_name from carinfo where category_id =? group by type_id";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1,categoryId);
            java.sql.ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                CarType carType = new CarType();
                carType.setTypeId(resultSet.getInt(1));

                carType.setTypeName(resultSet.getString(2));
//                System.out.println(carType);
                result.add(carType);
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
    public void deleteType(CarType currentType) throws BaseException {
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString =null;
            java.sql.PreparedStatement pStatement = null;
            ResultSet resultSet =null;

            sqlString ="select * from car_info where type_Id = ?";
            pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1,currentType.getTypeId());
            resultSet = pStatement.executeQuery();
            if(resultSet.next()) throw new BusinessException("存在属于当前车型的车辆,无法删除");
            pStatement.close();
            resultSet.close();

            sqlString ="select * from car_type where type_Id = ?\n";
            pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1,currentType.getTypeId());
            resultSet = pStatement.executeQuery();
            if(resultSet.next()) {
                resultSet.close();

                pStatement.close();
                sqlString ="delete from car_type where type_Id = ?";
                pStatement = connection.prepareStatement(sqlString);
                pStatement.setInt(1,currentType.getTypeId());
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
    public void add(CarType carType) throws BaseException {
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString =null;
            sqlString ="select count(*) from car_type where type_Name = ?";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);

            pStatement.setString(1,carType.getTypeName());
//            pStatement.setString(2, carCategory.getCategoryDescription());
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                if(resultSet.getInt(1) > 0) throw new BusinessException("相同类型已存在");
            }
            pStatement.close();
            resultSet.close();

            sqlString ="insert into car_type ( type_Name, category_Id, brand, displacement, gear, seat_num, price, pic) values (?,?,?,?,?,?,?,?)";
            pStatement = connection.prepareStatement(sqlString);
            pStatement.setString(1,carType.getTypeName());
            pStatement.setInt(2,carType.getCategoryId());
            pStatement.setString(3,carType.getBrand());
            pStatement.setDouble(4,carType.getDisplacement().doubleValue());
            pStatement.setInt(5,carType.getGear());
            pStatement.setInt(6,carType.getSeatNum());
            pStatement.setDouble(7,carType.getPrice().doubleValue());
            pStatement.setString(8,carType.getPic());
            pStatement.setString(8,carType.getPic());

            System.out.println(sqlString);
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
    public List<CarType> loadTypes(Integer netId, Integer categoryId) throws BaseException {
        List<CarType> result = new ArrayList<CarType>();
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString ="select type_id,type_name from carinfo where net_id = ? and category_id =? group by type_id";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1,netId);
            pStatement.setInt(2,categoryId);
            java.sql.ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                CarType carType = new CarType();
                carType.setTypeId(resultSet.getInt(1));

                carType.setTypeName(resultSet.getString(2));
//                System.out.println(carType);
                result.add(carType);
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
    public List<CarType> loadTypes(int netIdx) throws BaseException {
        List<CarType> result = new ArrayList<CarType>();
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString ="select type_id,type_name from carandtype where net_id = ? group by type_id";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1,netIdx);
            java.sql.ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                CarType carType = new CarType();
                carType.setTypeId(resultSet.getInt(1));

                carType.setTypeName(resultSet.getString(2));
                System.out.println(carType);
                result.add(carType);
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
