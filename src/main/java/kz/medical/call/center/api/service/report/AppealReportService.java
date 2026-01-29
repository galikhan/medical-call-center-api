package kz.medical.call.center.api.service.report;

import jakarta.inject.Singleton;
import kz.medical.call.center.api.record.report.AppealAmount;
import kz.medical.call.center.api.record.report.AppealAmountByOperator;
import kz.medical.call.center.api.repository.AppealRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

@Singleton
public class AppealReportService {

    private static final Logger log = LoggerFactory.getLogger(AppealReportService.class);
    private final AppealRepository appealRepository;

    public AppealReportService(AppealRepository appealRepository) {
        this.appealRepository = appealRepository;
    }

    public AppealAmount appealAmountByMonthReport(int month) {
        return this.appealRepository.calcAppealAmountByMonth(month);
    }

    public List<AppealAmountByOperator> appealAmountByOperator(LocalDate start, LocalDate end) {
        return appealRepository.appealAmountByOperator(start, end);
    }
}
