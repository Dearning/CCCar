package cn.edu.zucc.cccar.ui;

import cn.edu.zucc.cccar.CCCarUtil;
import cn.edu.zucc.cccar.model.CarCategory;
import cn.edu.zucc.cccar.model.CarInfo;
import cn.edu.zucc.cccar.model.CarType;
import cn.edu.zucc.cccar.model.Scrap;
import cn.edu.zucc.cccar.util.BaseException;
import cn.edu.zucc.cccar.util.BusinessException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.List;

public class FrmCatTypeCars  extends JFrame implements ActionListener {

    private JMenuBar menuBar=new JMenuBar();

    private JMenu menu_categories=new JMenu("车类管理");
    private JMenu menu_type=new JMenu("车型管理");
    private JMenu menu_car=new JMenu("车信息管理");
    private JMenu menu_scrap = new JMenu("报废管理");

    private JMenuItem  menuItem_AddCategory=new JMenuItem("新增车类别");
    private JMenuItem  menuItem_DeleteCategory=new JMenuItem("删除车类别");
    private JMenuItem  menuItem_AddType=new JMenuItem("新增车类");
    private JMenuItem  menuItem_DeleteType=new JMenuItem("删除车类");
    private JMenuItem  menuItem_AddCar=new JMenuItem("新增车");
    private JMenuItem  menuItem_DeleteCar=new JMenuItem("删除车");

    private JMenuItem  menuItem_AddScrap=new JMenuItem("报废车辆");
    private JMenuItem menuItem_AddScrapDescription = new JMenuItem("添加报废描述");

    DefaultTableModel tblCategory = new DefaultTableModel();
    DefaultTableModel tblTypeModel=new DefaultTableModel();
    DefaultTableModel tblCarModel=new DefaultTableModel();
    DefaultTableModel tblScrapModel=new DefaultTableModel();

    private JTable dataTblCategory = new JTable(tblCategory);
    private JTable dataTableType=new JTable(tblTypeModel);
    private JTable dataTblCarInfo=new JTable(tblCarModel);
    private JTable dataTblScrap = new JTable(tblScrapModel);

    private JPanel statusBar = new JPanel();
    private JPanel west = new JPanel(new BorderLayout());
    private JPanel center = new JPanel(new BorderLayout());
    private JPanel east = new JPanel(new BorderLayout());
    private JPanel eastOfeast = new JPanel(new BorderLayout());

    private JPanel up = new JPanel(new GridLayout(1,4));

    java.util.List<CarCategory> carCategories=null;
    java.util.List<CarType> carTypes=null;
    List<CarInfo> carInfos = null;
    List<Scrap> scraps = null;

    private CarCategory currentCategory=null;
    private CarType currentType = null;
    private CarInfo currentCar =null;
    private Scrap currentScraps =null;

    private Object tblCategoryTitle[]=CarCategory.tableTitles;
    private Object tblCategoryData[][];
    private Object tblTypeTitle[]=CarType.tableTitles;
    private Object tblTypeData[][];
    private Object tblCarTitle[]=CarInfo.tableTitles;
    private Object tblCarData[][];
    private Object tblScrapTitle[]=Scrap.tableTitles;
    private Object tblScrapData[][];
    public FrmCatTypeCars(){

        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("车类车型车管理");
        this.reloadCategoryTable();
        this.dataTblCategory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = FrmCatTypeCars.this.dataTblCategory.getSelectedRow();
                if(i<0) return;
//				System.out.println(i);

                currentCategory=carCategories.get(i);
                FrmCatTypeCars.this.reloadTypeTable(i);
            }
        });
        this.dataTableType.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = FrmCatTypeCars.this.dataTableType.getSelectedRow();
                if(i<0) return;
