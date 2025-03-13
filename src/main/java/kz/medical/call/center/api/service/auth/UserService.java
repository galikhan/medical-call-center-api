package kz.medical.call.center.api.service.auth;

import jakarta.inject.Singleton;
import kz.medical.call.center.api.record.user.UserWithPhones;
import kz.medical.call.center.api.repository.UserPhoneRepository;
import kz.medical.call.center.api.repository.auth.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

@Singleton
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;
    private UserPhoneRepository userPhoneRepository;

    public UserService(UserRepository userRepository, UserPhoneRepository userPhoneRepository) {
        this.userRepository = userRepository;
        this.userPhoneRepository = userPhoneRepository;
    }

    public UserWithPhones findByIin(String iin) {
        var user = userRepository.findByIin(iin);
        var userWithPhones = UserWithPhones.empty();
        if (user.id() != null) {
            var userPhones = this.userPhoneRepository.findByUser(user.id());
            var phones = userPhones.stream().map(userPhone -> userPhone.phone()).collect(Collectors.toUnmodifiableList());
            userWithPhones = UserWithPhones.to(user, phones);
        }
        return userWithPhones;
    }

    public UserWithPhones findByPhone(String phone) {
        var user = userRepository.findByPhone(phone);
        var userWithPhones = UserWithPhones.empty();
        if (user.id() != null){
            var userPhones = this.userPhoneRepository.findByUser(user.id());
            var phones = userPhones.stream().map(userPhone -> userPhone.phone()).collect(Collectors.toUnmodifiableList());
            userWithPhones = UserWithPhones.to(user, phones);
        }
        return userWithPhones;
    }
}
