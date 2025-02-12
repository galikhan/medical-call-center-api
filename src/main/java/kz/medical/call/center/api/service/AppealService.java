package kz.medical.call.center.api.service;

import jakarta.inject.Singleton;
import kz.medical.call.center.api.record.Appeal;
import kz.medical.call.center.api.repository.AppealRepository;
import kz.medical.call.center.api.record.page.AppealPage;
import kz.medical.call.center.api.repository.MedicalCallCenterUserRepository;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Singleton
public class AppealService implements PaginationService<Appeal, AppealPage> {

    private AppealRepository appealRepository;
    private MedicalCallCenterUserRepository medicalCallCenterUserRepository;

    public AppealService(AppealRepository appealRepository,
                         MedicalCallCenterUserRepository medicalCallCenterUserRepository
                         ) {
        this.appealRepository = appealRepository;
        this.medicalCallCenterUserRepository = medicalCallCenterUserRepository;
    }

    @Override
    public AppealPage page(int page, int size, Long reference) {
        int offset = (page > 0 ? (page - 1) * size : size);
        var limit = size;

        var total = appealRepository.total();
        var data = appealRepository.page(limit, offset);
        return new AppealPage(total, data, null);
    }

    public AppealPage pageByType(String username, String type, int page, int size, Object o) {

        var user = this.medicalCallCenterUserRepository.fetchUser(username);
        var organization = user.organization();
        var total = 0;
        var data = new ArrayList<Appeal>();

        int offset = (page > 0 ? (page - 1) * size : size);
        var limit = size;

        if("specialist".equals(user.role())) {
            total = appealRepository.totalByTypeAndOrganization(type, organization);
            data = (ArrayList<Appeal>) appealRepository.pageByTypeAndOrganization(type, organization, limit, offset);
        } else {
            total = appealRepository.totalByType(type);
            data = (ArrayList<Appeal>) appealRepository.pageByType(type, limit, offset);
        }


        var ownerIds = data.stream().map(row -> row.owner()).collect(Collectors.toUnmodifiableList());
        var owners = this.medicalCallCenterUserRepository.findByIds(ownerIds);
        var onwersMap = owners.stream().collect(Collectors.toMap(item -> item.id(), item -> item));
        return new AppealPage(total, data, onwersMap);
    }
}
