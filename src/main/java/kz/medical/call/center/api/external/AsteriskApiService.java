package kz.medical.call.center.api.external;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Singleton;
import kz.medical.call.center.api.external.record.Cdr;
import kz.medical.call.center.api.record.CallRecordingInfo;
import kz.medical.call.center.api.repository.AppealRepository;
import kz.medical.call.center.api.repository.CallRecordingInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

import static io.micronaut.scheduling.TaskExecutors.BLOCKING;

@Singleton
@ExecuteOn(BLOCKING)
public class AsteriskApiService {

    private static final Logger log = LoggerFactory.getLogger(AsteriskApiService.class);
    private final AppealRepository appealRepository;
    private final CallRecordingInfoRepository callRecordingInfoRepository;
    public String apiUrl = "http://89.35.124.139:8080";
    public HttpClient httpClient;

    public AsteriskApiService(AppealRepository appealRepository, HttpClient httpClient, CallRecordingInfoRepository callRecordingInfoRepository) {
        this.appealRepository = appealRepository;
        this.httpClient = httpClient;
        this.callRecordingInfoRepository = callRecordingInfoRepository;
    }

    public Mono<List<Cdr>> fetchByUniqueId(String uniqueId) {
        HttpRequest<?> request = HttpRequest.GET(apiUrl + "/api/v1/cdr/uniqueid/" + uniqueId);
        var httpResponse = httpClient.retrieve(request, Argument.listOf(Cdr.class));
        return Mono.from(httpResponse);
    }

    public void fetch() {

        var appeals = this.appealRepository.fetchAllWithUniqueIdForToday();
        appeals.forEach(appeal ->
        {
            log.info("start fetch {}", appeal.uniqueId());
            fetchByUniqueId(appeal.uniqueId())
                    .subscribe(
                            listOfCdr -> {
                                log.info("fetch size {}", listOfCdr.size());

                                listOfCdr.forEach(cdr -> {
                                    log.info("cdr {}", cdr.uniqueid());
                                    var opt = this.callRecordingInfoRepository.findRecordByAppealId(appeal.id());
                                    if (opt.isEmpty()) {
                                        var filepath = createFilepath(cdr.recordingfile(), cdr.calldate());
                                        var callRecordingInfo = new CallRecordingInfo(null, appeal.id(), cdr.uniqueid(), cdr.recordingfile(), cdr.calldate(), filepath);
                                        this.callRecordingInfoRepository.create(callRecordingInfo);
                                    } else {
                                        var record = opt.get();
                                        var filepath = createFilepath(cdr.recordingfile(), cdr.calldate());
                                        var callRecordingInfo = new CallRecordingInfo(record.getId_(), record.getAppeal_(), record.getUniqueid_(), record.getRecordingFile_(), record.getCalldate_(), filepath);
                                        this.callRecordingInfoRepository.update(callRecordingInfo);
                                    }
                                });

                            });
        });
    }

    public String createFilepath(String recordingfile, LocalDateTime calldate) {
        var year = calldate.getYear();
        var month = returnFullValue(calldate.getMonthValue());
        var day = returnFullValue(calldate.getDayOfMonth());
        return year + "/" + month + "/" + day + "/" + recordingfile;
    }

    private String returnFullValue(int dayOrMonth) {
        return dayOrMonth < 10 ? "0" + dayOrMonth : "" + dayOrMonth;
    }

}
