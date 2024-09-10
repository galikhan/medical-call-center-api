package kz.ai.assist.api.repository;

import jakarta.inject.Singleton;
import kz.ai.assist.api.record.Organization;
import kz.jooq.model.tables.records.OrganizationRecord;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.Organization.ORGANIZATION;
import static org.jooq.Records.mapping;

@Singleton
public class OrganizationRepository {
    private DSLContext dsl;

    public OrganizationRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Optional<OrganizationRecord> findRecordByBin(String bin) {
        return this.dsl
                .selectFrom(ORGANIZATION)
                .where(ORGANIZATION.BIN_.eq(bin))
                .fetchOptional();
    }

    public Optional<OrganizationRecord> findRecordById(Long id) {
        return this.dsl
                .selectFrom(ORGANIZATION)
                .where(ORGANIZATION.ID_.eq(id))
                .fetchOptional();
    }

    public Organization create(Organization organization, Long owner) {

        return this.dsl.insertInto(ORGANIZATION)
                .set(ORGANIZATION.BIN_, organization.bin())
                .set(ORGANIZATION.NAME_, organization.name())
                .set(ORGANIZATION.OWNER_, owner)
                .returningResult(
                        ORGANIZATION.ID_,
                        ORGANIZATION.BIN_,
                        ORGANIZATION.NAME_,
                        ORGANIZATION.OWNER_
                )
                .fetchOne(mapping(Organization::new));
    }

    public Organization update(Organization organization) {

        return this.dsl.update(ORGANIZATION)
                .set(ORGANIZATION.BIN_, organization.bin())
                .set(ORGANIZATION.NAME_, organization.name())
                .set(ORGANIZATION.OWNER_, organization.owner())
                .where(ORGANIZATION.ID_.eq(organization.id()))
                .returningResult(
                        ORGANIZATION.ID_,
                        ORGANIZATION.BIN_,
                        ORGANIZATION.NAME_,
                        ORGANIZATION.OWNER_
                )
                .fetchOne(mapping(Organization::new));
    }

    public List<Organization> findAll(Long id) {
        return this.dsl.selectFrom(ORGANIZATION)
                .where(ORGANIZATION.OWNER_.eq(id))
                .fetch()
                .stream().map(Organization::to)
                .collect(Collectors.toUnmodifiableList());
    }
}
