package kz.medical.call.center.api.record.user;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.enums.GenderType;
import kz.jooq.model.tables.records.MedicalCallCenterUserRecord;

import java.time.LocalDate;

@Serdeable
public record UserNoPassword(Long id, String username, String iin, String firstname, String lastname, LocalDate birtDate, GenderType gender, String role) {
    public static UserNoPassword to(MedicalCallCenterUserRecord record) {
        return new UserNoPassword(
                record.getId_(),
                record.getUsername_(),
                record.getIin_(),
                record.getFirstname_(),
                record.getLastname_(),
                record.getBirthDate_(),
                record.getGender_(),
                record.getRole_()
        );
    }

    public static UserNoPassword empty() {
        return new UserNoPassword(null, null, null, null, null, null, null, null);
    }
}
