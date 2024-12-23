package kz.vostok.shop.survey.api.repository;

import jakarta.inject.Singleton;
import kz.jooq.model.tables.records.AppealRecord;
import kz.jooq.model.tables.records.OrganizationRecord;
import kz.vostok.shop.survey.api.record.Appeal;
import kz.vostok.shop.survey.api.record.Organization;
import org.jooq.DSLContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.Appeal.APPEAL;
import static kz.jooq.model.tables.Organization.ORGANIZATION;
import static org.jooq.Records.mapping;

@Singleton
public class AppealRepository {

    private DSLContext dsl;

    public AppealRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Appeal create(Appeal appeal, Long owner) {
        return this.dsl
                .insertInto(APPEAL)
                .set(APPEAL.TYPE_, appeal.type())
                .set(APPEAL.STATUS_, appeal.status())
                .set(APPEAL.DESCRIPTION_, appeal.description())
                .set(APPEAL.APPEAL_DATE_, appeal.appealDate())
                .set(APPEAL.ORGANIZATION_, appeal.organization())
                .set(APPEAL.DOCTOR_, appeal.doctor())
                .set(APPEAL.APPLICANT_, appeal.applicant())
                .set(APPEAL.OWNER_, owner)
                .set(APPEAL.CREATED_, LocalDateTime.now())
                .returningResult(
                        APPEAL.ID_,
                        APPEAL.TYPE_,
                        APPEAL.STATUS_,
                        APPEAL.DESCRIPTION_,
                        APPEAL.APPEAL_DATE_,
                        APPEAL.ORGANIZATION_,
                        APPEAL.DOCTOR_,
                        APPEAL.APPLICANT_,
                        APPEAL.OWNER_,
                        APPEAL.CREATED_,
                        APPEAL.IS_REMOVED_
                ).fetchOne(mapping(Appeal::new));
    }

    public Appeal update(Appeal appeal) {
        return this.dsl
                .update(APPEAL)
                .set(APPEAL.TYPE_, appeal.type())
                .set(APPEAL.STATUS_, appeal.status())
                .set(APPEAL.DESCRIPTION_, appeal.description())
                .set(APPEAL.APPEAL_DATE_, appeal.appealDate())
                .set(APPEAL.ORGANIZATION_, appeal.organization())
                .set(APPEAL.DOCTOR_, appeal.doctor())
                .set(APPEAL.APPLICANT_, appeal.applicant())
                .set(APPEAL.OWNER_, appeal.owner())
                .where(APPEAL.ID_.eq(appeal.id()))
                .returningResult(
                        APPEAL.ID_,
                        APPEAL.TYPE_,
                        APPEAL.STATUS_,
                        APPEAL.DESCRIPTION_,
                        APPEAL.APPEAL_DATE_,
                        APPEAL.ORGANIZATION_,
                        APPEAL.DOCTOR_,
                        APPEAL.APPLICANT_,
                        APPEAL.OWNER_,
                        APPEAL.CREATED_,
                        APPEAL.IS_REMOVED_
                ).fetchOne(mapping(Appeal::new));
    }

    public Optional<AppealRecord> findRecordById(Long id) {
        return this.dsl
                .selectFrom(APPEAL)
                .where(APPEAL.ID_.eq(id))
                .fetchOptional();
    }

    public Appeal findById(Long id) {
        var rec = findRecordById(id);
        return rec.isPresent() ? Appeal.to(rec.get()) : Appeal.empty();
    }


    public List<Appeal> findByAll() {
        return this.dsl
                .selectFrom(APPEAL)
                .fetch().stream().map(Appeal::to)
                .collect(Collectors.toUnmodifiableList());
    }

    public int total() {
        return this.dsl
                .selectCount().from(APPEAL)
                .fetch().get(0).value1();
    }

    public List<Appeal> page(int limit, int offset) {
        return this.dsl
                .selectFrom(APPEAL)
                .where(APPEAL.IS_REMOVED_.eq(false))
                .orderBy(APPEAL.ID_)
                .limit(limit).offset(offset)
                .stream()
                .map(Appeal::to)
                .collect(Collectors.toList());

    }
}
