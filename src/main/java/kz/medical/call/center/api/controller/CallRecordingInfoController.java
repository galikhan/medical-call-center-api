package kz.medical.call.center.api.controller;


import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.medical.call.center.api.record.CallRecordingInfo;
import kz.medical.call.center.api.repository.CallRecordingInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/api/v1/call-recording-info")
@Secured(SecurityRule.IS_ANONYMOUS)
public class CallRecordingInfoController {


    private static final Logger log = LoggerFactory.getLogger(CallRecordingInfoController.class);
    private CallRecordingInfoRepository callRecordingInfoRepository;

    public CallRecordingInfoController(CallRecordingInfoRepository callRecordingInfoRepository) {
        this.callRecordingInfoRepository = callRecordingInfoRepository;
    }

    @Get("/appeal/{appealId}")
    public CallRecordingInfo findInfoByAppealId(Long appealId) {
        return this.callRecordingInfoRepository.findByAppealId(appealId);
    }
}
