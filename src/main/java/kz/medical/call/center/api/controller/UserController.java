package kz.medical.call.center.api.controller;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.medical.call.center.api.record.MedicalCallCenterUser;
import kz.medical.call.center.api.record.page.UserPage;
import kz.medical.call.center.api.record.user.UserNoPassword;
import kz.medical.call.center.api.record.user.UserWithPhones;
import kz.medical.call.center.api.repository.UserPhoneRepository;
import kz.medical.call.center.api.repository.auth.UserRepository;
import kz.medical.call.center.api.service.auth.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static io.micronaut.scheduling.TaskExecutors.BLOCKING;

@CrossOrigin
@Controller("/api/v1/user")
@Secured(SecurityRule.IS_ANONYMOUS)
@ExecuteOn(BLOCKING)
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Post
    public UserWithPhones create(@Body MedicalCallCenterUser user) {
        return userService.create(user);
    }

    @Put
    public UserWithPhones update(@Body MedicalCallCenterUser user) {
        return userService.update(user);
    }

    @Get("/find/iin/{iin}")
    public UserWithPhones findByIin(String iin) {
        return userService.findByIin(iin);
    }

    @Get("/find/role/{role}")
    public List<UserNoPassword> findByRole(String role) {
        return userRepository.findByRole(role);
    }

    @Get("/find/id/{id}")
    public UserWithPhones findByIin(Long id) {
        return userService.findById(id);
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/find/phone/{phone}")
    public UserWithPhones findByPhone(String phone) {
        return userService.findByPhone(phone);
    }

    @Get("/view/page/{page}/size/{size}")
    public UserPage findPage(int page, int size) {
        return this.userRepository.findPage(page, size);
    }

}
