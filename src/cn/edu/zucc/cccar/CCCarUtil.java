package cn.edu.zucc.cccar;

import cn.edu.zucc.cccar.control.*;
import cn.edu.zucc.cccar.itf.*;
import cn.edu.zucc.cccar.model.Employee;
import cn.edu.zucc.cccar.model.UserInfo;

public class CCCarUtil {
	public static INetManager netManager = new NetManager();
	public static IUserManager userManager=new UserManager();
	public static IEmployeeManager employeeManager=new EmployeeManager();
	public static IEmployeeManager superEmployeeManager = new SuperEmployeeManager();
	public static UserInfo currentLoginUser;
	public static Employee currentLoginEmployee;
	public static String currentUserName;
    public static ITypeManager typeManager = new TypeManager();


	public static ICarManager carManager = new CarManager();

	public static ICategoryManager categoryManager = new CategoryManager();

    public static IDiscountManager discountManager = new DiscountManager();
    public static ICouponManager couponManager = new CouponManager();
}
