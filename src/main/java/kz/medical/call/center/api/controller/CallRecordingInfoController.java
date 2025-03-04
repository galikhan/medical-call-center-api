package kz.medical.call.center.api.controller;


import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.medical.call.center.api.record.CallRecordingInfo;
import kz.medical.call.center.api.repository.CallRecordingInfoRepository;
import kz.medical.call.center.api.service.CallRecordingInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

@Controller("/api/v1/call-recording-info")
@Secured(SecurityRule.IS_ANONYMOUS)
public class CallRecordingInfoController {

    private static final Logger log = LoggerFactory.getLogger(CallRecordingInfoController.class);
    private CallRecordingInfoService callRecordingInfoService;

    public CallRecordingInfoController(CallRecordingInfoService callRecordingInfoService) {
        this.callRecordingInfoService = callRecordingInfoService;
    }

    @Get("/appeal/{appealId}")
    public CallRecordingInfo findInfoByAppealId(Long appealId) {
        return this.callRecordingInfoService.findByAppealId(appealId);

    }

    @Get("/bind-info-to-appeal/appeal/{appealId}/uniqueid/{uniqueId}")
    public CallRecordingInfo loadAndBindInfoToAppeal(Long appealId, String uniqueId) throws ExecutionException, InterruptedException {
        return this.callRecordingInfoService.loadAndBindInfoToAppeal(appealId, uniqueId);

    }
}
