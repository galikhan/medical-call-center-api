package kz.ai.assist.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.ChartDataRecord;

import java.math.BigDecimal;
import java.time.LocalDate;

@Serdeable
public record ChartData(Long id, String code, BigDecimal amount, LocalDate when, Long category, Long organization) {
    public static ChartData to(ChartDataRecord record) {
        return new ChartData(record.getId_(), record.getCode_(), record.getAmount_(), record.getWhen_(), record.getCategory_(), record.getOrganization_());
    }
}
