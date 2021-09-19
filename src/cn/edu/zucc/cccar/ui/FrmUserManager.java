package cn.edu.zucc.cccar.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.cccar.CCCarUtil;
import cn.edu.zucc.cccar.model.*;
import cn.edu.zucc.cccar.util.BaseException;


public class FrmUserManager extends JFrame implements ActionListener {
    // ���ڳ��ִ�����Ϣ,���ڱ�����,��ɾ���

    private JMenuBar menuBar=new JMenuBar();
    private JMenu menu_User = new JMenu("�û�����");
    private JMenu menu_employee = new JMenu("��ͨԱ������");
    private JMenu menu_admin = new JMenu("����Ա������Ϣ����");
    private JMenuItem  menuItem_addUser =new JMenuItem("�����û�");
    private JMenuItem  menuItem_deleteUser =new JMenuItem("ɾ���û�");
    private JMenuItem  menuItem_updateUser =new JMenuItem("�޸��û���Ϣ");
    private JMenuItem  menuItem_addEmployee =new JMenuItem("�����û�");
    private JMenuItem  menuItem_deleteEmployee =new JMenuItem("ɾ���û�");
    private JMenuItem  menuItem_updateEmployee =new JMenuItem("�޸��û���Ϣ");
    private JMenuItem  menuItem_addAdmin =new JMenuItem("�����û�");
    private JMenuItem  menuItem_deleteAdmin =new JMenuItem("ɾ���û�");
    private JMenuItem  menuItem_updateAdmin =new JMenuItem("�޸��û���Ϣ");

    DefaultTableModel tblUserModel = new DefaultTableModel();
    DefaultTableModel tblEmployeeModel = new DefaultTableModel();

    private JTable dataTblUser=new JTable(tblUserModel);
    private JTable dataTblEmployee=new JTable(tblEmployeeModel);

    private JPanel statusBar = new JPanel();
//    private JPanel center = new JPanel(new BorderLayout());

    List<UserInfo> userInfos=null;
    List<Employee> employees=null;
    private UserInfo currentUser = null;
    private Employee currentEmployee = null;


    private Object tblUserTitle[]=UserInfo.tableTitles;
    private Object tblUserData[][];
    private Object tblEmployeeTitle[]=Employee.tableTitles;
    private Object tblEmployeeData[][];


