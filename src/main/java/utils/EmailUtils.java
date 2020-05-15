package utils;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class EmailUtils {


    public static void main(String[] args) {
//       try {
//		sendVercode(ReceiverEmail, "123456");
//	} catch (MessagingException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
    }

    public static final String URL = "http://47.105.173.231:8080/android/";
    public static final String PROTOCOL = "smtp";
    public static final String HOST = "smtp.qq.com";

    public static final String Charset ="text/html;charset=UTF-8";

    public static final String SENDER_EMAIL = "foolfishy@foxmail.com";
    public static final String AUTH_CODE = "zjdybnttmtpqdaje";
//      private static final String ReceiverEmail = "2427565360@qq.com";
    //	private static final String ReceiverEmail = "2427565360@qq.com";
//    private static final String ReceiverEmail = "1936363891@qq.com";
    //		private static final String ReceiverEmail = "mzs123@dingtalk.com";
//		private static final String ReceiverEmail = "mzs@zjjzfy.com";

    public static final String EMAIL_TITLE = "注册邮箱验证";

   static Session session = createSession();

    
    public static void sendVercode(String email,String code) throws MessagingException {
      String html = "<span style='font-size:24px;background:#FA8072;padding:0.2cm 0.4cm;text-align:center;'>"+code+"</span>";
      String content = "尊敬的用户：" +
              "<br/>" +
              "我们收到了一个使用您的电子邮件地址创建帐户的请求。"+
              "<br/>" +
              "要完成帐户注册，需将以下验证码填入对应位置。" +
              "<br/>" +
              "<br/>" +
              "验证码:"+
              "<br/>" +
              html+
              "<br/>" +
              "<br/>" +
              "此验证码有效期为 10 分钟，请勿向任何人透露此验证码。如果验证码已过期，请尝试请求向您发送新的确认电子邮件。 <br/>" +
              "<br/>" +
              "<br/>" +
              "如果您没有提出此请求，请提忽略电子邮件或发送邮件至  " +
              SENDER_EMAIL +
              "谢谢！";

      sendEmail(email, EMAIL_TITLE,content );
    }
    
    private static void sendEmail(String email,String subject,String content) throws MessagingException {

        //2、通过session得到transport对象
        Transport ts = session.getTransport();

        //3、使用邮箱的用户名和授权码连上邮件服务器
        ts.connect(HOST, SENDER_EMAIL, AUTH_CODE);

        //4、创建邮件

        //创建邮件对象
        MimeMessage message = new MimeMessage(session);

        //指明邮件的发件人
        message.setFrom(new InternetAddress(SENDER_EMAIL));

        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        //邮件的标题
        message.setSubject(subject);
        
        message.setContent(content,Charset);
        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());

        ts.close();
    }
    
//    public void verifyEmail(String email, String name, String code) throws MessagingException {
//
//        //2、通过session得到transport对象
//        Transport ts = session.getTransport();
//
//        //3、使用邮箱的用户名和授权码连上邮件服务器
//        ts.connect(HOST, SENDER_EMAIL, AUTH_CODE);
//
//        //4、创建邮件
//
//        //创建邮件对象
//        MimeMessage message = new MimeMessage(session);
//
//        //指明邮件的发件人
//        message.setFrom(new InternetAddress(SENDER_EMAIL));
//
//        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
//        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
//
//        //邮件的标题
//        message.setSubject(EMAIL_TITLE);
//
//
//        String confirmHtml = "<a href = '" + URL + "?key=" + code + "' " +
//                "style='font-size:24px;background:#FA8072;padding:0.2cm 0.4cm;text-align:center;'>确认激活</a>";
//
//        String confirmHtml2 = "<a href = 'https://www.baidu.com?key=" + code + "' " +
//                "style='font-size:24px;background:#FA8072;padding:0.2cm 0.4cm;text-align:center;'>确认激活</a>";
//        //邮件的文本内容
//        System.out.println(confirmHtml);
//        message.setContent("尊敬的 " + name + "：" +
//                "<br/>" +
//                "要完成帐户注册，请先确认您的电子邮件地址完成激活。" +
//                "<br/>" +
//                "<br/>" +
//                confirmHtml+
//                "<br/>" +
//                "<br/>" +
//                "<br/>" +
//                "<br/>" +
//                confirmHtml2+
//                "<br/>" +
//                "<br/>" +
//                "此链接有效期为 24 小时。如果按钮已过期，请尝试请求向您发送新的确认电子邮件。 <br/>" +
//                "<br/>" +
//                "<br/>" +
//                "我们收到了一个使用您的电子邮件地址创建帐户的请求。如果您没有提出此请求，请提忽略电子邮件或发送邮件至  " +
//                SENDER_EMAIL +
//                "谢谢！", "text/html;charset=UTF-8");
//
//
//
//        //5、发送邮件
//        ts.sendMessage(message, message.getAllRecipients());
//
//        ts.close();
//
//    }

    private static Session createSession() {

        Properties prop = new Properties();
        prop.setProperty("mail.host", HOST); //// 设置QQ邮件服务器
        prop.setProperty("mail.transport.protocol", PROTOCOL); // 邮件发送协议
        prop.setProperty("mail.smtp.auth", "true"); // 需要验证用户名密码

        // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可
		try {
			MailSSLSocketFactory sf = new MailSSLSocketFactory();
	        sf.setTrustAllHosts(true);
	        prop.put("mail.smtp.ssl.enable", "true");
	        prop.put("mail.smtp.ssl.socketFactory", sf);
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return Session.getDefaultInstance(prop, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                //发件人邮件用户名、授权码
                return new PasswordAuthentication(SENDER_EMAIL, AUTH_CODE);
            }
        });
    }

//    public void t() throws NoSuchProviderException {


        //使用JavaMail发送邮件的5个步骤

        //创建定义整个应用程序所需的环境信息的 Session 对象


        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
//        session.setDebug(true);

//        //2、通过session得到transport对象
//        Transport ts = session.getTransport();
//
//        //3、使用邮箱的用户名和授权码连上邮件服务器
//        ts.connect(HOST, SENDER_EMAIL, AUTH_CODE);
//
//        //4、创建邮件
//
//        //创建邮件对象
//        MimeMessage message = new MimeMessage(session);
//
//        //指明邮件的发件人
//        message.setFrom(new InternetAddress(SENDER_EMAIL));
//
//        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
//        message.setRecipient(Message.RecipientType.TO, new InternetAddress(ReceiverEmail));
//
//        //邮件的标题
//        message.setSubject("发送测试");
//
//        //邮件的文本内容
//        message.setContent("尊敬的 bsbs： <br/>" +
//                "要完成 Oracle 帐户注册，请先确认您的电子邮件地址。 <br/>" +
//                "确认电子邮件地址 <br/>" +
//                "<br/>" +
//                "<br/>" +
//                "此链接有效期为 24 小时。如果按钮已过期，请尝试请求向您发送新的确认电子邮件。 <br/>" +
//                "<br/>" +
//                "<br/>" +
//                "我们收到了一个使用您的电子邮件地址创建 Oracle 帐户的请求。如果您没有提出此请求，请提交帮助请求或发送电子邮件至 profilehelp_ww@oracle.com。 <br/>" +
//                "谢谢！<br/>" +
//                "Oracle 帐户团队 ！", "text/html;charset=UTF-8");
//
//        //5、发送邮件
//        ts.sendMessage(message, message.getAllRecipients());
//
//        ts.close();
//    }

}
