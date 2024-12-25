package kz.medical.call.center.api.repository;

import jakarta.inject.Singleton;
import kz.jooq.model.tables.records.AppealTypeRecord;
import kz.medical.call.center.api.record.AppealType;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.AppealType.APPEAL_TYPE;

@Singleton
public class AppealTypeRepository {

    private DSLContext dsl;

    public AppealTypeRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Optional<AppealTypeRecord> findRecordById(String code) {
        return this.dsl
                .selectFrom(APPEAL_TYPE)
                .where(APPEAL_TYPE.CODE_.eq(code))
                .fetchOptional();
    }

    public AppealType findById(String code) {
        var rec = findRecordById(code);
        return rec.isPresent() ? AppealType.to(rec.get()) : AppealType.empty();
    }


    public List<AppealType> findByAll() {
        return this.dsl
                .selectFrom(APPEAL_TYPE)
                .where(APPEAL_TYPE.IS_REMOVED_.eq(false))
                .fetch().stream().map(AppealType::to)
                .collect(Collectors.toUnmodifiableList());
    }
}
