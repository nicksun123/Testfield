package com.example.springboot.controller;

import com.example.springboot.service.VaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/vault")
public class VaultController {
    @Autowired
    private VaultService vaultService;

    @RequestMapping(value = "/fetch", method= RequestMethod.GET)
    public String fetchFromVault() {
        return vaultService.fetchFromVault();
    }

    @RequestMapping(value = "/store", method= RequestMethod.GET)
    public String storeIntoVault() {
        return vaultService.storeIntoVault();
    }

    @PostMapping("/read")
    private String readConfigs() {
        return vaultService.readConfigs();
    }
}
