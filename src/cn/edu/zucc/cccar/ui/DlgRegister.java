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
	private Button btnOk = new Button("ע��");
	private Button btnCancel = new Button("ȡ��");


	private JPanel jrPane = new JPanel();
	private JRadioButton jrUser = new JRadioButton("�û�");
	private JRadioButton jrEmployee = new JRadioButton("Ա��");
	private JRadioButton jrSuperEmployee = new JRadioButton("����Ա", true); //��ʼ��ѡ�����
	private ButtonGroup btnGroup = new ButtonGroup();//��n����ѡΪnѡһ

	private JLabel labelUser = new JLabel("�û���");
	private JLabel labelPwd = new JLabel("���룺");
	private JLabel labelPwd2 = new JLabel("���룺");
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	String loginAccountType="�û�";

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

			loginAccountType = new String("�û�");
			System.out.println("ע������Ϊ �û�");
		} else if (e.getSource() == this.jrEmployee) {
			loginAccountType = new String("��ͨԱ��");
			System.out.println("ע������Ϊ ��ͨԱ��");
		} else if(e.getSource()==this.btnCancel){
			this.setVisible(false);
		}
		else if(e.getSource()==this.btnOk){
			String userid=this.edtUserId.getText();
			String pwd1=new String(this.edtPwd.getPassword());
			String pwd2=new String(this.edtPwd2.getPassword());
			try {
				switch(loginAccountType){
					case "�û�":
						UserInfo user= CCCarUtil.userManager.reg(userid,pwd1,pwd2);
						this.setVisible(false);
						break;
					case "��ͨԱ��":
						Employee employee= CCCarUtil.employeeManager.reg(userid,pwd1,pwd2);
						this.setVisible(false);
						break;
				}
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
			
		
	}


}
