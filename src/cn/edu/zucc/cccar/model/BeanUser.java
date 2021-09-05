package cn.edu.zucc.cccar.model;

import java.util.Date;

public class BeanUser {
	Date register_time;
	String user_id;
	public static BeanUser currentLoginUser=null;
	public Date getRegister_time() {
		return register_time;
	}
	public void setRegister_time(Date register_time) {
		this.register_time = register_time;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public static BeanUser getCurrentLoginUser() {
		return currentLoginUser;
	}
	public static void setCurrentLoginUser(BeanUser currentLoginUser) {
		BeanUser.currentLoginUser = currentLoginUser;
	}

}
