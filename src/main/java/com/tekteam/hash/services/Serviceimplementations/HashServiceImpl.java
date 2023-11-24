package com.tekteam.hash.services.Serviceimplementations;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

@Service
public class HashServiceImpl {
   private SecretKey key;
   private int Key_Size = 128;
   private Cipher cipher;

   public void hash() throws Exception{
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(Key_Size);
        key = keyGenerator.generateKey();
    }
    @SneakyThrows
    public String encrypt(String message){
        byte[] messageInBytes = message.getBytes();
        Cipher cipher = Cipher.getInstance("AES/GCM/noPadding");
        cipher.init(Cipher.ENCRYPT_MODE,key);
        byte[] encryptedByte = cipher.doFinal(messageInBytes);
        return encode(encryptedByte);
   }
   private String encode(byte[] data){
       return Base64.getEncoder().encodeToString(data);
   }

}
