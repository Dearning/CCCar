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
import cn.edu.zucc.cccar.util.BusinessException;


public class FrmMain extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	DlgLogin dlgLogin = null;
	// menu
	private JMenuBar menuBar=new JMenuBar();

	private JMenu menu_net=new JMenu("�������");
	private JMenu menu_types = new JMenu("���������");
	private JMenuItem  menuItem_types =new JMenuItem("���೵�ͳ��������");

//	private JMenu menu_categories=new JMenu("��������");
//	private JMenu menu_type=new JMenu("����ͳ��");
//	private JMenu menu_car=new JMenu("����Ϣ����");
//	private JMenu menu_coupon=new JMenu("�Ż�ȯ����");

//	private JMenuItem  menuItem_AddCategory=new JMenuItem("���������");
//	private JMenuItem  menuItem_DeleteCategory=new JMenuItem("ɾ�������");
//	private JMenuItem  menuItem_AddType=new JMenuItem("��������");
//	private JMenuItem  menuItem_DeleteType=new JMenuItem("ɾ������");
//	private JMenuItem  menuItem_AddCar=new JMenuItem("������");
//	private JMenuItem  menuItem_DeleteCar=new JMenuItem("ɾ����");

	private JMenu menu_discount=new JMenu("�Żݹ���");
//	private JMenu menu_scrap=new JMenu("���Ϲ���"); ȡ�����ϺͶ�������, ��Ϊ������������ʵ��,2021��9��13��13:53:32
//	private JMenu menu_order=new JMenu("��������");	�������������鿴�������
	private JMenu menu_user=new JMenu("������Ϣ����");

	private JMenuItem  menuItem_AddNet=new JMenuItem("��������");
	private JMenuItem  menuItem_DeleteNet=new JMenuItem("ɾ������");
	private JMenuItem  menuItem_Discount=new JMenuItem("��������");
	private JMenuItem  menuItem_Coupon=new JMenuItem("�Ż�ȯ����");
//	private JMenuItem  menuItem_AddScrap=new JMenuItem("��������");
//	private JMenuItem  menuItem_DeleteScrap=new JMenuItem("ɾ������");
//	private JMenuItem  menuItem_AddOrder=new JMenuItem("��������");
//	private JMenuItem  menuItem_DeleteOrder=new JMenuItem("ɾ������");
	private JMenuItem  menuItem_User=new JMenuItem("�鿴���޸ĸ�����Ϣ");
