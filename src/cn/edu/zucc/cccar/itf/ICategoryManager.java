package cn.edu.zucc.cccar.itf;

import cn.edu.zucc.cccar.model.CarCategory;
import cn.edu.zucc.cccar.model.CarInfo;
import cn.edu.zucc.cccar.util.BaseException;

import java.util.List;

public interface ICategoryManager {

    /**
     * 通过加载指定的车俩并返回 列表
     * @return List<CarCategory>
     * @throws BaseException
     */
    public List<CarCategory> loadTypes(Integer netId) throws BaseException;

    ;
}
