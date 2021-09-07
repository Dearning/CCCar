package cn.edu.zucc.cccar.ui;

import cn.edu.zucc.cccar.CCCarUtil;
import cn.edu.zucc.cccar.model.NetInfo;
import cn.edu.zucc.cccar.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DlgAddNet extends JDialog implements ActionListener {
    public NetInfo netInfo=null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();

    private Button btnOk = new Button("ȷ��");
    private Button btnCancel = new Button("ȡ��");

    private JLabel labelName = new JLabel("�������ƣ�");
    private JLabel labelCity = new JLabel("���ڳ��У�");
    private JLabel labelAddress = new JLabel("��ϸ��ַ��");
    private JLabel labelPhone = new JLabel("��ϵ�绰��");

    private JTextField edtName = new JTextField(20);
    private JTextField edtCity = new JTextField(20);
    private JTextField edtAddress = new JTextField(20);
    private JTextField edtPhone = new JTextField(20);
    public DlgAddNet(JFrame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelName);
        workPane.add(edtName);

        workPane.add(labelCity);
        workPane.add(edtCity);

        workPane.add(labelAddress);
        workPane.add(edtAddress);

        workPane.add(labelPhone);
        workPane.add(edtPhone);

        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(350, 180);
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
                String name=this.edtName.getText();
                String city = this.edtCity.getText();
                String address = this.edtAddress.getText();
                String phone = this.edtPhone.getText();
                if(name==null||city==null||address==null||phone==null){
                    throw new BaseException("����Ϊ��");
                }
                NetInfo net = new NetInfo();
                net.setNetName(name);
                net.setCity(city);
                net.setPhone(phone);
                net.setAddress(address);
                CCCarUtil.netManager.add(net);

                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
                return;
            }

        }
    }
}

