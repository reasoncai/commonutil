package com.cai.commonutil.http.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by cai on 2017/7/13.
 */
public class WebServiceHttpClient{

    public synchronized static String accessService(String wsdl, String ns, String method, Map<String,String> params, String result)throws Exception{
        //拼接参数
        String param = getParam(params);
        String soapResponseData = "";
        //拼接SOAP
        StringBuffer soapRequestData = new StringBuffer("");
        soapRequestData.append("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
        soapRequestData.append("<soap:Body>");
        soapRequestData.append("<ns1:"+method+" xmlns:ns1=\""+ns+"\">");
        soapRequestData.append(param);
        soapRequestData.append("</ns1:"+method+">");
        soapRequestData.append("</soap:Body>" + "</soap:Envelope>");
        // 然后把Soap请求数据添加到PostMethod中
        HttpPost httpPost = new HttpPost(wsdl);
        httpPost.setHeader("Content-Type", "text/xml;charset=utf-8");
        StringEntity requestEntity = new StringEntity(soapResponseData, "utf-8");
        httpPost.setEntity(requestEntity);
        //发起请求
        String result1 = "";
        HttpResponse response = null;
        CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
        try {
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                EntityUtils.consume(response.getEntity());

            } else {
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return getMesage(result1,result);
    }

    public static String getParam(Map<String,String> params){
        String param = "";
        if(params!=null){
            Iterator<String> it  = params.keySet().iterator();
            while(it.hasNext()){
                String str = it.next();
                param+="<"+str+">";
                param+=params.get(str);
                param+="</"+str+">";
            }
        }
        return param;
    }

    public static String getMesage(String soapAttachment,String result){
        System.out.println("message:"+soapAttachment);
        if(result==null){
            return null;
        }
        if(soapAttachment!=null && soapAttachment.length()>0){
            int begin = soapAttachment.indexOf(result);
            begin = soapAttachment.indexOf(">", begin);
            int end = soapAttachment.indexOf("</"+result+">");
            String str = soapAttachment.substring(begin+1, end);
            str = str.replaceAll("<", "<");
            str = str.replaceAll(">", ">");
            return str;
        }else{
            return "";
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            Map<String,String> param = new HashMap<String,String>();
            param.put("flight_no", "AQ1021");
            String wsdl="http://10.10.2.27/axis2/services/FlightInfo?wsdl";
            String ns = "http://Control.nair.com";
            String method="getFlightInfoByNOandSTD";
            String response =accessService(wsdl,ns,method,param,"return");
            System.out.println("main:"+response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}