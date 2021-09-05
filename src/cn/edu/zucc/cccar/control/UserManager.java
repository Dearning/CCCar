package cn.edu.zucc.cccar.control;

import cn.edu.zucc.cccar.itf.IUserManager;
import cn.edu.zucc.cccar.model.BeanUser;
import cn.edu.zucc.cccar.model.UserInfo;
import cn.edu.zucc.cccar.util.BaseException;

public class UserManager implements IUserManager {
    @Override
    public UserInfo reg(String userid, String pwd, String pwd2) throws BaseException {
        return null;
    }

    @Override
    public UserInfo login(String userid, String pwd) throws BaseException {
        return null;
    }

    @Override
    public void changePwd(UserInfo user, String oldPwd, String newPwd, String newPwd2) throws BaseException {

    }
}
