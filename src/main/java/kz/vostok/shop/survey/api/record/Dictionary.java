package kz.vostok.shop.survey.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.DictionaryRecord;

@Serdeable
public record Dictionary(Long id, String key, String code, String name) {
    public static Dictionary to(DictionaryRecord record) {
        return new Dictionary(record.getId_(), record.getKey_(), record.getCode_(), record.getName_());
    }

    public static Dictionary empty() {
        return new Dictionary(null, null, null, null);
    }
}
