package kz.vostok.shop.survey.api.record.client;

import io.micronaut.serde.annotation.Serdeable;
import kz.vostok.shop.survey.api.record.Answer;
import kz.vostok.shop.survey.api.record.Dictionary;
import kz.vostok.shop.survey.api.record.Question;

import java.util.List;

@Serdeable
public record SurveyQuestion(Question question, List<Answer> answers, Integer progress, Dictionary category) {
    public static SurveyQuestion empty() {
        return new SurveyQuestion(null, null, null, null);
    }
}
