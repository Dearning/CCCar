package cn.edu.zucc.cccar.itf;

import cn.edu.zucc.cccar.model.CarInfo;
import cn.edu.zucc.cccar.model.NetInfo;
import cn.edu.zucc.cccar.util.BaseException;

import java.util.List;

public interface ICarManager {
    /**
     * �������г�?������ �б�
     * @return List<CarInfo>
     * @throws BaseException
     */
    public List<CarInfo> loadAll() throws BaseException;
    /**
     * ͨ������ָ���ĳ��������� �б�
     * @return List<CarInfo>
     * @throws BaseException
     */
    public List<CarInfo> loadCars(Integer netId, Integer typeId) throws BaseException;

    void add(CarInfo carInfo) throws BaseException;

    void deleteCar(CarInfo currentCar) throws BaseException;

    List<CarInfo> loadCars(Integer typeId)throws BaseException;
}
