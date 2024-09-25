package kz.ai.assist.api.controller;

import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.ai.assist.api.record.ChartData;
import kz.ai.assist.api.record.NgxChartData;
import kz.ai.assist.api.record.consume.ConsumeChartData;
import kz.ai.assist.api.record.consume.ConsumeResult;
import kz.ai.assist.api.repository.ChartDataRepository;
import kz.ai.assist.api.service.ConsumeChartDataService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.server.cors.CrossOrigin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@Controller("/api/v1/chart-data")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class ChartDataController {

    private Logger log = LoggerFactory.getLogger(ChartDataController.class);

    private ChartDataRepository chartDataRepository;
    private ConsumeChartDataService consumeChartDataService;

    public ChartDataController(ChartDataRepository chartDataRepository, ConsumeChartDataService consumeChartDataService) {
        this.chartDataRepository = chartDataRepository;
        this.consumeChartDataService = consumeChartDataService;
    }

    @Get("/code/{code}/start/{startDate}/end{endData}")
    public List<ChartData> findByCodeAndStartEndDate(String code, LocalDate startDate, LocalDate endData) {
        return chartDataRepository.findByCodeAndDateRange(code, startDate, endData);
    }

    @Get("/date-mode/{dateMode}/day/{day}/month/{month}/year/{year}/code/{code}")
    public List<NgxChartData> findByCodeAndDatePeriod(String dateMode, int day, int month, int year, String code) {
        System.out.println("hello");
        log.info("day, {}, month {}, year {}", day, month, year);
        return consumeChartDataService.findByCodeAndDatePeriod(dateMode, day, month, year, code);
    }

    @Post("/consume")
    public ConsumeResult consume(@Body ConsumeChartData data) {
        return consumeChartDataService.consume(data);
    }


}
