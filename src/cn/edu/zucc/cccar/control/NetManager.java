package cn.edu.zucc.cccar.control;

import cn.edu.zucc.cccar.itf.INetManager;
import cn.edu.zucc.cccar.model.Employee;
import cn.edu.zucc.cccar.model.NetInfo;
import cn.edu.zucc.cccar.util.BaseException;
import cn.edu.zucc.cccar.util.BusinessException;
import cn.edu.zucc.cccar.util.DBUtil;
import cn.edu.zucc.cccar.util.DbException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class NetManager implements INetManager {
    @Override
    public void addPlan(String name) throws BaseException {

    }

    @Override
    public List<NetInfo> loadAll() throws BaseException {
        List<NetInfo> result = new ArrayList<NetInfo>();
        Connection connection =null;
        try {
            connection= DBUtil.getConnection();
            String sqlString ="select * from net_info ";
            java.sql.PreparedStatement pStatement = connection.prepareStatement(sqlString);
            java.sql.ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                NetInfo netInfo = new NetInfo();
                netInfo.setNetId(resultSet.getInt(1));
                netInfo.setNetName(resultSet.getString(2));
                netInfo.setCity(resultSet.getString(3));
                netInfo.setAddress(resultSet.getString(4));
                netInfo.setPhone(resultSet.getString(5));
                result.add(netInfo);


            }
            return result;

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

//        return result;
    }
}
