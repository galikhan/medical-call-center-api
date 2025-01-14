package kz.medical.call.center.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.enums.GenderType;
import kz.jooq.model.tables.records.MedicalCallCenterUserRecord;

import java.time.LocalDate;

@Serdeable
public record MedicalCallCenterUser(Long id, String username, String password, String iin, String firstname, String lastname, String birthDate, GenderType gender, String role, String phone) {
    public static MedicalCallCenterUser to(MedicalCallCenterUserRecord record) {
        return new MedicalCallCenterUser(record.getId_(),
                record.getUsername_(),
                record.getPassword_(),
                record.getIin_(),
                record.getFirstname_(),
                record.getLastname_(),
                record.getBirthDate_() !=null ? record.getBirthDate_() .toString() : null,
                record.getGender_(),
                record.getRole_(),
                record.getPhone_()
        );
    }

    public static MedicalCallCenterUser empty() {
        return new MedicalCallCenterUser(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}
