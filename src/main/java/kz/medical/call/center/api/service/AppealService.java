package kz.medical.call.center.api.service;

import jakarta.inject.Singleton;
import kz.medical.call.center.api.record.Appeal;
import kz.medical.call.center.api.record.MedicalCallCenterUser;
import kz.medical.call.center.api.record.page.AppealPage;
import kz.medical.call.center.api.repository.AppealRepository;
import kz.medical.call.center.api.repository.auth.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static io.micronaut.core.util.StringUtils.isNotEmpty;

@Singleton
public class AppealService implements PaginationService<Appeal, AppealPage> {

    private static final Logger log = LoggerFactory.getLogger(AppealService.class);
    private final AppealRepository appealRepository;
    private final UserRepository userRepository;

    public AppealService(AppealRepository appealRepository,
                         UserRepository userRepository
    ) {
        this.appealRepository = appealRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AppealPage page(int page, int size, Long reference) {
        int offset = (page > 0 ? (page - 1) * size : size);
        var limit = size;

        var total = appealRepository.total();
        var data = appealRepository.page(limit, offset);
        return new AppealPage(total, data, null);
    }

    public AppealPage pageByType(String username, String type, int page, int size, String searchText) {

        Long id = null;
        if (isNotEmpty(searchText)) {
            try {
                id = Long.parseLong(searchText.trim());
            } catch (Exception e) {
                log.info("not id but text {}", searchText);
            }
        }

        var user = this.userRepository.fetchUser(username);
        var organization = user.organization();
        var total = 0;
        var data = new ArrayList<Appeal>();

        int offset = (page > 0 ? (page - 1) * size : size);
        var limit = size;

        if ("specialist".equals(user.role())) {
            total = appealRepository.totalByParams(type, organization, searchText, id);
            data = (ArrayList<Appeal>) appealRepository.pageByParams(type, organization, limit, offset, searchText, id);
        } else {
            total = appealRepository.totalByParams(type, null, searchText, id);
            data = (ArrayList<Appeal>) appealRepository.pageByParams(type, null, limit, offset, searchText, id);
        }
        var ownerIds = data.stream().map(Appeal::owner).toList();
        var owners = this.userRepository.findByIds(ownerIds);
        var onwersMap = owners.stream().collect(Collectors.toMap(MedicalCallCenterUser::id, item -> item));
        return new AppealPage(total, data, onwersMap);
    }

}
