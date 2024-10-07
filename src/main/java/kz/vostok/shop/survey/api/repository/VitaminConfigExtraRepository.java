package kz.vostok.shop.survey.api.repository;

import jakarta.inject.Singleton;
import kz.jooq.model.tables.records.VitaminConfigExtraRecord;
import kz.vostok.shop.survey.api.record.VitaminConfigExtra;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.VitaminConfigExtra.VITAMIN_CONFIG_EXTRA;
import static org.jooq.Records.mapping;

@Singleton
public class VitaminConfigExtraRepository implements AbstractRepository<VitaminConfigExtra, VitaminConfigExtraRecord> {

    private DSLContext dsl;

    public VitaminConfigExtraRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public VitaminConfigExtra create(VitaminConfigExtra extraConfig) {
        return this.dsl
                .insertInto(VITAMIN_CONFIG_EXTRA)
                .set(VITAMIN_CONFIG_EXTRA.VITAMIN_CONFIG_, extraConfig.vitaminConfig())
                .set(VITAMIN_CONFIG_EXTRA.QUESTION_, extraConfig.question())
                .set(VITAMIN_CONFIG_EXTRA.ANSWER_, extraConfig.answer())
                .set(VITAMIN_CONFIG_EXTRA.ANSWERS_, extraConfig.answers())
                .set(VITAMIN_CONFIG_EXTRA.MIN_, extraConfig.min())
                .set(VITAMIN_CONFIG_EXTRA.MAX_, extraConfig.max())
                .returningResult(
                        VITAMIN_CONFIG_EXTRA.ID_,
                        VITAMIN_CONFIG_EXTRA.VITAMIN_CONFIG_,
                        VITAMIN_CONFIG_EXTRA.QUESTION_,
                        VITAMIN_CONFIG_EXTRA.ANSWER_,
                        VITAMIN_CONFIG_EXTRA.IS_REMOVED_,
                        VITAMIN_CONFIG_EXTRA.MIN_,
                        VITAMIN_CONFIG_EXTRA.MAX_,
                        VITAMIN_CONFIG_EXTRA.ANSWERS_
                ).fetchOne(mapping(VitaminConfigExtra::new));

    }

    @Override
    public VitaminConfigExtra update(VitaminConfigExtra extraConfig) {
        return this.dsl
                .update(VITAMIN_CONFIG_EXTRA)
                .set(VITAMIN_CONFIG_EXTRA.VITAMIN_CONFIG_, extraConfig.vitaminConfig())
                .set(VITAMIN_CONFIG_EXTRA.QUESTION_, extraConfig.question())
                .set(VITAMIN_CONFIG_EXTRA.ANSWER_, extraConfig.answer())
                .set(VITAMIN_CONFIG_EXTRA.IS_REMOVED_, extraConfig.isRemoved())
                .set(VITAMIN_CONFIG_EXTRA.ANSWERS_, extraConfig.answers())
                .set(VITAMIN_CONFIG_EXTRA.MIN_, extraConfig.min())
                .set(VITAMIN_CONFIG_EXTRA.MAX_, extraConfig.max())
                .where(VITAMIN_CONFIG_EXTRA.ID_.eq(extraConfig.id()))
                .returningResult(
                        VITAMIN_CONFIG_EXTRA.ID_,
                        VITAMIN_CONFIG_EXTRA.VITAMIN_CONFIG_,
                        VITAMIN_CONFIG_EXTRA.QUESTION_,
                        VITAMIN_CONFIG_EXTRA.ANSWER_,
                        VITAMIN_CONFIG_EXTRA.IS_REMOVED_,
                        VITAMIN_CONFIG_EXTRA.MIN_,
                        VITAMIN_CONFIG_EXTRA.MAX_,
                        VITAMIN_CONFIG_EXTRA.ANSWERS_

                ).fetchOne(mapping(VitaminConfigExtra::new));
    }

    @Override
    public List<VitaminConfigExtra> findAll() {
        return List.of();
    }

    public List<VitaminConfigExtra> findByConfigId(Long id) {
        return this.dsl
                .selectFrom(VITAMIN_CONFIG_EXTRA)
                .where(VITAMIN_CONFIG_EXTRA.VITAMIN_CONFIG_.eq(id))
                .fetch().stream().map(VitaminConfigExtra::to)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Optional<VitaminConfigExtraRecord> findRecordById(Long id) {
        return this.dsl
                .selectFrom(VITAMIN_CONFIG_EXTRA)
                .where(VITAMIN_CONFIG_EXTRA.ID_.eq(id))
                .fetchOptional();
    }

    @Override
    public VitaminConfigExtra findById(Long id) {
        var rec = findRecordById(id);
        return rec.isPresent() ? VitaminConfigExtra.to(rec.get()) : VitaminConfigExtra.empty();
    }

    @Override
    public int remove(Long id) {
        return this.dsl.update(VITAMIN_CONFIG_EXTRA)
                .set(VITAMIN_CONFIG_EXTRA.IS_REMOVED_, true)
                .where(VITAMIN_CONFIG_EXTRA.ID_.eq(id)).execute();
    }

    @Override
    public int total() {
        return 0;
    }
}
