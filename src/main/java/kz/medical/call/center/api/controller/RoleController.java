package kz.medical.call.center.api.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.medical.call.center.api.record.Role;
import kz.medical.call.center.api.repository.RoleRepository;

import java.util.List;

@Controller("/api/v1/role")
@Secured(SecurityRule.IS_ANONYMOUS)
public class RoleController {

    private RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Get("/find/all")
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

}
