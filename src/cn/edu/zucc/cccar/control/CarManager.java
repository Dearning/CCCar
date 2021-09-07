package cn.edu.zucc.cccar.control;

import cn.edu.zucc.cccar.itf.ICarManager;
import cn.edu.zucc.cccar.model.CarInfo;
import cn.edu.zucc.cccar.model.CarType;
import cn.edu.zucc.cccar.model.NetInfo;
import cn.edu.zucc.cccar.util.BaseException;
import cn.edu.zucc.cccar.util.BusinessException;
import cn.edu.zucc.cccar.util.DBUtil;
import cn.edu.zucc.cccar.util.DbException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CarManager implements ICarManager {
    @Override
    public List<CarInfo> loadAll() throws BaseException {
        return null;
    }

    @Override
    public List<CarInfo> loadCars(Integer typeId) throws BaseException {
        List<CarInfo> result = new ArrayList<CarInfo>();
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString ="select * from car_info where type_Id = ? order by net_id";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1,typeId);
            java.sql.ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                CarInfo carInfo = new CarInfo();
                carInfo.setCarId(resultSet.getInt(1));
                carInfo.setNetId(resultSet.getInt(2));
                carInfo.setTypeId(resultSet.getInt(3));
                carInfo.setLicense(resultSet.getString(4));
                carInfo.setCarStatus(resultSet.getInt(5));
                result.add(carInfo);
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
    public void deleteCar(CarInfo currentCar) throws BaseException {
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString =null;

            sqlString ="select * from car_info where car_id = ?";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1,currentCar.getCarId());
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()) {
                resultSet.close();

                pStatement.close();
                sqlString ="delete from car_info where car_id = ?";
                pStatement = connection.prepareStatement(sqlString);
                pStatement.setInt(1,currentCar.getCarId());
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
    public void add(CarInfo carInfo) throws BaseException {
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString =null;
            sqlString ="select count(*) from car_info where license=? ";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            pStatement.setString(1,carInfo.getLicense());
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                if(resultSet.getInt(1) > 0) throw new BusinessException("相同车牌已存在");
            }
            pStatement.close();
            resultSet.close();
            //TODO 没有车型？
            sqlString ="insert into car_info ( net_id, type_Id, license, car_status) values(?,?,?,?)";
            pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1,carInfo.getNetId());
            pStatement.setInt(2,carInfo.getTypeId());
            pStatement.setString(3,carInfo.getLicense());
            pStatement.setInt(4,carInfo.getCarStatus());
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
    public List<CarInfo> loadCars(Integer netId, Integer typeId) throws BaseException {
        List<CarInfo> result = new ArrayList<CarInfo>();
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString ="select * from car_info where net_id = ? and type_id = ?";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1,netId);
            pStatement.setInt(2,typeId);
            java.sql.ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                CarInfo carInfo = new CarInfo();
                carInfo.setCarId(resultSet.getInt(1));
                carInfo.setNetId(resultSet.getInt(2));
                carInfo.setTypeId(resultSet.getInt(3));
                carInfo.setLicense(resultSet.getString(4));
                carInfo.setCarStatus(resultSet.getInt(5));
                result.add(carInfo);
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
    // public List<CarInfo> loadCars(Integer netId, Integer carId) throws BaseException {
    //        List<CarInfo> result = new ArrayList<CarInfo>();
    //        Connection connection =null;
    //        try {
    //            connection= DBUtil.getConnection();
    //            String sqlString ="select type_id,type_name from carandtype where net_id = ? group by type_id";
    //            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
    //
    //            java.sql.ResultSet resultSet = pStatement.executeQuery();
    //            while(resultSet.next()){
    //                CarInfo carInfo = new CarInfo();
    //
    //
    //                result.add(carInfo);
    //            }
    //            return result;
    //
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //            throw new DbException(e);
    //        }finally {
    //            if(connection!=null) {
    //                try {
    //                    connection.close();
    //                } catch (Exception e2) {
    //                    e2.printStackTrace();
    //                }
    //            }
    //        }
    //    }
}
