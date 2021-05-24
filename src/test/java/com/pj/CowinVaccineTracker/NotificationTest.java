package com.pj.CowinVaccineTracker;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import org.junit.jupiter.api.Test;
import com.twilio.rest.api.v2010.account.Message;

public class NotificationTest {

    public static final String ACCOUNT_SID = "123";
    public static final String AUTH_TOKEN = "123";

    @Test
    public void sendWhatsAppMessage(){
        String refreshOTPURl = "http://192.168.0.107:8080/otppage.html?mobile=";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber("whatsapp:+"),
                new PhoneNumber("whatsapp:+"),
                "Hello there! \n Refresh OTP : " + refreshOTPURl)
                .create();

        System.out.println(message.getSid());
    }
    @Test
    public void TestSendMessage(){
        sendTextMessage("COVISHIELD","20-05-2021", "http://192.168.0.107:8080/otppage.html?mobile=");
    }

    public void sendTextMessage(String vaccineName, String date, String url){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+"),
                "SMPJCoVAX",
                "Vaccine: "+vaccineName+"+, Date: "+date+", Link: "+url)
                .create();

        System.out.println(message.getSid());
    }
}
