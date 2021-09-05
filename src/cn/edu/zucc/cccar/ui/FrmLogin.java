package cn.edu.zucc.cccar.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import cn.edu.zucc.cccar.CCCarUtil;
import cn.edu.zucc.cccar.util.BaseException;


public class FrmLogin extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();

	private JButton btnRegister = new JButton("ע��");
	private JButton btnLogin = new JButton("��½");
	private JButton btnCancel = new JButton("�˳�");
	
	private JLabel labelUser = new JLabel("�˺ţ�");
	private JLabel labelPwd = new JLabel("���룺");
	private JTextField edtAccount = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);


	private JPanel jrPane = new JPanel();
	private JRadioButton jrUser = new JRadioButton("�û�");
	private JRadioButton jrEmployee = new JRadioButton("Ա��");
	private JRadioButton jrSuperEmployee = new JRadioButton("����Ա", true); //��ʼ��ѡ�����
	private ButtonGroup btnGroup = new ButtonGroup();//��n����ѡΪnѡһ

	public FrmLogin(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));

		toolBar.add(btnRegister);
		toolBar.add(btnLogin);
		toolBar.add(btnCancel);
		//һ�� JFrame ���ʵ���������������������ܹ�ֱ�ӷŵ��������棬��Ҫ�������������ݴ��񣬶�����ֱ������� JFrame ����ʾ���������£�
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(edtAccount);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		this.getContentPane().add(workPane, BorderLayout.CENTER);

		jrPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		jrPane.add(jrUser);
		jrPane.add(jrEmployee);
		jrPane.add(jrSuperEmployee);
		// �� n ����ѡ��ťΪͬһ�� ButtonGroup �� n����ѡ����ͬʱ���ֻ��һ���ᱻѡ��
		btnGroup.add(jrUser);
		btnGroup.add(jrEmployee);
		btnGroup.add(jrSuperEmployee);
		this.workPane.add(jrPane, BorderLayout.SOUTH);

		// ����Ԥ������ TESTMODE TODO ���߰汾ɾ��
		this.edtAccount.setText("admin");
		this.edtPwd.setText("123");
		this.jrSuperEmployee.setSelected(true);


		this.setSize(320, 172);
		// ��Ļ������ʾ
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		btnLogin.addActionListener(this);
		btnCancel.addActionListener(this);
		btnRegister.addActionListener(this);
		jrUser.addActionListener(this);
		jrEmployee.addActionListener(this);
		jrSuperEmployee.addActionListener(this);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String loginAccountType="����Ա";
		if (e.getSource() == this.jrUser) {
			System.out.println("��½����Ϊ �û�");
			loginAccountType = "�û�";
//			Bus.LoginAccountType = "�û�";
		} else if (e.getSource() == this.jrEmployee) {
			loginAccountType = "��ͨԱ��";
			System.out.println("��½����Ϊ ��ͨԱ��");
//			Bus.LoginAccountType = "��ͨԱ��";
		} else if (e.getSource() == this.jrSuperEmployee) {
			loginAccountType = "����Ա";
			System.out.println("��½����Ϊ ����Ա");
//			Bus.LoginAccountType = "����Ա";
		}else if (e.getSource() == this.btnLogin) {
			String account=this.edtAccount.getText();
			String pwd=new String(this.edtPwd.getPassword());
			CCCarUtil.currentUserName = account;
			try {
				switch(loginAccountType){
					case "�û�":
						CCCarUtil.currentLoginUser= CCCarUtil.userManager.login(account,pwd);
						break;
					case "��ͨԱ��":
						CCCarUtil.currentLoginEmployee= CCCarUtil.employeeManager.login(account,pwd);
						break;
					case "����Ա":
						CCCarUtil.currentLoginEmployee= CCCarUtil.superEmployeeManager.login(account,pwd);
						break;
				}

			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
			
		} else if (e.getSource() == this.btnCancel) {
			System.exit(0);
		} else if(e.getSource()==this.btnRegister){
			FrmRegister dlg=new FrmRegister(this,"ע��",true);
			dlg.setVisible(true);
		}
	}

}
