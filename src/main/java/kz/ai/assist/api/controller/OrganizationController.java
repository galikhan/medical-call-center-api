package kz.ai.assist.api.controller;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.ai.assist.api.record.Organization;
import kz.ai.assist.api.repository.OrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin
@Controller("/api/v1/organization")
@Secured(SecurityRule.IS_ANONYMOUS)
public class OrganizationController {

    private OrganizationRepository organizationRepository;
    private Logger log = LoggerFactory.getLogger(OrganizationController.class);

    public OrganizationController(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Post
    public Organization create(@Body Organization org) {
        Long owner = 1l;
        return organizationRepository.create(org, owner);
    }

    @Put
    public Organization update(@Body Organization org) {
        return organizationRepository.update(org);
    }

    @Get("/find/{id}")
    public Organization findById(Long id) {
        var optOrg = organizationRepository.findRecordById(id);
        return optOrg.isPresent() ? Organization.to(optOrg.get()) : Organization.empty();
    }

    @Get("/find-all")
    public List<Organization> findAll() {
        var id = 1l;
        return organizationRepository.findAll(id);
    }


}
