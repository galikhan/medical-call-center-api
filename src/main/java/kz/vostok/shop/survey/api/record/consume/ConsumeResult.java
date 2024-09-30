package kz.vostok.shop.survey.api.record.consume;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record ConsumeResult(String code, String message) {
}
