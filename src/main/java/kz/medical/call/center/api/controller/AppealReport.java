package kz.medical.call.center.api.controller;


import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.medical.call.center.api.record.report.AppealAmount;
import kz.medical.call.center.api.record.report.AppealAmountByOperator;
import kz.medical.call.center.api.service.report.AppealReportService;

import static io.micronaut.scheduling.TaskExecutors.BLOCKING;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@Controller("/api/v1/report")
@Secured(SecurityRule.IS_AUTHENTICATED)
@ExecuteOn(BLOCKING)
public class AppealReport {

    private final AppealReportService appealReportService;

    public AppealReport(AppealReportService appealReportService) {
        this.appealReportService = appealReportService;
    }

    @Get("/general/month/{month}")
    public AppealAmount appealAmountByMonthReport(int month) {
        return this.appealReportService.appealAmountByMonthReport(month);
    }


    @Get("/operator/start/{start}/end/{end}")
    public List<AppealAmountByOperator> appealAmountByOperator(LocalDate start, LocalDate end) {
        return this.appealReportService.appealAmountByOperator(start, end);
    }

}
