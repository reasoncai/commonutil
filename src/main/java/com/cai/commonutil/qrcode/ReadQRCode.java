package com.cai.commonutil.qrcode;

import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by cai on 2017/11/8.
 */
public class ReadQRCode {
    public static void main(String[] args) throws NotFoundException,IOException {

        MultiFormatReader formatReader=new MultiFormatReader();

        File file=new File("./data/qrout/1.png");
        BufferedImage image= ImageIO.read(file);

        BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));

        //定义二维码的参数:
        HashMap hints=new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");//定义字符集

        Result result=formatReader.decode(binaryBitmap,hints);//开始解析
        System.out.println("numbit"+result.getNumBits());
        System.out.println(result.getResultMetadata());
        System.out.println("解析结果:"+result.toString());
        System.out.println("二维码的格式类型是:"+result.getBarcodeFormat());
        System.out.println("二维码的文本内容是:"+result.getText());
    }
}
