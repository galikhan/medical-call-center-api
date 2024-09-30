package kz.vostok.shop.survey.api.record;

import kz.jooq.model.tables.records.VostokShopSurveyUserRecord;

public record VostokShopSurveyUser(Long id, String username, String password) {
    public static VostokShopSurveyUser to(VostokShopSurveyUserRecord record) {
        return new VostokShopSurveyUser(record.getId_(), record.getUsername_(), record.getPassword_());
    }
}
