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
            simpleMailMessage.setFrom("1223020@donga.ac.kr");
            simpleMailMessage.setTo("dlackdtjq123@naver.com");
            simpleMailMessage.setSubject("Test");
            simpleMailMessage.setText("<p style='color: red;'>html 형식의 내용</p>"); 
            javaMailSender.send(simpleMailMessage);

        } catch(Exception exception) {
            exception.printStackTrace();
            return false;
        }
        //? sendMail이 boolean형태라서 true를 return을 보냄
        return true;
    }
}
