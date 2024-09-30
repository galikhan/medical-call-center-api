package kz.vostok.shop.survey.api.record;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.enums.GenderType;
import kz.jooq.model.tables.records.VitaminCategoryRecord;
import kz.jooq.model.tables.records.VitaminConfigRecord;

import java.math.BigDecimal;

@Serdeable
public record VitaminConfig(Long id, String name, String description, Float heightMin, Float heightMax, Float weightMin, Float widthMax, GenderType gender, Integer ageMin, Integer ageMax, String [] city, Long aim, Long allergy, Long [] vitamins, Boolean isRemoved) {
    public static VitaminConfig to(VitaminConfigRecord record) {
        return new VitaminConfig(record.getId_(), record.getName_(), record.getDescription_(), record.getHeightMin_(), record.getHeightMax_(), record.getWeightMin_(), record.getWeightMax_(), record.getGender_(), record.getAgeMin_(), record.getAgeMax_(), record.getCity_(), record.getAim_(), record.getAllergy_(), record.getVitamins_(), record.getIsRemoved_());
    }

    public static VitaminConfig empty() {
        return new VitaminConfig(null, null, null,null,null,null,null,null,null,null,null,null,null,null, null);
    }
}
