package kz.medical.call.center.api.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.medical.call.center.api.record.AppealCategory;
import kz.medical.call.center.api.repository.AppealCategoryRepository;

import java.util.List;

@Controller("/api/v1/appeal-category")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class AppealCategoryController {

    private AppealCategoryRepository  appealCategoryRepository;

    public AppealCategoryController(AppealCategoryRepository appealCategoryRepository) {
        this.appealCategoryRepository = appealCategoryRepository;
    }

    @Get("/view/type/{type}")
    public List<AppealCategory> findByType(String type) {
        return this.appealCategoryRepository.findByType(type);
    }

}
