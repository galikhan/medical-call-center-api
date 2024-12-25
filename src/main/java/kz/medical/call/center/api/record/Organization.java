package kz.medical.call.center.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.OrganizationRecord;

@Serdeable
public record Organization(Long id, String name) {
    public static Organization to(OrganizationRecord record) {
        return new Organization(record.getId_(), record.getName_());
    }

    public static Organization empty() {
        return new Organization(null, null);
    }
}
