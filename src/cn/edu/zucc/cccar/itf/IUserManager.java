package cn.edu.zucc.cccar.itf;

import cn.edu.zucc.cccar.model.UserInfo;
import cn.edu.zucc.cccar.util.BaseException;

import java.util.List;

public interface IUserManager {
    /**
     * 注册：
     * 要求用户名不能重复，不能为空
     * 两次输入的密码必须一致，密码不能为空
     * 如果注册失败，则抛出异常
     * @param userid
     * @param pwd  密码
     * @param pwd2 重复输入的密码
     * @return
     * @throws BaseException
     */
    public UserInfo reg(String userid, String pwd, String pwd2) throws BaseException;
    /**
     * 登陆
     * 1、如果用户不存在或者密码错误，抛出一个异常
     * 2、如果认证成功，则返回当前用户信息
     * @param userid
     * @param pwd
     * @return
     * @throws BaseException
     */
    public UserInfo login(String userid,String pwd)throws BaseException;
    /**
     * 修改密码
     * 如果没有成功修改，则抛出异常
     * @param user    当前用户
     * @param oldPwd  原密码
     * @param newPwd  新密码
     * @param newPwd2 重复输入的新密码
     */
    public void changePwd(UserInfo user, String oldPwd,String newPwd, String newPwd2)throws BaseException;

    List<UserInfo> loadAll() throws BaseException;

    void update(UserInfo carInfo)throws BaseException;
}
