package com.tpe;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

//Spring, Java tabanlı bir açık kaynaklı framework olup, özellikle kurumsal uygulamalar geliştirmek için kullanılır.

@ComponentScan("com.tpe")//bu dizinde yer alan tüm componentları tarar
@Configuration//bu sınıfta yapılandırma ayarları yapılacak
//default path:AppConfiguration sınıfının içinde bulunduğu path

@PropertySource("classpath:application.properties")
//application.properties dosyasındaki özelliklerin okunmasını sağlar
public class AppConfiguration {

    //Springe ait bir interface, bean oluşturmadan enjekte edilebilir,
    // PropertySourcedaki dosyanın içindeki özelliklere(propertylere)
    // ve hatta uygulamanın çalıştığı ortam değişkenlerine
    // erişmemizi sağlayan metodlar içerir.

    @Autowired
    private Environment environment;


    @Bean//third party classtan bean oluşturulmasını sağlar
    public Random random(){

        return new Random();
    }


    @Bean
    public Scanner scanner(){

        return new Scanner(System.in);
    }

    //component anotasyonu bizim yazdığımız classlar için kullanılır sınıf seviyesinde kullanılır.
    // bean anotasyonu bizim yazmadığımız classlarda kullanılır, method seviyesinde kullanılır.


    //value anotasyonu ile yaptığımız işlemi Environment ve
    // Properties (hash table i extend eder)  classını kullanarak da yapabiliriz

    @Bean
    public Properties properties(){
        Properties properties=new Properties();
        // properties.put("mymail","techpro@gmail.com");//emaili kaynaktan almak istiyoruz
        properties.put("mymail",environment.getProperty("eposta"));
        properties.put("myphone",environment.getProperty("phone"));
        properties.put("url",environment.getProperty("database.url"));
        properties.put("javahome",environment.getProperty("JAVA_HOME"));
        return properties;
    }








}
