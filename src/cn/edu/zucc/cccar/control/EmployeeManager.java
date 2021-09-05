package cn.edu.zucc.cccar.control;

import cn.edu.zucc.cccar.itf.IEmployeeManager;
import cn.edu.zucc.cccar.model.Employee;
import cn.edu.zucc.cccar.util.BaseException;

public class EmployeeManager implements IEmployeeManager {
    @Override
    public Employee reg(String userid, String pwd, String pwd2) throws BaseException {
        return null;
    }

    @Override
    public Employee login(String userid, String pwd) throws BaseException {
        return null;
    }

    @Override
    public void changePwd(Employee employee, String oldPwd, String newPwd, String newPwd2) throws BaseException {

    }
}
