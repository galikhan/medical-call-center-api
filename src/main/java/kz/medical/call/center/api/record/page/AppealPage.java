package kz.medical.call.center.api.record.page;

import io.micronaut.serde.annotation.Serdeable;
import kz.medical.call.center.api.record.Appeal;

import java.util.List;

@Serdeable
public record AppealPage(Integer total, List<Appeal> data) {
}
