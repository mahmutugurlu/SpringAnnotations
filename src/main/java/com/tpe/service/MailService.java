package com.tpe.service;

import com.tpe.repository.Repository;
import com.tpe.domain.Message;

public class MailService implements MessageService {

 //   private MysqlRepository repo = new MysqlRepository();

    private Repository repo ;

    public MailService(Repository repo) {
        this.repo = repo;
    }

    public void sendMessage(Message message){

        System.out.println("Mesajınız mail ile gönderiliyor...Mesaj : "+message.getBody());
    }

    @Override
    public void saveMessage(Message message) {

        repo.save(message);

    }


}
