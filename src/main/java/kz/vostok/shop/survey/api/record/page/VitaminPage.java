package kz.vostok.shop.survey.api.record.page;

import io.micronaut.serde.annotation.Serdeable;
import kz.vostok.shop.survey.api.record.Vitamin;

import java.util.List;

@Serdeable
public record VitaminPage(Integer total, List<Vitamin> data) {
}
