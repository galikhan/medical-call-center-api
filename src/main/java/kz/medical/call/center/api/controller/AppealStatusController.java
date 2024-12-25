package kz.medical.call.center.api.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.medical.call.center.api.record.Appeal;
import kz.medical.call.center.api.record.AppealStatus;
import kz.medical.call.center.api.record.page.AppealPage;
import kz.medical.call.center.api.repository.AppealRepository;
import kz.medical.call.center.api.repository.AppealStatusRepository;
import kz.medical.call.center.api.repository.MedicalCallCenterUserRepository;
import kz.medical.call.center.api.service.AppealService;

import java.security.Principal;
import java.util.List;

@Controller("/api/v1/appeal-status")
@Secured(SecurityRule.IS_ANONYMOUS)
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
