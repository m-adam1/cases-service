package net.techmentor.cases_service.payment.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class PaymentController {

    @GetMapping("/payments")
    public ResponseEntity<?> handle() {

        Logger.getAnonymousLogger().log(Level.INFO, "hello from payment");

        return ResponseEntity.ok().build();
    }
}
