package kz.vostok.shop.survey.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.VitaminConfigExtraRecord;

@Serdeable
public record VitaminConfigExtra(Long id, Long vitaminConfig, Long question, Long answer, Boolean isRemoved,
                                 Integer min, Integer max, Long[] answers) {
    public static VitaminConfigExtra to(VitaminConfigExtraRecord record) {
        return new VitaminConfigExtra(record.getId_(), record.getVitaminConfig_(), record.getQuestion_(), record.getAnswer_(), record.getIsRemoved_(), record.getMin_(), record.getMax_(), record.getAnswers_());
    }

    public static VitaminConfigExtra empty() {
        return new VitaminConfigExtra(null, null, null, null, null, null, null, null);
    }
}
