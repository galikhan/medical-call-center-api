package kz.medical.call.center.api.record.report;

import io.micronaut.serde.annotation.Serdeable;

import java.util.Map;

@Serdeable
public record OrganizationCategoryCountItem(
        String organizationName,
        Map<Long, Integer> categoryCountMap
) {
}
