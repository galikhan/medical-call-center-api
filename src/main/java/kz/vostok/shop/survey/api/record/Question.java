package kz.vostok.shop.survey.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.enums.QuestionType;
import kz.jooq.model.tables.records.QuestionRecord;

@Serdeable
public record Question(Long id, String name, Long survey, QuestionType type, Long prevQuestion, Long nextQuestion, Boolean isRemoved, String description, Long category) {
    public static Question to(QuestionRecord record) {
        return new Question(record.getId_(), record.getName_(), record.getSurvey_(), record.getType_(), record.getPrevQuestion_(), record.getNextQuestion_(), record.getIsRemoved_(), record.getDescription_(), record.getCategory_());
    }

    public static Question empty() {
        return new Question(null, null, null, null, null, null, null, null, null);
    }
}
