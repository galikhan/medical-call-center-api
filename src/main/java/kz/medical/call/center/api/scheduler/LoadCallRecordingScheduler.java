package kz.medical.call.center.api.scheduler;

import io.micronaut.core.annotation.Blocking;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;
import kz.medical.call.center.api.external.AsteriskApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static io.micronaut.scheduling.TaskExecutors.BLOCKING;

@ExecuteOn(BLOCKING)
@Singleton
public class LoadCallRecordingScheduler {

    private static final Logger log = LoggerFactory.getLogger(LoadCallRecordingScheduler.class);
    private final AsteriskApiService asteriskApiService;

    public LoadCallRecordingScheduler(AsteriskApiService asteriskApiService) {
        this.asteriskApiService = asteriskApiService;
    }

    @Scheduled(cron = "0 0/30 * * * *")
    public void hourlyTask() {
        var startDate = LocalDateTime.now();
        loadCallRecordingInfoFromAsteriskByUniqueid();
        var endDate = LocalDateTime.now();
        var diff = ChronoUnit.MILLIS.between(startDate, endDate);
        log.info("loadCallRecordingInfo executed in {} millis ({}-seconds)", diff, (double)diff/1000);
    }

    private void loadCallRecordingInfoFromAsteriskByUniqueid() {
        this.asteriskApiService.fetch();
    }
}
