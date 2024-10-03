package kz.vostok.shop.survey.api.repository;

import jakarta.inject.Singleton;
import kz.jooq.model.tables.records.AnswerRecord;
import kz.jooq.model.tables.records.QuestionRecord;
import kz.vostok.shop.survey.api.record.Answer;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.Answer.ANSWER;
import static org.jooq.Records.mapping;

@Singleton
public class AnswerRepository {

    private DSLContext dsl;

    public AnswerRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Answer create(Answer answer) {
        return this.dsl
                .insertInto(ANSWER)
                .set(ANSWER.NAME_, answer.name())
                .set(ANSWER.QUESTION_, answer.question())
                .set(ANSWER.IS_USER_ANSWER_, answer.isUserAnswer())
                .returningResult(
                        ANSWER.ID_,
                        ANSWER.QUESTION_,
                        ANSWER.NAME_,
                        ANSWER.NEXT_QUESTION_,
                        ANSWER.IS_USER_ANSWER_,
                        ANSWER.IS_REMOVED_
                ).fetchOne(mapping(Answer::new));
    }

    public Answer update(Answer answer) {
        return this.dsl
                .update(ANSWER)
                .set(ANSWER.NAME_, answer.name())
                .set(ANSWER.QUESTION_, answer.question())
                .set(ANSWER.IS_USER_ANSWER_, answer.isUserAnswer())
                .set(ANSWER.IS_REMOVED_, answer.isRemoved())
                .where(ANSWER.ID_.eq(answer.id()))
                .returningResult(
                        ANSWER.ID_,
                        ANSWER.QUESTION_,
                        ANSWER.NAME_,
                        ANSWER.NEXT_QUESTION_,
                        ANSWER.IS_USER_ANSWER_,
                        ANSWER.IS_REMOVED_
                ).fetchOne(mapping(Answer::new));
    }

    public List<Answer> findAllByQuestion(Long question) {
        return this.dsl
                .selectFrom(ANSWER)
                .where(ANSWER.IS_REMOVED_.eq(false))
                .and(ANSWER.QUESTION_.eq(question))
                .fetch()
                .stream().map(Answer::to)
                .collect(Collectors.toUnmodifiableList());
    }


    public Optional<AnswerRecord> findRecordById(Long id) {
        return this.dsl
                .selectFrom(ANSWER).where(ANSWER.ID_.eq(id))
                .fetchOptional();
    }

    public Answer findById(Long id) {
        var record = findRecordById(id);
        return record.isPresent() ? Answer.to(record.get()) :  Answer.empty();
    }


    public int remove(Long id) {
        return this.dsl.update(ANSWER)
                .set(ANSWER.IS_REMOVED_, true)
                .where(ANSWER.ID_.eq(id)).execute();
    }

//    public List<Answer> page(int limit, int offset, Long survey) {
//        return  this.dsl
//                .selectFrom(ANSWER)
//                .where(ANSWER.IS_REMOVED_.eq(false))
//                .and(ANSWER.QUESTION_.eq(survey))
//                .orderBy(ANSWER.ID_.desc())
//                .limit(limit).offset(offset)
//                .stream()
//                .map(Answer::to)
//                .collect(Collectors.toList());
//    }
}
