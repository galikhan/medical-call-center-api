package kz.vostok.shop.survey.api.service;

import jakarta.inject.Singleton;
import kz.vostok.shop.survey.api.record.Answer;
import kz.vostok.shop.survey.api.record.Vitamin;
import kz.vostok.shop.survey.api.record.page.VitaminPage;
import kz.vostok.shop.survey.api.repository.AnswerRepository;
import kz.vostok.shop.survey.api.repository.VitaminRepository;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class AnswerService {

    private AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answer> upsertAll(List<Answer> answers) {
        List<Answer> list = new ArrayList<>();
        answers.stream().forEach(answer -> {
            if(answer.id() != null) {
                list.add(this.answerRepository.update(answer));
            } else {
                list.add(this.answerRepository.create(answer));
            }
        });
        return list;
    }

}
