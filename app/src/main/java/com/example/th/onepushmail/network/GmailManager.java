package com.example.th.onepushmail.network;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.example.th.onepushmail.definition.MailDefinition;
import com.example.th.onepushmail.utils.DateFormatUtil;
import com.example.th.onepushmail.utils.LogUtil;
import com.example.th.onepushmail.utils.UserPreferenceUtil;

import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Created by T.H on 2017/01/04.
 */

public class GmailManager {
    private final GmailManager self = this;

    Activity activity;

    //自身のアカウント
    private String fromEmail;
    private String password;

    //メール設定
    private String toEmail;
    private String title;
    private String body;

    //UserPreferenceから取得
    private Map<String, String> user;

//    //メールの種類
//    public static class TitleKinds {
//        public static final int WORK = 1; //出社
//        public static final int REPORT = 2; //日報
//        public static final int ABSENTEE = 3; //勤怠
//    }


    /**
     * 出社連絡
     */
    public GmailManager(@NonNull Context context, @NonNull String toEmail, @NonNull String body) {
        //test 動的に保存されたものを取得できる仕組みにすべし
        UserPreferenceUtil.save(context, "h.tsuruta@leihauoli.com", "tsuruhiro0715");
        /////

        user = UserPreferenceUtil.getAccount(context);
        this.fromEmail = user.get("email");
        this.password = user.get("password");
        this.toEmail = toEmail;
        this.title = MailDefinition.WORK + " " + DateFormatUtil.getDate() + " " + user.get("name");
        this.body = MailDefinition.BODY_TOP + body + MailDefinition.BODY_BOTTOM;
    }

    /**
     *
     */
//    public GmailManager(@NonNull Context context, @NonNull String toEmail, @NonNull int kinds, @NonNull String body) throws Exception {
//
//    }

    public void sendMail(@NonNull final Context context) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    //メール送信
                    final Properties property = new Properties();
                    property.put("mail.smtp.host", "smtp.gmail.com");
                    property.put("mail.host", "smtp.gmail.com");
                    property.put("mail.smtp.port", "465");
                    property.put("mail.smtp.socketFactory.port", "465");
                    property.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

                    // セッション
                    final Session session = Session.getInstance(property, new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(fromEmail, password);
                        }
                    });

                    //送るメールを作成
                    MimeMessage mimeMsg = new MimeMessage(session);

                    //タイトル
                    mimeMsg.setSubject(title, "utf-8");
                    //送信元
                    mimeMsg.setFrom(new InternetAddress(fromEmail));
                    //送信先
                    mimeMsg.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(toEmail));
                    //本文
                    final MimeBodyPart txtPart = new MimeBodyPart();
                    txtPart.setText(body, "utf-8");

                    final Multipart multipart = new MimeMultipart();
                    multipart.addBodyPart(txtPart);
                    mimeMsg.setContent(multipart);

                    // メール送信
                    final Transport transport = session.getTransport("smtp");
                    transport.connect(fromEmail, password);
                    transport.sendMessage(mimeMsg, mimeMsg.getAllRecipients());
                    transport.close();

                } catch (MessagingException e) {
                    LogUtil.log(context, "sendMail", e.toString());
                } finally {
                    LogUtil.log(context, "sendMail", "------finish-----");
                }
            }
        });
    }
}
