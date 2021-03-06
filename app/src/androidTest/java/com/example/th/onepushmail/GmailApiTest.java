package com.example.th.onepushmail;

/**
 * Created by T.H on 2017/01/03.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.th.onepushmail.test.TestActivity;
import com.example.th.onepushmail.utils.LogUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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

@RunWith(AndroidJUnit4.class)
public class GmailApiTest {
    final GmailApiTest self = this;

    @Rule
    public ActivityTestRule<TestActivity> activityTestRule = new ActivityTestRule<>(TestActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    //gmailの送信テスト
    @Test
    public void gmailApiTest() {
        final TestActivity activity = activityTestRule.getActivity();
        Context context = activity.getApplicationContext();

        //テスト設定
        final String fromEmail = "ログインアドレス";
        final String password = "ログインパス";
        String toEmail = "送信先@~~~com";
        String title = "test title";
        String body = "test message";
        /////////////////////

        try {
            //email と password更新
            SharedPreferences.Editor gmailInfo = PreferenceManager.getDefaultSharedPreferences(context).edit();
            gmailInfo.putString("email", fromEmail);
            gmailInfo.putString("password", password);
            gmailInfo.commit();

            //以下メール送信
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
            LogUtil.log(context, "gmailApiTest", e.toString());
        } finally {
            LogUtil.log(context, "gmailApiTest", "succsess");
        }
    }
}
