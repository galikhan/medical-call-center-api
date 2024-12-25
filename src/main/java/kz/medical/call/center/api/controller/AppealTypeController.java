package kz.medical.call.center.api.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.medical.call.center.api.record.Appeal;
import kz.medical.call.center.api.record.AppealType;
import kz.medical.call.center.api.record.page.AppealPage;
import kz.medical.call.center.api.repository.AppealRepository;
import kz.medical.call.center.api.repository.AppealTypeRepository;
import kz.medical.call.center.api.repository.MedicalCallCenterUserRepository;
import kz.medical.call.center.api.service.AppealService;

import java.security.Principal;
import java.util.List;

@Controller("/api/v1/appeal-type")
@Secured(SecurityRule.IS_ANONYMOUS)
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
