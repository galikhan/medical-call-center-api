package kz.vostok.shop.survey.api.repository;

import jakarta.inject.Singleton;
import kz.vostok.shop.survey.api.record.City;
import org.jooq.DSLContext;

import java.util.List;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.City.CITY;

@Singleton
public class CityRepository {

    private final DSLContext dsl;

    public CityRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public List<City> findAll() {
        return this.dsl
                .selectFrom(CITY)
                .where(CITY.IS_REMOVED_.eq(false))
                .stream().map(City::to)
                .collect(Collectors.toList());
    }

}

