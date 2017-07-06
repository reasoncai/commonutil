package com.cai.commonutil.io;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by reason on 17/7/6.
 */
public class CopyFileNio {
    /**
     *普通NIO拷贝（很快）
     * @param src
     * @param dest
     * @throws IOException
     */
    public static void nioCopy(String src,String dest) throws IOException {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(dest);
        FileChannel srcChannel = fis.getChannel();
        FileChannel destChannel = fos.getChannel();

        destChannel.transferFrom(srcChannel,0,srcChannel.size());

        srcChannel.close();
        destChannel.close();

    }

    /**
     * 通过文件内存映射拷贝（最快）
     * @param src
     * @param dest
     * @throws IOException
     */
    public static void nioCopyByMap(String src,String dest) throws IOException {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(dest);
        FileChannel srcChannel = fis.getChannel();
        FileChannel destChannel = fos.getChannel();

        MappedByteBuffer mbb = srcChannel.map(FileChannel.MapMode.READ_ONLY, 0, srcChannel.size());
        destChannel.write(mbb);
        srcChannel.close();
        destChannel.close();

    }

    public static void main(String[] args) throws IOException {
        long l = System.currentTimeMillis();
        String src = "/Users/reason/Downloads/ideaIU-2016.2.5.dmg";
        String dest = "/Users/reason/Downloads/1.dmg";
        CopyFileUtil.copyByBioByBufferBy1(src,dest);
        System.out.println(System.currentTimeMillis()-l);

    }
}
