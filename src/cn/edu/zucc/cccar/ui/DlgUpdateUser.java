package cn.edu.zucc.cccar.ui;

import cn.edu.zucc.cccar.CCCarUtil;
import cn.edu.zucc.cccar.model.*;
import cn.edu.zucc.cccar.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class DlgUpdateUser extends JDialog implements ActionListener {
    public NetInfo netInfo=null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();

    private Button btnOk = new Button("ȷ���޸�");
    private Button btnCancel = new Button("�˳���Ϣ����");

    private JLabel labelName= new JLabel("�û�����");// ������ѡ��TODO
    private JLabel labelSex= new JLabel("��  ��");
    private JLabel labelPassword= new JLabel("��  �룺");
    private JLabel labelPhone= new JLabel("��  ����");
    private JLabel labelEmail= new JLabel("��  �䣺");
    private JLabel labelCity = new JLabel("��  ��");


    private JTextField edtName = new JTextField(20);
    private JTextField edtSex = new JTextField(20);
    private JTextField edtPassword = new JTextField(20);
    private JTextField edtPhone = new JTextField(20);
    private JTextField edtEmail = new JTextField(20);
    private JTextField edtCity = new JTextField(20);

    UserInfo currentUser;

    public DlgUpdateUser(JFrame f, String s, boolean b, UserInfo userInfo) {
        super(f, s, b);
        currentUser = userInfo;
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelName);
        workPane.add(edtName);
        workPane.add(labelSex);
        workPane.add(edtSex);
        workPane.add(labelPassword);
        workPane.add(edtPassword);
        workPane.add(labelPhone);
        workPane.add(edtPhone);
        workPane.add(labelEmail);
        workPane.add(edtEmail);
        workPane.add(labelCity);
        workPane.add(edtCity);

        edtName.setText(currentUser.getName());
        edtSex.setText(currentUser.getSex());
        edtPassword.setText(currentUser.getPassword());
        edtPhone.setText(currentUser.getPhone());
        edtEmail.setText(currentUser.getEmail());
        edtCity.setText(currentUser.getCity());

        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(330, 250);
        // ��Ļ������ʾ
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();
        this.btnOk.addActionListener(this);
        this.btnCancel.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btnCancel) {
            this.setVisible(false);
            return;
        }
        else if(e.getSource()==this.btnOk){
            try {
                String nameText= this.edtName.getText();
                String sexText= this.edtSex.getText();
                String passwordText= this.edtPassword.getText();
                String phoneText= this.edtPhone.getText();
                String emailText= this.edtEmail.getText();
                String cityText= this.edtCity.getText();
                if(nameText==null||sexText==null||passwordText==null||phoneText==null||emailText==null||cityText==null){
                    throw new BaseException("����Ϊ��");
                }
                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(currentUser.getUserId());
                userInfo.setName(nameText);
                userInfo.setSex(sexText);
                userInfo.setPassword(passwordText);
                userInfo.setPhone(phoneText);
                userInfo.setEmail(emailText);
                userInfo.setCity(cityText);
                CCCarUtil.userManager.update(userInfo);
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
                return;
            }

        }
    }
}

