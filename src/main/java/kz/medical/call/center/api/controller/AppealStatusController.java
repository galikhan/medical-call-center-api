package kz.medical.call.center.api.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.medical.call.center.api.record.AppealStatus;
import kz.medical.call.center.api.repository.AppealStatusRepository;

import java.util.List;

@Controller("/api/v1/appeal-status")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class AppealStatusController {

    public AppealStatusRepository appealStatusRepository;

    public AppealStatusController(AppealStatusRepository appealStatusRepository) {
        this.appealStatusRepository = appealStatusRepository;
    }

    @Get("/view/all")
    public List<AppealStatus> findAll() {
        return this.appealStatusRepository.findByAll();
    }

}
