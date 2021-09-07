package cn.edu.zucc.cccar.ui;

import cn.edu.zucc.cccar.CCCarUtil;
import cn.edu.zucc.cccar.model.CarCategory;
import cn.edu.zucc.cccar.model.CarInfo;
import cn.edu.zucc.cccar.model.CarType;
import cn.edu.zucc.cccar.util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FrmCatTypeCars  extends JFrame implements ActionListener {

    private JMenuBar menuBar=new JMenuBar();

    private JMenu menu_categories=new JMenu("车类管理");
    private JMenu menu_type=new JMenu("车型管理");
    private JMenu menu_car=new JMenu("车信息管理");

    private JMenuItem  menuItem_AddCategory=new JMenuItem("新增车类别");
    private JMenuItem  menuItem_DeleteCategory=new JMenuItem("删除车类别");
    private JMenuItem  menuItem_AddType=new JMenuItem("新增车类");
    private JMenuItem  menuItem_DeleteType=new JMenuItem("删除车类");
    private JMenuItem  menuItem_AddCar=new JMenuItem("新增车");
    private JMenuItem  menuItem_DeleteCar=new JMenuItem("删除车");

    DefaultTableModel tblCategory = new DefaultTableModel();
    DefaultTableModel tblTypeModel=new DefaultTableModel();
    DefaultTableModel tblCarModel=new DefaultTableModel();

    private JTable dataTblCategory = new JTable(tblCategory);
    private JTable dataTableType=new JTable(tblTypeModel);
    private JTable dataTblCarInfo=new JTable(tblCarModel);

    private JPanel statusBar = new JPanel();
    private JPanel west = new JPanel(new BorderLayout());
    private JPanel center = new JPanel(new BorderLayout());
    private JPanel east = new JPanel(new BorderLayout());

    java.util.List<CarCategory> carCategories=null;
    java.util.List<CarType> carTypes=null;
    List<CarInfo> carInfos = null;

    private CarCategory currentCategory=null;
    private CarType currentType = null;
    private CarInfo currentCar =null;

    private Object tblCategoryTitle[]=CarCategory.tableTitles;
    private Object tblCategoryData[][];
    private Object tblTypeTitle[]=CarType.tableTitles;
    private Object tblTypeData[][];
    private Object tblCarTitle[]=CarInfo.tableTitles;
    private Object tblCarData[][];
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
        this.menu_categories.add(this.menuItem_AddCategory); this.menuItem_AddCategory.addActionListener(this);
        this.menu_categories.add(this.menuItem_DeleteCategory); this.menuItem_DeleteCategory.addActionListener(this);
        this.menu_type.add(this.menuItem_AddType); this.menuItem_AddType.addActionListener(this);
        this.menu_type.add(this.menuItem_DeleteType); this.menuItem_DeleteType.addActionListener(this);
        this.menu_car.add(this.menuItem_AddCar); this.menuItem_AddCar.addActionListener(this);
        this.menu_car.add(this.menuItem_DeleteCar); this.menuItem_DeleteCar.addActionListener(this);

        menuBar.add(menu_categories);
        menuBar.add(menu_type);
        menuBar.add(menu_car);

        this.setJMenuBar(menuBar);

        west.setBorder(BorderFactory.createTitledBorder("车类"));
        center.setBorder(BorderFactory.createTitledBorder("车型"));
        east.setBorder(BorderFactory.createTitledBorder("车辆信息"));


        west.setPreferredSize(new Dimension(256,0));
        center.setPreferredSize(new Dimension(256,0));
        east.setPreferredSize(new Dimension(720,0));


        west.add(new JScrollPane(this.dataTblCategory));
        center.add(new JScrollPane(this.dataTableType));
        east.add(new JScrollPane(this.dataTblCarInfo));

        this.getContentPane().add(west, BorderLayout.WEST);
        this.getContentPane().add(center, BorderLayout.CENTER);
        this.getContentPane().add(east, BorderLayout.EAST);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.menuItem_AddCategory){
            FrmAddCategory dlg=new FrmAddCategory(this,"添加车类",true);
            dlg.setVisible(true);
        } else if(e.getSource()==this.menuItem_DeleteCategory){
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
        }else if(e.getSource()==this.menuItem_AddType){
            DlgAddCarType dlg=new DlgAddCarType(this,"添加车型",true);
            dlg.setVisible(true);
        }else if(e.getSource()==this.menuItem_DeleteType){
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
        }else if(e.getSource()==this.menuItem_AddCar){
            DlgAddCar dlg=new DlgAddCar(this,"添加车",true);
            dlg.setVisible(true);
        } else if(e.getSource()==this.menuItem_DeleteCar){
            if(this.currentCar==null) {
                JOptionPane.showMessageDialog(null, "请选择计划", "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                CCCarUtil.carManager.deleteCar(this.currentCar);
                this.reloadCarTable(currentType.getTypeId());
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }else if(e.getSource()==this.menuItem_AddCategory){
            FrmAddCategory dlg=new FrmAddCategory(this,"添加车类",true);
            dlg.setVisible(true);
        }else if(e.getSource()==this.menuItem_AddCategory){
            FrmAddCategory dlg=new FrmAddCategory(this,"添加车类",true);
            dlg.setVisible(true);
        }
    }
    private void reloadCategoryTable(){
        //TODO
        try {
            carCategories= CCCarUtil.categoryManager.loadAll();
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
