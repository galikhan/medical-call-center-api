package kz.medical.call.center.api.service;

public interface PaginationService<S, SP> {

    SP page(int page, int size, Long reference);

}