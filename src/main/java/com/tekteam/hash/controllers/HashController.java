package com.tekteam.hash.controllers;

import com.tekteam.hash.services.HashServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class HashController {

    private final HashServiceImpl hashService;

    @PostMapping("/encrypt")
    public String encrypt(@RequestBody String word) throws Exception {
        return hashService.encrypt(word);
    }

    @GetMapping ("/decrypt")
    public String decrypt(@Param("id") Long id) throws Exception {
        return hashService.decrypt(id);
    }
}
