package kz.vostok.shop.survey.api.service;

import jakarta.inject.Singleton;
import kz.vostok.shop.survey.api.record.Question;
import kz.vostok.shop.survey.api.repository.QuestionRepository;

import java.util.List;

@Singleton
public class QuestionService implements PaginationService<Question>{

    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> page(int page, int size, Long survey) {
        int offset = (page > 0 ? (page - 1) * size : size);
        var limit = size;

        return this.questionRepository.page(limit, offset, survey);
    }
}
