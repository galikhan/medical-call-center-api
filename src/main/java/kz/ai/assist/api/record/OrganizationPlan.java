package kz.ai.assist.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.OrganizationPlanRecord;

import java.math.BigDecimal;

@Serdeable
public record OrganizationPlan(Long id, Long organization, String code, BigDecimal amount, Long owner) {
    public  static OrganizationPlan to(OrganizationPlanRecord r) {
        return new OrganizationPlan(r.getId_(), r.getOrganization_(), r.getCode_(), r.getAmount_(), r.getOwner_());
    }

    public static OrganizationPlan empty() {
        return new OrganizationPlan(null, null, null, null, null);
    }
}
