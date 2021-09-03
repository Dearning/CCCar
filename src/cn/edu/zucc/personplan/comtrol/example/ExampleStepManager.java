package cn.edu.zucc.personplan.comtrol.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zucc.personplan.itf.IStepManager;
import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.model.BeanStep;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DbException;

public class ExampleStepManager implements IStepManager {

	@Override
	public void add(BeanPlan plan, String name, String planstartdate,
			String planfinishdate) throws BaseException {
		//date的格式是什么呢?
		System.out.println("判断计划名字");
	    if(name==null || "".equals(name)) throw new BusinessException("步骤名称必须提供");
		//首先需要查找是否有同名的步骤,由于不能重名,如果已经存在,则报错,如果不存在,则去找到当前最大步骤序号,添加行记录,name是步骤名称
		Connection connection =null;
		try {
			int maxStepIndex =0;
			String currentUserid = BeanUser.currentLoginUser.getUser_id();
			connection=DBUtil.getConnection();
			String userid = plan.getUser_idString();
			//这里beanPlan的数据类型没有统一,写起来比较难受,吸取教训
			//
			String sqlString = "select * from tbl_step,tbl_plan "
					+ " where tbl_step.plan_id=tbl_plan.plan_id "
					+ " and user_id=? and step_name=?";
			PreparedStatement pStatement = connection.prepareStatement(sqlString);

			pStatement.setString(1, userid);//此处没有beanUser但是currentUser静态变量存储量当前用户
			pStatement.setString(2, name);
			ResultSet rs = pStatement.executeQuery();
			if(rs.next()) {
				pStatement.close();
				rs.close();
				throw new BusinessException("同名步骤已经存在");
			}
			pStatement.close();
			rs.close();
			//找同一plan里最大步骤序号
			sqlString = "select max(step_order) from tbl_step where plan_id = ? ";
			pStatement = connection.prepareStatement(sqlString);

			int planid = plan.getPlan_id();
			pStatement.setInt(1, planid);
			rs = pStatement.executeQuery();
			
			if(rs.next()) {
				maxStepIndex = rs.getInt(1)+1;
			} else {
				maxStepIndex = 1;
				//因为找不到记录表示没有记录那么当前为第一条记录,throw new BusinessException("没找到最大序号,可能是不存在记录");
			}
			pStatement.close();
			rs.close();
			//plan create_time = plan_begin_time plan
			
			sqlString = " insert into tbl_step(plan_id,step_order,step_name,"
					+ "plan_begin_time,plan_end_time,real_begin_time,real_end_time) "
					+ " values(?,?,?,?,?,null,null) ";
			pStatement = connection.prepareStatement(sqlString);
			pStatement.setInt(1, planid);
			pStatement.setInt(2, maxStepIndex);
			pStatement.setString(3, name);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//			java.sql.Date planBeginDate = strToDate(planstartdate, format);
//			java.sql.Date planEndDate = strToDate(planfinishdate, format);
			//这里使用的方法是默认原来的string转换成年月日形式
//			pStatement.setDate(4, planBeginDate);
			pStatement.setTimestamp(4,Timestamp.valueOf(planstartdate));
			pStatement.setTimestamp(5, Timestamp.valueOf(planfinishdate));
			//TODO要修改成分秒时的timestampfin
			pStatement.execute();
			//后面也要修改TODO
			
			pStatement.close();
			sqlString = "select count(*) from tbl_step where plan_id = ?";
			pStatement = connection.prepareStatement(sqlString);
			pStatement.setInt(1, planid);
			rs = pStatement.executeQuery();
			int cntOfstep;
			int cntOfstartStep;
			int cntOfendStep;
			if(rs.next()) {
				cntOfstep = rs.getInt(1);
			} else {
				cntOfstep = 0;
			}
			rs.close();
			pStatement.close();
			updateStep(planid);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		
	}
	public static void updateStep(int planid)throws BaseException {
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			String sqlString = sqlString = "select count(*) from tbl_step where plan_id = ?";

			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			pStatement = connection.prepareStatement(sqlString);
			pStatement.setInt(1, planid);
			ResultSet rs = pStatement.executeQuery();
			int cntOfstep;
			int cntOfstartStep;
			int cntOfendStep;
			if(rs.next()) {
				cntOfstep = rs.getInt(1);
			} else {
				cntOfstep = 0;
			}
			rs.close();
			pStatement.close();
			sqlString = "update tbl_plan set step_count = ? where plan_id = " + planid;
			pStatement =connection.prepareStatement(sqlString);
			System.out.println("step"+cntOfstep);
			pStatement.setInt(1, cntOfstep);
			pStatement.execute();
			pStatement.close();
			
			sqlString = "select count(*) from tbl_step where plan_id = ? and real_begin_time is not null";
			pStatement = connection.prepareStatement(sqlString);
			pStatement.setInt(1, planid);
			rs = pStatement.executeQuery();
			if(rs.next()) {
				cntOfstartStep = rs.getInt(1);
			} else {
				cntOfstartStep = 0;
			}
			rs.close();
			pStatement.close();

			sqlString = "update tbl_plan set start_step_count = ? where plan_id = " + planid;
			pStatement =connection.prepareStatement(sqlString);
			System.out.println("start"+cntOfstartStep);
			pStatement.setInt(1, cntOfstartStep);
			pStatement.execute();
			pStatement.close();
			
			sqlString = "select count(*) from tbl_step where plan_id = ? and real_end_time is not null";
			pStatement = connection.prepareStatement(sqlString);
			pStatement.setInt(1, planid);
			rs = pStatement.executeQuery();
			if(rs.next()) {
				cntOfendStep = rs.getInt(1);
			} else {
				cntOfendStep = 0;
			}
			rs.close();
			pStatement.close();

			sqlString = "update tbl_plan set finished_step_count = ? where plan_id = " + planid;
			pStatement =connection.prepareStatement(sqlString);
			System.out.println("end"+cntOfendStep);
			pStatement.setInt(1, cntOfendStep);
			pStatement.execute();
			pStatement.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	}
	@Override
	public List<BeanStep> loadSteps(BeanPlan plan) throws BaseException {
		List<BeanStep> result=new ArrayList<BeanStep>();
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			String sqlString = "select step_id,plan_id,step_order,step_name,plan_begin_time, "
					+ " plan_end_time,real_begin_time,real_end_time "
					+ " from tbl_step where plan_id=? order by step_order";
			PreparedStatement pst = connection.prepareStatement(sqlString);
			pst.setInt(1, plan.getPlan_id());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				BeanStep beanStep = new BeanStep();
				beanStep.setStep_id(rs.getInt(1));
				beanStep.setPlan_id(rs.getInt(2));
				beanStep.setStep_order(rs.getInt(3));
				beanStep.setStep_name(rs.getString(4));
				beanStep.setPlan_begin_time(rs.getTimestamp(5));
				beanStep.setPlan_end_time(rs.getTimestamp(6));
				beanStep.setReal_begin_time(rs.getTimestamp(7));
				beanStep.setReal_end_time(rs.getTimestamp(8));
				result.add(beanStep);
			}
			System.out.println("result");
			System.out.println(result);

			int planid = plan.getPlan_id();
			updateStep(planid);
			return result;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	}

