package kz.vostok.shop.survey.api.record;

import kz.jooq.model.tables.records.MedicalCallCenterUserRecord;

public record MedicalCallCenterUser(Long id, String username, String password) {
    public static MedicalCallCenterUser to(MedicalCallCenterUserRecord record) {
        return new MedicalCallCenterUser(record.getId_(), record.getUsername_(), record.getPassword_());
    }
}
