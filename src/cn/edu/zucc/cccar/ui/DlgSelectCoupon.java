package cn.edu.zucc.cccar.ui;

import cn.edu.zucc.cccar.CCCarUtil;
import cn.edu.zucc.cccar.model.CarInfo;
import cn.edu.zucc.cccar.model.Coupon;
import cn.edu.zucc.cccar.model.NetInfo;
import cn.edu.zucc.cccar.util.BaseException;
import cn.edu.zucc.cccar.util.BusinessException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DlgSelectCoupon  extends JDialog implements ActionListener {

    private JButton btnSelete = new JButton("ѡ���Ż�ȯ");
    private JButton btnWithdraw = new JButton("ȡ��ѡ��");
    private JButton btnQuit = new JButton("�˳�");

    DefaultTableModel tblCoupon=new DefaultTableModel();
    private JTable dataTblCoupon=new JTable(tblCoupon);

    private JPanel statusBar = new JPanel();
    private JPanel downBtn = new JPanel();
//    private JPanel center = new JPanel(new BorderLayout());

    List<Coupon> coupons=null;
    private Coupon currentCoupon = null;


    private Object tblCouponTitle[]=Coupon.tableTitles;
    private Object tblCouponData[][];


    public DlgSelectCoupon() {

        this.setSize(500, 200);
//        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.btnSelete.addActionListener(this);
        this.btnQuit.addActionListener(this);

        this.btnWithdraw.addActionListener(this);
        this.getContentPane().add(new JScrollPane(dataTblCoupon), BorderLayout.CENTER);


        this.reloadCouponTable();
        this.dataTblCoupon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = DlgSelectCoupon.this.dataTblCoupon.getSelectedRow();
                if(i<0) return;
                currentCoupon=coupons.get(i);
            }
        });

        statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel label=new JLabel("����! "+CCCarUtil.currentUserName+", ��ѡ���Ż�ȯ.");//�޸ĳ�   ���ã�+��½�û���
        statusBar.add(label);
        downBtn.setLayout(new FlowLayout());
        downBtn.add(statusBar);
        downBtn.add(btnSelete);
        downBtn.add(btnWithdraw);
        downBtn.add(btnQuit);
        this.getContentPane().add(downBtn,BorderLayout.SOUTH);

//        this.addWindowListener(new WindowAdapter(){
//            public void windowClosing(WindowEvent e){
//                System.exit(0);
//            }
//        });
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btnSelete){
            try {

                CCCarUtil.CurrentCoupon = currentCoupon;
                if(currentCoupon.getUsed()==true) throw new BusinessException("�Ż�ȯ�Ѿ���ʹ��,������ѡ��");
                CCCarUtil.lbChangeCoupon.setText(""+currentCoupon.getCreditamount()+"Ԫ");
                this.setVisible(false);

            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if(e.getSource()==this.btnQuit){
            this.setVisible(false);
        } else if(e.getSource() == this.btnWithdraw){
            CCCarUtil.CurrentCoupon = null;

            CCCarUtil.lbChangeCoupon.setText("δѡ��");
            this.setVisible(false);
        }
    }

    private void reloadCouponTable(){
        try {
            if(CCCarUtil.currentLoginUser==null){
                coupons=CCCarUtil.couponManager.loadAll();
            } else {
                coupons=CCCarUtil.couponManager.loadUser(CCCarUtil.currentLoginUser);
            }


        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
            return;
        }
        //TODO ������ϢΪ�գ�
        tblCouponData =  new Object[coupons.size()][Coupon.tableTitles.length];
        for(int i=0;i<coupons.size();i++){
            for(int j=0;j<Coupon.tableTitles.length;j++)
                tblCouponData[i][j]=coupons.get(i).getCell(j);
        }
        tblCoupon.setDataVector(tblCouponData,tblCouponTitle);
        this.dataTblCoupon.validate();
        this.dataTblCoupon.repaint();
    }
}

