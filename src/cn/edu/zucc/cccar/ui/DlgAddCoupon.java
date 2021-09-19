package cn.edu.zucc.cccar.ui;

import cn.edu.zucc.cccar.CCCarUtil;
import cn.edu.zucc.cccar.model.CarInfo;
import cn.edu.zucc.cccar.model.Coupon;
import cn.edu.zucc.cccar.model.NetInfo;
import cn.edu.zucc.cccar.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DlgAddCoupon  extends JDialog implements ActionListener {
    public NetInfo netInfo=null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();

    private Button btnOk = new Button("ȷ��");
    private Button btnCancel = new Button("ȡ��");

    private JLabel labelContent = new JLabel("�Ż�ȯ���ݣ�");
    private JLabel labelCreditAmount= new JLabel("�Żݽ�");
    private JLabel labelStartDate= new JLabel("��ʼʱ�䣺");
    private JLabel labelEndDate= new JLabel("����ʱ�䣺");
    private JLabel labelUserId= new JLabel("�û�id��");


    private JTextField edtContent = new JTextField(20);
    private JTextField edtCreditAmount= new JTextField(20);
    private JTextField edtStartDate = new JTextField(20);
    private JTextField edtEndDate = new JTextField(20);
    private JTextField edtUserId = new JTextField(20);
    public DlgAddCoupon(JFrame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelContent);
        workPane.add(edtContent);
        workPane.add(labelCreditAmount);
        workPane.add(edtCreditAmount);
        workPane.add(labelStartDate);
        workPane.add(edtStartDate);
        workPane.add(labelEndDate);
        workPane.add(edtEndDate);
        workPane.add(labelUserId);
        workPane.add(edtUserId);

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

                String content = this.edtContent.getText();
                String amount = this.edtCreditAmount.getText();
                String startDate = this.edtStartDate.getText();
                String endDate = this.edtEndDate.getText();
                String userId = this.edtUserId.getText();

                String strDateFormat = "yyyy��MM��dd��HH:mm:ss";
                SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
                if(content==null||amount==null||startDate==null||endDate==null){
                    throw new BaseException("����Ϊ��");
                }
                Coupon coupon = new Coupon();
                coupon.setContent(content);
                coupon.setCreditamount(Integer.parseInt(amount));
                coupon.setStartdate(sdf.parse(startDate));
                coupon.setEnddate(sdf.parse(endDate));
                coupon.setNet_id(CCCarUtil.currentLoginEmployee.getNetId());
                CCCarUtil.couponManager.add(coupon);

                this.setVisible(false);
            } catch (BaseException | ParseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
                return;
            }

        }
    }
}
