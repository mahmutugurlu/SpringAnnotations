package com.tpe.controller;

import com.tpe.AppConfiguration;
import com.tpe.domain.Message;
import com.tpe.service.MessageService;
import com.tpe.service.SlackService;
import com.tpe.service.SmsService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MessageApplicationWithSpring {

    public static void main(String[] args) {

        Message message=new Message();
        message.setBody("Welcome SPRING:)");

        //config classını okur ve componentscan ile componentları ve beanleri tarar ve her birinden
        //sadece 1 tane Spring bean oluşturur ve contexte atar ve hazır olarak bekletir.
        //bean istendiğinde gerekliyse içine bağımlılığını da enjekte ederek gönderir.

        AnnotationConfigApplicationContext context=
                new AnnotationConfigApplicationContext(AppConfiguration.class);


        //mesajı SMS ile gönderelim: smsservice objesi gerekli

        MessageService service1 = context.getBean(SmsService.class); //new keywordunu kullanmadik, spring objeleri olusturdu
        service1.sendMessage(message);

        //mesajı Slack ile gönderelim: slackservice objesi gerekli
        MessageService service2=context.getBean(SlackService.class);
        service2.sendMessage(message);





        context.close();





    }
}
