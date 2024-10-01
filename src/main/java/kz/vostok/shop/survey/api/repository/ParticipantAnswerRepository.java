package kz.vostok.shop.survey.api.repository;

import jakarta.inject.Singleton;
import kz.jooq.model.tables.records.ParticipantAnswerRecord;
import kz.vostok.shop.survey.api.record.ParticipantAnswer;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.ParticipantAnswer.PARTICIPANT_ANSWER;
import static org.jooq.Records.mapping;

@Singleton
public class ParticipantAnswerRepository implements AbstractRepository<ParticipantAnswer, ParticipantAnswerRecord> {

    private DSLContext dsl;

    public ParticipantAnswerRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public ParticipantAnswer create(ParticipantAnswer answer) {
        return this.dsl
                .insertInto(PARTICIPANT_ANSWER)
                .set(PARTICIPANT_ANSWER.SURVEY_, answer.survey())
                .set(PARTICIPANT_ANSWER.QUESTION_, answer.question())
                .set(PARTICIPANT_ANSWER.ANSWER_, answer.answer())
                .set(PARTICIPANT_ANSWER.PARTICIPANT_, answer.participant())
                .returningResult(
                        PARTICIPANT_ANSWER.ID_,
                        PARTICIPANT_ANSWER.PARTICIPANT_,
                        PARTICIPANT_ANSWER.SURVEY_,
                        PARTICIPANT_ANSWER.QUESTION_,
                        PARTICIPANT_ANSWER.ANSWER_
                ).fetchOne(mapping(ParticipantAnswer::new));
    }

    @Override
    public ParticipantAnswer update(ParticipantAnswer answer) {
        return this.dsl
                .update(PARTICIPANT_ANSWER)
                .set(PARTICIPANT_ANSWER.SURVEY_, answer.survey())
                .set(PARTICIPANT_ANSWER.QUESTION_, answer.question())
                .set(PARTICIPANT_ANSWER.ANSWER_, answer.answer())
                .set(PARTICIPANT_ANSWER.PARTICIPANT_, answer.participant())
                .where(PARTICIPANT_ANSWER.ID_.eq(answer.id()))
                .returningResult(
                        PARTICIPANT_ANSWER.ID_,
                        PARTICIPANT_ANSWER.PARTICIPANT_,
                        PARTICIPANT_ANSWER.SURVEY_,
                        PARTICIPANT_ANSWER.QUESTION_,
                        PARTICIPANT_ANSWER.ANSWER_
                ).fetchOne(mapping(ParticipantAnswer::new));
    }

    @Override
    public List<ParticipantAnswer> findAll() {
        return List.of();
    }

    public List<ParticipantAnswer> findByParticipant(Long id) {
        return this.dsl.selectFrom(PARTICIPANT_ANSWER)
                .where(PARTICIPANT_ANSWER.PARTICIPANT_.eq(id))
                .fetch().stream().map(ParticipantAnswer::to)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Optional<ParticipantAnswerRecord> findRecordById(Long id) {
        return this.dsl.selectFrom(PARTICIPANT_ANSWER).where(PARTICIPANT_ANSWER.ID_.eq(id)).fetchOptional();
    }

    @Override
    public ParticipantAnswer findById(Long id) {
        var record = findRecordById(id);
        return record.isPresent() ? ParticipantAnswer.to(record.get()) : ParticipantAnswer.empty();
    }

    @Override
    public int remove(Long id) {
        return 0;
    }

    @Override
    public int total() {
        return 0;
    }
}
