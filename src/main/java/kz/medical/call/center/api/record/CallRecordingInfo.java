package kz.medical.call.center.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.CallRecordingInfoRecord;

import java.time.LocalDateTime;

@Serdeable
public record CallRecordingInfo(Long id, Long appealId, String uniqueId, String recordingFile, LocalDateTime callDate,
                                String filepath) {
    public static CallRecordingInfo empty() {
        return new CallRecordingInfo(null, null, null, null, null, null);
    }

    public static CallRecordingInfo to(CallRecordingInfoRecord record) {
        return new CallRecordingInfo(record.getId_(), record.getAppeal_(), record.getUniqueid_(), record.getRecordingFile_(), record.getCalldate_(), record.getFilepath_());
    }

}
