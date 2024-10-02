package kz.vostok.shop.survey.api.repository;

import jakarta.inject.Singleton;
import kz.jooq.model.tables.records.SurveyRecord;
import kz.vostok.shop.survey.api.record.Survey;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.Survey.SURVEY;
import static org.jooq.Records.mapping;

@Singleton
public class SurveyRepository implements AbstractRepository<Survey, SurveyRecord>{

    private DSLContext dsl;

    public SurveyRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Survey create(Survey survey) {

        return this.dsl
                .insertInto(SURVEY)
                .set(SURVEY.NAME_, survey.name())
                .set(SURVEY.IS_ACTIVE_, survey.isActive())
                .set(SURVEY.MODE_, survey.mode())
                .returningResult(
                        SURVEY.ID_,
                        SURVEY.NAME_,
                        SURVEY.MODE_,
                        SURVEY.IS_ACTIVE_,
                        SURVEY.IS_REMOVED_
                ).fetchOne(mapping(Survey::new));
    }

    public Survey update(Survey survey) {

        return this.dsl
                .update(SURVEY)
                .set(SURVEY.NAME_, survey.name())
                .set(SURVEY.IS_ACTIVE_, survey.isActive())
                .set(SURVEY.MODE_, survey.mode())
                .set(SURVEY.IS_REMOVED_, survey.isRemoved())
                .where(SURVEY.ID_.eq(survey.id()))
                .returningResult(
                        SURVEY.ID_,
                        SURVEY.NAME_,
                        SURVEY.MODE_,
                        SURVEY.IS_ACTIVE_,
                        SURVEY.IS_REMOVED_
                ).fetchOne(mapping(Survey::new));
    }

    public List<Survey> findAll() {
        return this.dsl.selectFrom(SURVEY).where(SURVEY.IS_REMOVED_.eq(false))
                .fetch()
                .stream().map(Survey::to).collect(Collectors.toUnmodifiableList());
    }


    public Optional<SurveyRecord> findActive() {
        return this.dsl
                .selectFrom(SURVEY).where(SURVEY.IS_ACTIVE_.eq(true))
                .fetch().stream().findFirst();
    }
    
    public Optional<SurveyRecord> findRecordById(Long id) {
        return this.dsl
                .selectFrom(SURVEY).where(SURVEY.ID_.eq(id))
                .fetchOptional();
    }

    public Survey findById(Long id) {
        var record = findRecordById(id);
        return record.isPresent() ? Survey.to(record.get()) :  Survey.empty();
    }


    public int remove(Long id) {
        return this.dsl.update(SURVEY)
                .set(SURVEY.IS_REMOVED_, true)
                .where(SURVEY.ID_.eq(id)).execute();
    }

    @Override
    public int total() {
        return this.dsl
                .selectCount().from(SURVEY).where(SURVEY.IS_REMOVED_.eq(false))
                .fetchSingle().value1();
    }

    public List<Survey> page(int limit, int offset) {
        return  this.dsl
                .selectFrom(SURVEY)
                .where(SURVEY.IS_REMOVED_.eq(false))
                .orderBy(SURVEY.ID_.desc())
                .limit(limit).offset(offset)
                .stream()
                .map(Survey::to)
                .collect(Collectors.toList());
    }

}
