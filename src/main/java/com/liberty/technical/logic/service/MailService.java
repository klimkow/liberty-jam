package com.liberty.technical.logic.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

/**
 * @author AKI
 */
public class MailService
{
  public static final String SMTP_SERVER = "mail.kompliment.by";
  public static final String DEFAULT_SENDER = "delivery@kompliment.by";

  private Properties properties;
  private static MailService INSTANSE;


  private MailService()
  {
    properties = System.getProperties();
//    properties.put("mail.transport.protocol", "smtp");
//    properties.put("mail.smtp.port", "587");
//    properties.put("mail.host", SMTP_SERVER);
//    properties.put("mail.smtp.auth", "true");
//    properties.put("mail.smtp.socketFactory.port", "587");
//    properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
//    properties.put("mail.smtp.starttls.enable", "true");
//    properties.put("mail.smtp.socketFactory.fallback", "true");

    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", SMTP_SERVER);
    properties.put("mail.smtp.port", "587");
  }


  public static MailService getInstanse()
  {
    if (INSTANSE == null) {
      INSTANSE = new MailService();
    }
    return INSTANSE;
  }

  public void test()
  {

  }


  public void sendEmail(String from, String to, String subject, String htmlMessage)
  {
    Session session = Session.getDefaultInstance(properties, new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(DEFAULT_SENDER,"8bkNsvnElH2T@1"); // username and the password
      }
    });

    try {
      MimeMessage message = new MimeMessage(session);

      message.setFrom(new InternetAddress(from));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.setSubject(subject);

      message.setContent(htmlMessage, "text/html");

      Transport.send(message);
    } catch (MessagingException mex) {
      mex.printStackTrace();
    }
  }


  public String createNewOrderMail(Long orderId, String nameFrom,
                                   String surName, String phone, String paymentType,
                                   List<String> items, Integer totalPrice)
  {
    StringBuilder sb = new StringBuilder();
    sb.append("<!DOCTYPE html>\n" +
        "<html lang=\"en\">\n" +
        "\n" +
        "<head>\n" +
        "\n" +
        "    <meta charset=\"utf-8\">\n" +
        "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
        "    <meta name=\"description\" content=\"\">\n" +
        "    <meta name=\"author\" content=\"\">\n" +
        "\n" +
        "\n" +
        "</head>\n" +
        "\n" +
        "<body>\n" +
        "\n" +
        "    <div id=\"wrapper\">\n" +
        "\n" +
        "        <div style=\"height: 100%;\" id=\"page-wrapper\">\n" +
        "\n" +
        "            <div class=\"container-fluid\">\n" +
        "\n" +
        "                <!-- Page Heading -->\n" +
        "                <div class=\"row\">\n" +
        "                    <div class=\"col-lg-12\">\n" +
        "                        <h1 style=\"font-size: 25px;\" class=\"page-header\">\n" +
        "                            Order");
    sb.append(orderId);
    sb.append("</h1>\n" +
        "                        \n" +
        "                    </div>\n" +
        "                </div>\n" +
        "                <!-- /.row -->\n" +
        "\n" +
        "                <div class=\"row\">\n" +
        "                    <div style=\"width: 100%; height: 100%\">\n" +
        "                        <div style=\"float: left; width: 50%;\">\n" +
        "                            <button class=\"panel panel-default\">\n" +
        "                                <div class=\"panel-heading\">Buyer information</div>\n" +
        "                                <div class=\"panel-body\">\n" +
        "                                    <p><b>Name:</b>");
    sb.append(nameFrom).
        append("</p><p><b>Surname:</b>").append(surName).
        append("</p><p><b>Phone:</b>").append(phone).append("</div>\n" +
        "                                <ul class=\"list-group\">\n" +
        "                                    <li class=\"list-group-item\">").append("<p><b>Payment type:</b>").
        append(paymentType).append("</p>").append("<p>");
    for (String itemName : items) {
      sb.append("Item: ").append(itemName).append("<br>");
    }
    sb.append("</p>").append(" <div style=\"font-size: 14pt;\" class=\"panel-footer text-center\">\n" +
        "                                    <p><b>Order total price:").append(totalPrice).append("000 BYR</b></p>\n" +
        "                                </div>");
    return sb.toString();
  }
}
