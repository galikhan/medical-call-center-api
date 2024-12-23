package kz.vostok.shop.survey.api.repository;

import java.util.List;
import java.util.Optional;

public interface AbstractRepository<T, R> {
    T create(T t);

    T update(T t);

    List<T> findAll();

    Optional<R> findRecordById(Long id);

    T findById(Long id);

    int remove(Long id);

    int total();
}
