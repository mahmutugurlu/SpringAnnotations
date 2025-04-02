package com.tpe.controller;

import com.tpe.repository.MysqlRepository;
import com.tpe.repository.Repository;
import com.tpe.service.MailService;
import com.tpe.domain.Message;
import com.tpe.service.MessageService;
import com.tpe.service.WhatsappService;

public class MessageApplication {

    public static void main(String[] args) {

        Message message = new Message();

        message.setBody("Spring is COMING");


        //bu mesajı maille gönderelim
      //  MailService mailService=new MailService();
     //   mailService.sendMessage(message);

        //bu mesaji whatsapp ile gönderelim

     //   WhatsappService whatsappService = new WhatsappService();
     //   whatsappService.sendMessage(message);

        //1-referans olarak interface kullanalım
        //MessageService service=new MailService();
        //   Repository repository=new MysqlRepository();
        Repository repository=new MysqlRepository();
        MessageService service=new WhatsappService(repository);//bağımlılık enjeksiyonu
        service.sendMessage(message);
        service.saveMessage(message);

        //-bagimlilik enjeksiyonunu uygulayalim
        MessageService service2=new MailService(repository);//1 tane repo objesi oluşur
        service2.sendMessage(message);
        service2.saveMessage(message);

        //objeler arasındaki bağımlılığı gevşetmek için
        //1-referans olarak interface kullanalım
        //2-bağımlılığı doğrudan vermek yerine daha sonra constructor(veya setter) ile enjekte edelim

        //problem:
        //1-objeleri biz oluşturuyoruz
        //2-objelerin bağımlılıklarını manuel olarak biz enjekte etmekte zorundayız.

        //çözüm:
        //Spring is COMING:)









    }

}
