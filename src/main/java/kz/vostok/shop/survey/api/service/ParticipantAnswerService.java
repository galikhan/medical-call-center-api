package kz.vostok.shop.survey.api.service;

import jakarta.inject.Singleton;
import kz.vostok.shop.survey.api.record.ParticipantAnswer;
import kz.vostok.shop.survey.api.repository.ParticipantAnswerRepository;

import java.util.List;

@Singleton
public class ParticipantAnswerService {

    private ParticipantAnswerRepository participantAnswerRepository;


    public ParticipantAnswerService(ParticipantAnswerRepository participantAnswerRepository) {
        this.participantAnswerRepository = participantAnswerRepository;
    }

    public ParticipantAnswer create(ParticipantAnswer answer) {
        return this.participantAnswerRepository.create(answer);
    }

    public ParticipantAnswer update(ParticipantAnswer answer) {
        return this.participantAnswerRepository.update(answer);
    }

    public ParticipantAnswer upsert(ParticipantAnswer answer) {
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
