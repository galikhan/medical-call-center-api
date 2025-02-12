package kz.medical.call.center.api.record.page;

import io.micronaut.serde.annotation.Serdeable;
import kz.medical.call.center.api.record.Appeal;
import kz.medical.call.center.api.record.MedicalCallCenterUser;

import java.util.List;
import java.util.Map;

@Serdeable
public record AppealPage(Integer total, List<Appeal> data, Map<Long, MedicalCallCenterUser> ownersMap) {
}
