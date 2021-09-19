package cn.edu.zucc.cccar.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cn.edu.zucc.cccar.CCCarUtil;
import cn.edu.zucc.cccar.model.Employee;
import cn.edu.zucc.cccar.model.UserInfo;
import cn.edu.zucc.cccar.util.BaseException;

public class DlgRegister extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("注册");
	private Button btnCancel = new Button("取消");


	private JPanel jrPane = new JPanel();
	private JRadioButton jrUser = new JRadioButton("用户");
	private JRadioButton jrEmployee = new JRadioButton("员工");
	private JRadioButton jrSuperEmployee = new JRadioButton("管理员", true); //初始化选择这个
	private ButtonGroup btnGroup = new ButtonGroup();//绑定n个单选为n选一

	private JLabel labelUser = new JLabel("用户：");
	private JLabel labelPwd = new JLabel("密码：");
	private JLabel labelPwd2 = new JLabel("密码：");
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	String loginAccountType="用户";

	public DlgRegister(Dialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(edtUserId);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		workPane.add(labelPwd2);
		workPane.add(edtPwd2);
		this.getContentPane().add(workPane, BorderLayout.CENTER);

		jrPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		jrPane.add(jrUser);
		jrPane.add(jrEmployee);
		this.workPane.add(jrPane, BorderLayout.SOUTH);
		this.setSize(300, 200);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.jrUser) {

			loginAccountType = new String("用户");
			System.out.println("注册类型为 用户");
		} else if (e.getSource() == this.jrEmployee) {
			loginAccountType = new String("普通员工");
			System.out.println("注册类型为 普通员工");
		} else if(e.getSource()==this.btnCancel){
			this.setVisible(false);
		}
		else if(e.getSource()==this.btnOk){
			String userid=this.edtUserId.getText();
			String pwd1=new String(this.edtPwd.getPassword());
			String pwd2=new String(this.edtPwd2.getPassword());
			try {
				switch(loginAccountType){
					case "用户":
						UserInfo user= CCCarUtil.userManager.reg(userid,pwd1,pwd2);
						this.setVisible(false);
						break;
					case "普通员工":
						Employee employee= CCCarUtil.employeeManager.reg(userid,pwd1,pwd2);
						this.setVisible(false);
						break;
				}
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
			
		
	}


}
