package kz.vostok.shop.survey.api.repository;

import jakarta.inject.Singleton;
import kz.jooq.model.tables.records.VitaminConfigRecord;
import kz.vostok.shop.survey.api.record.VitaminConfig;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.VitaminConfig.VITAMIN_CONFIG;
import static org.jooq.Records.mapping;

@Singleton
public class VitaminConfigRepository implements AbstractRepository<VitaminConfig, VitaminConfigRecord> {

    private DSLContext dsl;

    public VitaminConfigRepository(DSLContext dsl) {
        this.dsl = dsl;
    }


    @Override
    public VitaminConfig create(VitaminConfig config) {
        return this.dsl
                .insertInto(VITAMIN_CONFIG)
                .set(VITAMIN_CONFIG.NAME_, config.name())
                .set(VITAMIN_CONFIG.DESCRIPTION_, config.description())
                .set(VITAMIN_CONFIG.HEIGHT_MIN_, config.heightMin())
                .set(VITAMIN_CONFIG.HEIGHT_MAX_, config.heightMax())
                .set(VITAMIN_CONFIG.WEIGHT_MIN_, config.weightMin())
                .set(VITAMIN_CONFIG.WEIGHT_MIN_, config.weightMin())
                .set(VITAMIN_CONFIG.GENDER_, config.gender())
                .set(VITAMIN_CONFIG.AGE_MIN_, config.ageMin())
                .set(VITAMIN_CONFIG.AGE_MAX_, config.ageMax())
                .set(VITAMIN_CONFIG.CITY_, config.city())
                .set(VITAMIN_CONFIG.AIM_, config.aim())
                .set(VITAMIN_CONFIG.ALLERGY_, config.allergy())
                .set(VITAMIN_CONFIG.VITAMINS_, config.vitamins())
                .set(VITAMIN_CONFIG.LINK_, config.link())
                .returningResult(
                        VITAMIN_CONFIG.ID_,
                        VITAMIN_CONFIG.NAME_,
                        VITAMIN_CONFIG.DESCRIPTION_,
                        VITAMIN_CONFIG.HEIGHT_MIN_,
                        VITAMIN_CONFIG.HEIGHT_MAX_,
                        VITAMIN_CONFIG.WEIGHT_MIN_,
                        VITAMIN_CONFIG.WEIGHT_MIN_,
                        VITAMIN_CONFIG.GENDER_,
                        VITAMIN_CONFIG.AGE_MIN_,
                        VITAMIN_CONFIG.AGE_MAX_,
                        VITAMIN_CONFIG.CITY_,
                        VITAMIN_CONFIG.AIM_,
                        VITAMIN_CONFIG.ALLERGY_,
                        VITAMIN_CONFIG.VITAMINS_,
                        VITAMIN_CONFIG.IS_REMOVED_,
                        VITAMIN_CONFIG.LINK_
                ).fetchOne(mapping(VitaminConfig::new));
    }

    @Override
    public VitaminConfig update(VitaminConfig config) {
        return this.dsl
                .update(VITAMIN_CONFIG)
                .set(VITAMIN_CONFIG.NAME_, config.name())
                .set(VITAMIN_CONFIG.DESCRIPTION_, config.description())
                .set(VITAMIN_CONFIG.HEIGHT_MIN_, config.heightMin())
                .set(VITAMIN_CONFIG.HEIGHT_MAX_, config.heightMax())
                .set(VITAMIN_CONFIG.WEIGHT_MIN_, config.weightMin())
                .set(VITAMIN_CONFIG.WEIGHT_MIN_, config.weightMin())
                .set(VITAMIN_CONFIG.GENDER_, config.gender())
                .set(VITAMIN_CONFIG.AGE_MIN_, config.ageMin())
                .set(VITAMIN_CONFIG.AGE_MAX_, config.ageMax())
                .set(VITAMIN_CONFIG.CITY_, config.city())
                .set(VITAMIN_CONFIG.AIM_, config.aim())
                .set(VITAMIN_CONFIG.ALLERGY_, config.allergy())
                .set(VITAMIN_CONFIG.VITAMINS_, config.vitamins())
                .set(VITAMIN_CONFIG.LINK_, config.link())
                .where(VITAMIN_CONFIG.ID_.eq(config.id()))
                .returningResult(
                        VITAMIN_CONFIG.ID_,
                        VITAMIN_CONFIG.NAME_,
                        VITAMIN_CONFIG.DESCRIPTION_,
                        VITAMIN_CONFIG.HEIGHT_MIN_,
                        VITAMIN_CONFIG.HEIGHT_MAX_,
                        VITAMIN_CONFIG.WEIGHT_MIN_,
                        VITAMIN_CONFIG.WEIGHT_MIN_,
                        VITAMIN_CONFIG.GENDER_,
                        VITAMIN_CONFIG.AGE_MIN_,
                        VITAMIN_CONFIG.AGE_MAX_,
                        VITAMIN_CONFIG.CITY_,
                        VITAMIN_CONFIG.AIM_,
                        VITAMIN_CONFIG.ALLERGY_,
                        VITAMIN_CONFIG.VITAMINS_,
                        VITAMIN_CONFIG.IS_REMOVED_,
                        VITAMIN_CONFIG.LINK_
                ).fetchOne(mapping(VitaminConfig::new));
    }

    @Override
    public List<VitaminConfig> findAll() {
        return List.of();
    }

    @Override
    public Optional<VitaminConfigRecord> findRecordById(Long id) {
        return this.dsl
                .selectFrom(VITAMIN_CONFIG)
                .where(VITAMIN_CONFIG.ID_.eq(id))
                .fetchOptional();
    }

    @Override
    public VitaminConfig findById(Long id) {
        var rec = findRecordById(id);
        return rec.isPresent() ? VitaminConfig.to(rec.get()) : VitaminConfig.empty();
    }

    @Override
    public int remove(Long id) {
        return this.dsl.update(VITAMIN_CONFIG)
                .set(VITAMIN_CONFIG.IS_REMOVED_, true)
                .where(VITAMIN_CONFIG.ID_.eq(id)).execute();
    }

    @Override
    public int total() {
        return this.dsl.selectCount().from(VITAMIN_CONFIG)
                .where(VITAMIN_CONFIG.IS_REMOVED_.eq(false))
                .fetchSingle().value1();
    }

    public List<VitaminConfig> page(int limit, int offset) {
        return this.dsl
                .selectFrom(VITAMIN_CONFIG)
                .where(VITAMIN_CONFIG.IS_REMOVED_.eq(false))
                .orderBy(VITAMIN_CONFIG.ID_.desc())
                .limit(limit).offset(offset)
                .stream()
                .map(VitaminConfig::to)
                .collect(Collectors.toList());
    }

    public List<VitaminConfig> findBySurveyId(Long survey) {
        return this.dsl
                .selectFrom(VITAMIN_CONFIG)
                .where(VITAMIN_CONFIG.SURVEY_.eq(survey))
                .and(VITAMIN_CONFIG.IS_REMOVED_.eq(false))
                .fetch().stream().map(VitaminConfig::to)
                .collect(Collectors.toUnmodifiableList());
    }


}
