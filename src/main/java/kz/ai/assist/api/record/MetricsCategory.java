package kz.ai.assist.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.MetricsCategoryRecord;

@Serdeable
public record MetricsCategory(Long id, String type, String code, String name, Long parent) {
    public static MetricsCategory to(MetricsCategoryRecord record) {
        return new MetricsCategory(record.getId_(), record.getCode_(), record.getCode_(), record.getName_(), record.getParent_());
    }
}
