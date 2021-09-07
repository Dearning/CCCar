package cn.edu.zucc.cccar.util;

/**
 * string tool
 *
 * @author xuxueli 2018-05-02 20:43:25
 */
public class StringUtils {


    /**
     * ���ݿ� �ǿ��Դ�д��
     * https://blog.csdn.net/xianrenmodel/article/details/110792291
     * ���շ�ʽ�������ַ���ת��Ϊ�»��ߴ�д��ʽ�����ת��ǰ���շ�ʽ�������ַ���Ϊ�գ��򷵻ؿ��ַ�����
     * ���磺HelloWorld->HELLO_WORLD
     *
     * @param name ת��ǰ���շ�ʽ�������ַ���
     * @return ת�����»��ߴ�д��ʽ�������ַ���
     */
   public static String underscoreName(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // ����һ���ַ�����ɴ�д
            result.append(name.substring(0, 1).toUpperCase());
            // ѭ�����������ַ�
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // �ڴ�д��ĸǰ����»���
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // �����ַ�ֱ��ת�ɴ�д
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }


    /**
     * ���ݿ� �ǿ��Դ�д��
     * https://blog.csdn.net/xianrenmodel/article/details/110792291
     * ���շ�ʽ�������ַ���ת��Ϊ�»���Сд��ʽ�����ת��ǰ���շ�ʽ�������ַ���Ϊ�գ��򷵻ؿ��ַ�����
     * ���磺HelloWorld->hello_world
     *
     * @param name ת��ǰ���շ�ʽ�������ַ���
     * @return ת�����»��ߴ�д��ʽ�������ַ���
     */
    public static String underscoreNameLower(String name) {
        String underscoreName = underscoreName(name);
        return underscoreName.toLowerCase();

    }




    /**
     * ���»��ߴ�д��ʽ�������ַ���ת��Ϊ�շ�ʽ�����ת��ǰ���»��ߴ�д��ʽ�������ַ���Ϊ�գ��򷵻ؿ��ַ�����  * ���磺HELLO_WORLD->HelloWorld
     *
     * @param name ת��ǰ���»��ߴ�д��ʽ�������ַ���
     * @return ת������շ�ʽ�������ַ���
     */
    public static String camelName(String name) {
        StringBuilder result = new StringBuilder();
        // ���ټ��
        if (name == null || name.isEmpty()) {
            // û��Ҫת��
            return "";
        } else if (!name.contains("_")) {
            // �����»��ߣ���������ĸСд
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        // ���»��߽�ԭʼ�ַ����ָ�
        String camels[] = name.split("_");
        for (String camel : camels) {
            // ����ԭʼ�ַ����п�ͷ����β���»��߻�˫���»���
            if (camel.isEmpty()) {
                continue;
            }
            // �����������շ�Ƭ��
            if (result.length() == 0) {
                // ��һ���շ�Ƭ�Σ�ȫ����ĸ��Сд
                result.append(camel.toLowerCase());
            } else {
                // �������շ�Ƭ�Σ�����ĸ��д
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }


    /**
     * ����ĸ��д
     *
     * @param str
     * @return
     */
    public static String upperCaseFirst(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * ����ĸСд
     *
     * @param str
     * @return
     */
    public static String lowerCaseFirst(String str) {
        //2019-2-10 ���StringUtils.lowerCaseFirstǱ�ڵ�NPE�쳣@liutf
        return (str != null && str.length() > 1) ? str.substring(0, 1).toLowerCase() + str.substring(1) : "";
    }

//    �շ� ת��Ϊ �»���

    /**
     * �»��ߣ�ת��Ϊ�շ�ʽ
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
