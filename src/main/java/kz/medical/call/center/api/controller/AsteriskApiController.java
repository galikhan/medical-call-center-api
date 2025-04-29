package kz.medical.call.center.api.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.medical.call.center.api.external.AsteriskApiService;
import kz.medical.call.center.api.repository.AppealRepository;

@CrossOrigin
@Controller("/api/v1/fetch-asterisk-info")
@Secured(SecurityRule.IS_ANONYMOUS)
public class AsteriskApiController {

    public AsteriskApiService asteriskServiceApi;
    public AppealRepository appealRepository;

    public AsteriskApiController(AsteriskApiService asteriskServiceApi, AppealRepository appealRepository) {
        this.asteriskServiceApi = asteriskServiceApi;
        this.appealRepository = appealRepository;
    }

    @Get
    public void fetch() {
        this.asteriskServiceApi.fetch();
    }


    @Get("/unique-id/{uniqueId}")
    public void fetchByUniqueId(String uniqueId) {
        this.asteriskServiceApi.fetchByUniqueId(uniqueId);
    }

}
