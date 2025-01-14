package kz.medical.call.center.api.service;

import jakarta.inject.Singleton;
import kz.medical.call.center.api.record.Appeal;
import kz.medical.call.center.api.repository.AppealRepository;
import kz.medical.call.center.api.record.page.AppealPage;

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

    public AppealPage pageByType(String type, int page, int size, Object o) {
        int offset = (page > 0 ? (page - 1) * size : size);
        var limit = size;

        var total = appealRepository.totalByType(type);
        var data = appealRepository.pageByType(type, limit, offset);
        return new AppealPage(total, data);
    }
}
