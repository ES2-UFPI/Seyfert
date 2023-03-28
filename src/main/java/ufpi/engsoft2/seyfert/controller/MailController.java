package ufpi.engsoft2.seyfert.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ufpi.engsoft2.seyfert.service.mail.MailSenderManager;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailSenderManager mailSenderManager;

    @PostMapping("/enviar")
    public ResponseEntity<Void> enviarEmailUnico(){
        mailSenderManager.enviarUnicoEmail("jardessonrs1117@gmail.com", "Teste", "Bem-Vindo ao seyfert");
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/enviar-emails")
    public ResponseEntity<Void> enviarVariosEmails(){
        mailSenderManager.enviarVariosEmails(Arrays.asList("jardessonrs1117@gmail.com", "agulhars999@gmail.com"), "Teste", "Bem-Vindo ao <strong>Seyfert</strong>");
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/enviar-email-template")
    public ResponseEntity<Void> enviarEmailTemplate(){
        mailSenderManager.enviarTemplate("jardessonrs1117@gmail.com", "Bem-Vindo a Seyfert", "Bem-Vindo ao <strong>Seyfert</strong>");
        return ResponseEntity.noContent().build();
    }
    
}
