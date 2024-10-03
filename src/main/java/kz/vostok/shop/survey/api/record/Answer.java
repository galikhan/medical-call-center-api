package kz.vostok.shop.survey.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.AnswerRecord;

@Serdeable
public record Answer(Long id, Long question, String name, Long nextQuestion, Boolean isUserAnswer, Boolean isRemoved) {

    public static Answer to(AnswerRecord record) {
        return new Answer(record.getId_(), record.getQuestion_(), record.getName_(), record.getNextQuestion_(), record.getIsUserAnswer_(), record.getIsRemoved_());
    }

    public static Answer empty() {
        return new Answer(null, null, null, null, null, null);
    }
}
