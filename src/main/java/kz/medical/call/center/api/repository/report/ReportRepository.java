package kz.medical.call.center.api.repository.report;

import jakarta.inject.Singleton;
import kz.medical.call.center.api.record.report.OrganizationCategoryCount;
import kz.medical.call.center.api.record.report.OrganizationCategoryCountItem;
import kz.medical.call.center.api.repository.AppealCategoryRepository;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static kz.jooq.model.tables.Organization.ORGANIZATION;
import static org.jooq.impl.DSL.*;

@Singleton
public class ReportRepository {

    private final DSLContext dsl;
    private final AppealCategoryRepository appealCategoryRepository;

    public ReportRepository(DSLContext dsl, AppealCategoryRepository appealCategoryRepository) {
        this.dsl = dsl;
        this.appealCategoryRepository = appealCategoryRepository;
    }

    public OrganizationCategoryCount getOrganizationCategoryReport(int year, List<Integer> months, String type) {
        var categories = appealCategoryRepository.findByAll();
        
        Table<Record> view = table("organization_category_view");
        
        List<Field<?>> sumFields = new ArrayList<>();
        for (var category : categories) {
            String colName = String.valueOf(category.id());
            sumFields.add(coalesce(sum(field(name(colName), Integer.class)), 0).as(colName));
        }

        List<Field<?>> selectFields = new ArrayList<>();
        selectFields.add(ORGANIZATION.NAME_);
        selectFields.addAll(sumFields);

        var results = dsl.select(selectFields)
                .from(view)
                .join(ORGANIZATION).on(field(name("organization_"), Long.class).eq(ORGANIZATION.ID_))
                .where(field(name("year_")).eq(year))
                .and(field(name("month_")).in(months))
                .and(field(name("type_")).eq(type))
                .groupBy(ORGANIZATION.NAME_)
                .fetch();

        var items = results.stream().map(record -> {
            var categoryCountMap = new HashMap<Long, Integer>();
            for (var category : categories) {
                String colName = String.valueOf(category.id());
                Integer count = record.get(colName, Integer.class);
                categoryCountMap.put(category.id(), count);
            }
            return new OrganizationCategoryCountItem(
                    record.get(ORGANIZATION.NAME_),
                    categoryCountMap
            );
        }).toList();

        return new OrganizationCategoryCount(items, categories);
    }

}
