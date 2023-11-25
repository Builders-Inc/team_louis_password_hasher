package com.tekteam.hash.services;

import com.tekteam.hash.entity.EncryptMessage;
import com.tekteam.hash.exceptions.InternalErrorException;
import com.tekteam.hash.repositories.EncryptRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;


@Service
@RequiredArgsConstructor
public class HashServiceImpl {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    private final EncryptRepository repository;

    @Value("${encryption.key}")
    private String encryptionKey;

        public String encrypt(String word) throws Exception {
            SecretKey secretKey = new SecretKeySpec(encryptionKey.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(word.getBytes());
            Base64.getEncoder().encodeToString(encryptedBytes);

            String encryptedValue = String.valueOf(encryptedBytes);

            EncryptMessage encryptedDataEntity = new EncryptMessage();
            encryptedDataEntity.setEncrypted(encryptedValue);
            repository.save(encryptedDataEntity);

            return encryptedValue;
        }

        public String decrypt(Long id) throws Exception {
            EncryptMessage encryptedMessage = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Encrypted data not found"));
            byte[] encrypted = encryptedMessage.getEncrypted().getBytes();
            SecretKey secretKey = new SecretKeySpec(encryptionKey.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encrypted));
            return new String(decryptedBytes);
        }
}
