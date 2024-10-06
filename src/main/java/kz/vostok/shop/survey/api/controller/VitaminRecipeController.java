package kz.vostok.shop.survey.api.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.vostok.shop.survey.api.repository.ParticipantRepository;
import kz.vostok.shop.survey.api.service.EmailService;

@CrossOrigin
@Controller("/api/v1/recipe")
@Secured(SecurityRule.IS_ANONYMOUS)
public class VitaminRecipeController {

    private EmailService emailService;
    private ParticipantRepository participantRepository;


    public VitaminRecipeController(EmailService emailService, ParticipantRepository participantRepository) {
        this.emailService = emailService;
        this.participantRepository = participantRepository;
    }

    @Get("/send")
    public void send() {
        this.emailService.sendWelcomeEmail("galixan@mail.ru", "galikhan.khamitov@gmail.com");
    }

    @Get("/participant/{participant}")
    public void sendRecipeEmail(Long participant) {
        this.emailService.sendWelcomeEmail("galixan@mail.ru", "galikhan.khamitov@gmail.com");
    }
}
