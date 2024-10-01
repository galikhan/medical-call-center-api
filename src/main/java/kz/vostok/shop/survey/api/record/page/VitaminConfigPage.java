package kz.vostok.shop.survey.api.record.page;

import io.micronaut.serde.annotation.Serdeable;
import kz.vostok.shop.survey.api.record.VitaminConfig;

import java.util.List;

@Serdeable
public record VitaminConfigPage(Integer total, List<VitaminConfig> data) {
}
