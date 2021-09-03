package cn.edu.zucc.personplan.model;

import java.util.Date;

public class BeanStep {
	int step_id;
	int plan_id;
	int step_order;
	String step_name;
	Date plan_begin_time,plan_end_time,real_begin_time,real_end_time;
	public int getStep_id() {
		return step_id;
	}
	public void setStep_id(int step_id) {
		this.step_id = step_id;
	}
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public int getStep_order() {
		return step_order;
	}
	public void setStep_order(int step_order) {
		this.step_order = step_order;
	}
	public String getStep_name() {
		return step_name;
	}
	public void setStep_name(String step_name) {
		this.step_name = step_name;
	}
	public Date getPlan_begin_time() {
		return plan_begin_time;
	}
	public void setPlan_begin_time(Date plan_begin_time) {
		this.plan_begin_time = plan_begin_time;
	}
	public Date getPlan_end_time() {
		return plan_end_time;
	}
	public void setPlan_end_time(Date plan_end_time) {
		this.plan_end_time = plan_end_time;
	}
	public Date getReal_begin_time() {
		return real_begin_time;
	}
	public void setReal_begin_time(Date real_begin_time) {
		this.real_begin_time = real_begin_time;
	}
	public Date getReal_end_time() {
		return real_end_time;
	}
	public void setReal_end_time(Date real_end_time) {
		this.real_end_time = real_end_time;
	}
	public static final String[] tblStepTitle={"序号","名称","计划开始时间","计划完成时间","实际开始时间","实际完成时间"};
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 */
	public String getCell(int col){
		if(col==0) return String.valueOf(step_id);
		else if(col==1) return step_name;
		else if(col==2) return plan_begin_time==null? null:plan_begin_time.toString();
		else if(col==3) return plan_end_time==null? null:plan_end_time.toString();
		else if(col==4) return real_begin_time==null? null:real_begin_time.toString();
		else if(col==5) return real_end_time==null? null:real_end_time.toString();
		
		else return "";
	}
}
