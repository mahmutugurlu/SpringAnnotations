package com.tpe.service;

import com.tpe.domain.Message;
import org.springframework.stereotype.Component;

@Component
//bu classın objesi Spring tarafından oluşturulur ve yönetilir
//hatta bağımlılığı varsa enjekte edilir. Bu objelere de Spring Bean adı verilir.

public class SmsService implements MessageService{


    @Override
    public void sendMessage(Message message) {

        System.out.println("Mesajınız SMS ile gönderiliyor... Mesaj : "+message.getBody());

    }

    @Override
    public void saveMessage(Message message) {

    }
}
