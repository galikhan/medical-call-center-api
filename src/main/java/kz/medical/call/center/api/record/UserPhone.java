package kz.medical.call.center.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.UserPhoneRecord;

@Serdeable
public record UserPhone(Long id, Long user, String phone) {
    public static UserPhone to(UserPhoneRecord record) {
        return new UserPhone(record.getId_(), record.getUser_(), record.getPhone_());
    }
}
