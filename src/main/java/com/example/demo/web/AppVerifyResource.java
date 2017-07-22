package com.example.demo.web;

import com.example.demo.dtos.facebook.subsrciption.ChangesBean;
import com.example.demo.dtos.facebook.subsrciption.SubscriptionUpdateBean;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/facebook/verify")
@Slf4j
public class AppVerifyResource {

    private final String VERIFY_TOKEN = "verify_token";

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity vefiryCallback(@RequestParam Map<String, String> verifyParams) {

        String subscriptionMode
            = verifyParams.computeIfPresent("hub.mode", (key, mode) -> mode);

        if (!subscriptionMode.equals("subscribe")) {
            return ResponseEntity.badRequest().body("Authenticate failed");
        }

        String challengeString
            = verifyParams.computeIfPresent("hub.challenge", (key, challenge) -> challenge);

        String verifyToken
            = verifyParams.computeIfPresent("hub.verify_token", (key, token) -> token);


        if (verifyToken.equals(VERIFY_TOKEN)) {
            return ResponseEntity.ok(challengeString);
        }

        return ResponseEntity.badRequest().body("Authenticate failed");
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity verifyUpdateCallback(@RequestBody SubscriptionUpdateBean updateInfo) {

        String updateSubject = updateInfo.getObject();
        updateInfo.getEntry()
            .forEach(updateItem -> {
                String changeFields = updateItem.getChanges().stream().map(ChangesBean::getField)
                    .collect(Collectors.joining(","));

                log.info("[Facebook Webhook Update] {} Type: {} Id: {}. Changes: {}",
                    updateItem.getTime(), updateSubject, updateItem.getId(), changeFields);
            });

        return ResponseEntity.ok().build();

    }


}
