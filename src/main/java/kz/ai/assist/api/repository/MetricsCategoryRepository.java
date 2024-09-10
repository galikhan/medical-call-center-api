package kz.ai.assist.api.repository;

import jakarta.inject.Singleton;
import kz.ai.assist.api.record.MetricsCategory;
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
}
