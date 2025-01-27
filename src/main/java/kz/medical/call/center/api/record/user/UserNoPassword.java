package kz.medical.call.center.api.record.user;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.enums.GenderType;
import kz.jooq.model.tables.records.MedicalCallCenterUserRecord;

import java.time.LocalDate;

import static kz.medical.call.center.api.util.DateUtil.fromIsoDateToString;

@Serdeable
public record UserNoPassword(Long id, String username, String iin, String firstname, String lastname, String role, String phone) {
    public static UserNoPassword to(MedicalCallCenterUserRecord record) {
        return new UserNoPassword(
                record.getId_(),
                record.getUsername_(),
                record.getIin_(),
                record.getFirstname_(),
                record.getLastname_(),
                record.getRole_(),
                record.getPhone_()
        );
    }

    public static UserNoPassword fromColumnsTo(Long id, String username, String iin, String firstname, String lastname, String role, String phone) {
        return new UserNoPassword(
                id,
                username,
                iin,
                firstname,
                lastname,
                role,
                phone
        );
    }

    public static UserNoPassword empty() {
        return new UserNoPassword(null,null, null, null, null, null, null);
    }
}
