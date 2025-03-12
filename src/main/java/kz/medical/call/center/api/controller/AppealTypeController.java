package kz.medical.call.center.api.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.medical.call.center.api.record.AppealType;
import kz.medical.call.center.api.repository.AppealTypeRepository;

import java.util.List;

@Controller("/api/v1/appeal-type")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class AppealTypeController {

    public AppealTypeRepository appealTypeRepository;

    public AppealTypeController(AppealTypeRepository appealTypeRepository) {
        this.appealTypeRepository = appealTypeRepository;
    }

    @Get("/view/all")
    public List<AppealType> findAll() {
        return this.appealTypeRepository.findByAll();
    }

}
