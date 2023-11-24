package com.tekteam.hash;

import com.tekteam.hash.services.Serviceimplementations.HashServiceImpl;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HashApplication {

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(HashApplication.class, args);
        HashServiceImpl hash = new HashServiceImpl();
        hash.hash();
        String encrypted = hash.encrypt("Permission");
        System.out.println("Encryted message =" + encrypted);

    }

}
