package com.example.springboot.service;

import com.example.springboot.config.VaultConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultKeyValueOperations;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

import java.util.HashMap;
import java.util.Map;

@Service
public class VaultService {
    @Autowired
    private VaultConfig vaultConfig;
    @Autowired
    private VaultOperations operations;
    @Autowired
    private VaultTemplate vaultTemplate;

    public String fetchFromVault() {
        VaultResponse vaultResponse = vaultTemplate.read("secret/data/springvault");
        return "Data from vault --> " + vaultResponse.getData() ;
    }

    public String storeIntoVault() {
        Map<String, String> map = new HashMap<>();
        map.put("password","pwd");
        map.put("username","uname");
        VaultKeyValueOperations keyValue = vaultTemplate.opsForKeyValue(
                "secret", VaultKeyValueOperationsSupport.KeyValueBackend.versioned());
        keyValue.put("springvault", map);
        return "Data stored into vault";
    }

    public String writeSecrets(String userId, String password) {

        Map<String, String> data = new HashMap<String, String>();
        data.put("password", password);

        operations.write(userId, data);
        return "Wrote secret into vault";
    }

//    public Person readSecrets(String userId) {
//        VaultResponseSupport<Person> response = operations.read(userId, Person.class);
//        return response.getBody();
//    }

    @PostConstruct
    public String readConfigs() {
        return "Reading configuration " + vaultConfig.getToken();
    }
}
