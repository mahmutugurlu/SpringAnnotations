package com.tpe.repository;

import com.tpe.domain.Message;

public class MysqlRepository implements Repository{


    @Override
    public void save(Message message) {
        System.out.println("Mesajınız MySQL ile kaydediliyor...");
    }
}
