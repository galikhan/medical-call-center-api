package kz.vostok.shop.survey.api.service;

import jakarta.inject.Singleton;
import kz.vostok.shop.survey.api.record.Participant;
import kz.vostok.shop.survey.api.record.page.ParticipantPage;
import kz.vostok.shop.survey.api.repository.ParticipantRepository;

@Singleton
public class ParticipantService implements PaginationService<Participant, ParticipantPage> {

    private ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public ParticipantPage page(int page, int size, Long reference) {
        int offset = (page > 0 ? (page - 1) * size : size);
        var limit = size;

        var total = participantRepository.total();
        var data = participantRepository.page(limit, offset);
        return new ParticipantPage(total, data);
    }
}
