package kz.ai.assist.api.service;

import kz.ai.assist.api.record.ChartData;
import kz.ai.assist.api.record.NgxChartData;
import kz.ai.assist.api.record.consume.ConsumeChartData;
import kz.ai.assist.api.record.consume.ConsumeResult;
import kz.ai.assist.api.repository.ChartDataRepository;
import kz.ai.assist.api.repository.MetricsCategoryRepository;
import kz.ai.assist.api.repository.OrganizationRepository;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.*;

@Singleton
public class ConsumeChartDataService {

    private Logger log = LoggerFactory.getLogger(ConsumeChartDataService.class);
    private ChartDataRepository chartDataRepository;
    private MetricsCategoryRepository metricsCategoryRepository;
    private OrganizationRepository organizationRepository;

    public ConsumeChartDataService(ChartDataRepository chartDataRepository,
                                   MetricsCategoryRepository metricsCategoryRepository,
                                    OrganizationRepository organizationRepository) {
        this.chartDataRepository = chartDataRepository;
        this.metricsCategoryRepository = metricsCategoryRepository;
        this.organizationRepository = organizationRepository;
    }

    public ConsumeResult consume(ConsumeChartData data) {

        try {
            var optOrganization = organizationRepository.findRecordByBin(data.bin());
            if(optOrganization.isEmpty()) {
                return new ConsumeResult("002", "No such organization with bin: " + data.bin());
            }
            var organization = optOrganization.get();

            data.rows().stream().forEach(row -> {

                var optCategory = metricsCategoryRepository.findRecordByCode(row.code());
                Long categoryId = null;
                if(optCategory.isPresent()) {
                    categoryId = optCategory.get().getId_();
                }

                var dateWhen = LocalDate.parse(row.when(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                var optChartData = chartDataRepository.findByBinCodeAndWhenDate(organization.getId_(), row.code(), dateWhen);
                if(optChartData.isPresent()) {
                    var oldChartData = optChartData.get();
                    var chartData = new ChartData(oldChartData.getId_(),  oldChartData.getCode_(), row.amount(), oldChartData.getWhen_(), oldChartData.getCategory_(), organization.getId_());
                    this.chartDataRepository.update(chartData);

                } else {
                    var chartData = new ChartData(null, row.code(), row.amount(), dateWhen, categoryId, organization.getId_());
                    this.chartDataRepository.create(chartData);
                }
            });

        } catch (Exception e) {
            return new ConsumeResult("100", "Unexpected error");
        }

        return new ConsumeResult("200", "success");
    }

    public List<NgxChartData> findByCodeAndDatePeriod(String dateMode, int date, int month, int year, String code) {

        log.info("date {}", date, "mon {}", month, "yerar {}", year);
        LocalDate plainDate;
        LocalDate start = null;
        LocalDate end = null;
        var normalMonth = month + 1;
        var weekLen = 7;

        if("date".equals(dateMode)) {

            start = LocalDate.of( year, normalMonth,date);
            end = LocalDate.of(year, normalMonth, date);

        } else if("week".equals(dateMode)) {

            plainDate = LocalDate.of(year, normalMonth, date);
            int dayOfWeek = plainDate.getDayOfWeek().getValue();
            var dayOfMonth = plainDate.getDayOfMonth();
            var startWeekDay = dayOfMonth - (dayOfWeek - 1);
            var endWeekDay = dayOfMonth + (7-dayOfWeek);

//            log.info("start {} end {}", startWeekDay, endWeekDay);

            start = LocalDate.of(year, normalMonth, startWeekDay);
            end = LocalDate.of(year, normalMonth,endWeekDay);

        } else if("month".equals(dateMode)) {

            start = LocalDate.of(year, normalMonth, 1);
            end = start.with(lastDayOfMonth());

        } else if ("year".equals(dateMode)) {
            start = LocalDate.of(year, 1,1);
            end = LocalDate.of(year, 12, 1);
        }

        log.info("start {}, end {}", start, end);

        var optOrganization = organizationRepository.findRecordByBin("200240011727");
        var organization = optOrganization.get();


        return chartDataRepository.findByCodeAndDatePeriod(organization.getId_(), start, end, code);
    }
}
