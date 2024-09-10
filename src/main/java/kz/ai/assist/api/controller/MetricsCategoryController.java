package kz.ai.assist.api.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.server.cors.CrossOrigin;
import kz.ai.assist.api.record.MetricsCategory;
import kz.ai.assist.api.repository.MetricsCategoryRepository;

import java.util.List;

@CrossOrigin
@Controller("/api/v1/metrics-category")
public class MetricsCategoryController {

    private MetricsCategoryRepository metricsCategoryRepository;

    public MetricsCategoryController(MetricsCategoryRepository metricsCategoryRepository) {
        this.metricsCategoryRepository = metricsCategoryRepository;
    }

    @Get("/find-all")
    public List<MetricsCategory> findAll() {
        return metricsCategoryRepository.findAll();
    }
}
