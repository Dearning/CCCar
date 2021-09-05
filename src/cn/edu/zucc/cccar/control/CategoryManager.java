package cn.edu.zucc.cccar.control;

import cn.edu.zucc.cccar.itf.ICategoryManager;
import cn.edu.zucc.cccar.model.CarCategory;
import cn.edu.zucc.cccar.model.CarInfo;
import cn.edu.zucc.cccar.util.BaseException;
import cn.edu.zucc.cccar.util.DBUtil;
import cn.edu.zucc.cccar.util.DbException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager implements ICategoryManager {
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
