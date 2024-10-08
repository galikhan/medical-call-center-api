package kz.vostok.shop.survey.api.service;

import jakarta.inject.Singleton;
import kz.vostok.shop.survey.api.record.VitaminConfigParam;
import kz.vostok.shop.survey.api.record.page.VitaminConfigParamPage;
import kz.vostok.shop.survey.api.repository.VitaminConfigParamRepository;

@Singleton
public class VitaminConfigParamService implements PaginationService<VitaminConfigParam, VitaminConfigParamPage> {

    private VitaminConfigParamRepository vitaminConfigParamRepository;

    public VitaminConfigParamService(VitaminConfigParamRepository vitaminConfigRepository) {
        this.vitaminConfigParamRepository = vitaminConfigRepository;
    }

    @Override
    public VitaminConfigParamPage page(int page, int size, Long reference) {
        int offset = (page > 0 ? (page - 1) * size : size);
        var limit = size;
        var data = vitaminConfigParamRepository.page(limit, offset, reference);
        var total = vitaminConfigParamRepository.total(reference);
        return new VitaminConfigParamPage(total, data);
    }
}
