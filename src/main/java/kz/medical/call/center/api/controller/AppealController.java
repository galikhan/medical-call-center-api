package kz.medical.call.center.api.controller;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.medical.call.center.api.record.Appeal;
import kz.medical.call.center.api.repository.AppealRepository;
import kz.medical.call.center.api.record.page.AppealPage;
import kz.medical.call.center.api.repository.MedicalCallCenterUserRepository;
import kz.medical.call.center.api.service.AppealService;

import java.security.Principal;

@Controller("/api/v1/appeal")
@Secured(SecurityRule.IS_ANONYMOUS)
public class AppealController {

    public AppealRepository appealRepository;
    public AppealService appealService;
    public MedicalCallCenterUserRepository medicalCallCenterUserRepository;

    public AppealController(AppealRepository appealRepository, AppealService appealService, MedicalCallCenterUserRepository medicalCallCenterUserRepository) {
        this.appealRepository = appealRepository;
        this.appealService = appealService;
        this.medicalCallCenterUserRepository = medicalCallCenterUserRepository;
    }

    @Post
    public Appeal create(Principal principal, @Body Appeal appeal) {
        var user = medicalCallCenterUserRepository.fetchUser(principal.getName());
        return this.appealRepository.create(appeal, user.id());
    }

    @Put
    public Appeal update(Principal principal, @Body Appeal appeal) {
        var user = medicalCallCenterUserRepository.fetchUser(principal.getName());
        return this.appealRepository.update(appeal);
    }


    @Get("/view/{id}")
    public Appeal findById(Long id) {
        return this.appealRepository.findById(id);
    }

    @Get("/view/page/{page}/size/{size}")
    public AppealPage findPage(int page, int size) {
        return this.appealService.page(page, size, null);
    }

    @Get("/view/type/{type}/page/{page}/size/{size}")
    public AppealPage findPage(String type, int page, int size) {
        return this.appealService.pageByType(type, page, size, null);
    }

    @Delete("{id}")
    public int remove(Long id) {
        return this.appealRepository.remove(id);
    }
}
