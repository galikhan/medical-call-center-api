package kz.vostok.shop.survey.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.VitaminConfigParamRecord;

@Serdeable
public record VitaminConfigParam(Long id, Long vitaminConfig, Long question, Long answer, Boolean isRemoved,
                                 Integer min, Integer max, Long[] answers) {
    public static VitaminConfigParam to(VitaminConfigParamRecord record) {
        return new VitaminConfigParam(record.getId_(), record.getVitaminConfig_(), record.getQuestion_(), record.getAnswer_(), record.getIsRemoved_(), record.getMin_(), record.getMax_(), record.getAnswers_());
    }

    public static VitaminConfigParam empty() {
        return new VitaminConfigParam(null, null, null, null, null, null, null, null);
    }
}
