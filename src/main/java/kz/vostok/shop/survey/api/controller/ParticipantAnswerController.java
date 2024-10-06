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
import kz.vostok.shop.survey.api.record.ParticipantAnswer;
import kz.vostok.shop.survey.api.repository.AnswerRepository;
import kz.vostok.shop.survey.api.repository.ParticipantAnswerRepository;
import kz.vostok.shop.survey.api.service.ParticipantAnswerService;
import kz.vostok.shop.survey.api.service.ParticipantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin
@Controller("/api/v1/participant/{participant}/answer")
@Secured(SecurityRule.IS_ANONYMOUS)
public class ParticipantAnswerController {

    private Logger log = LoggerFactory.getLogger(ParticipantAnswerController.class);

    private ParticipantAnswerService participantAnswerService;

    public ParticipantAnswerController(ParticipantAnswerService participantAnswerService) {
        this.participantAnswerService = participantAnswerService;
    }

    @Post
    public ParticipantAnswer create(@Body ParticipantAnswer survey) {
        return participantAnswerService.create(survey);
    }

    @Put
    public ParticipantAnswer update(@Body ParticipantAnswer survey) {
        return participantAnswerService.update(survey);
    }

    @Post("/upsert")
    public ParticipantAnswer upsert(@Body ParticipantAnswer survey) {
        return participantAnswerService.upsert(survey);
    }

    @Get("/{id}")
    public ParticipantAnswer findById(Long id) {
        return participantAnswerService.findById(id);
    }

    @Get("/all")
    public List<ParticipantAnswer> findByParticipant(Long participant) {
        return participantAnswerService.findByParticipant(participant);
    }

    @Delete("/{id}")
    public int remove(Long id) {
        return participantAnswerService.remove(id);
    }


}
