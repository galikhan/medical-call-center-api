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
                .set(PARTICIPANT_ANSWER.ANSWERS_, answer.answers())
                .set(PARTICIPANT_ANSWER.VALUE_, answer.value())
                .returningResult(
                        PARTICIPANT_ANSWER.ID_,
                        PARTICIPANT_ANSWER.PARTICIPANT_,
                        PARTICIPANT_ANSWER.SURVEY_,
                        PARTICIPANT_ANSWER.QUESTION_,
                        PARTICIPANT_ANSWER.ANSWER_,
                        PARTICIPANT_ANSWER.ANSWERS_,
                        PARTICIPANT_ANSWER.VALUE_
                ).fetchOne(mapping(ParticipantAnswer::new));
    }

    public ParticipantAnswer update(ParticipantAnswer answer) {
        return update(null, answer);
    }

    public ParticipantAnswer update(Long id, ParticipantAnswer answer) {
        var answerId = id != null ? id : answer.id();
        return this.dsl
                .update(PARTICIPANT_ANSWER)
                .set(PARTICIPANT_ANSWER.SURVEY_, answer.survey())
                .set(PARTICIPANT_ANSWER.QUESTION_, answer.question())
                .set(PARTICIPANT_ANSWER.ANSWER_, answer.answer())
                .set(PARTICIPANT_ANSWER.PARTICIPANT_, answer.participant())
                .set(PARTICIPANT_ANSWER.ANSWERS_, answer.answers())
                .set(PARTICIPANT_ANSWER.VALUE_, answer.value())
                .where(PARTICIPANT_ANSWER.ID_.eq(answerId))
                .returningResult(
                        PARTICIPANT_ANSWER.ID_,
                        PARTICIPANT_ANSWER.PARTICIPANT_,
                        PARTICIPANT_ANSWER.SURVEY_,
                        PARTICIPANT_ANSWER.QUESTION_,
                        PARTICIPANT_ANSWER.ANSWER_,
                        PARTICIPANT_ANSWER.ANSWERS_,
                        PARTICIPANT_ANSWER.VALUE_
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

    public Optional<ParticipantAnswerRecord> findByParams(Long participant, Long survey, Long question) {
        return this.dsl.selectFrom(PARTICIPANT_ANSWER)
                .where(PARTICIPANT_ANSWER.PARTICIPANT_.eq(participant))
                .and(PARTICIPANT_ANSWER.SURVEY_.eq(survey))
                .and(PARTICIPANT_ANSWER.QUESTION_.eq(question))
                .fetchOptional();

    }

}
