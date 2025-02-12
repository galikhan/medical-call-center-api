package kz.medical.call.center.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.MedicalCallCenterUserRecord;

@Serdeable
public record MedicalCallCenterUser(Long id, String username, String password, String iin, String firstname,
                                    String lastname, String role, String phone, String fullname, Long organization) {
    public static MedicalCallCenterUser to(MedicalCallCenterUserRecord record) {
        return new MedicalCallCenterUser(record.getId_(),
                record.getUsername_(),
                record.getPassword_(),
                record.getIin_(),
                record.getFirstname_(),
                record.getLastname_(),
                record.getRole_(),
                record.getPhone_(),
                record.getFullname_(),
                record.getOrganization_()
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
