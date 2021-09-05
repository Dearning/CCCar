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

    ;
}