//	private JMenuItem  menuItem_DeleteUser=new JMenuItem("ɾ���û�");
	//table
	DefaultTableModel tblNetModel=new DefaultTableModel();
	DefaultTableModel tblTypesModel=new DefaultTableModel();
	DefaultTableModel tblCategory = new DefaultTableModel();
	DefaultTableModel tblTypeModel=new DefaultTableModel();
	DefaultTableModel tblCarModel=new DefaultTableModel();
	DefaultTableModel tblOrderModel=new DefaultTableModel();



	private JTable dataTableNet=new JTable(tblNetModel);
	private JTable dataTblTypes = new JTable(tblTypesModel);
	private JTable dataTblCategory = new JTable(tblCategory);
	private JTable dataTableType=new JTable(tblTypeModel);
	private JTable dataTblCarInfo=new JTable(tblCarModel);
	private JTable dataTblOrder=new JTable(tblOrderModel);

	//panel
	private JPanel statusBar = new JPanel();
	private JPanel west = new JPanel(new BorderLayout());
	private JPanel center = new JPanel(new BorderLayout());
	private JPanel east = new JPanel(new BorderLayout());
	private JPanel categoryPanel = new JPanel(new BorderLayout());
	private JPanel typePanel = new JPanel(new BorderLayout());
	private JPanel orderCtrl = new JPanel(new BorderLayout());
	private JPanel south = new JPanel(new BorderLayout());
	private JPanel northOfsouth = new JPanel(new BorderLayout());
	private JPanel up = new JPanel(new GridLayout(1,4));

	private JPanel southOfsouth = new JPanel(new FlowLayout() );

	private JButton btnSelectReturnNet= new JButton("ѡ��黹����");
	private JButton btnSelectCoupon = new JButton("ѡ���Ż�ȯ");
	private JButton btnOrderAdd = new JButton("���ɶ���");
	private JButton btnOrderDelete = new JButton("ɾ������");
	private JButton btnOrderComplete = new JButton("��ɶ���");


	JLabel lbReturnNet = new JLabel("�黹����:");
	JLabel lbCoupon = new JLabel("��ѡ���Ż�ȯ:");
	//list in table
	List<NetInfo> allNets=null;
	List<CarCategory> carCategories=null;
	List<CarType> carTypes=null;
	List<CarInfo> carInfos = null;
	List<TblOrder> orders= null;


	private NetInfo currentNet=null;
	private CarCategory currentCategory=null;
	private CarType currentType = null;
	private CarInfo currentCar =null;
	private TblOrder currentOrder =null;
	private Coupon currentCoupon = null;
	private NetInfo currentReturnNet = null;

	//

	private Object tblNetTitle[]=NetInfo.tableTitles;
	private Object tblNetData[][];
	private Object tblCategoryTitle[]=CarCategory.tableTitles;
	private Object tblCategoryData[][];
	private Object tblTypeTitle[]=CarType.tableTitles;
	private Object tblTypeData[][];
	private Object tblCarTitle[]=CarInfo.tableTitles;
	private Object tblCarData[][];
	private Object tblOrderTitle[]=TblOrder.tableTitles;
	private Object tblOrderData[][];



	public FrmMain(){
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("CC�⳵����ϵͳ");
		//��¼��ʼ
		dlgLogin = new DlgLogin(this, "��½", true);
		dlgLogin.setVisible(true);
		{

		this.menu_net.add(this.menuItem_AddNet); this.menuItem_AddNet.addActionListener(this);
		this.menu_net.add(this.menuItem_DeleteNet); this.menuItem_DeleteNet.addActionListener(this);

		if(CCCarUtil.currentLoginEmployee!=null&&CCCarUtil.currentLoginEmployee.getNetId()==null){
			this.menu_discount.add(this.menuItem_Discount); this.menuItem_Discount.addActionListener(this);
		}
		this.menu_discount.add(this.menuItem_Coupon); this.menuItem_Coupon.addActionListener(this);

//		this.menu_scrap.add(this.menuItem_AddScrap); this.menuItem_AddScrap.addActionListener(this);
//		this.menu_scrap.add(this.menuItem_DeleteScrap); this.menuItem_DeleteScrap.addActionListener(this);
//
//		this.menu_order.add(this.menuItem_AddOrder); this.menuItem_AddOrder.addActionListener(this);
//		this.menu_order.add(this.menuItem_DeleteOrder); this.menuItem_DeleteOrder.addActionListener(this);

		this.menu_user.add(this.menuItem_User); this.menuItem_User.addActionListener(this);
//		this.menu_user.add(this.menuItem_DeleteUser); this.menuItem_DeleteUser.addActionListener(this);
// ȡ��
		menu_user.addActionListener(this);

		this.menu_types.add(this.menuItem_types);this.menuItem_types.addActionListener(this);
		this.btnSelectCoupon.addActionListener(this);
		this.btnSelectReturnNet.addActionListener(this);
		this.btnOrderAdd.addActionListener(this);
		this.btnOrderDelete.addActionListener(this);
		this.btnOrderComplete.addActionListener(this);

		}
		{
		    if(CCCarUtil.currentLoginEmployee!=null){
				System.out.println(CCCarUtil.currentLoginEmployee.getEmployeeId()+"��ǰ��¼Ա��");
				if(CCCarUtil.currentLoginEmployee.getNetId()==null){
					menuBar.add(menu_net);
				}
				menuBar.add(menu_types);
                menuBar.add(menu_discount);
            }
//		menuBar.add(menu_scrap);
//		menuBar.add(menu_order);
		menuBar.add(menu_user);
		}
		this.dataTblOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = FrmMain.this.dataTblOrder.getSelectedRow();
				if(i<0) return;
				currentOrder=orders.get(i);
				FrmMain.this.reloadOrderTable();
			}
		});
		this.dataTableNet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = FrmMain.this.dataTableNet.getSelectedRow();
				if(i<0) return;
				currentNet=allNets.get(i);
				CCCarUtil.currentNet = currentNet;
				FrmMain.this.reloadCategoryTable(i);
			}
		});
		this.reloadNetTable();
		reloadOrderTable();
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
		this.dataTblOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = FrmMain.this.dataTblOrder.getSelectedRow();
				if(i<0) return;
				currentOrder = orders.get(i);
			}
		});

		this.setJMenuBar(menuBar);

		west.setBorder(BorderFactory.createTitledBorder("����"));
		categoryPanel.setBorder(BorderFactory.createTitledBorder("����"));
		typePanel.setBorder(BorderFactory.createTitledBorder("����"));
		east.setBorder(BorderFactory.createTitledBorder("������Ϣ"));
		northOfsouth.setBorder(BorderFactory.createTitledBorder("��������"));

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
		//�¿���붩��

		southOfsouth.add(lbReturnNet);
		southOfsouth.add(CCCarUtil.lbChangeReturnNet);
		southOfsouth.add(lbCoupon);
		southOfsouth.add(CCCarUtil.lbChangeCoupon);
		southOfsouth.add(btnSelectReturnNet);
		southOfsouth.add(btnSelectCoupon);
		southOfsouth.add(btnOrderAdd);
		southOfsouth.add(btnOrderComplete);
        if(CCCarUtil.currentLoginEmployee!=null&&CCCarUtil.currentLoginEmployee.getNetId()==null){
            southOfsouth.add(btnOrderDelete);
        }
		northOfsouth.add(new JScrollPane(this.dataTblOrder));
		south.add(southOfsouth,BorderLayout.NORTH);
		south.add(northOfsouth,BorderLayout.SOUTH);
		northOfsouth.setPreferredSize(new Dimension(0,300));

		up.add(west);
		up.add(categoryPanel);
		up.add(typePanel);
		up.add(east);
		this.getContentPane().add(up, BorderLayout.CENTER);
		this.getContentPane().add(south,BorderLayout.SOUTH);
		//�����״̬��
