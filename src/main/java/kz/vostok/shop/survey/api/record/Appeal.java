package kz.vostok.shop.survey.api.record;

import kz.jooq.model.tables.records.AppealRecord;

import java.time.LocalDateTime;

public record Appeal(Long id,
                     String type,
                     String status,
                     String description,
                     LocalDateTime appealDate,
                     Long organization,
                     Long doctor,
                     Long applicant,
                     Long owner,
                     LocalDateTime created,
                     Boolean isRemoved) {

    public static Appeal to(AppealRecord record) {
        return new Appeal(record.getId_(),
                record.getType_(),
                record.getStatus_(),
                record.getDescription_(),
                record.getAppealDate_(),
                record.getOrganization_(),
                record.getDoctor_(),
                record.getApplicant_(),
                record.getOwner_(),
                record.getCreated_(),
                record.getIsRemoved_());
    }

    public static Appeal empty() {
        return new Appeal(null, null, null, null, null, null, null, null, null, null, null);
    }
}
