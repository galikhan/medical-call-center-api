package kz.vostok.shop.survey.api.repository;

import jakarta.inject.Singleton;
import kz.jooq.model.tables.records.ParticipantRecord;
import kz.vostok.shop.survey.api.record.Participant;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.Participant.PARTICIPANT;
import static org.jooq.Records.mapping;

@Singleton
public class ParticipantRepository implements AbstractRepository<Participant, ParticipantRecord> {

    private DSLContext dsl;

    public ParticipantRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Participant create(Participant participant) {
        return this.dsl
                .insertInto(PARTICIPANT)
                .set(PARTICIPANT.FIRSTNAME_, participant.firstname())
                .returningResult(
                        PARTICIPANT.ID_,
                        PARTICIPANT.FIRSTNAME_,
                        PARTICIPANT.EMAIL_,
                        PARTICIPANT.HEIGHT_,
                        PARTICIPANT.WEIGHT_,
                        PARTICIPANT.AGE_,
                        PARTICIPANT.GENDER_,
                        PARTICIPANT.AIM_,
                        PARTICIPANT.CITY_,
                        PARTICIPANT.ALLERGY_
                ).fetchOne(mapping(Participant::new));
    }

    @Override
    public Participant update(Participant participant) {
        return this.dsl
                .update(PARTICIPANT)
                .set(PARTICIPANT.FIRSTNAME_, participant.firstname())
                .set(PARTICIPANT.EMAIL_, participant.email())
                .set(PARTICIPANT.HEIGHT_, participant.height())
                .set(PARTICIPANT.WEIGHT_, participant.weight())
                .set(PARTICIPANT.AGE_, participant.age())
                .set(PARTICIPANT.GENDER_, participant.gender())
                .set(PARTICIPANT.AIM_, participant.aim())
                .set(PARTICIPANT.CITY_, participant.city())
                .set(PARTICIPANT.ALLERGY_, participant.allergy())
                .where(PARTICIPANT.ID_.eq(participant.id()))
                .returningResult(
                        PARTICIPANT.ID_,
                        PARTICIPANT.FIRSTNAME_,
                        PARTICIPANT.EMAIL_,
                        PARTICIPANT.HEIGHT_,
                        PARTICIPANT.WEIGHT_,
                        PARTICIPANT.AGE_,
                        PARTICIPANT.GENDER_,
                        PARTICIPANT.AIM_,
                        PARTICIPANT.CITY_,
                        PARTICIPANT.ALLERGY_
                ).fetchOne(mapping(Participant::new));
    }

    @Override
    public List<Participant> findAll() {
        return List.of();
    }

    @Override
    public Optional<ParticipantRecord> findRecordById(Long id) {
        return this.dsl
                .selectFrom(PARTICIPANT).where(PARTICIPANT.ID_.eq(id))
                .fetchOptional();
    }

    @Override
    public Participant findById(Long id) {
        var record = findRecordById(id);
        return record.isPresent() ? Participant.to(record.get()) : Participant.empty();
    }

    @Override
    public int remove(Long id) {
        return 0;
    }

    public List<Participant> page(int limit, int offset) {
        return this.dsl
                .selectFrom(PARTICIPANT)
                .orderBy(PARTICIPANT.ID_.desc())
                .limit(limit).offset(offset)
                .stream()
                .map(Participant::to)
                .collect(Collectors.toList());
    }
}