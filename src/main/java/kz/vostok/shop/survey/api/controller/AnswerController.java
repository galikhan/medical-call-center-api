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
import kz.vostok.shop.survey.api.record.Answer;
import kz.vostok.shop.survey.api.repository.AnswerRepository;
import kz.vostok.shop.survey.api.repository.SurveyRepository;
import kz.vostok.shop.survey.api.service.SurveyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin
@Controller("/api/v1/survey/{survey}/question/{question}/answer")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class AnswerController {

    private Logger log = LoggerFactory.getLogger(AnswerController.class);
    private AnswerRepository answerRepository;

    public AnswerController(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Post
    public Answer create(@Body Answer survey) {
        return answerRepository.create(survey);
    }

    @Put
    public Answer update(@Body Answer survey) {
        return answerRepository.update(survey);
    }

    @Get("/{id}")
    public Answer findById(Long id) {
        return answerRepository.findById(id);
    }

    @Get("/all")
    public List<Answer> findActiveSurvey(Long question) {
        return answerRepository.findAllByQuestion(question);
    }

    @Delete("/{id}")
    public int remove(Long id) {
        return answerRepository.remove(id);
    }


}
