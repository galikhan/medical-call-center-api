package kz.vostok.shop.survey.api.service;

import jakarta.inject.Singleton;
import kz.vostok.shop.survey.api.record.Question;
import kz.vostok.shop.survey.api.record.client.SurveyQuestion;
import kz.vostok.shop.survey.api.record.page.QuestionPage;
import kz.vostok.shop.survey.api.repository.AnswerRepository;
import kz.vostok.shop.survey.api.repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class QuestionService implements PaginationService<Question, QuestionPage>{

    private static final Logger log = LoggerFactory.getLogger(QuestionService.class);
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public QuestionPage page(int page, int size, Long survey) {
        int offset = (page > 0 ? (page - 1) * size : size);
        var limit = size;

        var data = this.questionRepository.page(limit, offset, survey);
        var total = this.questionRepository.total(survey);
        return new QuestionPage(total, data);
    }

    public SurveyQuestion next(Long survey, Long id) {

        var total = this.questionRepository.total(survey);
        var optional = this.questionRepository.next(survey, id);
        if(optional.isPresent()) {
            var question = optional.get();
            var answers = this.answerRepository.findAllByQuestion(question.getId_());
            var amount = this.questionRepository.countLeftAmount(survey, question.getId_());
            return new SurveyQuestion(Question.to(question), answers, calcProgress(total, amount));
        } else {
            return SurveyQuestion.empty();
        }
    }

    public SurveyQuestion prev(Long survey, Long id) {

        var total = this.questionRepository.total(survey);
        var optional = this.questionRepository.prev(survey, id);
        if(optional.isPresent()) {
            var question = optional.get();
            var answers = this.answerRepository.findAllByQuestion(question.getId_());
            var amount = this.questionRepository.countLeftAmount(survey, question.getId_());
            return new SurveyQuestion(Question.to(question), answers, calcProgress(total, amount));
        } else {
            return SurveyQuestion.empty();
        }
    }

    private Integer calcProgress(Integer total, Integer amount) {
        var diff = total - amount;
        return diff * 100 / total;
    }

    public SurveyQuestion loadFirst(Long survey) {

        var total = this.questionRepository.total(survey);
        var optional = this.questionRepository.loadFirst(survey);
        if(optional.isPresent()) {
            var question = optional.get();
            var answers = this.answerRepository.findAllByQuestion(question.getId_());
            var amount = this.questionRepository.countLeftAmount(survey, question.getId_());
            return new SurveyQuestion(Question.to(question), answers, calcProgress(total, amount));
        } else {
            return SurveyQuestion.empty();
        }
    }
}
