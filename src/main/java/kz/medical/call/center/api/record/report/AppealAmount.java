package kz.medical.call.center.api.record.report;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AppealAmount(Integer complaint, Integer thanks, Integer proposal, Integer total) {
}
