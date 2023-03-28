package ufpi.engsoft2.seyfert.service.mail;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.internet.MimeMessage;
import ufpi.engsoft2.seyfert.config.mail.MailConfig;

@Service
public class MailSenderManager {
    private static final String FROM = "noreply@seyfert.com";
    @Autowired
    private MailConfig mailSenderConfig;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public Boolean enviarUnicoEmail(String destinatario, String subtitulo, String mensagem){
        try {
            JavaMailSender mailSender = mailSenderConfig.javaMailSender();
            
            SimpleMailMessage mensagemSimples = new SimpleMailMessage();
            mensagemSimples.setFrom(FROM);
            mensagemSimples.setTo(destinatario);
            mensagemSimples.setSubject(subtitulo);
            mensagemSimples.setText(mensagem);

            mailSender.send(mensagemSimples);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public Boolean enviarVariosEmails(List<String> destinatarios, String subtitulo, String mensagem){

        try {
            String destinatariosArray[] = new String[destinatarios.size()];
            for (int i = 0; i < destinatarios.size(); i++) {
                destinatariosArray[i] = destinatarios.get(i);
            }

            JavaMailSender mailSender = mailSenderConfig.javaMailSender();

            SimpleMailMessage mensagemSimples = new SimpleMailMessage();
            mensagemSimples.setFrom(FROM);
            mensagemSimples.setTo(destinatariosArray);
            mensagemSimples.setSubject(subtitulo);
            mensagemSimples.setText(mensagem);

            mailSender.send(mensagemSimples);
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public Boolean enviarTemplate(String destinatario, String subtitulo, String mensagem){
        try {
            JavaMailSender mailSender = mailSenderConfig.javaMailSender();
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8.name());

            Map<String, Object> variaveis = new HashMap<>();
            variaveis.put("name", "Járdesson Ribeiro");
            variaveis.put("especialidades", Arrays.asList("Ortopedia", "Otorrino"));

            Context contexto = new Context();
            contexto.setVariables(variaveis);
            mimeMessageHelper.setFrom(FROM);
            mimeMessageHelper.setTo(destinatario);
            mimeMessageHelper.setSubject("Especialidades disponíveis na Seyfert");
            String htmlProcessado = templateEngine.process("email-bem-vindo.html", contexto);

            mimeMessageHelper.setText(htmlProcessado, true);

            mailSender.send(mimeMessage);
            System.out.println("Email enviado com sucesso.");
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
