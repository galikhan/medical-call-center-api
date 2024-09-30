package kz.vostok.shop.survey.api.service;

import jakarta.inject.Singleton;
import kz.vostok.shop.survey.api.record.Survey;
import kz.vostok.shop.survey.api.repository.SurveyRepository;

import java.util.List;

@Singleton
public class SurveyService implements PaginationService<Survey>{

    private SurveyRepository surveyRepository;

    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public List<Survey> page(int page, int size, Long reference) {
        int offset = (page > 0 ? (page - 1) * size : size);
        var limit = size;
        return surveyRepository.page(limit, offset);
    }
}
