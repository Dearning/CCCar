package cn.edu.zucc.cccar;

import cn.edu.zucc.cccar.control.*;
import cn.edu.zucc.cccar.itf.*;
import cn.edu.zucc.cccar.model.Coupon;
import cn.edu.zucc.cccar.model.Employee;
import cn.edu.zucc.cccar.model.NetInfo;
import cn.edu.zucc.cccar.model.UserInfo;

import javax.swing.*;
import java.awt.*;

public class CCCarUtil {
	public static IUserManager userManager=new UserManager();
	public static IEmployeeManager employeeManager=new EmployeeManager();
	public static IEmployeeManager superEmployeeManager = new SuperEmployeeManager();

	public static INetManager netManager = new NetManager();
	public static ICategoryManager categoryManager = new CategoryManager();
	public static ITypeManager typeManager = new TypeManager();
	public static ICarManager carManager = new CarManager();

    public static IDiscountManager discountManager = new DiscountManager();
    public static ICouponManager couponManager = new CouponManager();
    public static IOrderManager orderManager = new OrderManager();
	public static IScrapManager scrapManager = new ScrapManager();


	public static UserInfo currentLoginUser;
	public static UserInfo currentManagerUser;
	public static Employee currentLoginEmployee;
	public static String currentUserName;
	public static Coupon currentCoupon = null;
	public static NetInfo currentNet = null;
	public static NetInfo currentReturnNet = null;
	public static JLabel lbChangeReturnNet = new JLabel("Î´Ñ¡Ôñ");
	public static JLabel lbChangeCoupon = new JLabel("Î´Ñ¡Ôñ");
}
