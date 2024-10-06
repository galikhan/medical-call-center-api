package kz.vostok.shop.survey.api.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.vostok.shop.survey.api.record.City;
import kz.vostok.shop.survey.api.repository.CityRepository;

import java.util.List;

import static io.micronaut.scheduling.TaskExecutors.BLOCKING;

@Secured(SecurityRule.IS_ANONYMOUS)
@CrossOrigin
@ExecuteOn(BLOCKING)
@Controller("/api/v1/city")
public class CityController {

    private CityRepository cityRepository;

    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Get("/all")
    public List<City> findAll() {
        return this.cityRepository.findAll();
    }

}
