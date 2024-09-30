package kz.vostok.shop.survey.api.repository;

import kz.jooq.model.tables.records.AnswerRecord;
import kz.vostok.shop.survey.api.record.Answer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.Answer.ANSWER;
import static org.jooq.Records.mapping;

public interface AbstractRepository<T, R> {
    T create(T t);
    T update(T t);
    List<T> findAll();
    Optional<R> findRecordById(Long id);
    T findById(Long id);
    int remove(Long id);
}
