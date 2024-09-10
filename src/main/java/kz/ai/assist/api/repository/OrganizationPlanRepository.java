package kz.ai.assist.api.repository;

import jakarta.inject.Singleton;
import kz.ai.assist.api.record.OrganizationPlan;
import kz.jooq.model.tables.records.OrganizationPlanRecord;
import org.jooq.DSLContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.OrganizationPlan.ORGANIZATION_PLAN;
import static org.jooq.Records.mapping;

@Singleton
public class OrganizationPlanRepository {

    private DSLContext dsl;

    public OrganizationPlanRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public OrganizationPlan create(OrganizationPlan organizationPlan, Long owner) {
        return this.dsl.insertInto(ORGANIZATION_PLAN)
                .set(ORGANIZATION_PLAN.CODE_, organizationPlan.code())
                .set(ORGANIZATION_PLAN.AMOUNT_, organizationPlan.amount())
                .set(ORGANIZATION_PLAN.ORGANIZATION_, organizationPlan.organization())
                .set(ORGANIZATION_PLAN.OWNER_, owner)
                .returningResult(
                        ORGANIZATION_PLAN.ID_,
                        ORGANIZATION_PLAN.ORGANIZATION_,
                        ORGANIZATION_PLAN.CODE_,
                        ORGANIZATION_PLAN.AMOUNT_,
                        ORGANIZATION_PLAN.OWNER_
                )
                .fetchOne(mapping(OrganizationPlan::new));
    }

    public OrganizationPlan update(OrganizationPlan organizationPlan) {
        return this.dsl.update(ORGANIZATION_PLAN)
                .set(ORGANIZATION_PLAN.CODE_, organizationPlan.code())
                .set(ORGANIZATION_PLAN.AMOUNT_, organizationPlan.amount())
                .set(ORGANIZATION_PLAN.ORGANIZATION_, organizationPlan.organization())
                .set(ORGANIZATION_PLAN.MODIFIED_, LocalDateTime.now())
                .set(ORGANIZATION_PLAN.OWNER_, organizationPlan.owner())
                .where(ORGANIZATION_PLAN.ID_.eq(organizationPlan.id()))
                .returningResult(
                        ORGANIZATION_PLAN.ID_,
                        ORGANIZATION_PLAN.ORGANIZATION_,
                        ORGANIZATION_PLAN.CODE_,
                        ORGANIZATION_PLAN.AMOUNT_,
                        ORGANIZATION_PLAN.OWNER_
                )
                .fetchOne(mapping(OrganizationPlan::new));
    }

    public Map<String, BigDecimal> findByOrganizationAsMap(Long organization) {
        return dsl.selectFrom(ORGANIZATION_PLAN)
                .where(ORGANIZATION_PLAN.ORGANIZATION_.eq(organization))
                .fetch().stream()
                .collect(Collectors.toMap(key -> key.getCode_(), value -> value.getAmount_()));
    }

    public Optional<OrganizationPlanRecord> findRecordById(Long id) {
        return dsl.selectFrom(ORGANIZATION_PLAN)
                .where(ORGANIZATION_PLAN.ID_.eq(id))
                .fetchOptional();
    }

    public List<OrganizationPlan> findAll(Long  ownerId) {
        return dsl
                .selectFrom(ORGANIZATION_PLAN)
                .where(ORGANIZATION_PLAN.OWNER_.eq(ownerId))
                .fetch().stream().map(OrganizationPlan::to)
                .collect(Collectors.toUnmodifiableList());
    }
}
