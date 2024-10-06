package kz.vostok.shop.survey.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.CityRecord;

@Serdeable
public record City(String code, String nameKk, String nameRu, String nameEn) {
    public static City to(CityRecord record) {
        return new City(record.getCode_(), record.getNameKk_(), record.getNameRu_(), record.getNameEn_())
;    }
}
