package kz.vostok.shop.survey.api.controller;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.vostok.shop.survey.api.record.Participant;
import kz.vostok.shop.survey.api.record.Question;
import kz.vostok.shop.survey.api.record.page.ParticipantPage;
import kz.vostok.shop.survey.api.repository.AnswerRepository;
import kz.vostok.shop.survey.api.repository.ParticipantRepository;
import kz.vostok.shop.survey.api.service.ParticipantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin
@Controller("/api/v1/participant")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class ParticipantController {

    private Logger log = LoggerFactory.getLogger(ParticipantController.class);
    private ParticipantRepository participantRepository;
    private ParticipantService participantService;

    public ParticipantController(ParticipantRepository participantRepository, ParticipantService participantService) {
        this.participantRepository = participantRepository;
        this.participantService = participantService;
    }

    @Post
    public Participant create(@Body Participant survey) {
        return participantRepository.create(survey);
    }

    @Put
    public Participant update(@Body Participant survey) {
        return participantRepository.update(survey);
    }

    @Get("/{id}")
    public Participant findById(Long id) {
        return participantRepository.findById(id);
    }

    @Get("/page/{page}/size/{size}")
    public ParticipantPage page(Integer page, Integer size) {
        return participantService.page(page, size, null);
    }

    @Delete("/{id}")
    public int remove(Long id) {
        return participantRepository.remove(id);
    }


}
