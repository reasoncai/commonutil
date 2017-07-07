package com.cai.commonutil.mail;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;

import java.util.List;

/**
 * Created by cai on 2017/3/14.
 */
public class CommonMailUtil {
    private String smtpHost;
    private String username;
    private String password;
    private String emailAddress;

    public CommonMailUtil(String smtpHost, String username, String password, String emailAddress) {
        this.smtpHost = smtpHost;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    /**
     * 发送带附件html邮件
     * @param subject   主题
     * @param htmlContent   内容
     * @param reciveAdd 收件人地址，多个地址以;分隔
     * @param attachmentPaths   附件路径
     * @return
     */
    public  boolean sendHtmlEmailWithAttachment(String subject, String htmlContent, String reciveAdd, List<String> attachmentPaths){

        try {
            HtmlEmail email = new HtmlEmail();
            email.setDebug(true);
            email.setHostName(smtpHost);
            email.setAuthentication(username, password);
            email.setFrom(emailAddress);
            email.setCharset("UTF-8");
            email.setSubject(subject);
            email.setHtmlMsg(htmlContent);
            //添加附件
            if(attachmentPaths!=null){
                for(String path : attachmentPaths){
                    EmailAttachment attachment = new EmailAttachment();
                    attachment.setPath(path);
                    attachment.setDisposition(EmailAttachment.ATTACHMENT);
                    email.attach(attachment);
                }
            }
            //循环添加每个以“;”分隔的收件地址
            for(int i=0;i<reciveAdd.split(";").length;i++){
                email.addTo(reciveAdd.split(";")[i].trim());
            }
            email.send();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
