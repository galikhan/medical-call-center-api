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
import kz.vostok.shop.survey.api.record.VitaminConfig;
import kz.vostok.shop.survey.api.repository.VitaminConfigRepository;
import kz.vostok.shop.survey.api.repository.VitaminRepository;
import kz.vostok.shop.survey.api.service.VitaminConfigService;
import kz.vostok.shop.survey.api.service.VitaminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin
@Controller("/api/v1/vitamin-config")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class VitaminConfigController {

    private Logger log = LoggerFactory.getLogger(VitaminConfigController.class);
    private VitaminConfigRepository vitaminConfigRepository;
    private VitaminConfigService vitaminConfigService;


    public VitaminConfigController(VitaminConfigRepository vitaminConfigRepository, VitaminConfigService vitaminConfigService) {
        this.vitaminConfigRepository = vitaminConfigRepository;
        this.vitaminConfigService = vitaminConfigService;
    }

    @Post
    public VitaminConfig create(@Body VitaminConfig vitamin) {
        return vitaminConfigRepository.create(vitamin);
    }

    @Put
    public VitaminConfig update(@Body VitaminConfig vitamin) {
        return vitaminConfigRepository.update(vitamin);
    }

    @Get("/{id}")
    public VitaminConfig findById(Long id) {
        return vitaminConfigRepository.findById(id);
    }

    @Get("/page/{page}/size/{size}")
    public List<VitaminConfig> page(Integer page, Integer size) {
        return vitaminConfigService.page(page, size, null);
    }

    @Delete("/{id}")
    public int remove(Long id) {
        return vitaminConfigRepository.remove(id);
    }


}
