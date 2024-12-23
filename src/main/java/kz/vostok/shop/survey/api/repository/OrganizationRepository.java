package kz.vostok.shop.survey.api.repository;

import jakarta.inject.Singleton;
import kz.jooq.model.tables.records.OrganizationRecord;
import kz.vostok.shop.survey.api.record.Organization;
import org.jooq.DSLContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.Organization.ORGANIZATION;
import static org.jooq.Records.mapping;

@Singleton
public class OrganizationRepository {
//        implements AbstractRepository<Organization, DictionaryRecord> {

    private DSLContext dsl;

    public OrganizationRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Organization create(Organization organization) {
        return this.dsl
                .insertInto(ORGANIZATION)
                .set(ORGANIZATION.NAME_, organization.name())
                .set(ORGANIZATION.CREATED_, LocalDateTime.now())
                .returningResult(
                        ORGANIZATION.ID_,
                        ORGANIZATION.NAME_
                ).fetchOne(mapping(Organization::new));
    }

    public Organization update(Organization organization) {
        return this.dsl
                .update(ORGANIZATION)
                .set(ORGANIZATION.NAME_, organization.name())
                .set(ORGANIZATION.CREATED_, LocalDateTime.now())
                .where(ORGANIZATION.ID_.eq(organization.id()))
                .returningResult(
                        ORGANIZATION.ID_,
                        ORGANIZATION.NAME_
                ).fetchOne(mapping(Organization::new));
    }

    public Optional<OrganizationRecord> findRecordById(Long id) {
        return this.dsl
                .selectFrom(ORGANIZATION)
                .where(ORGANIZATION.ID_.eq(id))
                .fetchOptional();
    }

    public Organization findById(Long id) {
        var rec = findRecordById(id);
        return rec.isPresent() ? Organization.to(rec.get()) : Organization.empty();
    }


    public List<Organization> findByAll() {
        return this.dsl
                .selectFrom(ORGANIZATION)
                .fetch().stream().map(Organization::to)
                .collect(Collectors.toUnmodifiableList());
    }
}
