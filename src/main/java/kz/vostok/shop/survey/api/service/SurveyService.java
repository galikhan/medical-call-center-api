package kz.vostok.shop.survey.api.service;

import jakarta.inject.Singleton;
import kz.vostok.shop.survey.api.record.page.SurveyPage;
import kz.vostok.shop.survey.api.record.Survey;
import kz.vostok.shop.survey.api.repository.SurveyRepository;

@Singleton
public class SurveyService implements PaginationService<Survey, SurveyPage>{

    private SurveyRepository surveyRepository;

    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }



    @Override
    public SurveyPage page(int page, int size, Long reference) {
        int offset = (page > 0 ? (page - 1) * size : size);
        var limit = size;
        var total = surveyRepository.total();
        var data = surveyRepository.page(limit, offset);
        return new SurveyPage(total, data);
    }
}
