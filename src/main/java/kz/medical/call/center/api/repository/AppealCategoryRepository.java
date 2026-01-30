package kz.medical.call.center.api.repository;

import jakarta.inject.Singleton;
import kz.jooq.model.tables.records.AppealCategoryRecord;
import kz.medical.call.center.api.record.Appeal;
import kz.medical.call.center.api.record.AppealCategory;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.AppealCategory.APPEAL_CATEGORY;

@Singleton
public class AppealCategoryRepository {

    private DSLContext dsl;

    public AppealCategoryRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Optional<AppealCategoryRecord> findRecordById(Long id) {
        return this.dsl
                .selectFrom(APPEAL_CATEGORY)
                .where(APPEAL_CATEGORY.ID_.eq(id))
                .fetchOptional();
    }

    public AppealCategory findById(Long id) {
        var rec = findRecordById(id);
        return rec.map(AppealCategory::to).orElseGet(AppealCategory::empty);
    }


    public List<AppealCategory> findByAll() {
        return this.dsl
                .selectFrom(APPEAL_CATEGORY)
                .where(APPEAL_CATEGORY.IS_REMOVED_.eq(false))
                .fetch().stream().map(AppealCategory::to)
                .toList();
    }

    public List<AppealCategory> findByType(String type) {
        return this.dsl
                .selectFrom(APPEAL_CATEGORY)
                .where(APPEAL_CATEGORY.IS_REMOVED_.eq(false))
                .and(APPEAL_CATEGORY.TYPE_.eq(type))
                .fetch().stream().map(AppealCategory::to)
                .toList();
    }
}
