package kz.ai.assist.api.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.ai.assist.api.record.MetricsCategory;
import kz.ai.assist.api.repository.MetricsCategoryRepository;

import java.util.List;

@CrossOrigin
@Controller("/api/v1/metrics-category")
@Secured(SecurityRule.IS_ANONYMOUS)
public class MetricsCategoryController {

    private MetricsCategoryRepository metricsCategoryRepository;

    public MetricsCategoryController(MetricsCategoryRepository metricsCategoryRepository) {
        this.metricsCategoryRepository = metricsCategoryRepository;
    }

    @Get("/find-all")
    public List<MetricsCategory> findAll() {
        return metricsCategoryRepository.findAll();
    }

    @Get("/find/section/{type}/blocks")
    public List<MetricsCategory> findSectionsByBlockType(String type) {
        return metricsCategoryRepository.findSectionsByBlockType(type);
    }

    @Get("/find/section/{type}/all")
    public List<MetricsCategory> findAllByBlockType(String type) {
        return metricsCategoryRepository.findAllByBlockType(type);
    }

    @Get("/find/block/{code}")
    public List<MetricsCategory> findByBlock(String code) {
        return metricsCategoryRepository.findByBlock(code);
    }

    @Get("/find/metrics/{code}")
    public MetricsCategory findMetricsByCode(String code) {
        return metricsCategoryRepository.findMetricsByCode(code);
    }

    @Get("/find/metrics/codes/{codes}")
    public List<MetricsCategory> findMetricsByCodes(String [] codes) {
        return metricsCategoryRepository.findMetricsByCodes(codes);
    }

}
