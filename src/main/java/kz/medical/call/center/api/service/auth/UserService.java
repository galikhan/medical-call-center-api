package kz.medical.call.center.api.service.auth;

import io.micronaut.core.util.StringUtils;
import jakarta.inject.Singleton;
import kz.medical.call.center.api.record.MedicalCallCenterUser;
import kz.medical.call.center.api.record.UserPhone;
import kz.medical.call.center.api.record.user.UserNoPassword;
import kz.medical.call.center.api.record.user.UserWithPhones;
import kz.medical.call.center.api.repository.UserPhoneRepository;
import kz.medical.call.center.api.repository.auth.UserRepository;
import org.jooq.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Singleton
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserPhoneRepository userPhoneRepository;

    public UserService(UserRepository userRepository, UserPhoneRepository userPhoneRepository) {
        this.userRepository = userRepository;
        this.userPhoneRepository = userPhoneRepository;
    }

    public UserWithPhones findByIin(String iin) {
        var user = userRepository.findByIin(iin);
        var userWithPhones = UserWithPhones.empty();
        if (user.id() != null) {
            var userPhones = this.userPhoneRepository.findByUser(user.id());
            var phones = userPhones.stream().map(UserPhone::phone).toList();
            userWithPhones = UserWithPhones.to(user, phones);
        }
        return userWithPhones;
    }

    public UserWithPhones findByPhone(String phone) {
        var user = userRepository.findByPhone(phone);
        var userWithPhones = UserWithPhones.empty();
        if (user.id() != null){
            var userPhones = this.userPhoneRepository.findByUser(user.id());
            var phones = userPhones.stream().map(UserPhone::phone).filter(Objects::nonNull).toList();
            userWithPhones = UserWithPhones.to(user, phones);
        }
        return userWithPhones;
    }

    public UserWithPhones findById(Long id) {
        var user = userRepository.findById(id);
        var userWithPhones = UserWithPhones.empty();
        if (user.id() != null){
            var userPhones = this.userPhoneRepository.findByUser(user.id());
            var phones = userPhones.stream().map(UserPhone::phone).filter(Objects::nonNull).toList();
            userWithPhones = UserWithPhones.to(user, phones);
        }
        return userWithPhones;
    }

    public UserWithPhones create(MedicalCallCenterUser user) {
        var createdUser = userRepository.create(user);
        var phone = user.phone();
        if(StringUtils.isNotEmpty(phone)) {
            this.userPhoneRepository.create(new UserPhone(null, createdUser.id(), user.phone()));
        }
        return UserWithPhones.to(createdUser, List.of(phone));
    }

    public UserWithPhones update(MedicalCallCenterUser user) {
        var updatedUser = userRepository.update(user);
        var phone = user.phone();
        if(StringUtils.isNotEmpty(phone)) {
            var userPhoneRecord = this.userPhoneRepository.findByUserAndPhone(updatedUser.id(), phone);
            if(userPhoneRecord == null) {
                this.userPhoneRepository.create(new UserPhone(null, updatedUser.id(), phone));
            }
        }
        var phones = this.userPhoneRepository.findByUser(updatedUser.id()).stream().map(UserPhone::phone)
                .toList();
        return UserWithPhones.to(updatedUser, phones);
    }
}
