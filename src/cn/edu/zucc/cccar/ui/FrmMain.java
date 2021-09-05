package cn.edu.zucc.cccar.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.cccar.CCCarUtil;
import cn.edu.zucc.cccar.model.*;
import cn.edu.zucc.cccar.util.BaseException;



public class FrmMain extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	FrmLogin dlgLogin = null;
	// menu
	private JMenuBar menuBar=new JMenuBar();

	private JMenu menu_net=new JMenu("网点管理");
	private JMenu menu_categories=new JMenu("车类别管理");
	private JMenu menu_type=new JMenu("车型统计");
	private JMenu menu_car=new JMenu("车信息管理");
	private JMenu menu_discount=new JMenu("促销管理");
	private JMenu menu_coupon=new JMenu("优惠券管理");
	private JMenu menu_scrap=new JMenu("报废管理");
	private JMenu menu_order=new JMenu("订单管理");
	private JMenu menu_user=new JMenu("用户管理");

	private JMenuItem  menuItem_AddNet=new JMenuItem("新增网点");
	private JMenuItem  menuItem_DeleteNet=new JMenuItem("删除网点");
	private JMenuItem  menuItem_AddCategory=new JMenuItem("新增车类别");
	private JMenuItem  menuItem_DeleteCategory=new JMenuItem("删除车类别");
	private JMenuItem  menuItem_AddType=new JMenuItem("新增车类");
	private JMenuItem  menuItem_DeleteNType=new JMenuItem("删除车类");
	private JMenuItem  menuItem_AddCar=new JMenuItem("新增车");
	private JMenuItem  menuItem_DeleteCar=new JMenuItem("删除车");
	private JMenuItem  menuItem_AddDiscount=new JMenuItem("新增促销");
	private JMenuItem  menuItem_DeleteDiscount=new JMenuItem("删除促销");
	private JMenuItem  menuItem_AddCoupon=new JMenuItem("新增优惠券");
	private JMenuItem  menuItem_DeleteCoupon=new JMenuItem("删除优惠券");
	private JMenuItem  menuItem_AddScrap=new JMenuItem("新增报废");
	private JMenuItem  menuItem_DeleteScrap=new JMenuItem("删除报废");
	private JMenuItem  menuItem_AddOrder=new JMenuItem("新增订单");
	private JMenuItem  menuItem_DeleteOrder=new JMenuItem("删除订单");
	private JMenuItem  menuItem_AddUser=new JMenuItem("新增用户");
	private JMenuItem  menuItem_DeleteUser=new JMenuItem("删除用户");
	//table
	DefaultTableModel tblNetModel=new DefaultTableModel();
	DefaultTableModel tblTypeModel=new DefaultTableModel();
	DefaultTableModel tblCarModel=new DefaultTableModel();

	private JTable dataTableNet=new JTable(tblNetModel);
	private JTable dataTableType=new JTable(tblTypeModel);
	private JTable dataTblCarInfo=new JTable(tblCarModel);

	//panel
	private JPanel statusBar = new JPanel();

	//list in table
	List<NetInfo> allNets=null;
	List<CarType> carTypes=null;
	List<CarInfo> carInfos = null;

	private NetInfo currentNet=null;
	private CarType currentType = null;
	private CarInfo currentCar =null;


	//
	private Object tblNetTitle[]=NetInfo.tableTitles;
	private Object tblNetData[][];
	private Object tblTypeTitle[]=CarType.tableTitles;
	private Object tblTypeData[][];
	private Object tblCarTitle[]=CarInfo.tableTitles;
	private Object tblCarData[][];

	public FrmMain(){
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("CC租车管理系统");
		//登录开始
		dlgLogin = new FrmLogin(this, "登陆", true);
		dlgLogin.setVisible(true);

		{
		this.menu_net.add(this.menuItem_AddNet); this.menuItem_AddNet.addActionListener(this);
		this.menu_net.add(this.menuItem_DeleteNet); this.menuItem_DeleteNet.addActionListener(this);
		this.menu_categories.add(this.menuItem_AddCategory); this.menuItem_AddCategory.addActionListener(this);
		this.menu_categories.add(this.menuItem_DeleteCategory); this.menuItem_DeleteCategory.addActionListener(this);
		this.menu_type.add(this.menuItem_AddType); this.menuItem_AddType.addActionListener(this);
		this.menu_type.add(this.menuItem_DeleteNType); this.menuItem_DeleteNType.addActionListener(this);
		this.menu_car.add(this.menuItem_AddCar); this.menuItem_AddCar.addActionListener(this);
		this.menu_car.add(this.menuItem_DeleteCar); this.menuItem_DeleteCar.addActionListener(this);
		this.menu_discount.add(this.menuItem_AddDiscount); this.menuItem_AddDiscount.addActionListener(this);
		this.menu_discount.add(this.menuItem_DeleteDiscount); this.menuItem_DeleteDiscount.addActionListener(this);
		this.menu_coupon.add(this.menuItem_AddCoupon); this.menuItem_AddCoupon.addActionListener(this);
		this.menu_coupon.add(this.menuItem_DeleteCoupon); this.menuItem_DeleteCoupon.addActionListener(this);
		this.menu_scrap.add(this.menuItem_AddScrap); this.menuItem_AddScrap.addActionListener(this);
		this.menu_scrap.add(this.menuItem_DeleteScrap); this.menuItem_DeleteScrap.addActionListener(this);
		this.menu_order.add(this.menuItem_AddOrder); this.menuItem_AddOrder.addActionListener(this);
		this.menu_order.add(this.menuItem_DeleteOrder); this.menuItem_DeleteOrder.addActionListener(this);
		this.menu_user.add(this.menuItem_AddUser); this.menuItem_AddUser.addActionListener(this);
		this.menu_user.add(this.menuItem_DeleteUser); this.menuItem_DeleteUser.addActionListener(this);
		}
		{
		menuBar.add(menu_net);
		menuBar.add(menu_categories);
		menuBar.add(menu_type);
		menuBar.add(menu_car);
		menuBar.add(menu_discount);
		menuBar.add(menu_coupon);
		menuBar.add(menu_scrap);
		menuBar.add(menu_order);
		menuBar.add(menu_user);
		}

		this.setJMenuBar(menuBar);
		this.getContentPane().add(new JScrollPane(this.dataTableNet), BorderLayout.WEST);
		this.dataTableNet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = FrmMain.this.dataTableNet.getSelectedRow();
				if(i<0) return;
//				System.out.println(i);
				FrmMain.this.reloadTypeTable(i);
			}
		});
		this.reloadNetTable();

		this.getContentPane().add(new JScrollPane(this.dataTableType), BorderLayout.CENTER);
		this.dataTableType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = FrmMain.this.dataTableType.getSelectedRow();
				if(i<0) return;
