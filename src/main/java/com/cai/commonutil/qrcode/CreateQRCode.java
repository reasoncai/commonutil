package com.cai.commonutil.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * Created by cai on 2017/11/9.
 */
public class CreateQRCode {
        public static void main(String[] args) throws WriterException,IOException {
            int height=147;
            int width=147;//图片大小
            String format="png";//图片格式
            //String content="https://qr.m.jd.com/p?k=AAEAIKnuB6ZS53UyyBrsTR9viPNRinXUDglQ3ZPdRv89rja_";//内容
            String content = "http://www.baidu.com";
            //定义二维码的参数:
            HashMap hints=new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET,"utf-8");//定义字符集
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);//定义纠错级别
            hints.put(EncodeHintType.MARGIN,0);//定义边距为2

            BitMatrix bitMatrix=new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width,height,hints);//开始生成二维码

            File file=new File("./data/qrout/baidu.png");//指定保存路径
            MatrixToImageWriter.writeToFile(bitMatrix,format,file);
        }

}
