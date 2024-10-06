package kz.vostok.shop.survey.api.service;

import io.micronaut.email.Email;
import io.micronaut.email.EmailSender;
import io.micronaut.email.MultipartBody;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class EmailService {

    private Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final EmailSender<?, ?> emailSender;

    public EmailService(EmailSender<?, ?> emailSender) {
        this.emailSender = emailSender;
    }

    public void sendWelcomeEmail(String from, String to) {
        emailSender.send(Email.builder()
                .from(from)
                .to(to)
                .subject("Micronaut test")
                .body(new MultipartBody("<html><body><strong>Hello</strong> dear Micronaut user.</body></html>", "Hello dear Micronaut user")));
    }

    public boolean sendForgotPasswordCode(String from, String to, String code) {
        emailSender.send(Email.builder()
                .from(from)
                .to(to)
//                .subject("Код для обновления пароля")
                .subject("Құпия сөзді жаңарту коды (steam.kz сайты)")
                .body(new MultipartBody(""
                        + "<html>"
                        +   "<body>"
                        +       "<strong>Саламатсыз</strong> <br> "
//                        +       "<strong>Добрый день</strong> <br> "
                        +       "Сіздің құпия сөзді жаңарту коды: <strong>" + code + "</strong>"
                        +   "</body>"
                        + "</html>", "Құпия сөзді жаңарту коды")));

        logger.info("Password reset code sent successfully!");
        return true;
    }
}