package cn.edu.zucc.cccar.model;

import java.util.Date;

public class BeanPlan {
	int plan_id;
	String user_idString;
	int plan_order;
	String plan_nameString;
	Date create_timeDate;
	int step_count,start_step_count,finished_step_count;
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public String getUser_idString() {
		return user_idString;
	}
	public void setUser_idString(String user_idString) {
		this.user_idString = user_idString;
	}
	public int getPlan_order() {
		return plan_order;
	}
	public void setPlan_order(int plan_order) {
		this.plan_order = plan_order;
	}
	public String getPlan_nameString() {
		return plan_nameString;
	}
	public void setPlan_nameString(String plan_nameString) {
		this.plan_nameString = plan_nameString;
	}
	public Date getCreate_timeDate() {
		return create_timeDate;
	}
	public void setCreate_timeDate(Date create_timeDate) {
		this.create_timeDate = create_timeDate;
	}
	public int getStep_count() {
		return step_count;
	}
	public void setStep_count(int step_count) {
		this.step_count = step_count;
	}
	public int getStart_step_count() {
		return start_step_count;
	}
	public void setStart_step_count(int start_step_count) {
		this.start_step_count = start_step_count;
	}
	public int getFinished_step_count() {
		return finished_step_count;
	}
	public void setFinished_step_count(int finished_step_count) {
		this.finished_step_count = finished_step_count;
	}
	public static final String[] tableTitles={"���","����","������","�������"};
	/**
	 * �����и���javabean������޸ı��������룬col��ʾ��������е�����ţ�0��ʼ
	 */
	public String getCell(int col){
		if(col==0) return String.valueOf(plan_id);
		else if(col==1) return plan_nameString;
		else if(col==2) return String.valueOf(step_count);
		else if(col==3) return String.valueOf(finished_step_count);
		else return "";
	}

}