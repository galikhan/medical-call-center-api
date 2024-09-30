package kz.vostok.shop.survey.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.VitaminCategoryRecord;

@Serdeable
public record VitaminCategory(Long id, String name) {
    public static VitaminCategory to(VitaminCategoryRecord record) {
        return new VitaminCategory(record.getId_(), record.getName_());
    }
}
