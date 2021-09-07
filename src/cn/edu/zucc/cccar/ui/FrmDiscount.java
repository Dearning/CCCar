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
import cn.edu.zucc.cccar.model.DiscountInfo;
import cn.edu.zucc.cccar.model.NetInfo;
import cn.edu.zucc.cccar.util.BaseException;


public class FrmDiscount extends JFrame implements ActionListener {
    // ���ڳ��ִ�����Ϣ,���ڱ�����,��ɾ���

    private JMenuBar menuBar=new JMenuBar();
    private JMenu menu_Discount = new JMenu("��������");
    private JMenuItem  menuItem_add =new JMenuItem("��������");
    private JMenuItem  menuItem_delete =new JMenuItem("ɾ������");
    private JMenuItem  menuItem_update =new JMenuItem("�޸Ĵ���");

    DefaultTableModel tblDiscount=new DefaultTableModel();
    private JTable dataTblDiscount=new JTable(tblDiscount);

    private JPanel statusBar = new JPanel();
//    private JPanel center = new JPanel(new BorderLayout());

    List<DiscountInfo> discountInfos=null;
    private DiscountInfo currentDiscount = null;


    private Object tblDicountTitle[]=DiscountInfo.tableTitles;
    private Object tblDicountData[][];


    public FrmDiscount() {

        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.menu_Discount.add(this.menuItem_add); this.menuItem_add.addActionListener(this);
        this.menu_Discount.add(this.menuItem_delete); this.menuItem_delete.addActionListener(this);
        this.menu_Discount.add(this.menuItem_update); this.menuItem_update.addActionListener(this);

        menuBar.add(menu_Discount);


        this.setJMenuBar(menuBar);
        this.getContentPane().add(new JScrollPane(dataTblDiscount), BorderLayout.CENTER);


        this.reloadDiscountTable();
        this.dataTblDiscount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = FrmDiscount.this.dataTblDiscount.getSelectedRow();
                if(i<0) return;
                currentDiscount = discountInfos.get(i);
            }
        });

        statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel label=new JLabel("����! "+CCCarUtil.currentUserName);//�޸ĳ�   ���ã�+��½�û���
        statusBar.add(label);
        this.getContentPane().add(statusBar,BorderLayout.SOUTH);

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
            DlgAddNet dlg=new DlgAddNet(this,"�������",true);
            dlg.setVisible(true);
            this.reloadDiscountTable();
        } else if(e.getSource()==this.menuItem_delete){
            if(this.currentDiscount==null) {
                JOptionPane.showMessageDialog(null, "��ѡ������", "����",JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                CCCarUtil.discountManager.delete(this.currentDiscount);

                this.reloadDiscountTable();
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if(e.getSource()==this.menuItem_update){
            if (this.currentDiscount==null) {
                JOptionPane.showMessageDialog(null, "��ѡ������", "����",JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                CCCarUtil.discountManager.update(this.currentDiscount);

                this.reloadDiscountTable();
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
                return;
            }

        }
    }

    private void reloadDiscountTable(){
        try {
            discountInfos=CCCarUtil.discountManager.loadAll();

        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
            return;
        }
        //TODO ������ϢΪ�գ�
        tblDicountData =  new Object[discountInfos.size()][DiscountInfo.tableTitles.length];
        for(int i=0;i<discountInfos.size();i++){
            for(int j=0;j<DiscountInfo.tableTitles.length;j++)
                tblDicountData[i][j]=discountInfos.get(i).getCell(j);
        }
        tblDiscount.setDataVector(tblDicountData,tblDicountTitle);
        this.dataTblDiscount.validate();
        this.dataTblDiscount.repaint();
    }

}
