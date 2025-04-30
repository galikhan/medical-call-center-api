package kz.medical.call.center.api.controller;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.medical.call.center.api.record.Appeal;
import kz.medical.call.center.api.repository.AppealRepository;
import kz.medical.call.center.api.record.page.AppealPage;
import kz.medical.call.center.api.repository.auth.UserRepository;
import kz.medical.call.center.api.service.AppealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;

@Controller("/api/v1/appeal")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class AppealController {

    public AppealRepository appealRepository;
    public AppealService appealService;
    public UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(AppealController.class);

    public AppealController(AppealRepository appealRepository, AppealService appealService, UserRepository userRepository) {
        this.appealRepository = appealRepository;
        this.appealService = appealService;
        this.userRepository = userRepository;
    }

    @Post
    public Appeal create(Principal principal, @Body Appeal appeal) {
        var user = userRepository.fetchUser(principal.getName());
        return this.appealRepository.create(appeal, user.id());
    }

    @Put
    public Appeal update(Principal principal, @Body Appeal appeal) {
        var user = userRepository.fetchUser(principal.getName());
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
    public AppealPage findPage(Principal principal, String type, int page, int size, @QueryValue("search-text") String searchText) {
        log.info("principal {} searchText {}", principal.getName(), searchText);
        return this.appealService.pageByType(principal.getName(), type, page, size, searchText);
    }

    @Delete("{id}")
    public int remove(Long id) {
        return this.appealRepository.remove(id);
    }
}
