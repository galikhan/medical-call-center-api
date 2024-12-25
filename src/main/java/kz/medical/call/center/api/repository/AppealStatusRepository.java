package kz.medical.call.center.api.repository;

import jakarta.inject.Singleton;
import kz.jooq.model.tables.records.AppealStatusRecord;
import kz.medical.call.center.api.record.AppealStatus;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.AppealStatus.APPEAL_STATUS;
import static org.jooq.Records.mapping;

@Singleton
public class AppealStatusRepository {

    private DSLContext dsl;

    public AppealStatusRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Optional<AppealStatusRecord> findRecordById(String code) {
        return this.dsl
                .selectFrom(APPEAL_STATUS)
                .where(APPEAL_STATUS.CODE_.eq(code))
                .fetchOptional();
    }

    public AppealStatus findById(String code) {
        var rec = findRecordById(code);
        return rec.isPresent() ? AppealStatus.to(rec.get()) : AppealStatus.empty();
    }


    public List<AppealStatus> findByAll() {
        return this.dsl
                .selectFrom(APPEAL_STATUS)
                .where(APPEAL_STATUS.IS_REMOVED_.eq(false))
                .fetch().stream().map(AppealStatus::to)
                .collect(Collectors.toUnmodifiableList());
    }
}
