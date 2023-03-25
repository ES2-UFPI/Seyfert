package ufpi.engsoft2.seyfert.config.mail;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class MailConfig {

    @Value("${mail.host}")
    private String  host;

    @Value("${mail.port}")
    private String port;

    @Value("${mail.email.username}")
    private String  emailSenderName;

    @Value("${mail.password}")
    private String  passwordEmailSender;

    @Value("${mail.transport.protocol}")
    private String  mailTransportProtocol;

    @Value("${mail.smtp.auth}")
    private String  smtpAuht;

    @Value("${mail.start.ttls.enable}")
    private String  startTlsEnable;

    @Value("${mail.mode.debug}")
    private String  modeDebug;
    
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(Integer.parseInt(port));

        mailSender.setUsername(emailSenderName);
        mailSender.setPassword(passwordEmailSender);

        Properties props = new Properties();
        props.put("mail.transport.protocol", mailTransportProtocol);
        props.put("mail.smtp.auth", smtpAuht);
        props.put("mail.smtp.starttls.enable", startTlsEnable);
        props.put("mail.debug", modeDebug);
        props.put("mail.username", emailSenderName);
        props.put("mail.password", passwordEmailSender);

        mailSender.setJavaMailProperties(props);

        return mailSender;
    }
}
