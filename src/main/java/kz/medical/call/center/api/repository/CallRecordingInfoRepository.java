package kz.medical.call.center.api.repository;

import jakarta.inject.Singleton;
import kz.jooq.model.tables.records.CallRecordingInfoRecord;
import kz.medical.call.center.api.record.Appeal;
import kz.medical.call.center.api.record.CallRecordingInfo;
import org.jooq.DSLContext;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

import static kz.jooq.model.tables.CallRecordingInfo.CALL_RECORDING_INFO;
import static org.jooq.Records.mapping;

@Singleton
public class CallRecordingInfoRepository {

    private DSLContext ctx;

    public CallRecordingInfoRepository(DSLContext ctx) {
        this.ctx = ctx;
    }

    public CallRecordingInfo create(CallRecordingInfo callRecordingInfo) {
        return this.ctx
                .insertInto(CALL_RECORDING_INFO)
                .set(CALL_RECORDING_INFO.CREATED_, LocalDateTime.now())
                .set(CALL_RECORDING_INFO.RECORDING_FILE_, callRecordingInfo.recordingFile())
                .set(CALL_RECORDING_INFO.APPEAL_, callRecordingInfo.appealId())
                .set(CALL_RECORDING_INFO.UNIQUEID_, callRecordingInfo.uniqueId())
                .set(CALL_RECORDING_INFO.CALLDATE_, callRecordingInfo.callDate())
                .set(CALL_RECORDING_INFO.FILEPATH_, callRecordingInfo.filepath())
                .returningResult(
                        CALL_RECORDING_INFO.ID_,
                        CALL_RECORDING_INFO.APPEAL_,
                        CALL_RECORDING_INFO.UNIQUEID_,
                        CALL_RECORDING_INFO.RECORDING_FILE_,
                        CALL_RECORDING_INFO.CALLDATE_,
                        CALL_RECORDING_INFO.FILEPATH_
                )
                .fetchOne(mapping(CallRecordingInfo::new));
    }

    public CallRecordingInfo update(CallRecordingInfo callRecordingInfo) {
        return this.ctx
                .update(CALL_RECORDING_INFO)
                .set(CALL_RECORDING_INFO.RECORDING_FILE_, callRecordingInfo.recordingFile())
                .set(CALL_RECORDING_INFO.APPEAL_, callRecordingInfo.appealId())
                .set(CALL_RECORDING_INFO.UNIQUEID_, callRecordingInfo.uniqueId())
                .set(CALL_RECORDING_INFO.CALLDATE_, callRecordingInfo.callDate())
                .set(CALL_RECORDING_INFO.FILEPATH_, callRecordingInfo.filepath())
                .where(CALL_RECORDING_INFO.ID_.eq(callRecordingInfo.id()))
                .returningResult(
                        CALL_RECORDING_INFO.ID_,
                        CALL_RECORDING_INFO.APPEAL_,
                        CALL_RECORDING_INFO.UNIQUEID_,
                        CALL_RECORDING_INFO.RECORDING_FILE_,
                        CALL_RECORDING_INFO.CALLDATE_,
                        CALL_RECORDING_INFO.FILEPATH_
                )
                .fetchOne(mapping(CallRecordingInfo::new));
    }

    public Optional<CallRecordingInfoRecord> findRecordByAppealId(Long appealId) {
        return this.ctx.selectFrom(CALL_RECORDING_INFO)
                .where(CALL_RECORDING_INFO.APPEAL_.eq(appealId))
                .fetchOptional();
    }

    public CallRecordingInfo findByAppealId(Long appealId) {
        var record = findRecordByAppealId(appealId);
        return record.isPresent() ? CallRecordingInfo.to(record.get()) : CallRecordingInfo.empty();
    }
}
