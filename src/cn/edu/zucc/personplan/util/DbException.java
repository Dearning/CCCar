package cn.edu.zucc.personplan.util;

public class DbException extends BaseException {
	public DbException(Throwable ex){
		super("���ݿ��������"+ex.getMessage());
	}
}
