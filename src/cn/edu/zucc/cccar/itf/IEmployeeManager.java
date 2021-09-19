package cn.edu.zucc.cccar.itf;

import cn.edu.zucc.cccar.model.Employee;
import cn.edu.zucc.cccar.util.BaseException;

import java.util.List;

public interface IEmployeeManager {
    /**
     * ע�᣺
     * Ҫ���û��������ظ�������Ϊ��
     * ����������������һ�£����벻��Ϊ��
     * ���ע��ʧ�ܣ����׳��쳣
     * @param  employeeid
     * @param pwd  ����
     * @param pwd2 �ظ����������
     * @return
     * @throws BaseException
     */
    public Employee reg(String  employeeid, String pwd, String pwd2) throws BaseException;
    /**
     * ��½
     * 1�����Ա�������ڻ�����������׳�һ���쳣
     * 2�������֤�ɹ����򷵻ص�ǰ�û���Ϣ
     * @param  employeeid
     * @param pwd
     * @return
     * @throws BaseException
     */
    public Employee login(String  employeeid,String pwd)throws BaseException;
    /**
     * �޸�����
     * ���û�гɹ��޸ģ����׳��쳣
     * @param employee    ��ǰԱ��
     * @param oldPwd  ԭ����
     * @param newPwd  ������
     * @param newPwd2 �ظ������������
     */
    public void changePwd(Employee employee, String oldPwd, String newPwd, String newPwd2)throws BaseException;

    List<Employee> loadAll()throws BaseException;

    void update(Employee employee) throws BaseException;
}
