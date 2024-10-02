package kz.vostok.shop.survey.api.repository;

import jakarta.inject.Singleton;
import kz.jooq.model.tables.records.VitaminRecord;
import kz.vostok.shop.survey.api.record.Vitamin;
import kz.vostok.shop.survey.api.record.VitaminCategory;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.Vitamin.VITAMIN;
import static kz.jooq.model.tables.VitaminCategory.VITAMIN_CATEGORY;
import static org.jooq.Records.mapping;

@Singleton
public class VitaminRepository implements AbstractRepository<Vitamin, VitaminRecord> {

    private DSLContext dsl;

    public VitaminRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Vitamin create(Vitamin vitamin) {
        return this.dsl
                .insertInto(VITAMIN)
                .set(VITAMIN.NAME_, vitamin.name())
                .set(VITAMIN.CATEGORY_, vitamin.category())
                .set(VITAMIN.DESCRIPTION_, vitamin.description())
                .returningResult(
                        VITAMIN.ID_,
                        VITAMIN.CATEGORY_,
                        VITAMIN.NAME_,
                        VITAMIN.DESCRIPTION_,
                        VITAMIN.IS_REMOVED_
                ).fetchOne(mapping(Vitamin::new));
    }

    @Override
    public Vitamin update(Vitamin vitamin) {
        return this.dsl
                .update(VITAMIN)
                .set(VITAMIN.NAME_, vitamin.name())
                .set(VITAMIN.CATEGORY_, vitamin.category())
                .set(VITAMIN.DESCRIPTION_, vitamin.description())
                .set(VITAMIN.IS_REMOVED_, vitamin.isRemoved())
                .where(VITAMIN.ID_.eq(vitamin.id()))
                .returningResult(
                        VITAMIN.ID_,
                        VITAMIN.CATEGORY_,
                        VITAMIN.NAME_,
                        VITAMIN.DESCRIPTION_,
                        VITAMIN.IS_REMOVED_
                ).fetchOne(mapping(Vitamin::new));
    }

    @Override
    public List<Vitamin> findAll() {
        return List.of();
    }

    @Override
    public Optional<VitaminRecord> findRecordById(Long id) {
        return this.dsl.selectFrom(VITAMIN).where(VITAMIN.ID_.eq(id)).fetchOptional();
    }

    @Override
    public Vitamin findById(Long id) {
        var rec = findRecordById(id);
        return rec.isPresent() ? Vitamin.to(rec.get()) : Vitamin.empty();
    }

    @Override
    public int remove(Long id) {
        return this.dsl.update(VITAMIN)
                .set(VITAMIN.IS_REMOVED_, true)
                .where(VITAMIN.ID_.eq(id)).execute();
    }

    @Override
    public int total() {
        return this.dsl.selectCount().from(VITAMIN)
                .where(VITAMIN.IS_REMOVED_.eq(false))
                .fetchSingle()
                .value1();

    }

    public List<Vitamin> page(int limit, int offset) {
        return this.dsl
                .selectFrom(VITAMIN)
                .where(VITAMIN.IS_REMOVED_.eq(false))
                .orderBy(VITAMIN.ID_.desc())
                .limit(limit).offset(offset)
                .stream()
                .map(Vitamin::to)
                .collect(Collectors.toList());
    }

    public List<VitaminCategory> findCategory() {
        return this.dsl
                .selectFrom(VITAMIN_CATEGORY)
                .orderBy(VITAMIN_CATEGORY.ID_)
                .stream()
                .map(VitaminCategory::to)
                .collect(Collectors.toList());
    }
}
