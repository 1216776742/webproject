package org.lanqiao.util;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtil {
	static String from = "1216776742@qq.com";
	static String authPassword = "zlbsyxmsiwrchdhj";//需要授权密码
	static String smtpHost = "smtp.qq.com";

	public static void sendMail(String to, String subject, String content) {
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", smtpHost);// 设置邮件服务器
		props.setProperty("mail.transport.protocol", "smtp");// 设置发送邮件的协议
		props.setProperty("mail.smtp.auth", "true");// 设置验证

		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, authPassword);// 客户端授权密码
			}
		};
		// qq邮箱开启ssl认证
		MailSSLSocketFactory sf = null;
		try {
			sf = new MailSSLSocketFactory();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sf.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", sf);
		
		// 建立邮件服务器会话；---Session
		Session session = Session.getDefaultInstance(props, auth);
		// 2、创建一封邮件；---MimeMessage
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			message.setSubject(subject);
			message.setSentDate(new Date());// 设置发送日期；
			message.setContent(content, "text/html;charset=utf-8");
			// 设置收件人
			message.setRecipient(MimeMessage.RecipientType.TO,
					new InternetAddress(to));// 发件
			// 3、发送邮件 -- Transport
			Transport transport = session.getTransport();
			transport.connect(from, authPassword);// 通过授权码以发送账号身份链接
			transport.sendMessage(message, message.getAllRecipients());// 发送邮件
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
