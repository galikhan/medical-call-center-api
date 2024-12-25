package kz.medical.call.center.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.AppealStatusRecord;

@Serdeable
public record AppealStatus(String code, String nameKk, String nameRu) {
    public static AppealStatus to(AppealStatusRecord record) {
        return new AppealStatus(record.getCode_(), record.getNameKk_(), record.getNameRu_());
    }

    public static AppealStatus empty() {
        return new AppealStatus(null, null, null);
    }
}
