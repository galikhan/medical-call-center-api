package kz.vostok.shop.survey.api.service;

import jakarta.inject.Singleton;
import kz.vostok.shop.survey.api.record.VitaminConfig;
import kz.vostok.shop.survey.api.record.page.VitaminConfigPage;
import kz.vostok.shop.survey.api.repository.VitaminConfigRepository;

import java.util.List;

@Singleton
public class VitaminConfigService implements PaginationService<VitaminConfig, VitaminConfigPage> {

    private VitaminConfigRepository vitaminConfigRepository;

    public VitaminConfigService(VitaminConfigRepository vitaminConfigRepository) {
        this.vitaminConfigRepository = vitaminConfigRepository;
    }

    @Override
    public VitaminConfigPage page(int page, int size, Long reference) {
        int offset = (page > 0 ? (page - 1) * size : size);
        var limit = size;
        var data = vitaminConfigRepository.page(limit, offset);
        var total = vitaminConfigRepository.total();
        return new VitaminConfigPage(total, data);
    }
}
