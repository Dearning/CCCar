package cn.edu.zucc.cccar.itf;

import cn.edu.zucc.cccar.model.Employee;
import cn.edu.zucc.cccar.model.NetInfo;
import cn.edu.zucc.cccar.util.BaseException;

import java.util.List;

public interface INetManager {

    /**
     * �����������㲢���� �б�
     * @return List<NetInfo>
     * @throws BaseException
     */
    public List<NetInfo> loadAll() throws BaseException;

    void addPlan(String name) throws BaseException;
}
