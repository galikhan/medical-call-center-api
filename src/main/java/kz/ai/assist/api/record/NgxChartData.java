package kz.ai.assist.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.ChartDataRecord;

import java.math.BigDecimal;

@Serdeable
public record NgxChartData(String name, BigDecimal value) {
    public static NgxChartData to(ChartDataRecord item) {
        return new NgxChartData(String.valueOf(item.getWhen_()), item.getAmount_());
    }
}
