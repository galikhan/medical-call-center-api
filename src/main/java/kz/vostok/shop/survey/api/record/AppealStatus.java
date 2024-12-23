package kz.vostok.shop.survey.api.record;

import kz.jooq.model.tables.records.AppealStatusRecord;

import java.security.AllPermission;

public record AppealStatus(String code, String nameKk, String nameRu) {
    public static AppealStatus to(AppealStatusRecord record) {
        return new AppealStatus(record.getCode_(), record.getNameKk_(), record.getNameRu_());
    }

    public static AppealStatus empty() {
        return new AppealStatus(null, null, null);
    }
}
