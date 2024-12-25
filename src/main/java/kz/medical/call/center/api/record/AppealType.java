package kz.medical.call.center.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.AppealTypeRecord;

@Serdeable
public record AppealType(String code, String nameKk, String nameRu) {
    public static AppealType to(AppealTypeRecord record) {
        return new AppealType(record.getCode_(), record.getNameKk_(), record.getNameRu_());
    }

    public static AppealType empty() {
        return new AppealType(null, null, null);
    }
}
