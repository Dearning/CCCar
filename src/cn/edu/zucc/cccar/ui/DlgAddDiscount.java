package cn.edu.zucc.cccar.ui;

import cn.edu.zucc.cccar.CCCarUtil;
import cn.edu.zucc.cccar.model.CarInfo;
import cn.edu.zucc.cccar.model.NetInfo;
import cn.edu.zucc.cccar.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DlgAddDiscount  extends JDialog implements ActionListener {
    public NetInfo netInfo=null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();

    private Button btnOk = new Button("ȷ��");
    private Button btnCancel = new Button("ȡ��");

    private JLabel labelNetId= new JLabel("�����ţ�");// ������ѡ��TODO
    private JLabel labelCarType= new JLabel("�������ͣ�");
    private JLabel labelLicense= new JLabel("�������գ�");
    private JLabel labelCarStatus= new JLabel("����״̬��");


    private JTextField edtNetId = new JTextField(20);
    private JTextField edtCarType = new JTextField(20);
    private JTextField edtLicense = new JTextField(20);
    private JTextField edtCarStatus = new JTextField(20);

    public DlgAddDiscount(JFrame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelNetId);
        workPane.add(edtNetId);
        workPane.add(labelCarType);
        workPane.add(edtCarType);
        workPane.add(labelLicense);
        workPane.add(edtLicense);
        workPane.add(labelCarStatus);
        workPane.add(edtCarStatus);

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
                String netIdText= this.edtNetId.getText();
                String carTypeText= this.edtCarType.getText();
                String licenseText= this.edtLicense.getText();
                String carStatusText= this.edtCarStatus.getText();
                if(netIdText==null||carTypeText==null||licenseText==null||carStatusText==null){
                    throw new BaseException("����Ϊ��");
                }
                CarInfo carInfo = new CarInfo();
                carInfo.setNetId(Integer.parseInt(netIdText));
                carInfo.setTypeId(Integer.parseInt(carTypeText));
                carInfo.setLicense(licenseText);
                carInfo.setCarStatus(Integer.parseInt(carStatusText));
                CCCarUtil.carManager.add(carInfo);

                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
                return;
            }

        }
    }
}
