package kz.vostok.shop.survey.api.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.vostok.shop.survey.api.record.Appeal;
import kz.vostok.shop.survey.api.record.page.AppealPage;
import kz.vostok.shop.survey.api.repository.AppealRepository;
import kz.vostok.shop.survey.api.repository.MedicalCallCenterUserRepository;
import kz.vostok.shop.survey.api.service.AppealService;

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
    public Appeal create(Principal principal, Appeal appeal) {
        var user = medicalCallCenterUserRepository.fetchUser(principal.getName());
        return this.appealRepository.create(appeal, user.id());
    }

    @Put
    public Appeal update(Principal principal, Appeal appeal) {
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
}
