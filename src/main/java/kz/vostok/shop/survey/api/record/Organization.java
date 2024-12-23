package kz.vostok.shop.survey.api.record;

import kz.jooq.model.tables.records.OrganizationRecord;

public record Organization(Long id, String name) {
    public static Organization to(OrganizationRecord record) {
        return new Organization(record.getId_(), record.getName_());
    }

    public static Organization empty() {
        return new Organization(null, null);
    }
}
