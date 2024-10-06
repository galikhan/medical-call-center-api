package kz.vostok.shop.survey.api.service;

import jakarta.inject.Singleton;
import kz.vostok.shop.survey.api.record.ParticipantAnswer;
import kz.vostok.shop.survey.api.repository.DictionaryRepository;
import kz.vostok.shop.survey.api.repository.ParticipantAnswerRepository;
import kz.vostok.shop.survey.api.repository.ParticipantRepository;
import kz.vostok.shop.survey.api.repository.QuestionRepository;

import java.util.List;

@Singleton
public class ParticipantAnswerService {

    private ParticipantAnswerRepository participantAnswerRepository;
    private ParticipantRepository participantRepository;
    private DictionaryRepository dictionaryRepository;
    private QuestionRepository questionRepository;

    public ParticipantAnswerService(ParticipantAnswerRepository participantAnswerRepository, ParticipantRepository participantRepository, DictionaryRepository dictionaryRepository, QuestionRepository questionRepository) {
        this.participantAnswerRepository = participantAnswerRepository;
        this.participantRepository = participantRepository;
        this.dictionaryRepository = dictionaryRepository;
        this.questionRepository = questionRepository;
    }

    public ParticipantAnswer create(ParticipantAnswer answer) {
        return this.participantAnswerRepository.create(answer);
    }

    public ParticipantAnswer update(ParticipantAnswer answer) {
        return this.participantAnswerRepository.update(answer);
    }

    public ParticipantAnswer upsert(ParticipantAnswer answer) {

        Long questionId= answer.question();
        if(questionId != null) {
            var question = this.questionRepository.findById(questionId);
            Long categoryId = question.category();
            if(categoryId != null) {
                var category = this.dictionaryRepository.findById(categoryId);
                if(category.code().equals("email")) {
                    this.participantRepository.updateEmail(answer.participant(), answer.value());
                }
            }
        }

        var optional = this.participantAnswerRepository.findByParams(answer.participant(), answer.survey(), answer.question());
        if(optional.isPresent()) {
            var id = optional.get().getId_();
            return this.participantAnswerRepository.update(id, answer);
        } else {
            return this.participantAnswerRepository.create(answer);
        }
    }
    public ParticipantAnswer findById(Long id) {
        return this.participantAnswerRepository.findById(id);
    }

    public List<ParticipantAnswer> findByParticipant(Long participant) {
        return this.participantAnswerRepository.findByParticipant(participant);
    }

    public int remove(Long id) {
        return this.participantAnswerRepository.remove(id);
    }
}
