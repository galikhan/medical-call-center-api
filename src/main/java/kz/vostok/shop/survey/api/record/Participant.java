package kz.vostok.shop.survey.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.enums.GenderType;
import kz.jooq.model.tables.records.ParticipantRecord;

import java.time.LocalDateTime;

@Serdeable
public record Participant(Long id, String firstname, String email, Float height, Float weight, Integer age, GenderType gender, Long aim, String city, Long allergy, LocalDateTime created) {
    public static Participant to(ParticipantRecord record) {
        return new Participant(record.getId_(), record.getFirstname_(), record.getEmail_(), record.getHeight_(), record.getWeight_(), record.getAge_(), record.getGender_(), record.getAim_(), record.getCity_(), record.getAllergy_(), record.getCreated_());
    }

    public static Participant empty() {
        return new Participant(null, null, null, null, null, null, null, null, null, null, null);
    }
}
