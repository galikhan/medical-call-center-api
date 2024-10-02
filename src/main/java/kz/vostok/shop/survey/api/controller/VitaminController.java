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
import kz.vostok.shop.survey.api.record.Vitamin;
import kz.vostok.shop.survey.api.record.VitaminCategory;
import kz.vostok.shop.survey.api.record.page.VitaminPage;
import kz.vostok.shop.survey.api.repository.VitaminRepository;
import kz.vostok.shop.survey.api.service.VitaminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin
@Controller("/api/v1/vitamin")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class VitaminController {

    private Logger log = LoggerFactory.getLogger(VitaminController.class);
    private VitaminRepository vitaminRepository;
    private VitaminService vitaminService;

    public VitaminController(VitaminRepository vitaminRepository, VitaminService vitaminService) {
        this.vitaminRepository = vitaminRepository;
        this.vitaminService = vitaminService;
    }

    @Post
    public Vitamin create(@Body Vitamin vitamin) {
        return vitaminRepository.create(vitamin);
    }

    @Put
    public Vitamin update(@Body Vitamin vitamin) {
        return vitaminRepository.update(vitamin);
    }

    @Get("/{id}")
    public Vitamin findById(Long id) {
        return vitaminRepository.findById(id);
    }

    @Get("/page/{page}/size/{size}")
    public VitaminPage page(Integer page, Integer size) {
        return vitaminService.page(page, size, null);
    }

    @Get("/category")
    public List<VitaminCategory> category() {
        log.info("caregory {}", vitaminRepository.findCategory());
        return vitaminRepository.findCategory();
    }


    @Delete("/{id}")
    public int remove(Long id) {
        return vitaminRepository.remove(id);
    }


}
