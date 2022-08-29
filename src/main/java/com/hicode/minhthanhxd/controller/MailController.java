package com.hicode.minhthanhxd.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hicode.minhthanhxd.model.ContentMessage;
import com.hicode.minhthanhxd.service.MailService;

@RestController
@CrossOrigin("*")
public class MailController {
    
    @Autowired
    MailService service;
    

    @PostMapping("sendmail")
    public ResponseEntity<String> sendMail(@ModelAttribute ContentMessage cm){
        try {
            service.push(cm);
            return ResponseEntity.ok("Send mail successfully !");
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Send mail failed !");
        }

    }
}
