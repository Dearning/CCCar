package cn.edu.zucc.cccar.itf;

import cn.edu.zucc.cccar.model.TblOrder;
import cn.edu.zucc.cccar.util.BaseException;

import java.util.List;

public interface IOrderManager {
    List<TblOrder> loadAll() throws BaseException;
}
