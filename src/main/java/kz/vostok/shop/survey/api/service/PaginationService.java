package kz.vostok.shop.survey.api.service;

import java.util.List;

public interface PaginationService<S, SP> {

    SP page(int page, int size, Long reference);

}