package kz.ai.assist.api.record.consume;

import io.micronaut.serde.annotation.Serdeable;

import java.math.BigDecimal;
import java.time.LocalDate;

@Serdeable
public record ConsumeRow(String type, String code, BigDecimal amount, String when) { }
