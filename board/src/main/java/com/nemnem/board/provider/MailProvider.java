package com.nemnem.board.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailProvider {
    
    @Autowired private JavaMailSender javaMailSender;

    public boolean sendMail() {
        
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("보내는 사람의 메일");
            simpleMailMessage.setTo("받는 사람의 메일");
            simpleMailMessage.setSubject("제목");
            simpleMailMessage.setText("html 형식의 내용"); 
            javaMailSender.send(simpleMailMessage);

        } catch(Exception exception) {
            exception.printStackTrace();
            return false;
        }
        //? sendMail이 boolean형태라서 true를 return을 보냄
        return true;
    }
}
