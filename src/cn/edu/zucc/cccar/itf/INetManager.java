package cn.edu.zucc.cccar.itf;

import cn.edu.zucc.cccar.model.Employee;
import cn.edu.zucc.cccar.model.NetInfo;
import cn.edu.zucc.cccar.util.BaseException;
import cn.edu.zucc.cccar.util.DbException;

import java.util.List;

public interface INetManager {

    /**
     * 加载所有网点并返回 列表
     * @return List<NetInfo>
     * @throws BaseException
     */
    public List<NetInfo> loadAll() throws BaseException;

    void addPlan(String name) throws BaseException;

    void add(NetInfo net) throws BaseException;

    void deleteNet(NetInfo currentNet) throws BaseException;
}
