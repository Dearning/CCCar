package cn.edu.zucc.cccar.itf;

import cn.edu.zucc.cccar.model.CarInfo;
import cn.edu.zucc.cccar.model.NetInfo;
import cn.edu.zucc.cccar.util.BaseException;

import java.util.List;

public interface ICarManager {
    /**
     * 加载所有车?并返回 列表
     * @return List<CarInfo>
     * @throws BaseException
     */
    public List<CarInfo> loadAll() throws BaseException;
    /**
     * 通过加载指定的车俩并返回 列表
     * @return List<CarInfo>
     * @throws BaseException
     */
    public List<CarInfo> loadCars(Integer netId, Integer typeId) throws BaseException;
}
