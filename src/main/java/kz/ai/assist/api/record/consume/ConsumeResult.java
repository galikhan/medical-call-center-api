package kz.ai.assist.api.record.consume;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record ConsumeResult(String code, String message) {
}
