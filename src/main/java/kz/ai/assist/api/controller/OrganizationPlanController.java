package kz.ai.assist.api.controller;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.ai.assist.api.record.OrganizationPlan;
import kz.ai.assist.api.repository.OrganizationPlanRepository;
import kz.ai.assist.api.service.OrganizationPlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller("/api/v1/organization-plan")
@Secured(SecurityRule.IS_ANONYMOUS)
public class OrganizationPlanController {

    private OrganizationPlanRepository organizationPlanRepository;
    private OrganizationPlanService organizationPlanService;
    private Logger log = LoggerFactory.getLogger(OrganizationPlanController.class);

    public OrganizationPlanController(OrganizationPlanRepository organizationPlanRepository, OrganizationPlanService organizationPlanService) {
        this.organizationPlanRepository = organizationPlanRepository;
        this.organizationPlanService = organizationPlanService;
    }

    @Post
    public OrganizationPlan create(@Body OrganizationPlan org) {
        Long owner = 1l;
        return organizationPlanRepository.create(org, owner);
    }

    @Put
    public OrganizationPlan update(@Body OrganizationPlan org) {
        return organizationPlanRepository.update(org);
    }

    @Get("/find/{id}")
    public OrganizationPlan findById(Long id) {
        var optOrg = organizationPlanRepository.findRecordById(id);
        return optOrg.isPresent() ? OrganizationPlan.to(optOrg.get()) : OrganizationPlan.empty();
    }

    @Get("/find-all")
    public List<OrganizationPlan> findAll() {
        var id = 1l;
        return organizationPlanRepository.findAll(id);
    }

    @Get("/find/plan-performance/{organizationId}")
    public Map<String, Double> planPerformance(Long organizationId) {
        return organizationPlanService.planPerformance(organizationId);
    }


}
