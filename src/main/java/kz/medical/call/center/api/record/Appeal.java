package kz.medical.call.center.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.AppealRecord;

import java.time.LocalDateTime;

@Serdeable
public record Appeal(Long id,
                     String type,
                     String status,
                     String description,
                     LocalDateTime appealDate,
                     Long organization,
                     Long applicant,
                     Long owner,
                     LocalDateTime created,
                     Boolean isRemoved
        ,
                     Long category,
                     String doctorInfo,
                     String actsTaken
) {

    public static Appeal to(AppealRecord record) {
        return new Appeal(record.getId_(),
                record.getType_(),
                record.getStatus_(),
                record.getDescription_(),
                record.getAppealDate_(),
                record.getOrganization_(),
                record.getApplicant_(),
                record.getOwner_(),
                record.getCreated_(),
                record.getIsRemoved_(),
                record.getCategory_(),
                record.getDoctorInfo_(),
                record.getActsTaken_()
        );
    }

    public static Appeal empty() {
        return new Appeal(null, null,null, null, null, null, null, null, null, null, null, null, null);
    }
}
