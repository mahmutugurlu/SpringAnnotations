package com.tpe.controller;

import com.tpe.AppConfiguration;
import com.tpe.domain.Message;
import com.tpe.repository.MysqlRepository;
import com.tpe.repository.Repository;
import com.tpe.service.MessageService;
import com.tpe.service.SlackService;
import com.tpe.service.SmsService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

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


        MessageService service3 = context.getBean("sms_service", MessageService.class);//parametre olarak parente verdik,
        service3.sendMessage(message);                                      //hangi beani cagirmasi gerektigini belirtmek icin adini yazdik

        //NOT: contexten parametre olarak parentı(interfacei) vererek de bean isteyebiliriz
        //Ancak eğer 1'den fazla aynı parentın childı olan bean varsa isim belirtmeliyiz


        //bagimlilik gerekirse

        MessageService service4 = context.getBean(SmsService.class);
        service4.sendMessage(message);
        service4.saveMessage(message);

        //smsservice beanini üretirken içine repo enjekte etmesi gerekir
        //elinde repo olarak mysql var, onu enjekte etti


        Repository repository = context.getBean(MysqlRepository.class);
        repository.save(message);


        //random bir değer üretelim ve yazdıralım
        //Random random=new Random();
        Random random=context.getBean(Random.class);
        System.out.println("random değer"+random.nextInt(100));


        MessageService service5=context.getBean(SlackService.class);
        MessageService service6=context.getBean(SlackService.class);

        if (service5==service6){
            System.out.println("Aynı Objeler");

        }else {
            System.out.println("Farklı Objeler");
        }

        SlackService service7 = context.getBean(SlackService.class);
        service7.printContact();


        //prototype olan beani imha etmek için- manuel olarak yaptik
        context.getBeanFactory().destroyBean(service5);







        context.close();





    }
}
