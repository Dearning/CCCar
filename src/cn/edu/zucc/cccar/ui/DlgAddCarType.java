package cn.edu.zucc.cccar.ui;

import cn.edu.zucc.cccar.CCCarUtil;
import cn.edu.zucc.cccar.model.CarCategory;
import cn.edu.zucc.cccar.model.CarType;
import cn.edu.zucc.cccar.model.NetInfo;
import cn.edu.zucc.cccar.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class DlgAddCarType extends JDialog implements ActionListener {
    public NetInfo netInfo=null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();

    private Button btnOk = new Button("ȷ��");
    private Button btnCancel = new Button("ȡ��");

    private JLabel labelCategory= new JLabel("����ѡ��");// ������ѡ��TODO
    private JLabel labelName = new JLabel("�������ƣ�");
    private JLabel labelDisplacement = new JLabel("����������");
    private JLabel labelGear = new JLabel("�����ŵ���");
    private JLabel labelSeatSum = new JLabel("��λ������");
    private JLabel labelPrice = new JLabel("�۸�(Ԫ/ʱ)��");
    private JLabel labelPic = new JLabel("����ͼƬ��");
    private JLabel labelBrand = new JLabel("����Ʒ��");


    private JTextField edtCategory = new JTextField(20);
    private JTextField edtName = new JTextField(20);
    private JTextField edtDisplacement = new JTextField(20);
    private JTextField edtGear = new JTextField(20);
    private JTextField edtSeatSum = new JTextField(20);
    private JTextField edtPrice = new JTextField(20);
    private JTextField edtPic = new JTextField(20);
    private JTextField edtBrand = new JTextField(20);

    public DlgAddCarType(JFrame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelName);
        workPane.add(edtName);
        workPane.add(labelCategory);
        workPane.add(edtCategory);
        workPane.add(labelDisplacement);
        workPane.add(edtDisplacement);
        workPane.add(labelGear);
        workPane.add(edtGear);
        workPane.add(labelSeatSum);
        workPane.add(edtSeatSum);
        workPane.add(labelPrice);
        workPane.add(edtPrice);
        workPane.add(labelPic);
        workPane.add(edtPic);
        workPane.add(labelBrand);
        workPane.add(edtBrand);

        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(350, 300);
        // ��Ļ������ʾ
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
                String name=this.edtName.getText();
                String category= this.edtCategory.getText();
                String displacementText= this.edtDisplacement.getText();
                String gearText= this.edtGear.getText();
                String seatSumText= this.edtSeatSum.getText();
                String priceText= this.edtPrice.getText();
                String picText= this.edtPic.getText();
                String brand = this.edtBrand.getText();
                if(name==null||category==null||displacementText==null||gearText==null
                        ||seatSumText==null||picText==null||priceText==null||brand ==null){
                    throw new BaseException("����Ϊ��");
                }
                CarType carType = new CarType();
                carType.setTypeName(name);
                carType.setCategoryId(Integer.parseInt(category));
                carType.setDisplacement(BigDecimal.valueOf(Double.parseDouble(displacementText)));
                carType.setGear(Integer.parseInt(gearText));
                carType.setSeatNum(Integer.parseInt(seatSumText));
                carType.setPrice(BigDecimal.valueOf(Double.valueOf(priceText)));
                carType.setPic(picText);
                carType.setBrand(brand);
                CCCarUtil.typeManager.add(carType);

                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
                return;
            }

        }
    }
}

