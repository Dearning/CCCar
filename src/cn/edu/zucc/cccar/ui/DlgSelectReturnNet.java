package cn.edu.zucc.cccar.ui;

import cn.edu.zucc.cccar.CCCarUtil;
import cn.edu.zucc.cccar.model.NetInfo;
import cn.edu.zucc.cccar.util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DlgSelectReturnNet extends JDialog implements ActionListener {

    private JButton btnSelete = new JButton("选择归还网点");
    private JButton btnQuit = new JButton("确认并退出");

    private JPanel statusBar = new JPanel();
//    private JPanel center = new JPanel(new BorderLayout());
    private JPanel downBtn = new JPanel();
    List<NetInfo> allNets=null;
    private NetInfo currentNet = null;


    DefaultTableModel tblNetModel=new DefaultTableModel();
    private JTable dataTableNet=new JTable(tblNetModel);

    private Object tblNetTitle[]=NetInfo.tableTitles;
    private Object tblNetData[][];

    public DlgSelectReturnNet() {

        this.setSize(500, 200);
//        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.btnSelete.addActionListener(this);
        this.btnQuit.addActionListener(this);
        this.getContentPane().add(new JScrollPane(dataTableNet), BorderLayout.CENTER);


        this.reloadNetTable();
        this.dataTableNet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = DlgSelectReturnNet.this.dataTableNet.getSelectedRow();
                if(i<0) return;
                currentNet=allNets.get(i);
            }
        });
        statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel label=new JLabel("您好! "+CCCarUtil.currentUserName+", 请选择还车网点.");//修改成   您好！+登陆用户名
        statusBar.add(label);
        downBtn.setLayout(new FlowLayout());
        downBtn.add(statusBar);
        downBtn.add(btnSelete);
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
            CCCarUtil.currentReturnNet = currentNet;
            CCCarUtil.lbChangeReturnNet.setText(""+currentNet.getNetName()+"");
            this.setVisible(false);
        } else if(e.getSource()==this.btnQuit){
            this.setVisible(false);
        }
    }

    private void reloadNetTable(){
        try {
            allNets=CCCarUtil.netManager.loadAll();

        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        //TODO 网点信息为空？
        tblNetData =  new Object[allNets.size()][NetInfo.tableTitles.length];
        for(int i=0;i<allNets.size();i++){
            for(int j=0;j<NetInfo.tableTitles.length;j++)
                tblNetData[i][j]=allNets.get(i).getCell(j);
        }
        tblNetModel.setDataVector(tblNetData,tblNetTitle);
        this.dataTableNet.validate();
        this.dataTableNet.repaint();
    }
}
