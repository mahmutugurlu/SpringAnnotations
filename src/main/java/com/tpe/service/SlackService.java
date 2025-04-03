package com.tpe.service;

import com.tpe.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;
import java.util.Random;

@Component
@Scope("prototype")
public class SlackService implements MessageService{

    @Autowired
    private Random random;


    @Override

    public void sendMessage(Message message) {
        System.out.println("Mesajınız SLACK ile gönderiliyor... Mesaj : "+message.getBody());

    }

    @Override
    public void saveMessage(Message message) {

        //random bir deger üretelim ve yazdiralim
        System.out.println(random.nextInt(100));




    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("-------- slack service objesi üretildi.");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("-------- slack service objesi imha edildi.");
    }



    @Value("${eposta}") //@Value("${eposta}") bu sekilde olusturdugumuz dosyadaki degerleri bu fieldlera aktardik
    private String email;
    @Value("${phone}")
    private String phone;

    public void printContact(){
        System.out.println("email : "+this.email);
        System.out.println("phone number : "+this.phone);
    }

    @Autowired
    private Properties properties;
    public void showContactInfo(){
        System.out.println("email : "+properties.getProperty("mymail"));
        System.out.println("database url : "+properties.getProperty("url"));
        System.out.println(properties.getProperty("javahome"));
    }


}

/*
Singleton Scope
Spring konteyneri, bir bean tanımı için yalnızca bir örnek (instance) oluşturur ve bu örneği tüm uygulama boyunca paylaşır.
Bean, Spring konteyneri başlatıldığında oluşturulur.
Uygulama kapanana kadar aynı örnek kullanılır.
Stateless (Durumsuz) nesneler için kullanılır.
Aynı davranışı ve veriyi paylaşması gereken hizmet sınıfları (örneğin, Service ve Repository sınıfları) için idealdir.
Tek bir örnek oluşturulduğu için bellek tüketimini azaltır.
----------------
Prototype Scope
Spring konteyneri, bir bean'e her erişimde yeni bir örnek (instance) oluşturur.
Bean, Spring konteyneri tarafından her çağrıldığında (örneğin, getBean() metodu ile) yeniden oluşturulur.
Kısa ömürlüdür ve Spring konteyneri yaşam döngüsünü yönetmez.
Stateful (Durum bilgisi taşıyan) nesneler için kullanılır.
Her istekte farklı veriyle çalışması gereken nesneler (örneğin, kullanıcı oturum bilgisi veya dosya işlemleri) için uygundur.
-------------
Singleton: Varsayılan olduğu için Spring genellikle bu scope'u kullanır ve çoğu servis veya repository sınıfı için yeterlidir.
Prototype: Kısa ömürlü nesneler için kullanışlıdır, ancak manuel olarak yönetilmesi gerekebilir. Özellikle Spring konteynerinin dışında kullanılan prototip nesnelerin yaşam döngüsüne dikkat edilmelidir.
 */
