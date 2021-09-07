package cn.edu.zucc.cccar.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.*;
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
	private JMenu menu_types = new JMenu("车管理界面");
	private JMenuItem  menuItem_types =new JMenuItem("车类车型车管理界面");

	//	private JMenu menu_categories=new JMenu("车类别管理");
//	private JMenu menu_type=new JMenu("车型统计");
//	private JMenu menu_car=new JMenu("车信息管理");
	private JMenu menu_discount=new JMenu("促销管理");
	private JMenu menu_coupon=new JMenu("优惠券管理");
	private JMenu menu_scrap=new JMenu("报废管理");
	private JMenu menu_order=new JMenu("订单管理");
	private JMenu menu_user=new JMenu("用户管理");

	private JMenuItem  menuItem_AddNet=new JMenuItem("新增网点");
	private JMenuItem  menuItem_DeleteNet=new JMenuItem("删除网点");
//	private JMenuItem  menuItem_AddCategory=new JMenuItem("新增车类别");
//	private JMenuItem  menuItem_DeleteCategory=new JMenuItem("删除车类别");
//	private JMenuItem  menuItem_AddType=new JMenuItem("新增车类");
//	private JMenuItem  menuItem_DeleteType=new JMenuItem("删除车类");
//	private JMenuItem  menuItem_AddCar=new JMenuItem("新增车");
//	private JMenuItem  menuItem_DeleteCar=new JMenuItem("删除车");
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
	DefaultTableModel tblTypesModel=new DefaultTableModel();
	DefaultTableModel tblCategory = new DefaultTableModel();
	DefaultTableModel tblTypeModel=new DefaultTableModel();
	DefaultTableModel tblCarModel=new DefaultTableModel();


	private JTable dataTableNet=new JTable(tblNetModel);
	private JTable dataTblTypes = new JTable(tblTypesModel);
	private JTable dataTblCategory = new JTable(tblCategory);
	private JTable dataTableType=new JTable(tblTypeModel);
	private JTable dataTblCarInfo=new JTable(tblCarModel);

	//panel
	private JPanel statusBar = new JPanel();
	private JPanel west = new JPanel(new BorderLayout());
	private JPanel center = new JPanel(new BorderLayout());
	private JPanel east = new JPanel(new BorderLayout());
	private JPanel categoryPanel = new JPanel(new BorderLayout());
	private JPanel typePanel = new JPanel(new BorderLayout());


	//list in table
	List<NetInfo> allNets=null;
	List<CarCategory> carCategories=null;
	List<CarType> carTypes=null;
	List<CarInfo> carInfos = null;


	private NetInfo currentNet=null;
	private CarCategory currentCategory=null;
	private CarType currentType = null;
	private CarInfo currentCar =null;


	//
	private Object tblNetTitle[]=NetInfo.tableTitles;
	private Object tblNetData[][];
	private Object tblCategoryTitle[]=CarCategory.tableTitles;
	private Object tblCategoryData[][];
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
//		this.menu_categories.add(this.menuItem_AddCategory); this.menuItem_AddCategory.addActionListener(this);
//		this.menu_categories.add(this.menuItem_DeleteCategory); this.menuItem_DeleteCategory.addActionListener(this);
//		this.menu_type.add(this.menuItem_AddType); this.menuItem_AddType.addActionListener(this);
//		this.menu_type.add(this.menuItem_DeleteType); this.menuItem_DeleteType.addActionListener(this);
//		this.menu_car.add(this.menuItem_AddCar); this.menuItem_AddCar.addActionListener(this);
//		this.menu_car.add(this.menuItem_DeleteCar); this.menuItem_DeleteCar.addActionListener(this);
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
		this.menu_types.add(this.menuItem_types);this.menuItem_types.addActionListener(this);
		}
		{
		menuBar.add(menu_net);
//		menuBar.add(menu_categories);
//		menuBar.add(menu_type);
//		menuBar.add(menu_car);
		menuBar.add(menu_types);
		menuBar.add(menu_discount);
		menuBar.add(menu_coupon);
		menuBar.add(menu_scrap);
		menuBar.add(menu_order);
		menuBar.add(menu_user);
		}

		this.dataTableNet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = FrmMain.this.dataTableNet.getSelectedRow();
				if(i<0) return;

