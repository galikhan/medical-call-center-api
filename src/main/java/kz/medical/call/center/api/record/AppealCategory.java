package kz.medical.call.center.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.AppealCategoryRecord;

@Serdeable
public record AppealCategory(Long id, String type, String nameRu, String nameKk, Boolean isRemoved) {

    public static AppealCategory to(AppealCategoryRecord record) {
        return new AppealCategory(record.getId_(), record.getType_(), record.getNameRu_(), record.getNameKk_(), record.getIsRemoved_());
    }

    public static AppealCategory empty() {
        return new AppealCategory(null, null, null, null, null);
    }
}
