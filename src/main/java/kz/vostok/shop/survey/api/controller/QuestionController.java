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
import kz.vostok.shop.survey.api.record.Question;
import kz.vostok.shop.survey.api.record.client.SurveyQuestion;
import kz.vostok.shop.survey.api.record.page.QuestionPage;
import kz.vostok.shop.survey.api.repository.QuestionRepository;
import kz.vostok.shop.survey.api.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin
@Controller("/api/v1/survey/{survey}/question")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class QuestionController {

    private Logger log = LoggerFactory.getLogger(QuestionController.class);
    private QuestionRepository questionRepository;
    private QuestionService questionService;

    public QuestionController(QuestionRepository questionRepository, QuestionService questionService) {
        this.questionRepository = questionRepository;
        this.questionService = questionService;
    }

    @Post
    public Question create(Long survey, @Body Question question) {
        return questionRepository.create(question);
    }

    @Put
    public Question update(Long survey, @Body Question question) {
        return questionRepository.update(question);
    }

    @Get("/{id}")
    public Question findById(Long survey, Long id) {
        return questionRepository.findById(id);
    }

    @Get("/all")
    public List<Question> findAllBySurvey(Long survey) {
        return questionRepository.findAllBySurvey(survey);
    }

    @Get("/page/{page}/size/{size}")
    public QuestionPage page(Long survey, Integer page, Integer size) {
        return questionService.page(page, size, survey);
    }

    @Delete("/{id}")
    public int remove(Long id) {
        return questionRepository.remove(id);
    }

    @Get("/{id}/next")
    public SurveyQuestion next(Long survey, Long id) {
        return questionService.next(survey, id);
    }

    @Get("/{id}/prev")
    public SurveyQuestion prev(Long survey, Long id) {
        return questionService.prev(survey, id);
    }

    @Get("/load-first")
    public SurveyQuestion loadFirst(Long survey) {
        return questionService.loadFirst(survey);
    }



}
