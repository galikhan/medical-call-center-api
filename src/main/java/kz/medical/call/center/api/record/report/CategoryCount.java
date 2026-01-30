package kz.medical.call.center.api.record.report;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record CategoryCount(Long id, String name, Integer count) {
}
