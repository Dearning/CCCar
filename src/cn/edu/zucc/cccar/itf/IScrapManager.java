package cn.edu.zucc.cccar.itf;

import cn.edu.zucc.cccar.model.Scrap;
import cn.edu.zucc.cccar.util.BaseException;

import java.util.List;

public interface IScrapManager {
    void add(Scrap scrap) throws BaseException;

    List<Scrap> loadAll() throws BaseException;

    void addDescription(String netIdText,int carId) throws BaseException;
}
