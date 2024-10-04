package kz.vostok.shop.survey.api.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.vostok.shop.survey.api.record.Dictionary;
import kz.vostok.shop.survey.api.repository.DictionaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin
@Controller("/api/v1/dictionary")
@Secured(SecurityRule.IS_ANONYMOUS)
public class DictionaryController {

    private Logger log = LoggerFactory.getLogger(DictionaryController.class);
    private DictionaryRepository dictionaryRepository;

    public DictionaryController(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    @Get("/key/{key}")
    public List<Dictionary> findByKey(String key) {
        return dictionaryRepository.findByKey(key);
    }

    @Get("/id/{id}")
    public Dictionary findById(Long id) {
        return dictionaryRepository.findById(id);
    }


}
