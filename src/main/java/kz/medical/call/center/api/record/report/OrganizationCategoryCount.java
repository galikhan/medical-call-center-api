package kz.medical.call.center.api.record.report;

import java.util.List;

import io.micronaut.serde.annotation.Serdeable;
import kz.medical.call.center.api.record.AppealCategory;

@Serdeable
public record OrganizationCategoryCount(List<OrganizationCategoryCountItem> data, List<AppealCategory> categories) {
}
