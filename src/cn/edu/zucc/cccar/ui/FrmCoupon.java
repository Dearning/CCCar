package cn.edu.zucc.cccar.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.cccar.CCCarUtil;
import cn.edu.zucc.cccar.model.CarType;
import cn.edu.zucc.cccar.model.Coupon;
import cn.edu.zucc.cccar.model.NetInfo;
import cn.edu.zucc.cccar.util.BaseException;


public class FrmCoupon extends JFrame implements ActionListener {
    // 用于呈现促销信息,存在表单管理,增删查改

    private JMenuBar menuBar=new JMenuBar();
    private JMenu menu_Coupon = new JMenu("优惠券管理");
    private JMenuItem  menuItem_add =new JMenuItem("新增优惠券");
    private JMenuItem  menuItem_delete =new JMenuItem("删除优惠券");
    private JMenuItem  menuItem_update =new JMenuItem("修改优惠券");

    DefaultTableModel tblCoupon=new DefaultTableModel();
    private JTable dataTblCoupon=new JTable(tblCoupon);

    private JPanel statusBar = new JPanel();
//    private JPanel center = new JPanel(new BorderLayout());

    List<Coupon> coupons=null;
    private Coupon currentCoupon = null;


    private Object tblCouponTitle[]=Coupon.tableTitles;
    private Object tblCouponData[][];


    public FrmCoupon() {
        this.setTitle("优惠券管理");
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.menu_Coupon.add(this.menuItem_add); this.menuItem_add.addActionListener(this);
        this.menu_Coupon.add(this.menuItem_delete); this.menuItem_delete.addActionListener(this);
        this.menu_Coupon.add(this.menuItem_update); this.menuItem_update.addActionListener(this);

        menuBar.add(menu_Coupon);


        this.setJMenuBar(menuBar);
        this.getContentPane().add(new JScrollPane(dataTblCoupon), BorderLayout.CENTER);


        this.reloadCouponTable();
        this.dataTblCoupon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = FrmCoupon.this.dataTblCoupon.getSelectedRow();
                if(i<0) return;
                currentCoupon = coupons.get(i);
            }
        });

        statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel label=new JLabel("您好! "+CCCarUtil.currentLoginEmployee.getEmployeeId()+
                "号员工"+", 当前网点: "+CCCarUtil.currentLoginEmployee.getNetId());//修改成   您好！+登陆用户名
        statusBar.add(label);
        this.getContentPane().add(statusBar,BorderLayout.SOUTH);
        this.setVisible(true);
//        this.addWindowListener(new WindowAdapter(){
//            public void windowClosing(WindowEvent e){
//                System.exit(0);
//            }
//        });
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.menuItem_add){
            DlgAddCoupon dlg=new DlgAddCoupon(this,"添加优惠券",true);
            dlg.setVisible(true);
            this.reloadCouponTable();
        } else if(e.getSource()==this.menuItem_delete){
            if(this.currentCoupon==null) {
                JOptionPane.showMessageDialog(null, "请选择网点", "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                CCCarUtil.couponManager.delete(this.currentCoupon);

                this.reloadCouponTable();
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if(e.getSource()==this.menuItem_update){
            if (this.currentCoupon==null) {
                JOptionPane.showMessageDialog(null, "请选择网点", "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                CCCarUtil.couponManager.update(this.currentCoupon);

                this.reloadCouponTable();
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }

        }
    }

    private void reloadCouponTable(){
        try {
            coupons=CCCarUtil.couponManager.loadAll();

        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        //TODO 网点信息为空？
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
