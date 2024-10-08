package kz.vostok.shop.survey.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.VitaminConfigParamRecord;
import org.jooq.Record;
import org.jooq.Record3;
import org.jooq.Record4;

@Serdeable
public record VitaminConfigParamExt(Long id, String name, Long category, Boolean isRemoved) {
    public static VitaminConfigParamExt to(Record4<Long, String, Long, Boolean> record) {
        return new VitaminConfigParamExt(record.value1(), record.value2(), record.value3(), record.value4());
    }
//    public static VitaminConfigParamExt to(VitaminConfigParamRecord record) {
//        return new VitaminConfigParamExt(record.getId_(), record.getVitaminConfig_(), record.getQuestion_(),  record.getAnswer_(), record.getIsRemoved_(), record.getMin_(), record.getMax_(), record.getAnswers_());
//    }

//    public static VitaminConfigParamExt empty() {
//        return new VitaminConfigParamExt(null, null, null, null, null, null, null, null);
//    }
}
