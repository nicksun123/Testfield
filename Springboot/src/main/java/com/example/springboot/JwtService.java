package com.example.springboot;

import io.jsonwebtoken.Jwts;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    public String generateJwt(String clientID, String privateKeyPath, String KID, String tokenEndpoint) throws IOException {
        // Set expiry time now + 5 mins
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, 5);
        Date expiryDate = now.getTime();

//        PrivateKey privateKey = getPrivateKey(privateKeyPath);

        // Set header and payload claims. Sign with private key
        return buildJWT(clientID, tokenEndpoint, expiryDate, null, KID);
    }

    private String buildJWT(String clientID, String tokenEndpoint, Date expiryDate, PrivateKey privateKey, String KID) {
        String jwt = Jwts.builder()
                .setHeaderParam("alg", "RS512")
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("kid", KID)
                .setIssuer(clientID)
                .setSubject(clientID)
//                .setAudience(tokenEndpoint)
                .setId(UUID.randomUUID().toString())
                .setExpiration(expiryDate)
//                .signWith(privateKey, SignatureAlgorithm.RS512)
                .compact();
        return jwt;
    }

    private PrivateKey getPrivateKey(String privateKeyPath) throws IOException {
        // Use Bouncy castle openssl library
        BufferedReader reader = new BufferedReader(new FileReader(privateKeyPath));
        PEMParser parser = new PEMParser(reader);
        PEMKeyPair pemKeyPair = (PEMKeyPair) parser.readObject();
        KeyPair keyPair = new JcaPEMKeyConverter().getKeyPair(pemKeyPair);
        PrivateKey privateKey = keyPair.getPrivate();
        parser.close();
        return privateKey;
    }
}
