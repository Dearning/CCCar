package cn.edu.zucc.cccar.itf;

import cn.edu.zucc.cccar.model.CarType;
import cn.edu.zucc.cccar.model.NetInfo;
import cn.edu.zucc.cccar.util.BaseException;

import java.util.List;

public interface ITypeManager {

    /**
     * ��������ָ�����㳵�Ͳ����� �б�
     * @return List<CarType>
     * @throws BaseException
     */
    public void add(CarType carType) throws BaseException ;
    public List<CarType> loadTypes(int netIdx) throws BaseException;

    public List<CarType> loadTypes(Integer netId, Integer categoryId) throws BaseException;

    void deleteType(CarType currentType) throws BaseException;

    List<CarType> loadTypesByCategoryOnly(Integer categoryId)throws BaseException;
}
