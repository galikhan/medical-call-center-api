package kz.vostok.shop.survey.api.service;

import jakarta.inject.Singleton;
import kz.vostok.shop.survey.api.record.Appeal;
import kz.vostok.shop.survey.api.record.page.AppealPage;
import kz.vostok.shop.survey.api.repository.AppealRepository;

@Singleton
public class AppealService implements PaginationService<Appeal, AppealPage> {

    private AppealRepository appealRepository;

    public AppealService(AppealRepository appealRepository) {
        this.appealRepository = appealRepository;
    }

    @Override
    public AppealPage page(int page, int size, Long reference) {
        int offset = (page > 0 ? (page - 1) * size : size);
        var limit = size;

        var total = appealRepository.total();
        var data = appealRepository.page(limit, offset);
        return new AppealPage(total, data);
    }
}