//				System.out.println(i);
                currentType=carTypes.get(i);

                FrmCatTypeCars.this.reloadCarTable(i);
            }
        });
        this.dataTblCarInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = FrmCatTypeCars.this.dataTblCarInfo.getSelectedRow();
                if(i<0) return;
                currentCar = carInfos.get(i);

            }
        });
        this.dataTblScrap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = FrmCatTypeCars.this.dataTblScrap.getSelectedRow();
                if(i<0) return;
                currentScraps = scraps.get(i);
            }
        });
        this.menu_categories.add(this.menuItem_AddCategory); this.menuItem_AddCategory.addActionListener(this);
        this.menu_categories.add(this.menuItem_DeleteCategory); this.menuItem_DeleteCategory.addActionListener(this);
        this.menu_type.add(this.menuItem_AddType); this.menuItem_AddType.addActionListener(this);
        this.menu_type.add(this.menuItem_DeleteType); this.menuItem_DeleteType.addActionListener(this);
        this.menu_car.add(this.menuItem_AddCar); this.menuItem_AddCar.addActionListener(this);
        this.menu_car.add(this.menuItem_DeleteCar); this.menuItem_DeleteCar.addActionListener(this);
        this.menu_scrap.add(this.menuItem_AddScrap);this.menuItem_AddScrap.addActionListener(this);
        this.menu_scrap.add(this.menuItem_AddScrapDescription);
        this.menuItem_AddScrapDescription.addActionListener(this);
        if(CCCarUtil.currentLoginEmployee!=null&&CCCarUtil.currentLoginEmployee.getNetId()==null){
                menuBar.add(menu_categories);
                menuBar.add(menu_type);
                menuBar.add(menu_car);
        }
        menuBar.add(menu_scrap);

        this.setJMenuBar(menuBar);

        west.setBorder(BorderFactory.createTitledBorder("车类"));
        center.setBorder(BorderFactory.createTitledBorder("车型"));
        east.setBorder(BorderFactory.createTitledBorder("车辆信息"));
        eastOfeast.setBorder(BorderFactory.createTitledBorder("报废信息"));

        west.add(new JScrollPane(this.dataTblCategory));
        center.add(new JScrollPane(this.dataTableType));
        east.add(new JScrollPane(this.dataTblCarInfo));
        eastOfeast.add(new JScrollPane(this.dataTblScrap));

        up.add(west);
        up.add(center);
        up.add(east);
        up.add(eastOfeast);
        this.getContentPane().add(up, BorderLayout.CENTER);

        statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel label=new JLabel("您好! "+CCCarUtil.currentLoginEmployee.getEmployeeId()+"号员工"+", 当前网点: "+CCCarUtil.currentLoginEmployee.getNetId());//修改成   您好！+登陆用户名
        statusBar.add(label);
        this.getContentPane().add(statusBar,BorderLayout.SOUTH);
        this.setVisible(true);
        reloadScrapTable();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.menuItem_AddCategory){
            DlgAddCategory dlg=new DlgAddCategory(this,"添加车类",true);
            dlg.setVisible(true);
            // 需要添加车类 后 自动更新已有车类列表,
            this.reloadCategoryTable();
        }
        else if(e.getSource()==this.menuItem_DeleteCategory){
            if(this.currentCategory==null) {
                JOptionPane.showMessageDialog(null, "请选择车类", "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                CCCarUtil.categoryManager.deleteCategory(this.currentCategory);
                this.reloadCategoryTable();
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        else if(e.getSource()==this.menuItem_AddType){
            DlgAddCarType dlg=new DlgAddCarType(this,"添加车型",true);
            dlg.setVisible(true);
            this.reloadTypeTable(currentCategory.getCategoryId());
        }
        else if(e.getSource()==this.menuItem_DeleteType){
            if(this.currentType==null) {
                JOptionPane.showMessageDialog(null, "请选择计划", "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                CCCarUtil.typeManager.deleteType(this.currentType);
                this.reloadTypeTable(currentCategory.getCategoryId());
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        else if(e.getSource()==this.menuItem_AddCar){
            DlgAddCar dlg=new DlgAddCar(this,"添加车",true);
            dlg.setVisible(true);
            this.reloadCarTable(currentType.getTypeId());
        }
        else if(e.getSource()==this.menuItem_DeleteCar){
            if(this.currentCar==null) {
                JOptionPane.showMessageDialog(null, "请选择计划", "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                if(currentCar.getCarStatus()==0) throw new BusinessException("当前车辆租借中不能删除   ");
                CCCarUtil.carManager.deleteCar(this.currentCar);
                this.reloadCarTable(currentType.getTypeId());
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        else if(e.getSource()==this.menuItem_AddScrap){
            //TODO
            try {
                if(currentCar==null) throw  new BusinessException("请选择车辆");
                if(currentCar.getCarStatus()==0) throw new BusinessException("车辆租借中,不能报废");
                else if (currentCar.getCarStatus()==-1) throw new BusinessException("车辆已经报废");
                Scrap scrap = new Scrap();
                scrap.setCarId(currentCar.getCarId());
                scrap.setScraptime(new Timestamp(System.currentTimeMillis()));
                CCCarUtil.scrapManager.add(scrap);
                this.reloadScrapTable();
                this.reloadCarTable(currentType.getTypeId());
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
        } if(e.getSource()==this.menuItem_AddScrapDescription){
            try {
                if(currentScraps==null) throw new BusinessException("没有选择报废车辆");
                DlgAddScrapDescription dlg=new DlgAddScrapDescription(this,"添加报废描述",true,currentScraps.getCarId());
                dlg.setVisible(true);
                this.reloadScrapTable();
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

//        else if(e.getSource()==this.menuItem_AddCategory){
//            DlgAddCategory dlg=new DlgAddCategory(this,"添加车类",true);
//            dlg.setVisible(true);
//        }
//        else if(e.getSource()==this.menuItem_AddCategory){
//            DlgAddCategory dlg=new DlgAddCategory(this,"添加车类",true);
//            dlg.setVisible(true);
//        }
    }
    private void reloadScrapTable(){
        try {
            scraps= CCCarUtil.scrapManager.loadAll();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblScrapData =new Object[scraps.size()][Scrap.tableTitles.length];
        for(int i=0;i<scraps.size();i++){
            for(int j=0;j<Scrap.tableTitles.length;j++)
                tblScrapData[i][j]=scraps.get(i).getCell(j);
        }
        tblScrapModel.setDataVector(tblScrapData,tblScrapTitle);
        this.dataTblScrap.validate();
        this.dataTblScrap.repaint();
    }
    private void reloadCategoryTable(){
        //TODO
        try {
            if(CCCarUtil.currentLoginEmployee.getNetId()!=null){
                carCategories= CCCarUtil.categoryManager.loadTypes(CCCarUtil.currentLoginEmployee.getNetId());
            }
            else carCategories= CCCarUtil.categoryManager.loadAll();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
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
            carTypes=CCCarUtil.typeManager.loadTypesByCategoryOnly(currentCategory.getCategoryId());
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
            carInfos=CCCarUtil.carManager.loadCars(currentType.getTypeId());
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
