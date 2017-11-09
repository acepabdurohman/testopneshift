package com.acepabdurohman.testopenshift;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

@Component
public class LoginSSO {
    public HeaderSSO login(String usrId){
        HeaderSSO headerSSO = new HeaderSSO();
        try {
            String secret = "526519";
            String publicKey = "909057";
            long timeStamp = Instant.now().getEpochSecond();

            String message = publicKey + "&" + timeStamp;

            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
            mac.init(secretKey);
            String hash = Base64.encodeBase64String(mac.doFinal(message.getBytes("UTF-8")));
            headerSSO.setUsrId(usrId);
            headerSSO.setSignature(hash);
            headerSSO.setTimestamp(timeStamp);
        } catch (UnsupportedEncodingException | IllegalStateException | InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return headerSSO;
    }
}
