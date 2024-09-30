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
    public Question create(@Body Question survey) {
        return questionRepository.create(survey);
    }

    @Put
    public Question update(@Body Question survey) {
        return questionRepository.update(survey);
    }

    @Get("/{id}")
    public Question findById(Long id) {
        return questionRepository.findById(id);
    }

    @Get("/all")
    public List<Question> findAllBySurvey(Long id) {
        return questionRepository.findAllBySurvey(id);
    }

    @Get("/page/{page}/size/{size}")
    public List<Question> page(Long survey, Integer page, Integer size) {
        return questionService.page(page, size, survey);
    }

    @Delete("/{id}")
    public int remove(Long id) {
        return questionRepository.remove(id);
    }



}
