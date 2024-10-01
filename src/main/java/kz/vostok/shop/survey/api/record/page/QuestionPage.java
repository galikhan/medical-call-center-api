package kz.vostok.shop.survey.api.record.page;

import io.micronaut.serde.annotation.Serdeable;
import kz.vostok.shop.survey.api.record.Question;

import java.util.List;

@Serdeable
public record QuestionPage(Integer total, List<Question> data) {
}
