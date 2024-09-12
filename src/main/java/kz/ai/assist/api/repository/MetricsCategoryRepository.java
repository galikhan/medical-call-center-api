package kz.ai.assist.api.repository;

import jakarta.inject.Singleton;
import kz.ai.assist.api.record.MetricsCategory;
import kz.jooq.model.enums.DepartmentType;
import kz.jooq.model.tables.records.MetricsCategoryRecord;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.MetricsCategory.METRICS_CATEGORY;

@Singleton
public class MetricsCategoryRepository {

    private DSLContext dsl;

    public MetricsCategoryRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Optional<MetricsCategoryRecord> findRecordByCode(String code) {
        return this
                .dsl.selectFrom(METRICS_CATEGORY)
                .where(METRICS_CATEGORY.CODE_.eq(code))
                .fetchOptional();
    }

    public List<MetricsCategory> findAll() {
        return this
                .dsl
                .selectFrom(METRICS_CATEGORY)
                .fetch().stream().map(MetricsCategory::to).collect(Collectors.toUnmodifiableList());
    }

    public List<MetricsCategory> findSectionsByBlockType(String type) {
        return this
                .dsl
                .selectFrom(METRICS_CATEGORY)
                .where(METRICS_CATEGORY.TYPE_.eq(DepartmentType.valueOf(type)))
                .and(METRICS_CATEGORY.PARENT_.isNull())
                .fetch().stream().map(MetricsCategory::to).collect(Collectors.toUnmodifiableList());
    }

    public List<MetricsCategory> findAllByBlockType(String type) {
        return this
                .dsl
                .selectFrom(METRICS_CATEGORY)
                .where(METRICS_CATEGORY.TYPE_.eq(DepartmentType.valueOf(type)))
                .fetch().stream().map(MetricsCategory::to)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<MetricsCategory> findByBlock(String code) {
        return this
                .dsl
                .selectFrom(METRICS_CATEGORY)
                .where(METRICS_CATEGORY.PARENT_.eq(
                        dsl.select(METRICS_CATEGORY.ID_).from(METRICS_CATEGORY).where(METRICS_CATEGORY.CODE_.eq(code)))
                )
                .fetch().stream().map(MetricsCategory::to)
                .collect(Collectors.toUnmodifiableList());
    }

    public MetricsCategory findMetricsByCode(String code) {
        var record = this.dsl
                .selectFrom(METRICS_CATEGORY)
                .where(METRICS_CATEGORY.CODE_.eq(code))
                .fetchOptional();
        return record.isPresent() ? MetricsCategory.to(record.get()) : MetricsCategory.empty();
    }

    public List<MetricsCategory> findMetricsByCodes(String[] codes) {
        return this.dsl.selectFrom(METRICS_CATEGORY)
                .where(METRICS_CATEGORY.CODE_.in(codes))
                .fetch().stream().map(MetricsCategory::to)
                .collect(Collectors.toUnmodifiableList());
    }
}
