package kz.vostok.shop.survey.api.repository;

import jakarta.inject.Singleton;
import kz.jooq.model.enums.QuestionType;
import kz.jooq.model.tables.records.DictionaryRecord;
import kz.jooq.model.tables.records.VitaminConfigParamRecord;
import kz.vostok.shop.survey.api.record.ParticipantAnswer;
import kz.vostok.shop.survey.api.record.Question;
import kz.vostok.shop.survey.api.record.VitaminConfigParam;
import org.jooq.DSLContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.VitaminConfigParam.VITAMIN_CONFIG_PARAM;
import static org.jooq.Records.mapping;

@Singleton
public class VitaminConfigParamRepository implements AbstractRepository<VitaminConfigParam, VitaminConfigParamRecord> {

    private DSLContext dsl;

    public VitaminConfigParamRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public VitaminConfigParam create(VitaminConfigParam extraConfig) {
        return this.dsl
                .insertInto(VITAMIN_CONFIG_PARAM)
                .set(VITAMIN_CONFIG_PARAM.VITAMIN_CONFIG_, extraConfig.vitaminConfig())
                .set(VITAMIN_CONFIG_PARAM.QUESTION_, extraConfig.question())
                .set(VITAMIN_CONFIG_PARAM.ANSWER_, extraConfig.answer())
                .set(VITAMIN_CONFIG_PARAM.ANSWERS_, extraConfig.answers())
                .set(VITAMIN_CONFIG_PARAM.MIN_, extraConfig.min())
                .set(VITAMIN_CONFIG_PARAM.MAX_, extraConfig.max())
                .returningResult(
                        VITAMIN_CONFIG_PARAM.ID_,
                        VITAMIN_CONFIG_PARAM.VITAMIN_CONFIG_,
                        VITAMIN_CONFIG_PARAM.QUESTION_,
                        VITAMIN_CONFIG_PARAM.ANSWER_,
                        VITAMIN_CONFIG_PARAM.IS_REMOVED_,
                        VITAMIN_CONFIG_PARAM.MIN_,
                        VITAMIN_CONFIG_PARAM.MAX_,
                        VITAMIN_CONFIG_PARAM.ANSWERS_
                ).fetchOne(mapping(VitaminConfigParam::new));

    }

    @Override
    public VitaminConfigParam update(VitaminConfigParam extraConfig) {
        return this.dsl
                .update(VITAMIN_CONFIG_PARAM)
                .set(VITAMIN_CONFIG_PARAM.VITAMIN_CONFIG_, extraConfig.vitaminConfig())
                .set(VITAMIN_CONFIG_PARAM.QUESTION_, extraConfig.question())
                .set(VITAMIN_CONFIG_PARAM.ANSWER_, extraConfig.answer())
                .set(VITAMIN_CONFIG_PARAM.IS_REMOVED_, extraConfig.isRemoved())
                .set(VITAMIN_CONFIG_PARAM.ANSWERS_, extraConfig.answers())
                .set(VITAMIN_CONFIG_PARAM.MIN_, extraConfig.min())
                .set(VITAMIN_CONFIG_PARAM.MAX_, extraConfig.max())
                .where(VITAMIN_CONFIG_PARAM.ID_.eq(extraConfig.id()))
                .returningResult(
                        VITAMIN_CONFIG_PARAM.ID_,
                        VITAMIN_CONFIG_PARAM.VITAMIN_CONFIG_,
                        VITAMIN_CONFIG_PARAM.QUESTION_,
                        VITAMIN_CONFIG_PARAM.ANSWER_,
                        VITAMIN_CONFIG_PARAM.IS_REMOVED_,
                        VITAMIN_CONFIG_PARAM.MIN_,
                        VITAMIN_CONFIG_PARAM.MAX_,
                        VITAMIN_CONFIG_PARAM.ANSWERS_

                ).fetchOne(mapping(VitaminConfigParam::new));
    }

    @Override
    public List<VitaminConfigParam> findAll() {
        return List.of();
    }

    public List<VitaminConfigParam> findByConfigId(Long id) {
        return this.dsl
                .selectFrom(VITAMIN_CONFIG_PARAM)
                .where(VITAMIN_CONFIG_PARAM.VITAMIN_CONFIG_.eq(id))
                .fetch().stream().map(VitaminConfigParam::to)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Optional<VitaminConfigParamRecord> findRecordById(Long id) {
        return this.dsl
                .selectFrom(VITAMIN_CONFIG_PARAM)
                .where(VITAMIN_CONFIG_PARAM.ID_.eq(id))
                .fetchOptional();
    }

    @Override
    public VitaminConfigParam findById(Long id) {
        var rec = findRecordById(id);
        return rec.isPresent() ? VitaminConfigParam.to(rec.get()) : VitaminConfigParam.empty();
    }

    @Override
    public int remove(Long id) {
        return this.dsl.update(VITAMIN_CONFIG_PARAM)
                .set(VITAMIN_CONFIG_PARAM.IS_REMOVED_, true)
                .where(VITAMIN_CONFIG_PARAM.ID_.eq(id)).execute();
    }

    @Override
    public int total() {
        return 0;
    }

    public Set<Long> findConfigsThatMeetsAnswer(List<Long> vitaminConfigIds, Optional<DictionaryRecord> optionalCategory, ParticipantAnswer answer, Question question) {
        String code = null;
        if(optionalCategory.isPresent()) {
            code = optionalCategory.get().getCode_();
        }

        var query = this.dsl
                .select(VITAMIN_CONFIG_PARAM.VITAMIN_CONFIG_).from(VITAMIN_CONFIG_PARAM)
                .where(VITAMIN_CONFIG_PARAM.VITAMIN_CONFIG_.in(vitaminConfigIds))
                .and(VITAMIN_CONFIG_PARAM.QUESTION_.eq(answer.question()));


//        if(code.equals("birthdate")) {
//
//        }

        if(question.type().equals(QuestionType.random_number)) {
            var number = Integer.parseInt(answer.value());
//            System.out.println("number : " + number);
            query.and(VITAMIN_CONFIG_PARAM.MIN_.le(number))
                    .and(VITAMIN_CONFIG_PARAM.MAX_.ge(number));

        } else if(question.type().equals(QuestionType.date)) {

            String stringDate = answer.value();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy", Locale.ENGLISH);
            var date = LocalDate.parse(stringDate, formatter);
            System.out.println("date formatted: " + date);
            var age = LocalDate.now().getYear() - date.getYear();

            query.and(VITAMIN_CONFIG_PARAM.MIN_.le(age))
                    .and(VITAMIN_CONFIG_PARAM.MAX_.ge(age));

        } else if(question.type().equals(QuestionType.single_choice)) {
            Long id = Long.parseLong(answer.value());
            query.and(VITAMIN_CONFIG_PARAM.ANSWER_.eq(id));
        } else if(question.type().equals(QuestionType.multiple_choice)) {
            query.and(VITAMIN_CONFIG_PARAM.ANSWERS_.eq(answer.answers()));
        }
//        System.out.println("----------start ----------");
//        System.out.println(query.getSQL());
//        System.out.println("----------end ----------");
        return query.fetch().stream().map(item-> item.value1())
                .collect(Collectors.toSet());
    }
}
