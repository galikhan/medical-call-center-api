package kz.medical.call.center.api.controller;


import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.medical.call.center.api.record.report.AppealAmount;
import kz.medical.call.center.api.service.report.AppealReportService;

@CrossOrigin
@Controller("/api/v1/report")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class AppealReport {

    private AppealReportService appealReportService;

    public AppealReport(AppealReportService appealReportService) {
        this.appealReportService = appealReportService;
    }

    @Get("/general/month/{month}")
    public AppealAmount appealAmountByMonthReport(int month) {
        return this.appealReportService.appealAmountByMonthReport(month);
    }

}
