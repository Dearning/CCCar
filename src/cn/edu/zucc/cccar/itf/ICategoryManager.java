package cn.edu.zucc.cccar.itf;

import cn.edu.zucc.cccar.model.CarCategory;
import cn.edu.zucc.cccar.model.CarInfo;
import cn.edu.zucc.cccar.util.BaseException;

import java.util.List;

public interface ICategoryManager {

    /**
     * ͨ������ָ���ĳ��������� �б�
     * @return List<CarCategory>
     * @throws BaseException
     */
    public List<CarCategory> loadTypes(Integer netId) throws BaseException;

    public void add(CarCategory carCategory) throws BaseException;

    void deleteCategory(CarCategory currentCategory)  throws BaseException;

    List<CarCategory> loadAll()throws BaseException;

    ;
}