//				System.out.println(i);

				currentNet=allNets.get(i);
				FrmMain.this.reloadCategoryTable(i);
			}
		});
		this.reloadNetTable();

		this.dataTblCategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = FrmMain.this.dataTblCategory.getSelectedRow();
				if(i<0) return;
//				System.out.println(i);

				currentCategory=carCategories.get(i);
				FrmMain.this.reloadTypeTable(i);
			}
		});
		this.dataTableType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = FrmMain.this.dataTableType.getSelectedRow();
				if(i<0) return;
//				System.out.println(i);
				currentType=carTypes.get(i);

				FrmMain.this.reloadCarTable(i);
			}
		});
		this.dataTblCarInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = FrmMain.this.dataTblCarInfo.getSelectedRow();
				if(i<0) return;
				currentCar = carInfos.get(i);

			}
		});

		this.setJMenuBar(menuBar);

		west.setBorder(BorderFactory.createTitledBorder("网点"));
		categoryPanel.setBorder(BorderFactory.createTitledBorder("车类"));
		typePanel.setBorder(BorderFactory.createTitledBorder("车型"));
		east.setBorder(BorderFactory.createTitledBorder("车辆信息"));

		categoryPanel.setPreferredSize(new Dimension(256,0));
		typePanel.setPreferredSize(new Dimension(256,0));
		west.setPreferredSize(new Dimension(256,0));
		center.setPreferredSize(new Dimension(512,0));
		east.setPreferredSize(new Dimension(600,0));

		categoryPanel.add(new JScrollPane(this.dataTblCategory));
		typePanel.add(new JScrollPane(this.dataTableType));
		west.add(new JScrollPane(this.dataTableNet));
		center.add(categoryPanel,BorderLayout.WEST);
		center.add(typePanel,BorderLayout.CENTER);
		east.add(new JScrollPane(this.dataTblCarInfo));

		this.getContentPane().add(west, BorderLayout.WEST);
		this.getContentPane().add(center, BorderLayout.CENTER);
		this.getContentPane().add(east, BorderLayout.EAST);


		//下面的状态栏
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
			DlgAddNet dlg=new DlgAddNet(this,"添加网点",true);
			dlg.setVisible(true);
			this.reloadNetTable();
		} else if(e.getSource()==this.menuItem_DeleteNet){
			if(this.currentNet==null) {
				JOptionPane.showMessageDialog(null, "请选择网点", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				CCCarUtil.netManager.deleteNet(this.currentNet);

				this.reloadNetTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		} else if(e.getSource()==this.menuItem_types){
			FrmCatTypeCars dlg=new FrmCatTypeCars();
			dlg.setVisible(true);
			this.reloadNetTable();

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
	private void reloadCategoryTable(int netIdx){
		//TODO
		if(netIdx<0) return;
		try {
			carCategories=CCCarUtil.categoryManager.loadTypes(currentNet.getNetId());
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCategoryData =new Object[carCategories.size()][CarCategory.tableTitles.length];
		for(int i=0;i<carCategories.size();i++){
			for(int j=0;j<CarType.tableTitles.length;j++)
				tblCategoryData[i][j]=carCategories.get(i).getCell(j);
		}
		tblCategory.setDataVector(tblCategoryData,tblCategoryTitle);
		this.dataTblCategory.validate();
		this.dataTblCategory.repaint();
	}
	private void reloadTypeTable(int categoryIdx){
		//TODO
		if(categoryIdx<0) return;
		try {
			carTypes=CCCarUtil.typeManager.loadTypes(currentNet.getNetId(),currentCategory.getCategoryId());
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
		try {
			carInfos=CCCarUtil.carManager.loadCars(currentNet.getNetId(),currentType.getTypeId());
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCarData =new Object[carInfos.size()][CarInfo.tableTitles.length];
		for(int i=0;i<carInfos.size();i++){
			for(int j=0;j<CarInfo.tableTitles.length;j++)
				tblCarData[i][j]=carInfos.get(i).getCell(j);
		}
		tblCarModel.setDataVector(tblCarData,tblCarTitle);
		this.dataTableType.validate();
		this.dataTableType.repaint();
	}
}
