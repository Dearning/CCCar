package cn.edu.zucc.cccar.ui;

import cn.edu.zucc.cccar.CCCarUtil;
import cn.edu.zucc.cccar.model.*;
import cn.edu.zucc.cccar.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class DlgUpdateEmployee extends JDialog implements ActionListener {
    public NetInfo netInfo=null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();

    private Button btnOk = new Button("确定");
    private Button btnCancel = new Button("取消");

    private JLabel labelName= new JLabel("员工名：");// 下拉框选择TODO
    private JLabel labelPassword= new JLabel("密  码：");
    private JLabel labelNet= new JLabel("网  点：");


    private JTextField edtName = new JTextField(20);
    private JTextField edtPassword = new JTextField(20);
    private JTextField edtNet = new JTextField(20);

    Employee currentEmployee;

    public DlgUpdateEmployee(JFrame f, String s, boolean b, Employee employee) {
        super(f, s, b);
        currentEmployee = employee;
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelName);
        workPane.add(edtName);
        workPane.add(labelPassword);
        workPane.add(edtPassword);
        workPane.add(labelNet);
        workPane.add(edtNet);

        edtName.setText(currentEmployee.getName());
        edtPassword.setText(currentEmployee.getPassword());
        edtNet.setText(currentEmployee.getNetId().toString());

        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(330, 150);
        // 屏幕居中显示
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
                String passwordText= this.edtPassword.getText();
                String net= this.edtNet.getText();
                if(nameText==null||net==null||passwordText==null){
                    throw new BaseException("不能为空");
                }
                Employee employee = new Employee();
                employee.setEmployeeId(currentEmployee.getEmployeeId());
                employee.setName(nameText);
                employee.setPassword(passwordText);
                employee.setNetId(Integer.parseInt(net));

                CCCarUtil.employeeManager.update(employee);
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }

        }
    }
}

