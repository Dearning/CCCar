package cn.edu.zucc.cccar;

import cn.edu.zucc.cccar.model.CarType;
import cn.edu.zucc.cccar.model.NetInfo;
import cn.edu.zucc.cccar.util.BaseException;

import java.util.List;

public interface ITypeManager {

    /**
     * 加载所有指定网点车型并返回 列表
     * @return List<CarType>
     * @throws BaseException
     */
    public List<CarType> loadTypes(int netIdx) throws BaseException;
}
