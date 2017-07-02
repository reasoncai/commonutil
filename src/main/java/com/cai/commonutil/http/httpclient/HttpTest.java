package com.cai.commonutil.http.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by reason on 17/6/9.
 */
public class HttpTest {
    public static void main(String[] args) {
        HttpResponse response = null;
        try {
            HttpGet get = new HttpGet("https://yundao.unimip.cn");
            response = HttpsClientUtil.getHttpClient().execute(get);
            if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
                EntityUtils.consume(response.getEntity());
                //error
            } else {
                String result = EntityUtils.toString(response.getEntity());
                System.out.println(result);
                //ok
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if(response != null){
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
