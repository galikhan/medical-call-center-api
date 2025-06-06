package kz.medical.call.center.api.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.medical.call.center.api.record.Organization;
import kz.medical.call.center.api.repository.OrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin
@Controller("/api/v1/organization")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class OrganizationController {

    private final Logger log = LoggerFactory.getLogger(OrganizationController.class);
    private final OrganizationRepository organizationRepository;

    public OrganizationController(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Post
    public Organization create(Organization organization) {
        return organizationRepository.create(organization);
    }

    @Put
    public Organization update(Organization organization) {
        return organizationRepository.update(organization);
    }

    @Get("/view/{id}")
    public Organization findById(Long id) {
        return organizationRepository.findById(id);
    }

    @Get("/view/all")
    public List<Organization> findAll() {
        return organizationRepository.findByAll();
    }

}
