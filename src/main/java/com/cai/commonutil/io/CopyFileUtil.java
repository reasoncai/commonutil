package com.cai.commonutil.io;

import java.io.*;

/**
 * Created by reason on 17/7/6.
 */
public class CopyFileUtil {
    /**
     * 使用基本字节流一次读写一个字节(极慢仅供测试)
     * @param src
     * @param dest
     * @throws IOException
     */
    public static void copyByBioBy1(String src, String dest) throws IOException {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(dest);
        int by = 0;

        while ((by = fis.read()) != -1) {
            fos.write(by);
        }

        fos.close();
        fis.close();

    }

    /**
     * 使用基本字节流一次读写一个字节数组
     * @param src
     * @param dst
     * @throws IOException
     */
    public static void copyByBioByArr(String src, String dst) throws IOException {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(dst);
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }
        fos.close();
        fis.close();

    }

    /**
     * 使用高效字节流一次读写一个字节(极慢仅供测试)
     * @param src
     * @param dst
     * @throws IOException
     */
    public static void copyByBioByBufferBy1(String src, String dst) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dst));
        int by = 0;
        while ((by = bis.read()) != -1) {
            bos.write(by);
        }
        bos.close();
        bis.close();

    }

    /**
     * 使用高效字节流一次读写一个字节数组
     * @param src
     * @param dst
     * @throws IOException
     */
    public static void copyByBioByBufferByArr(String src, String dst) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dst));
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
        bos.close();
        bis.close();
    }


}
