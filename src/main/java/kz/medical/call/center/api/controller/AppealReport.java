package kz.medical.call.center.api.controller;


import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.medical.call.center.api.record.report.AppealAmount;
import kz.medical.call.center.api.record.report.AppealAmountByOperator;
import kz.medical.call.center.api.record.report.OrganizationCategoryCount;
import kz.medical.call.center.api.repository.report.ReportRepository;
import kz.medical.call.center.api.service.report.AppealReportService;

import static io.micronaut.scheduling.TaskExecutors.BLOCKING;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller("/api/v1/report")
@Secured(SecurityRule.IS_AUTHENTICATED)
@ExecuteOn(BLOCKING)
public class AppealReport {

    private static final Logger log = LoggerFactory.getLogger(AppealReport.class);
    private final AppealReportService appealReportService;
    private final ReportRepository reportRepository;


    public AppealReport(AppealReportService appealReportService, ReportRepository reportRepository) {
        this.appealReportService = appealReportService;
        this.reportRepository = reportRepository;
    }

    @Get("/general/month/{month}")
    public AppealAmount appealAmountByMonthReport(int month) {
        return this.appealReportService.appealAmountByMonthReport(month);
    }


    @Get("/operator/start/{start}/end/{end}")
    public List<AppealAmountByOperator> appealAmountByOperator(LocalDate start, LocalDate end) {
        return this.appealReportService.appealAmountByOperator(start, end);
    }

    @Get("/operator/year/{year}/quarters/{quarters}/type/{type}")
    public OrganizationCategoryCount appealAmountByOperator(Integer year, List<Integer> quarters, String type) {
        var arrayOfMonths = new ArrayList<Integer>();
        for(var quarter :  quarters) {
            addMonth(arrayOfMonths, quarter);
        }
        log.info("quarters {}", quarters);
        log.info("arrayOfMonths {}", arrayOfMonths);

        return this.reportRepository.getOrganizationCategoryReport(year, arrayOfMonths, type);
    }

    private void addMonth(List<Integer> months, Integer quarter) {
        if(quarter == 1) {
            months.add(1);
            months.add(2);
            months.add(3);
        } else if(quarter == 2) {
            months.add(4);
            months.add(5);
            months.add(6);
        } else if(quarter == 3) {
            months.add(7);
            months.add(8);
            months.add(9);
        } else if(quarter == 4) {
            months.add(10);
            months.add(11);
            months.add(12);
        }
    }
}