//
//		statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
//		JLabel label=new JLabel("����! "+CCCarUtil.currentUserName);//�޸ĳ�   ���ã�+��½�û���
//		statusBar.add(label);
//		this.getContentPane().add(statusBar,BorderLayout.SOUTH);

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
			DlgAddNet dlg=new DlgAddNet(this,"�������",true);
			dlg.setVisible(true);
			this.reloadNetTable();
		}
		else if(e.getSource()==this.menuItem_DeleteNet){
			if(this.currentNet==null) {
				JOptionPane.showMessageDialog(null, "��ѡ������", "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				CCCarUtil.netManager.deleteNet(this.currentNet);

				this.reloadNetTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==this.menuItem_types){
			FrmCatTypeCars dlg=new FrmCatTypeCars();
			dlg.setVisible(true);
			this.reloadNetTable();

		}
		else if(e.getSource()==this.menuItem_Discount){
			FrmDiscount frmDiscount=new FrmDiscount();
			frmDiscount.setVisible(true);

		}
		else if(e.getSource()==this.menuItem_Coupon){
			FrmCoupon frmCoupon=new FrmCoupon();
			frmCoupon.setVisible(true);
		}
		else if(e.getSource()==this.btnSelectCoupon){
			DlgSelectCoupon dlg=new DlgSelectCoupon();
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.btnSelectReturnNet){
			DlgSelectReturnNet dlg=new DlgSelectReturnNet();
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.btnOrderAdd){

			try {
				currentReturnNet = CCCarUtil.currentReturnNet;
				if(this.currentNet==null) {
					JOptionPane.showMessageDialog(null, "��ѡ������", "����",JOptionPane.ERROR_MESSAGE);
					return;
				} else if(this.currentCategory==null){
					JOptionPane.showMessageDialog(null, "��ѡ����", "����",JOptionPane.ERROR_MESSAGE);
					return;
				} else if(this.currentType==null){
					JOptionPane.showMessageDialog(null, "��ѡ����", "����",JOptionPane.ERROR_MESSAGE);
					return;
				} else if(this.currentCar==null){
					JOptionPane.showMessageDialog(null, "��ѡ����峵��", "����",JOptionPane.ERROR_MESSAGE);
					return;
				}else if (currentCar.getCarStatus()==0) throw new BaseException("�ó�����������");
				else if(currentCar.getCarStatus()==-1) throw new BusinessException("�����Ѿ�����,������Ӷ���");
				else if(this.currentReturnNet==null){
					JOptionPane.showMessageDialog(null, "��ѡ�񻹳�����", "����",JOptionPane.ERROR_MESSAGE);
					return;
				}
				TblOrder order = new TblOrder();
				order.setNetBorrowId(currentNet.getNetId());
				order.setOrderStatus(0);
				order.setInitialAmount(currentType.getPrice());
				order.setCarId(currentCar.getCarId());
				if(CCCarUtil.currentLoginUser!=null) order.setUserId(CCCarUtil.currentLoginUser.getUserId());
				else order.setUserId(null);//Ϊ�ձ�ʾ����Ա���ɵĲ�������

				order.setNetReturnId(currentReturnNet.getNetId());
				if(currentCoupon!=null) order.setCouponId(currentCoupon.getCouponId());
				else order.setCouponId(null);
				CCCarUtil.orderManager.addOrder(order);
//				if(CCCarUtil.currentLoginUser!=null)
				this.reloadOrderTable();
				this.reloadCarTable(currentType.getTypeId());
				currentNet=null;
				currentReturnNet = null;
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}

			reloadOrderTable();
		}
		else if(e.getSource()==this.btnOrderComplete){
			try {
				if(currentOrder==null){
					throw  new BusinessException("��ѡ�񶩵�");
				}
				else if(currentOrder.getOrderStatus()==1){
					throw new BusinessException("�ö����Ѿ����");
				}
				CCCarUtil.orderManager.completeOrder(currentOrder.getOrderId());
				if(currentCar!=null) this.reloadCarTable(currentCar.getCarId());
				this.reloadOrderTable();
				this.reloadCarTable(currentCar.getTypeId());
				currentReturnNet = null;
				currentCoupon = null;
				CCCarUtil.lbChangeReturnNet.setText("δѡ��");

			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==this.btnOrderDelete){
			try {
				if(currentOrder==null){
					throw  new BusinessException("��ѡ�񶩵�");
				}
				else if(currentOrder.getOrderStatus()==0){
					throw new BusinessException("�ö���δ���,����ɾ��");
				}
				CCCarUtil.orderManager.deleteOrder(currentOrder.getOrderId());

				this.reloadOrderTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}

		}
		else if(e.getSource()==this.menuItem_User){
            if(CCCarUtil.currentLoginEmployee!=null&&CCCarUtil.currentLoginEmployee.getNetId()==null){

                FrmUserManager frmUserManager=new FrmUserManager();
                frmUserManager.setVisible(true);
            }
            else {
                if (CCCarUtil.currentLoginUser==null) {
                    JOptionPane.showMessageDialog(null, "�û���Ϣ��������ϵ����Ա", "����",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    DlgUpdateUser dlg = new DlgUpdateUser(this,"�û���Ϣ����",true,CCCarUtil.currentLoginUser);
                    dlg.setVisible(true);
//                CCCarUtil.userManager.update(userInfo);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
		}
	}
	private void reloadOrderTable() {
		try {
			if(CCCarUtil.currentLoginUser!=null) orders=CCCarUtil.orderManager.loadByUser(CCCarUtil.currentLoginUser.getUserId());
			else orders=CCCarUtil.orderManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		//TODO ������ϢΪ�գ�
		tblOrderData =  new Object[orders.size()][TblOrder.tableTitles.length];
		for(int i=0;i<orders.size();i++){
			for(int j=0;j<TblOrder.tableTitles.length;j++)
				tblOrderData[i][j]=orders.get(i).getCell(j);
		}
		tblOrderModel.setDataVector(tblOrderData,tblOrderTitle);
		this.dataTblOrder.validate();
		this.dataTblOrder.repaint();
	}

	private void reloadNetTable(){
		try {
			allNets=CCCarUtil.netManager.loadAll();

		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		//TODO ������ϢΪ�գ�
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
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCategoryData =new Object[carCategories.size()][CarCategory.tableTitles.length];
		for(int i=0;i<carCategories.size();i++){
			for(int j=0;j<CarCategory.tableTitles.length;j++)
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
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
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