    public FrmUserManager() {
        this.setTitle("�û�����ͨԱ����Ϣ����");
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        menuBar.add(menu_User);
        menuBar.add(menu_employee);
        menuBar.add(menu_admin);
        this.menu_User.add(this.menuItem_addUser); this.menuItem_addUser.addActionListener(this);
        this.menu_User.add(this.menuItem_deleteUser);this.menuItem_deleteUser.addActionListener(this);
        this.menu_User.add(this.menuItem_updateUser);this.menuItem_updateUser.addActionListener(this);
        this.menu_employee.add(this.menuItem_addEmployee); this.menuItem_addEmployee.addActionListener(this);
        this.menu_employee.add(this.menuItem_deleteEmployee);this.menuItem_deleteEmployee.addActionListener(this);
        this.menu_employee.add(this.menuItem_updateEmployee);this.menuItem_updateEmployee.addActionListener(this);
        this.menu_admin.add(this.menuItem_addAdmin); this.menuItem_addAdmin.addActionListener(this);
        this.menu_admin.add(this.menuItem_deleteAdmin);this.menuItem_deleteAdmin.addActionListener(this);
        this.menu_admin.add(this.menuItem_updateAdmin);this.menuItem_updateAdmin.addActionListener(this);
        this.setJMenuBar(menuBar);
        JPanel left = new JPanel(new BorderLayout());
        JPanel right = new JPanel(new BorderLayout());

        left.setBorder(BorderFactory.createTitledBorder("�û�"));
        right.setBorder(BorderFactory.createTitledBorder("Ա����Ϣ"));
        left.add(new JScrollPane(dataTblUser));
        right.add(new JScrollPane(dataTblEmployee));
        JPanel cneter =  new JPanel(new GridLayout(1,2));
        cneter.add(left);
        cneter.add(right);
        this.getContentPane().add(cneter, BorderLayout.CENTER);

        this.reloadUserTable();
        reloadEmployeeTable();
        this.dataTblUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = FrmUserManager.this.dataTblUser.getSelectedRow();
                if(i<0) return;
                currentUser = userInfos.get(i);
                CCCarUtil.currentManagerUser = currentUser;
            }
        });
        this.dataTblEmployee.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = FrmUserManager.this.dataTblEmployee.getSelectedRow();
                if(i<0) return;
                currentEmployee = employees.get(i);
            }
        });

        //״̬��

		statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label=new JLabel("����! "+CCCarUtil.currentUserName);//�޸ĳ�   ���ã�+��½�û���
		statusBar.add(label);
		this.getContentPane().add(statusBar,BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.menuItem_addUser){
//            DlgAddCoupon dlg=new DlgAddCoupon(this,"����Ż�ȯ",true);
//            dlg.setVisible(true);
            this.reloadUserTable();
        } else if(e.getSource()==this.menuItem_deleteUser){
//            if(this.currentCoupon==null) {
//                JOptionPane.showMessageDialog(null, "��ѡ������", "����",JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//            try {
//                CCCarUtil.couponManager.delete(this.currentCoupon);
//
//                this.reloadCouponTable();
//            } catch (BaseException e1) {
//                JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
//                return;
//            }
            this.reloadUserTable();
        } else if(e.getSource()==this.menuItem_updateUser){
            if (this.currentUser==null) {
                JOptionPane.showMessageDialog(null, "��ѡ���û�", "����",JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                UserInfo userInfo = new UserInfo();
                DlgUpdateUser dlg = new DlgUpdateUser(this,"�û���Ϣ����",true,currentUser);
                dlg.setVisible(true);
//                CCCarUtil.userManager.update(userInfo);

                this.reloadUserTable();
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
                return;
            }

        }else if(e.getSource()==this.menuItem_updateEmployee){
            if (this.currentEmployee==null) {
                JOptionPane.showMessageDialog(null, "��ѡ��Ա��", "����",JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                UserInfo userInfo = new UserInfo();
                DlgUpdateEmployee dlg = new DlgUpdateEmployee(this,"Ա����Ϣ����",true,currentEmployee);
                dlg.setVisible(true);
//                CCCarUtil.userManager.update(userInfo);

                this.reloadEmployeeTable();
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }
    private void reloadUserTable(){
        try {
            userInfos=CCCarUtil.userManager.loadAll();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
            return;
        }
        //TODO ������ϢΪ�գ�
        tblUserData =  new Object[userInfos.size()][UserInfo.tableTitles.length];
        for(int i=0;i<userInfos.size();i++){
            for(int j=0;j<UserInfo.tableTitles.length;j++)
                tblUserData[i][j]=userInfos.get(i).getCell(j);
        }
        tblUserModel.setDataVector(tblUserData,tblUserTitle);
        this.dataTblUser.validate();
        this.dataTblUser.repaint();
    }
    private void reloadEmployeeTable(){;
        try {
            employees=CCCarUtil.employeeManager.loadAll();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
            return;
        }
        //TODO ������ϢΪ�գ�
        tblEmployeeData =  new Object[employees.size()][Employee.tableTitles.length];
        for(int i=0;i<employees.size();i++){
            for(int j=0;j<Employee.tableTitles.length;j++)
                tblEmployeeData[i][j]=employees.get(i).getCell(j);
        }
        tblEmployeeModel.setDataVector(tblEmployeeData,tblEmployeeTitle);
        this.dataTblEmployee.validate();
        this.dataTblEmployee.repaint();
    }

}
