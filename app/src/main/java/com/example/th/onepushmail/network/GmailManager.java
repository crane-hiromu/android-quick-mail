package com.example.th.onepushmail.network;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.th.onepushmail.definition.MailDefinition;
import com.example.th.onepushmail.definition.MessageDefinition;
import com.example.th.onepushmail.definition.UtilDefinition;
import com.example.th.onepushmail.utils.CommonUtil;
import com.example.th.onepushmail.utils.LogUtil;
import com.example.th.onepushmail.utils.ToastUtil;
import com.example.th.onepushmail.utils.UserPreferenceUtil;
import com.example.th.onepushmail.views.animation.ViewAnimations;

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
 * <p>
 * 実装例
 * new GmailManager(self, "送り先のアドレス", "業務内容").sendMail(button);
 */

public class GmailManager {
    private final GmailManager self = this;
    Context context;
//    ProgressDialogFragment progressDialogFragment;

    //自身のアカウント
    private String fromEmail;
    private String password;

    //メール設定
    private String toEmail;
    private String title;
    private String body;

    //UserPreferenceから取得
    private Map<String, String> user;

    //リクエストの成功失敗
    private static class ResultCode {
        private static final int SUCCESS = 200; //成功
        private static final int ERROR = 400; //失敗
    }

    //ResultCodeを判定
    private Boolean keepCode = true;

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
        //test
        // todo すでに保存されたものを取得できる仕組みにすべし
        UserPreferenceUtil.save(context, "ユーザー名", "アドレス", "パス");
        ///

        user = UserPreferenceUtil.getAccount(context);
        this.context = context;
        this.fromEmail = user.get(UtilDefinition.EMAIL);
        this.password = user.get(UtilDefinition.PASSWORD);
        this.toEmail = toEmail;
        this.title = MailDefinition.WORK + " " + CommonUtil.getDate(1) + " " + user.get(UtilDefinition.NAME);
        this.body = MailDefinition.BODY_TOP + body + MailDefinition.BODY_BOTTOM;
    }

    /**
     *
     */
//    public GmailManager(@NonNull Context context, @NonNull String toEmail, @NonNull int kinds, @NonNull String body) throws Exception {
//
//    }
    public void sendMail(final View button) {
        new MyAsyncTask() {
//            //通信前
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                progressDialogFragment = ProgressDialogFragment.newInstance(MessageDefinition.SENDING, MessageDefinition.WAITING);
//                progressDialogFragment.getDialog();
//            }

            //通信中
            @Override
            protected Integer doInBackground(Void... params) {
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
                    keepCode = false;
                } catch (Exception e) {
                    LogUtil.log(context, "sendMail", e.toString());
                    keepCode = false;
                } finally {
                    LogUtil.log(context, "sendMail", "------finish-----");
                }

                //onPostExecuteに渡すリザルトコード
                if (keepCode) {
                    return ResultCode.SUCCESS;
                } else {
                    return ResultCode.ERROR;
                }

            }

            //通信後
            @Override
            protected void onPostExecute(Integer num) {
//                progressDialogFragment.dismiss();
                switch (num) {
                    case ResultCode.ERROR:
                        ToastUtil.show((Activity) context, MessageDefinition.NONCOMPLEATE, 1);
                        break;

                    case ResultCode.SUCCESS:
                        ToastUtil.show((Activity) context, MessageDefinition.COMPLEATE, 1);
                        break;
                }

                //ボタンを再び押せるように
                if (button.getVisibility() != View.VISIBLE) {
                    ViewAnimations.visibleSize(button, 0, 200, 1000);
                    button.setEnabled(true);
                }
            }
        }.execute();
    }
}
