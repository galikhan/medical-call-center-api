package kz.vostok.shop.survey.api.service;

import jakarta.inject.Singleton;
import kz.vostok.shop.survey.api.record.VitaminConfig;
import kz.vostok.shop.survey.api.repository.VitaminConfigRepository;

import java.util.List;

@Singleton
public class VitaminConfigService implements PaginationService<VitaminConfig> {

    private VitaminConfigRepository vitaminConfigRepository;

    public VitaminConfigService(VitaminConfigRepository vitaminConfigRepository) {
        this.vitaminConfigRepository = vitaminConfigRepository;
    }

    @Override
    public List<VitaminConfig> page(int page, int size, Long reference) {
        int offset = (page > 0 ? (page - 1) * size : size);
        var limit = size;
        return vitaminConfigRepository.page(limit, offset);
    }
}
