package com.cai.commonutil.ip;

/**
 * ip与十进制整数转换
 * Created by reason on 17/12/24.
 */
public class IPTransform {

    /**
     * 以192.168.1.2为例，把它转为10进制数的公式是：
     * <p>
     * 192 * 256^3 + 168 * 256^2 +1 * 256 + 2 * 1 = 3232235778
     * <p>
     * 相当于将IP地址看成256进制的数。
     *
     * @param ip
     * @return
     */
    public static long ipToLong(String ip) {
        String[] arr = ip.split("\\.");
        long result = 0;
        for (String s : arr) {
            result = result * 256 + Long.parseLong(s);
        }
        return result;
    }

    /**
     * 以192.168.1.2为例，把它转为10进制数的公式是：
     * <p>
     * 192 * 256^3 + 168 * 256^2 +1 * 256 + 2 * 1 = 3232235778
     * <p>
     * 相当于将IP地址看成256进制的数。
     *
     * @param ip
     * @return
     */
    public static long ipToLong2(String ip) {
        String[] arr = ip.split("\\.");
        long result = 0;
        result = Double.valueOf(Long.parseLong(arr[0]) * Math.pow(256, 3) +
                Long.parseLong(arr[1]) * Math.pow(256, 2) +
                Long.parseLong(arr[2]) * Math.pow(256, 1) +
                Long.parseLong(arr[3]) * Math.pow(256,0)).longValue();
        return result;
    }


    /**
     * 由于乘法计算不如位运算快，因此对这个方法做如下改进：

     a = 192 << 24
     b = 168 << 16
     c = 1 << 8
     d = 2 << 0
     最后计算 a | b | c | d的值，结果也是3232235778
     * @param ip
     * @return
     */
    public static long ipToLong3(String ip) {
        String[] arr = ip.split("\\.");
        long result = 0;
        for (int i = 0; i <= 3; i++) {
            long ips = Long.parseLong(arr[i]);
            result |= ips << ((3-i) << 3);
        }
        return result;
    }


    public static long ipToLong4(String strIp) {
        String[] ipArr = strIp.split("\\.");
        if(ipArr.length!=4){
            return 0;
        }
        long[] ip = new long[4];
        // 先找到IP地址字符串中.的位置
        // 将每个.之间的字符串转换成整型
        ip[0] = Long.parseLong(ipArr[0]);
        ip[1] = Long.parseLong(ipArr[1]);
        ip[2] = Long.parseLong(ipArr[2]);
        ip[3] = Long.parseLong(ipArr[3]);
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    /**
     *将十进制整数形式转换成127.0.0.1形式的ip地址
     * @param longIp
     * @return
     */
    public static String longToIP(long longIp) {
        StringBuffer sb = new StringBuffer("");
        // 直接右移24位
        sb.append(String.valueOf((longIp >>> 24)));
        sb.append(".");
        // 将高8位置0，然后右移16位
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
        sb.append(".");
        // 将高16位置0，然后右移8位
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
        sb.append(".");
        // 将高24位置0
        sb.append(String.valueOf((longIp & 0x000000FF)));
        return sb.toString();
    }

    public static void main(String[] args) {
        long l = ipToLong("192.168.1.2");
        System.out.println(l);
        long l2 = ipToLong2("192.168.1.2");
        System.out.println(l2);
        long l3 = ipToLong3("192.168.1.2");
        System.out.println(l3);
        long l4 = ipToLong4("192.168.1.2");
        System.out.println(l4);
        String s = longToIP(3232235778L);
        System.out.println(s);
    }

}
