package com.tpe.repository;

import com.tpe.domain.Message;

public class PostgresqlRepository implements Repository{
    @Override
    public void save(Message message) {

        System.out.println("Mesajınız PostgreSQL ile kaydediliyor...");
    }
}