	@Override
	public void deleteStep(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
		Connection connection = null;
		int stepId = step.getStep_id();
		try {
			connection = DBUtil.getConnection();
			//逻辑就是找到步骤然后删除,然后更新后面的步骤,和更新plan表
			Statement statement = connection.createStatement();
			String currentUseIdString = BeanUser.getCurrentLoginUser().getUser_id();
			String sqlString = "select step_order,s.plan_id,user_id "
					+ " from tbl_step s,tbl_plan p where s.plan_id=p.plan_id "
					+ " and step_id = ?";
			//不同用户stepid可以一样的吧
			System.out.println(stepId);
			System.out.println(sqlString);
			PreparedStatement pst = connection.prepareStatement(sqlString);
			pst.setInt(1, stepId);
			ResultSet resultSet = pst.executeQuery();
			//寻找step_id和user_id确认的step记录
			int stepOrd =0;			//因为后面需要整理
			String userId = null; 	//好像因为可以查看别人的表,所以不能删别人的步骤信息什么的,所以需要通过连接plan表和step表来找到planid
			int planId=0;
			if(resultSet.next()) {
				stepOrd = resultSet.getInt(1);
				planId = resultSet.getInt(2);
				userId = resultSet.getString(3);
			}else {
				resultSet.close();
				statement.close();
				throw new BusinessException("该步骤不存在,无法删除");
			}
			resultSet.close();
			//如果当前的id不是所删除计划所属用户id
			System.out.println(userId);
			if(!currentUseIdString.equals(userId)) {
				pst.close();
				throw new BusinessException("这是别人的步骤,不能删除别人的表");
			}
			
			sqlString = "delete from tbl_step where step_id = "+stepId;
			statement.execute(sqlString);
			statement.close();
			
			sqlString = "update tbl_step set step_order = step_order - 1 where plan_id=? and step_order>" + stepOrd;
			pst = connection.prepareStatement(sqlString);
			pst.setInt(1, planId);
			pst.execute();
			pst.close();
			int planid = step.getPlan_id();
			updateStep(planid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void startStep(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			
			String sqlString = "update tbl_step set real_begin_time = ? "
					+ "where step_id = ? and plan_id = ?";
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			pStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			int stepId = step.getStep_id();
			pStatement.setInt(2, stepId);
			int planId =step.getPlan_id();
			pStatement.setInt(3, planId);
			pStatement.execute();

			int planid = step.getPlan_id();
			updateStep(planid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	}

	@Override
	public void finishStep(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			
			String sqlString = "update tbl_step set real_end_time = ? "
					+ "where step_id = ? and plan_id = ?";
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			pStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			int stepId = step.getStep_id();
			pStatement.setInt(2, stepId);
			int planId =step.getPlan_id();
			pStatement.setInt(3, planId);
			pStatement.execute();

			int planid = step.getPlan_id();
			updateStep(planid);
			//应该要更新已完成数TODO
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	}

	@Override
	public void moveUp(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
		//假设是一个个移动的,那么需要找到当前order ,然后找到比他前一个order然后交换order
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			int secStepOrd = step.getStep_order();
			int secStepId = step.getStep_id();
			int planId = step.getPlan_id();
			String sqlString = "select step_id from tbl_step where step_order = ? and plan_id = ?";
			PreparedStatement pStatement = connection.prepareStatement(sqlString);

			int firstStepOrd=secStepOrd-1;
			pStatement.setInt(1, firstStepOrd);
			pStatement.setInt(2, planId);
			ResultSet rSet = pStatement.executeQuery();
			int firstStepId = 0;
			if(rSet.next()) {
				firstStepId = rSet.getInt(1);
			} else{
				throw new BusinessException("没找到step");
				// 如果没搜到会有这样的情况出现吗?
			}
			rSet.close();
			pStatement.close();
			
//			sqlString = "select max(step_order) from tbl_step where plan_id = ? ";
//			pStatement = connection.prepareStatement(sqlString);
//			pStatement.setInt(1, planId);
//			rSet = pStatement.executeQuery();
//			int maxOrd = 0;
//			if(rSet.next()) {
//				maxOrd = rSet.getInt(1)+1;
//			}
//			rSet.close();
//			pStatement.close();
			// tmp = small, small = big, big = tmp;
			int tmp = secStepOrd;
			System.out.println("first"+firstStepOrd);
			System.out.println("sec"+secStepOrd);
			sqlString = "update tbl_step set step_order = case "
					+ "when step_id =? then ? when step_id =? then ? "
					+ "else step_order end where plan_id = ?";
			pStatement = connection.prepareStatement(sqlString);
			pStatement.setInt(1, firstStepId);
			pStatement.setInt(2, secStepOrd);
			pStatement.setInt(3, secStepId);
			pStatement.setInt(4, firstStepOrd);
			pStatement.setInt(5, planId);
			System.out.println(pStatement);
			pStatement.execute();
			pStatement.close();
			rSet.close();
			//应该要更新已完成数TODO
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void moveDown(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			int firstStepOrd = step.getStep_order();
			int  firstStepId= step.getStep_id();
			int planId = step.getPlan_id();
			String sqlString = "select step_id from tbl_step where step_order = ? and plan_id = ?";
			PreparedStatement pStatement = connection.prepareStatement(sqlString);

			int secStepOrd=firstStepOrd+1;
			pStatement.setInt(1, firstStepOrd);
			pStatement.setInt(2, planId);
			ResultSet rSet = pStatement.executeQuery();
			int  secStepId= 0;
			if(rSet.next()) {
				secStepId = rSet.getInt(1);
			} else{
				throw new BusinessException("没找到step");
				// 如果没搜到会有这样的情况出现吗?
			}
			rSet.close();
			pStatement.close();
			
//			sqlString = "select max(step_order) from tbl_step where plan_id = ? ";
//			pStatement = connection.prepareStatement(sqlString);
//			pStatement.setInt(1, planId);
//			rSet = pStatement.executeQuery();
//			int maxOrd = 0;
//			if(rSet.next()) {
//				maxOrd = rSet.getInt(1)+1;
//			}
//			rSet.close();
//			pStatement.close();
			// tmp = small, small = big, big = tmp;
			int tmp = secStepOrd;
			sqlString = "update tbl_step set step_order = case "
					+ "when step_id =? then ? when step_id =? then ? "
					+ "else step_order end where plan_id = ?";
			pStatement = connection.prepareStatement(sqlString);
			pStatement.setInt(1, firstStepId);
			pStatement.setInt(2, secStepOrd);
			pStatement.setInt(3, secStepId);
			pStatement.setInt(4, firstStepOrd);
			pStatement.setInt(5, planId);
			pStatement.execute();
			pStatement.close();
			rSet.close();
			//应该要更新已完成数TODO
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	}

}
