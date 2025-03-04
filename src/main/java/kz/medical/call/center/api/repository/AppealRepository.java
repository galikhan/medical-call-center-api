package kz.medical.call.center.api.repository;

import io.micronaut.core.util.StringUtils;
import jakarta.inject.Singleton;
import kz.jooq.model.tables.records.AppealRecord;
import kz.medical.call.center.api.record.Appeal;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

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
                .set(APPEAL.UNIQUEID_, appeal.uniqueId())
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
                        APPEAL.ACTS_TAKEN_,
                        APPEAL.UNIQUEID_
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
                .set(APPEAL.UNIQUEID_, appeal.uniqueId())
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
                        APPEAL.ACTS_TAKEN_,
                        APPEAL.UNIQUEID_
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

    public int totalByParams(String type, Long organization, String searchText, Long id) {
        var searchQuery = getSearchCondition(type, organization, searchText, id);
        return this.dsl
                .selectCount()
                .from(APPEAL)
                .where(APPEAL.TYPE_.eq(type))
                .and(searchQuery)
                .fetch().get(0).value1();
    }


    public List<Appeal> pageByParams(String type, Long organization, int limit, int offset, String searchText, Long id) {
        var searchQuery = getSearchCondition(type, organization, searchText, id);
        return this.dsl
                .selectFrom(APPEAL)
                .where(APPEAL.IS_REMOVED_.eq(false))
                .and(APPEAL.TYPE_.eq(type))
                .and(searchQuery)
                .orderBy(APPEAL.ID_.desc())
                .limit(limit).offset(offset)
                .stream()
                .map(Appeal::to)
                .collect(Collectors.toList());
    }

    private Condition getSearchCondition(String type, Long organization, String searchText, Long id) {
        var searchQuery = APPEAL.TYPE_.eq(type);
        if(organization !=null) {
            searchQuery = searchQuery.and(APPEAL.ORGANIZATION_.eq(organization));
        }

        if(StringUtils.isNotEmpty(searchText)) {
            if (id != null) {
                searchQuery = searchQuery.and(APPEAL.ID_.eq(id).or(DSL.lower(APPEAL.DESCRIPTION_).contains(searchText.toLowerCase())));
            } else {
                searchQuery = searchQuery.and(DSL.lower(APPEAL.DESCRIPTION_).contains(searchText.toLowerCase()));
            }
        }
        return searchQuery;
    }

    public List<Appeal> fetchAllWithUniqueId() {
        return this.dsl
                .selectFrom(APPEAL)
                .where(APPEAL.IS_REMOVED_.eq(false))
                .and(APPEAL.UNIQUEID_.isNotNull())
                .stream()
                .map(Appeal::to)
                .collect(Collectors.toList());
    }
}
