package kz.vostok.shop.survey.api.record.page;

import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public record ParticipantPage(Integer total) {
//public record ParticipantPage(Integer total, List<Participant> data) {
}
