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
import kz.vostok.shop.survey.api.record.Survey;
import kz.vostok.shop.survey.api.record.page.SurveyPage;
import kz.vostok.shop.survey.api.repository.SurveyRepository;
import kz.vostok.shop.survey.api.service.SurveyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin
@Controller("/api/v1/survey")
@Secured(SecurityRule.IS_ANONYMOUS)
public class SurveyController {

    private Logger log = LoggerFactory.getLogger(SurveyController.class);
    private SurveyRepository surveyRepository;
    private SurveyService surveyService;

    public SurveyController(SurveyRepository surveyRepository, SurveyService surveyService) {
        this.surveyRepository = surveyRepository;
        this.surveyService = surveyService;
    }

    @Post
    public Survey create(@Body Survey survey) {
        return surveyRepository.create(survey);
    }

    @Put
    public Survey update(@Body Survey survey) {
        return surveyRepository.update(survey);
    }

    @Get("/{id}")
    public Survey findById(Long id) {
        return surveyRepository.findById(id);
    }

    @Get("/active")
    public Survey findActiveSurvey() {
        var rec = surveyRepository.findActive();
        return rec.isPresent() ? Survey.to(rec.get()) : Survey.empty();
    }

    @Get("/page/{page}/size/{size}")
    public SurveyPage findActiveSurvey(Integer page, Integer size) {
        return surveyService.page(page, size, null);
    }

    @Delete("/{id}")
    public int remove(Long id) {
        return surveyRepository.remove(id);
    }

}
