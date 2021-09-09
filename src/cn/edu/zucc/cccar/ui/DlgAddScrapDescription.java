package cn.edu.zucc.cccar.ui;

import cn.edu.zucc.cccar.CCCarUtil;
import cn.edu.zucc.cccar.model.CarInfo;
import cn.edu.zucc.cccar.model.NetInfo;
import cn.edu.zucc.cccar.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DlgAddScrapDescription extends JDialog implements ActionListener  {
    public NetInfo netInfo=null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();

    private Button btnOk = new Button("确定");
    private Button btnCancel = new Button("取消");

    private JLabel labelDescription= new JLabel("添加报废描述：");// 下拉框选择TODO


    private JTextField edtDescription = new JTextField(20);

    public int currentCarId;
    public DlgAddScrapDescription(JFrame f, String s, boolean b,int carId) {
        super(f, s, b);
        currentCarId = carId;
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelDescription);
        workPane.add(edtDescription);

        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(350, 80);
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
                String netIdText= this.edtDescription.getText();
                if(netIdText==null){
                    throw new BaseException("不能为空");
                }
                CCCarUtil.scrapManager.addDescription(netIdText, currentCarId);

                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }

        }
    }
}
