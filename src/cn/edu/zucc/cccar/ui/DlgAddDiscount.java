package cn.edu.zucc.cccar.ui;

import cn.edu.zucc.cccar.CCCarUtil;
import cn.edu.zucc.cccar.model.CarInfo;
import cn.edu.zucc.cccar.model.Coupon;
import cn.edu.zucc.cccar.model.DiscountInfo;
import cn.edu.zucc.cccar.model.NetInfo;
import cn.edu.zucc.cccar.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DlgAddDiscount  extends JDialog implements ActionListener {
    public NetInfo netInfo=null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();

    private Button btnOk = new Button("确定");
    private Button btnCancel = new Button("取消");

    private JLabel labelNet = new JLabel("打折网点：");
    private JLabel labelType = new JLabel("打折车型：");
    private JLabel labelCreditAmount= new JLabel("打折额度：");
    private JLabel labelCreditNum= new JLabel("打折数量：");
    private JLabel labelStartDate= new JLabel("开始时间：");
    private JLabel labelEndDate= new JLabel("结束时间：");

    private JTextField edtNet = new JTextField(20);
    private JTextField edtType = new JTextField(20);
    private JTextField edtCreditAmount= new JTextField(20);
    private JTextField edtCreditNum= new JTextField(20);
    private JTextField edtStartDate = new JTextField(20);
    private JTextField edtEndDate = new JTextField(20);

    public DlgAddDiscount(JFrame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelNet);
        workPane.add(edtNet);
        workPane.add(labelType);
        workPane.add(edtType);
        workPane.add(labelCreditAmount);
        workPane.add(edtCreditAmount);
        workPane.add(labelCreditNum);
        workPane.add(edtCreditNum);
        workPane.add(labelStartDate);
        workPane.add(edtStartDate);
        workPane.add(labelEndDate);
        workPane.add(edtEndDate);

        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(350, 250);
        // 屏幕居中显示
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();
        this.btnOk.addActionListener(this);
        this.btnCancel.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btnCancel) {
            this.setVisible(false);
            return;
        }
        else if(e.getSource()==this.btnOk){
            try {

                String net = this.edtNet.getText();
                String type = this.edtType.getText();
                String amount = this.edtCreditAmount.getText();
                String num = this.edtCreditNum.getText();
                String startDate = this.edtStartDate.getText();
                String endDate = this.edtEndDate.getText();

                String strDateFormat = "yyyy年MM月dd日HH:mm:ss";
                SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
                if(net==null||amount==null||startDate==null||endDate==null){
                    throw new BaseException("不能为空");
                }
                DiscountInfo discountInfo = new DiscountInfo();
                discountInfo.setDiscountnum(Integer.parseInt(num));
                discountInfo.setDiscountamount(Integer.parseInt(amount));
                discountInfo.setTypeId(Integer.parseInt(type));
                discountInfo.setNetId(Integer.parseInt(net));
                discountInfo.setStartdate(sdf.parse(startDate));
                discountInfo.setEnddate(sdf.parse(endDate));
                CCCarUtil.discountManager.add(discountInfo);

                this.setVisible(false);
            } catch (BaseException | ParseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }

        }
    }
}
