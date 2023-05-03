package com.seungh1024.encrypt;

import com.seungh1024.exception.custom.EncryptException;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public class CustomPasswordEncoder implements SeunghPasswordEncoder, RandomSalt, PasswordChecker{
    @Override
    public String encryptPassword(String password, String salt){
//        String salt = getSalt();
        String result = "";
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update((password+salt).getBytes());
            byte[] passwordSalt = md.digest();

            StringBuffer sb = new StringBuffer();
            for(byte b : passwordSalt){
                sb.append(String.format("%02x",b));
            }

            result = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new EncryptException();
        }

        return result;
    }

    @Override
    public String getSalt(){
        // SecureRandom, salt 생성
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[30];

        // 난수 생성
        sr.nextBytes(salt);

        // byte를 String으로 변경(10진수 문자열로 변경)
        StringBuffer sb = new StringBuffer();
        for(byte b : salt){
            sb.append(String.format("%02x",b));
        }

        return sb.toString();
    }

    @Override
    public Boolean isCorrectPassword(String inputPassword, String memberPassword, String salt){
        String encryptedPassword = encryptPassword(inputPassword, salt);
        System.out.println(encryptedPassword);
        return encryptedPassword.equals(memberPassword);
    }
}
