package kz.vostok.shop.survey.api.service;

import jakarta.inject.Singleton;
import kz.vostok.shop.survey.api.record.Participant;
import kz.vostok.shop.survey.api.repository.ParticipantRepository;

import java.util.List;

@Singleton
public class ParticipantService implements PaginationService<Participant>{

    private ParticipantRepository participantRepository;

    @Override
    public List<Participant> page(int page, int size, Long reference) {
        int offset = (page > 0 ? (page - 1) * size : size);
        var limit = size;

        return participantRepository.page(limit, offset);
    }
}
