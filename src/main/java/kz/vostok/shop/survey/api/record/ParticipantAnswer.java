package kz.vostok.shop.survey.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.ParticipantAnswerRecord;

@Serdeable
public record ParticipantAnswer(Long id, Long participant, Long survey, Long question, Long answer, Long [] answers, String value) {
    public static ParticipantAnswer to(ParticipantAnswerRecord record) {
        return new ParticipantAnswer(record.getId_(), record.getParticipant_(), record.getSurvey_(), record.getQuestion_(), record.getAnswer_(), record.getAnswers_(), record.getValue_());
    }
    public static ParticipantAnswer empty() {
        return new ParticipantAnswer(null, null, null, null, null, null, null);
    }
}
