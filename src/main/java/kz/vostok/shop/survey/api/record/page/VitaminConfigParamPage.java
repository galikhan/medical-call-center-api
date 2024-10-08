package kz.vostok.shop.survey.api.record.page;

import io.micronaut.serde.annotation.Serdeable;
import kz.vostok.shop.survey.api.record.VitaminConfigParamExt;

import java.util.List;

@Serdeable
public record VitaminConfigParamPage(Integer total, List<VitaminConfigParamExt> data) {
}
