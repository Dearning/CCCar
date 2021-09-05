package cn.edu.zucc.cccar.control;

import cn.edu.zucc.cccar.ITypeManager;
import cn.edu.zucc.cccar.model.CarType;
import cn.edu.zucc.cccar.model.NetInfo;
import cn.edu.zucc.cccar.util.BaseException;
import cn.edu.zucc.cccar.util.DBUtil;
import cn.edu.zucc.cccar.util.DbException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class TypeManager implements ITypeManager {
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
