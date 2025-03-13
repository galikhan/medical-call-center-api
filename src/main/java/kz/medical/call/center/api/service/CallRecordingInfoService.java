package kz.medical.call.center.api.service;

import io.micronaut.core.annotation.Blocking;
import jakarta.inject.Singleton;
import kz.medical.call.center.api.external.AsteriskApiService;
import kz.medical.call.center.api.record.CallRecordingInfo;
import kz.medical.call.center.api.repository.CallRecordingInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Singleton
@Blocking
public class CallRecordingInfoService {

    private static final Logger log = LoggerFactory.getLogger(CallRecordingInfoService.class);
    private CallRecordingInfoRepository callRecordingInfoRepository;
    private AsteriskApiService asteriskApiService;

    public CallRecordingInfoService(CallRecordingInfoRepository callRecordingInfoRepository, AsteriskApiService asteriskApiService) {
        this.callRecordingInfoRepository = callRecordingInfoRepository;
        this.asteriskApiService = asteriskApiService;
    }

    public CallRecordingInfo findByAppealId(Long appealId) {
        return this.callRecordingInfoRepository.findByAppealId(appealId);
    }

    public CallRecordingInfo loadAndBindInfoToAppeal(Long appealId, String uniqueId) throws ExecutionException, InterruptedException {
        log.info("appeal {}, uniqueId {}", appealId, uniqueId);
        var future = new CompletableFuture<CallRecordingInfo>();
        this.asteriskApiService.fetchByUniqueId(uniqueId).subscribe(
                listOfCdr -> {
                    log.info("listOfCdr size = {}", listOfCdr.size());
                    if(listOfCdr.size() > 0) {
                        var cdr = listOfCdr.get(0);
                        log.info("cdr {}", cdr);
                        var opt = this.callRecordingInfoRepository.findRecordByAppealId(appealId);
                        if (opt.isEmpty()) {
                            var filepath = asteriskApiService.createFilepath(cdr.recordingfile(), cdr.calldate());
                            var callRecordingInfo = new CallRecordingInfo(null, appealId, cdr.uniqueid(), cdr.recordingfile(), cdr.calldate(), filepath);
                            var result = this.callRecordingInfoRepository.create(callRecordingInfo);
                            future.complete(result);
                        } else {
                            var record = opt.get();
                            var filepath = asteriskApiService.createFilepath(cdr.recordingfile(), cdr.calldate());
                            var callRecordingInfo = new CallRecordingInfo(record.getId_(), record.getAppeal_(), record.getUniqueid_(), record.getRecordingFile_(), record.getCalldate_(), filepath);
                            var result = this.callRecordingInfoRepository.update(callRecordingInfo);
                            future.complete(result);
                        }
                    } else {
                        log.warn("cannon find call recording by uniqueid {}", uniqueId);
                    }
                });
        return future.get();
    }
}
