package kz.vostok.shop.survey.api.repository;

import kz.jooq.model.tables.records.QuestionRecord;
import kz.jooq.model.tables.records.SurveyRecord;
import kz.vostok.shop.survey.api.record.Question;
import kz.vostok.shop.survey.api.record.Survey;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.Question.QUESTION;
import static org.jooq.Records.mapping;

public class QuestionRepository {

    private DSLContext dsl;

    public QuestionRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Question create(Question question) {
        return this.dsl
                .insertInto(QUESTION)
                .set(QUESTION.NAME_, question.name())
                .set(QUESTION.SURVEY_, question.survey())
                .set(QUESTION.TYPE_, question.type())
                .returningResult(
                        QUESTION.ID_,
                        QUESTION.NAME_,
                        QUESTION.SURVEY_,
                        QUESTION.TYPE_,
                        QUESTION.PREV_QUESTION_,
                        QUESTION.NEXT_QUESTION_,
                        QUESTION.IS_REMOVED_
                ).fetchOne(mapping(Question::new));
    }

    public Question update(Question question) {
        return this.dsl
                .update(QUESTION)
                .set(QUESTION.NAME_, question.name())
                .set(QUESTION.SURVEY_, question.survey())
                .set(QUESTION.TYPE_, question.type())
                .set(QUESTION.PREV_QUESTION_, question.prevQuestion())
                .set(QUESTION.NEXT_QUESTION_, question.nextQuestion())
                .set(QUESTION.IS_REMOVED_, question.isRemoved())
                .where(QUESTION.ID_.eq(question.id()))
                .returningResult(
                        QUESTION.ID_,
                        QUESTION.NAME_,
                        QUESTION.SURVEY_,
                        QUESTION.TYPE_,
                        QUESTION.PREV_QUESTION_,
                        QUESTION.NEXT_QUESTION_,
                        QUESTION.IS_REMOVED_
                ).fetchOne(mapping(Question::new));
    }

    public List<Question> findAllBySurvey(Long survey) {
        return this.dsl.selectFrom(QUESTION)
                .where(QUESTION.IS_REMOVED_.eq(false))
                .and(QUESTION.SURVEY_.eq(survey))
                .fetch()
                .stream().map(Question::to)
                .collect(Collectors.toUnmodifiableList());
    }


    public Optional<QuestionRecord> findRecordById(Long id) {
        return this.dsl
                .selectFrom(QUESTION).where(QUESTION.ID_.eq(id))
                .fetchOptional();
    }

    public Question findById(Long id) {
        var record = findRecordById(id);
        return record.isPresent() ? Question.to(record.get()) :  Question.empty();
    }


    public int remove(Long id) {
        return this.dsl.update(QUESTION)
                .set(QUESTION.IS_REMOVED_, true)
                .where(QUESTION.ID_.eq(id)).execute();
    }

    public List<Question> page(int limit, int offset, Long survey) {
        return  this.dsl
                .selectFrom(QUESTION)
                .where(QUESTION.IS_REMOVED_.eq(false))
                .and(QUESTION.SURVEY_.eq(survey))
                .orderBy(QUESTION.ID_.desc())
                .limit(limit).offset(offset)
                .stream()
                .map(Question::to)
                .collect(Collectors.toList());
    }
}
