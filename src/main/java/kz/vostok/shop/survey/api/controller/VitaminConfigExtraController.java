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
import kz.vostok.shop.survey.api.record.VitaminConfigExtra;
import kz.vostok.shop.survey.api.repository.VitaminConfigExtraRepository;
import kz.vostok.shop.survey.api.repository.VitaminConfigRepository;
import kz.vostok.shop.survey.api.service.VitaminConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin
@Controller("/api/v1/vitamin-config/{vitaminConfig}/extra")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class VitaminConfigExtraController {

    private Logger log = LoggerFactory.getLogger(VitaminConfigExtraController.class);
    private VitaminConfigExtraRepository vitaminConfigExtraRepository;

    public VitaminConfigExtraController(VitaminConfigExtraRepository vitaminConfigExtraRepository) {
        this.vitaminConfigExtraRepository = vitaminConfigExtraRepository;
    }

    @Post
    public VitaminConfigExtra create(@Body VitaminConfigExtra vitamin) {
        return vitaminConfigExtraRepository.create(vitamin);
    }

    @Put
    public VitaminConfigExtra update(@Body VitaminConfigExtra vitamin) {
        return vitaminConfigExtraRepository.update(vitamin);
    }

    @Get("/{id}")
    public VitaminConfigExtra findById(Long id) {
        return vitaminConfigExtraRepository.findById(id);
    }

    @Get("/all")
    public List<VitaminConfigExtra> find(Long vitaminConfig) {
        return vitaminConfigExtraRepository.findByConfigId(vitaminConfig);
    }


    @Delete("/{id}")
    public int remove(Long id) {
        return vitaminConfigExtraRepository.remove(id);
    }


}
