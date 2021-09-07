package cn.edu.zucc.cccar.util;

/**
 * string tool
 *
 * @author xuxueli 2018-05-02 20:43:25
 */
public class StringUtils {


    /**
     * 数据库 是可以大写的
     * https://blog.csdn.net/xianrenmodel/article/details/110792291
     * 将驼峰式命名的字符串转换为下划线大写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。
     * 例如：HelloWorld->HELLO_WORLD
     *
     * @param name 转换前的驼峰式命名的字符串
     * @return 转换后下划线大写方式命名的字符串
     */
   public static String underscoreName(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toUpperCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成大写
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }


    /**
     * 数据库 是可以大写的
     * https://blog.csdn.net/xianrenmodel/article/details/110792291
     * 将驼峰式命名的字符串转换为下划线小写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。
     * 例如：HelloWorld->hello_world
     *
     * @param name 转换前的驼峰式命名的字符串
     * @return 转换后下划线大写方式命名的字符串
     */
    public static String underscoreNameLower(String name) {
        String underscoreName = underscoreName(name);
        return underscoreName.toLowerCase();

    }




    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。  * 例如：HELLO_WORLD->HelloWorld
     *
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String camelName(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母小写
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String camels[] = name.split("_");
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }


    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String upperCaseFirst(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String lowerCaseFirst(String str) {
        //2019-2-10 解决StringUtils.lowerCaseFirst潜在的NPE异常@liutf
        return (str != null && str.length() > 1) ? str.substring(0, 1).toLowerCase() + str.substring(1) : "";
    }

//    驼峰 转化为 下划线

    /**
     * 下划线，转换为驼峰式
     *
     * @param underscoreName
     * @return
     */
    public static String underlineToCamelCase(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.trim().length() > 0) {
            boolean flag = false;
            for (int i = 0; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }
//    public static boolean isNotNull(String str){
////        return org.apache.commons.lang3.StringUtils.isNotEmpty(str);
//        return false;
//    }

    static void test6() {
        String name_up_lo = camelName("_name_up_lo");
//       String name_up_lo = camelName("_name_up_lo");
        String name_up_lo2 = camelName("_name_up_lo_");
        String name_up_lo3 = camelName("_name__up_lo_");
        String name_up_lo4 = camelName("_na_me_up_lo_");
        String name_up_lo5 = camelName("na_me_up_lo");

        System.out.println("name_up_lo");
        System.out.println(name_up_lo);


        System.out.println("name_up_lo2");
        System.out.println(name_up_lo2);

        System.out.println("name_up_lo3");
        System.out.println(name_up_lo3);

        System.out.println("name_up_lo4");
        System.out.println(name_up_lo4);

        System.out.println("name_up_lo5");
        System.out.println(name_up_lo5);

//        name_up_lo
//                nameUpLo
//        name_up_lo2
//                nameUpLo
//        name_up_lo3
//                nameUpLo
//        name_up_lo4
//                naMeUpLo
//        name_up_lo5
//                naMeUpLo
    }

    public static void main(String[] args) {
        test6();
    }

}
