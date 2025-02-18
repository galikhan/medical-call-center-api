package kz.medical.call.center.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.RoleRecord;

@Serdeable
public record Role(String code, String name) {

    public static Role to(RoleRecord role) {
        return new Role(role.getCode_(), role.getName_());
    }
}
