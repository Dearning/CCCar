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
		//date�ĸ�ʽ��ʲô��?
		System.out.println("�жϼƻ�����");
	    if(name==null || "".equals(name)) throw new BusinessException("�������Ʊ����ṩ");
		//������Ҫ�����Ƿ���ͬ���Ĳ���,���ڲ�������,����Ѿ�����,�򱨴�,���������,��ȥ�ҵ���ǰ��������,�����м�¼,name�ǲ�������
		Connection connection =null;
		try {
			int maxStepIndex =0;
			String currentUserid = BeanUser.currentLoginUser.getUser_id();
			connection=DBUtil.getConnection();
			String userid = plan.getUser_idString();
			//����beanPlan����������û��ͳһ,д�����Ƚ�����,��ȡ��ѵ
			//
			String sqlString = "select * from tbl_step,tbl_plan "
					+ " where tbl_step.plan_id=tbl_plan.plan_id "
					+ " and user_id=? and step_name=?";
			PreparedStatement pStatement = connection.prepareStatement(sqlString);

			pStatement.setString(1, userid);//�˴�û��beanUser����currentUser��̬�����洢����ǰ�û�
			pStatement.setString(2, name);
			ResultSet rs = pStatement.executeQuery();
			if(rs.next()) {
				pStatement.close();
				rs.close();
				throw new BusinessException("ͬ�������Ѿ�����");
			}
			pStatement.close();
			rs.close();
			//��ͬһplan����������
			sqlString = "select max(step_order) from tbl_step where plan_id = ? ";
			pStatement = connection.prepareStatement(sqlString);

			int planid = plan.getPlan_id();
			pStatement.setInt(1, planid);
			rs = pStatement.executeQuery();
			
			if(rs.next()) {
				maxStepIndex = rs.getInt(1)+1;
			} else {
				maxStepIndex = 1;
				//��Ϊ�Ҳ�����¼��ʾû�м�¼��ô��ǰΪ��һ����¼,throw new BusinessException("û�ҵ�������,�����ǲ����ڼ�¼");
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
			//����ʹ�õķ�����Ĭ��ԭ����stringת������������ʽ
//			pStatement.setDate(4, planBeginDate);
			pStatement.setTimestamp(4,Timestamp.valueOf(planstartdate));
			pStatement.setTimestamp(5, Timestamp.valueOf(planfinishdate));
			//TODOҪ�޸ĳɷ���ʱ��timestampfin
			pStatement.execute();
			//����ҲҪ�޸�TODO
			
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
			//�߼������ҵ�����Ȼ��ɾ��,Ȼ����º���Ĳ���,�͸���plan��
			Statement statement = connection.createStatement();
			String currentUseIdString = BeanUser.getCurrentLoginUser().getUser_id();
			String sqlString = "select step_order,s.plan_id,user_id "
					+ " from tbl_step s,tbl_plan p where s.plan_id=p.plan_id "
					+ " and step_id = ?";
			//��ͬ�û�stepid����һ���İ�
			System.out.println(stepId);
			System.out.println(sqlString);
			PreparedStatement pst = connection.prepareStatement(sqlString);
			pst.setInt(1, stepId);
			ResultSet resultSet = pst.executeQuery();
			//Ѱ��step_id��user_idȷ�ϵ�step��¼
			int stepOrd =0;			//��Ϊ������Ҫ����
			String userId = null; 	//������Ϊ���Բ鿴���˵ı�,���Բ���ɾ���˵Ĳ�����Ϣʲô��,������Ҫͨ������plan����step�����ҵ�planid
			int planId=0;
			if(resultSet.next()) {
				stepOrd = resultSet.getInt(1);
				planId = resultSet.getInt(2);
				userId = resultSet.getString(3);
			}else {
				resultSet.close();
				statement.close();
				throw new BusinessException("�ò��費����,�޷�ɾ��");
			}
			resultSet.close();
			//�����ǰ��id������ɾ���ƻ������û�id
			System.out.println(userId);
			if(!currentUseIdString.equals(userId)) {
				pst.close();
				throw new BusinessException("���Ǳ��˵Ĳ���,����ɾ�����˵ı�");
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
			//Ӧ��Ҫ�����������TODO
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
		//������һ�����ƶ���,��ô��Ҫ�ҵ���ǰorder ,Ȼ���ҵ�����ǰһ��orderȻ�󽻻�order
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
				throw new BusinessException("û�ҵ�step");
				// ���û�ѵ��������������������?
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
			//Ӧ��Ҫ�����������TODO
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
				throw new BusinessException("û�ҵ�step");
				// ���û�ѵ��������������������?
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
			//Ӧ��Ҫ�����������TODO
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