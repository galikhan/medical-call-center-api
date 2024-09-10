package kz.ai.assist.api.controller;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.server.cors.CrossOrigin;
import kz.ai.assist.api.record.OrganizationPlan;
import kz.ai.assist.api.repository.OrganizationPlanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin
@Controller("/api/v1/organization-plan")
public class OrganizationPlanController {

    private OrganizationPlanRepository organizationPlanRepository;
    private Logger log = LoggerFactory.getLogger(OrganizationPlanController.class);

    public OrganizationPlanController(OrganizationPlanRepository organizationPlanRepository) {
        this.organizationPlanRepository = organizationPlanRepository;
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

}
