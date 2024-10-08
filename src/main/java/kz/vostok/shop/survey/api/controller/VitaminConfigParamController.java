package kz.vostok.shop.survey.api.controller;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.vostok.shop.survey.api.record.VitaminConfigParam;
import kz.vostok.shop.survey.api.record.page.VitaminConfigPage;
import kz.vostok.shop.survey.api.record.page.VitaminConfigParamPage;
import kz.vostok.shop.survey.api.repository.VitaminConfigParamRepository;
import kz.vostok.shop.survey.api.service.VitaminConfigParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin
@Controller("/api/v1/vitamin-config-param")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class VitaminConfigParamController {

    private Logger log = LoggerFactory.getLogger(VitaminConfigParamController.class);
    private VitaminConfigParamRepository vitaminConfigParamRepository;
    private VitaminConfigParamService vitaminConfigParamService;


    public VitaminConfigParamController(VitaminConfigParamRepository vitaminConfigParamRepository, VitaminConfigParamService vitaminConfigParamService) {
        this.vitaminConfigParamRepository = vitaminConfigParamRepository;
        this.vitaminConfigParamService = vitaminConfigParamService;
    }

    @Post
    public VitaminConfigParam create(@Body VitaminConfigParam vitamin) {
        return vitaminConfigParamRepository.create(vitamin);
    }

    @Put
    public VitaminConfigParam update(@Body VitaminConfigParam vitamin) {
        return vitaminConfigParamRepository.update(vitamin);
    }

    @Get("/{id}")
    public VitaminConfigParam findById(Long id) {
        return vitaminConfigParamRepository.findById(id);
    }

    @Get("/all")
    public List<VitaminConfigParam> find(Long vitaminConfig) {
        return vitaminConfigParamRepository.findByConfigId(vitaminConfig);
    }

    @Get("/page/{page}/size/{size}/config/{configId}")
    public VitaminConfigParamPage page(Integer page, Integer size, Long configId) {
        return vitaminConfigParamService.page(page, size, configId);
    }


    @Delete("/{id}")
    public int remove(Long id) {
        return vitaminConfigParamRepository.remove(id);
    }


}