//				System.out.println(i);
				FrmMain.this.reloadCarTable(i);
			}
		});
		FrmMain.this.reloadTypeTable(0);

		this.getContentPane().add(new JScrollPane(this.dataTblCarInfo), BorderLayout.EAST);

		this.getContentPane().add(new JScrollPane(this.dataTblCarInfo), BorderLayout.SOUTH);

		statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label=new JLabel("您好! "+CCCarUtil.currentUserName);//修改成   您好！+登陆用户名
		statusBar.add(label);
		this.getContentPane().add(statusBar,BorderLayout.SOUTH);
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		this.setVisible(true);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.menuItem_AddNet){
			FrmAddNet dlg=new FrmAddNet(this,"添加计划",true);
			dlg.setVisible(true);
		} else if(e.getSource()==this.menuItem_DeleteNet){
			FrmAddPlan dlg=new FrmAddPlan(this,"添加计划",true);
			dlg.setVisible(true);
		} else if(e.getSource()==this.menuItem_AddCategory){
			FrmAddPlan dlg=new FrmAddPlan(this,"添加计划",true);
			dlg.setVisible(true);
		} else if(e.getSource()==this.menuItem_DeleteCategory){
			FrmAddPlan dlg=new FrmAddPlan(this,"添加计划",true);
			dlg.setVisible(true);
		} else if(e.getSource()==this.menuItem_AddCar){
			FrmAddPlan dlg=new FrmAddPlan(this,"添加计划",true);
			dlg.setVisible(true);
		}
		//TODO 上列表的动作
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
	private void reloadTypeTable(int netIdx){
		if(netIdx<0) return;
		currentNet=allNets.get(netIdx);
		try {
			carTypes=CCCarUtil.typeManager.loadTypes(currentNet.getNetId());
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblTypeData =new Object[carTypes.size()][CarType.tableTitles.length];
		for(int i=0;i<carTypes.size();i++){
			for(int j=0;j<CarType.tableTitles.length;j++)
				tblTypeData[i][j]=carTypes.get(i).getCell(j);
		}
		tblTypeModel.setDataVector(tblTypeData,tblTypeTitle);
		this.dataTableType.validate();
		this.dataTableType.repaint();
	}
	private void reloadCarTable(int carTypeIdx) {

		if(carTypeIdx<0) return;
		currentType=carTypes.get(carTypeIdx);
		try {
			carInfos=CCCarUtil.carManager.loadCars(currentNet.getNetId(),currentType.getTypeId());
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCarData =new Object[carInfos.size()][CarInfo.tableTitles.length];
		for(int i=0;i<carInfos.size();i++){
			for(int j=0;j<CarType.tableTitles.length;j++)
				tblCarData[i][j]=carInfos.get(i).getCell(j);
		}
		tblCarModel.setDataVector(tblCarData,tblCarTitle);
		this.dataTableType.validate();
		this.dataTableType.repaint();
	}
}
