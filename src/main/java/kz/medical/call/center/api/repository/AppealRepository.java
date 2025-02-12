package kz.medical.call.center.api.repository;

import io.micronaut.core.util.StringUtils;
import jakarta.inject.Singleton;
import kz.jooq.model.tables.records.AppealRecord;
import kz.medical.call.center.api.record.Appeal;
import org.jooq.DSLContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.Appeal.APPEAL;
import static org.jooq.Records.mapping;

@Singleton
public class AppealRepository {

    private DSLContext dsl;

    public AppealRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Appeal create(Appeal appeal, Long owner) {
        var status = StringUtils.isNotEmpty(appeal.status()) ? appeal.status() : null;
        return this.dsl
                .insertInto(APPEAL)
                .set(APPEAL.TYPE_, appeal.type())
                .set(APPEAL.STATUS_, status)
                .set(APPEAL.DESCRIPTION_, appeal.description())
                .set(APPEAL.APPEAL_DATE_, appeal.appealDate())
                .set(APPEAL.ORGANIZATION_, appeal.organization())
                .set(APPEAL.APPLICANT_, appeal.applicant())
                .set(APPEAL.OWNER_, owner)
                .set(APPEAL.CREATED_, LocalDateTime.now())
                .set(APPEAL.DOCTOR_INFO_, appeal.doctorInfo())
                .set(APPEAL.CATEGORY_, appeal.category())
                .set(APPEAL.ACTS_TAKEN_, appeal.actsTaken())
                .returningResult(
                        APPEAL.ID_,
                        APPEAL.TYPE_,
                        APPEAL.STATUS_,
                        APPEAL.DESCRIPTION_,
                        APPEAL.APPEAL_DATE_,
                        APPEAL.ORGANIZATION_,
                        APPEAL.APPLICANT_,
                        APPEAL.OWNER_,
                        APPEAL.CREATED_,
                        APPEAL.IS_REMOVED_,
                        APPEAL.CATEGORY_,
                        APPEAL.DOCTOR_INFO_,
                        APPEAL.ACTS_TAKEN_
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
                .set(APPEAL.APPLICANT_, appeal.applicant())
                .set(APPEAL.OWNER_, appeal.owner())
                .set(APPEAL.DOCTOR_INFO_, appeal.doctorInfo())
                .set(APPEAL.CATEGORY_, appeal.category())
                .set(APPEAL.ACTS_TAKEN_, appeal.actsTaken())
                .where(APPEAL.ID_.eq(appeal.id()))
                .returningResult(
                        APPEAL.ID_,
                        APPEAL.TYPE_,
                        APPEAL.STATUS_,
                        APPEAL.DESCRIPTION_,
                        APPEAL.APPEAL_DATE_,
                        APPEAL.ORGANIZATION_,
                        APPEAL.APPLICANT_,
                        APPEAL.OWNER_,
                        APPEAL.CREATED_,
                        APPEAL.IS_REMOVED_,
                        APPEAL.CATEGORY_,
                        APPEAL.DOCTOR_INFO_,
                        APPEAL.ACTS_TAKEN_
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
                .orderBy(APPEAL.ID_.desc())
                .limit(limit).offset(offset)
                .stream()
                .map(Appeal::to)
                .collect(Collectors.toList());

    }

    public int remove(Long id) {
        return this.dsl
                .update(APPEAL)
                .set(APPEAL.IS_REMOVED_, true)
                .where(APPEAL.ID_.eq(id))
                .execute();
    }

    public int totalByTypeAndOrganization(String type, Long organization) {
        return this.dsl
                .selectCount()
                .from(APPEAL)
                .where(APPEAL.TYPE_.eq(type))
                .and(APPEAL.ORGANIZATION_.eq(organization))
                .fetch().get(0).value1();
    }

    public List<Appeal> pageByTypeAndOrganization(String type, Long organization, int limit, int offset) {
        return this.dsl
                .selectFrom(APPEAL)
                .where(APPEAL.IS_REMOVED_.eq(false))
                .and(APPEAL.TYPE_.eq(type))
                .and(APPEAL.ORGANIZATION_.eq(organization))
                .orderBy(APPEAL.ID_.desc())
                .limit(limit).offset(offset)
                .stream()
                .map(Appeal::to)
                .collect(Collectors.toList());
    }
    public int totalByType(String type) {
        return this.dsl
                .selectCount()
                .from(APPEAL)
                .where(APPEAL.TYPE_.eq(type))
                .fetch().get(0).value1();
    }

    public List<Appeal> pageByType(String type, int limit, int offset) {
        return this.dsl
                .selectFrom(APPEAL)
                .where(APPEAL.IS_REMOVED_.eq(false))
                .and(APPEAL.TYPE_.eq(type))
                .orderBy(APPEAL.ID_.desc())
                .limit(limit).offset(offset)
                .stream()
                .map(Appeal::to)
                .collect(Collectors.toList());
    }
}
