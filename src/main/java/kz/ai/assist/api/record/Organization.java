package kz.ai.assist.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.OrganizationRecord;

@Serdeable
public record Organization(Long id, String bin, String name, Long owner) {

    public static Organization empty() {
        return new Organization(null, null, null, null);
    }

    public static Organization to(OrganizationRecord record) {
        return new Organization(record.getId_(), record.getBin_(), record.getName_(), record.getOwner_());
    }

}
