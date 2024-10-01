package kz.vostok.shop.survey.api.record.page;

import io.micronaut.serde.annotation.Serdeable;
import kz.vostok.shop.survey.api.record.Survey;

import java.util.List;

@Serdeable
public record SurveyPage(Integer total, List<Survey> data) {
}
