package kz.vostok.shop.survey.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.enums.SurveyMode;
import kz.jooq.model.tables.records.SurveyRecord;

@Serdeable
public record Survey(Long id, String name, SurveyMode mode, Boolean isActive, Boolean isRemoved) {
    public static Survey to(SurveyRecord record) {
        return new Survey(record.getId_(), record.getName_(), record.getMode_(), record.getIsActive_(), record.getIsRemoved_());
    }

    public static Survey empty() {
        return new Survey(null, null, null, null, null);
    }
}
