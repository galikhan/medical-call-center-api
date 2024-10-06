package kz.vostok.shop.survey.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.VitaminRecord;

@Serdeable
public record Vitamin(Long id, Long category, String name, String description, Boolean isRemoved, String link) {
    public static Vitamin to(VitaminRecord record) {
        return new Vitamin(record.getId_(), record.getCategory_(), record.getName_(), record.getDescription_(), record.getIsRemoved_(), record.getLink_());
    }

    public static Vitamin empty() {
        return new Vitamin(null, null, null, null, null, null);
    }
}
