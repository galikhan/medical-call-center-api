package kz.vostok.shop.survey.api.service;

import jakarta.inject.Singleton;
import kz.vostok.shop.survey.api.record.Vitamin;
import kz.vostok.shop.survey.api.repository.VitaminRepository;

import java.util.List;

@Singleton
public class VitaminService implements PaginationService<Vitamin>{

    private VitaminRepository vitaminRepository;

    public VitaminService(VitaminRepository vitaminRepository) {
        this.vitaminRepository = vitaminRepository;
    }

    @Override
    public List<Vitamin> page(int page, int size, Long reference) {
        int offset = (page > 0 ? (page - 1) * size : size);
        var limit = size;
        return vitaminRepository.page(limit, offset);
    }
}
