package kz.vostok.shop.survey.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.CityRecord;

@Serdeable
public record City(String code, String name) {
    public static City to(CityRecord record) {
        return new City(record.getCode_(), record.getNameRu_());
    }
}
