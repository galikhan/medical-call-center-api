package kz.vostok.shop.survey.api.record.page;

import io.micronaut.serde.annotation.Serdeable;
import kz.vostok.shop.survey.api.record.Appeal;

import java.util.List;

@Serdeable
public record AppealPage(Integer total, List<Appeal> data) {
}
