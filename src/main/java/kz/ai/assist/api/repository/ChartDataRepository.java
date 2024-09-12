package kz.ai.assist.api.repository;

import kz.ai.assist.api.record.ChartData;
import kz.ai.assist.api.record.NgxChartData;
import jakarta.inject.Singleton;
import kz.jooq.model.tables.records.ChartDataRecord;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.ChartData.CHART_DATA;
import static org.jooq.Records.mapping;

@Singleton
public class ChartDataRepository {

    private DSLContext dsl;

    public ChartDataRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public List<ChartData> findByCodeAndDateRange(String code, LocalDate start, LocalDate end) {
        return this.dsl.selectFrom(CHART_DATA)
                .where(CHART_DATA.CODE_.eq(code))
                .and(CHART_DATA.WHEN_.between(start, end))
                .fetch()
                .stream().map(ChartData::to)
                .collect(Collectors.toUnmodifiableList());
    }

    public ChartData create(ChartData data) {
        return this.dsl.insertInto(CHART_DATA)
                .set(CHART_DATA.CODE_, data.code())
                .set(CHART_DATA.WHEN_, data.when())
                .set(CHART_DATA.AMOUNT_, data.amount())
                .set(CHART_DATA.CATEGORY_, data.category())
                .set(CHART_DATA.ORGANIZATION_, data.organization())
                .returningResult(
                        CHART_DATA.ID_,
                        CHART_DATA.CODE_,
                        CHART_DATA.AMOUNT_,
                        CHART_DATA.WHEN_,
                        CHART_DATA.CATEGORY_,
                        CHART_DATA.ORGANIZATION_
                )
                .fetchOne(mapping(ChartData::new));
    }

    public ChartData update(ChartData data) {
        return this.dsl.update(CHART_DATA)
                .set(CHART_DATA.CODE_, data.code())
                .set(CHART_DATA.WHEN_, data.when())
                .set(CHART_DATA.AMOUNT_, data.amount())
                .set(CHART_DATA.CATEGORY_, data.category())
                .set(CHART_DATA.ORGANIZATION_, data.organization())
                .set(CHART_DATA.MODIFIED_, LocalDateTime.now())
                .where(CHART_DATA.ID_.eq(data.id()))
                .returningResult(
                        CHART_DATA.ID_,
                        CHART_DATA.CODE_,
                        CHART_DATA.AMOUNT_,
                        CHART_DATA.WHEN_,
                        CHART_DATA.CATEGORY_,
                        CHART_DATA.ORGANIZATION_
                )
                .fetchOne(mapping(ChartData::new));
    }

    public Optional<ChartDataRecord> findByBinCodeAndWhenDate(Long organizationId, String code, LocalDate when) {
        return this.dsl
                .selectFrom(CHART_DATA)
                .where(CHART_DATA.ORGANIZATION_.eq(organizationId))
                .and(CHART_DATA.CODE_.eq(code))
                .and(CHART_DATA.WHEN_.eq(when))
                .fetchOptional();
    }

    public List<NgxChartData> findByCodeAndDatePeriod(Long organizationId, LocalDate start, LocalDate end, String code) {

        return this.dsl
                .selectFrom(CHART_DATA)
                .where(CHART_DATA.ORGANIZATION_.eq(organizationId))
                .and(CHART_DATA.CODE_.eq(code))
                .and(CHART_DATA.WHEN_.between(start, end))
                .orderBy(CHART_DATA.WHEN_)
                .fetch().stream().map(item -> NgxChartData.to(item))
                .collect(Collectors.toUnmodifiableList());

    }


    public BigDecimal findSumByOrganizationAndCode(Long organizationId, String code) {
        return this.dsl
                .select(DSL.sum(CHART_DATA.AMOUNT_))
                .from(CHART_DATA)
                .where(CHART_DATA.ORGANIZATION_.eq(organizationId))
                .and(CHART_DATA.CODE_.eq(code))
                .fetchSingle().value1();
    }
}
