package com.tekteam.hash.services;

import com.tekteam.hash.entity.EncryptMessage;
import com.tekteam.hash.repositories.EncryptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Service
@RequiredArgsConstructor
public class HashServiceImpl {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    private final EncryptRepository repository;
    private String initVector = "1234567812345678";

    @Value("${encryption.key}")
    private String encryptionKey;

    public String encrypt(String word) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec skeySpec = new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8), "AES");

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

        byte[] encrypted = cipher.doFinal(word.getBytes());

        // Correctly encode the encrypted data to Base64
        String encryptedValue = Base64.getEncoder().encodeToString(encrypted);

        EncryptMessage encryptedDataEntity = new EncryptMessage();
        encryptedDataEntity.setEncrypted(encryptedValue);
        encryptedDataEntity.setInitializationVector(Base64.getEncoder().encodeToString(iv.getIV())); // Save IV to the entity
        repository.save(encryptedDataEntity);

        return encryptedValue;
    }


    public String decrypt(Long id) throws Exception {
        EncryptMessage encryptMessage = repository.findById(id).orElseThrow(() -> new RuntimeException("Encrypted data not found"));
        String encryptedWord = encryptMessage.getEncrypted();
        String initializationVectorString = encryptMessage.getInitializationVector();

        IvParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(initializationVectorString));
        SecretKeySpec skeySpec = new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8), ALGORITHM);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedWord);
        byte[] original = cipher.doFinal(encryptedBytes);

        return new String(original, StandardCharsets.UTF_8);
    }
}
