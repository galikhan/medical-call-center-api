package kz.ai.assist.api.record.consume;

import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public record ConsumeChartData(String bin, String category, List<ConsumeRow> rows) {
}
