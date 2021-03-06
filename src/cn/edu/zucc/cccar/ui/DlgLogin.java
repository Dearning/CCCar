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


public class DlgLogin extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();

	private JButton btnRegister = new JButton("注册");
	private JButton btnLogin = new JButton("登陆");
	private JButton btnCancel = new JButton("退出");
	
	private JLabel labelUser = new JLabel("账号：");
	private JLabel labelPwd = new JLabel("密码：");
	private JTextField edtAccount = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);


	private JPanel jrPane = new JPanel();
	private JRadioButton jrUser = new JRadioButton("用户");
	private JRadioButton jrEmployee = new JRadioButton("员工");
	private JRadioButton jrSuperEmployee = new JRadioButton("管理员", true); //初始化选择这个
	private ButtonGroup btnGroup = new ButtonGroup();//绑定n个单选为n选一
	String loginAccountType="管理员";

	public DlgLogin(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));

		toolBar.add(btnRegister);
		toolBar.add(btnLogin);
		toolBar.add(btnCancel);
		//一个 JFrame 类的实例化对象后，其他组件并不能够直接放到容器上面，需要将组件添加至内容窗格，而不是直接添加至 JFrame 对象。示例代码如下：
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
		// 绑定 n 个单选按钮为同一个 ButtonGroup 则 n个单选项中同时最多只有一个会被选中
		btnGroup.add(jrUser);
		btnGroup.add(jrEmployee);
		btnGroup.add(jrSuperEmployee);
		this.workPane.add(jrPane, BorderLayout.SOUTH);

		// 调试预设账密 TESTMODE TODO 上线版本删除
		this.edtAccount.setText("admin");
		this.edtPwd.setText("123");
		this.jrSuperEmployee.setSelected(true);


		this.setSize(320, 172);
		// 屏幕居中显示
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
		if (e.getSource() == this.jrUser) {

			loginAccountType = new String("用户");
			System.out.println("登陆类型为 用户");
//			Bus.LoginAccountType = "用户";
		} else if (e.getSource() == this.jrEmployee) {
			loginAccountType = new String("普通员工");
			System.out.println("登陆类型为 普通员工");
//			Bus.LoginAccountType = "普通员工";
		} else if (e.getSource() == this.jrSuperEmployee) {
			loginAccountType = new String("管理员");
			System.out.println("登陆类型为 管理员");
//			Bus.LoginAccountType = "管理员";
		}else if (e.getSource() == this.btnLogin) {
			String account=this.edtAccount.getText();
			String pwd=new String(this.edtPwd.getPassword());
			CCCarUtil.currentUserName = account;
			CCCarUtil.currentLoginEmployee = null;
			CCCarUtil.currentLoginUser = null;
			try {
				switch(loginAccountType){
					case "用户":
						CCCarUtil.currentLoginUser= CCCarUtil.userManager.login(account,pwd);
						break;
					case "普通员工":
						CCCarUtil.currentLoginEmployee= CCCarUtil.employeeManager.login(account,pwd);
						break;
					case "管理员":
						CCCarUtil.currentLoginEmployee= CCCarUtil.superEmployeeManager.login(account,pwd);
						break;
				}

			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
			
		} else if (e.getSource() == this.btnCancel) {
			System.exit(0);
		} else if(e.getSource()==this.btnRegister){
			DlgRegister dlg=new DlgRegister(this,"注册",true);
			dlg.setVisible(true);
		}
	}

}
