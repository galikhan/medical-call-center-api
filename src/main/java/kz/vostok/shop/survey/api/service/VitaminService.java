package kz.vostok.shop.survey.api.service;

import jakarta.inject.Singleton;
import kz.vostok.shop.survey.api.record.Vitamin;
import kz.vostok.shop.survey.api.record.page.VitaminPage;
import kz.vostok.shop.survey.api.repository.VitaminRepository;

import java.util.List;

@Singleton
public class VitaminService implements PaginationService<Vitamin, VitaminPage>{

    private VitaminRepository vitaminRepository;

    public VitaminService(VitaminRepository vitaminRepository) {
        this.vitaminRepository = vitaminRepository;
    }

    @Override
    public VitaminPage page(int page, int size, Long reference) {
        int offset = (page > 0 ? (page - 1) * size : size);
        var limit = size;
        var data = vitaminRepository.page(limit, offset);
        var total = vitaminRepository.total();
        return new VitaminPage(total, data);
    }
}
