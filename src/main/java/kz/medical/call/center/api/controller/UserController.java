package kz.medical.call.center.api.controller;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.medical.call.center.api.record.MedicalCallCenterUser;
import kz.medical.call.center.api.record.page.UserPage;
import kz.medical.call.center.api.record.user.UserNoPassword;
import kz.medical.call.center.api.repository.auth.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin
@Controller("/api/v1/user")
@Secured(SecurityRule.IS_ANONYMOUS)
public class UserController {

    private UserRepository userRepository;
    private Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Post
    public UserNoPassword create(@Body MedicalCallCenterUser user) {
        return userRepository.create(user);
    }

    @Put
    public UserNoPassword update(@Body MedicalCallCenterUser user) {
        return userRepository.update(user);
    }

    @Get("/find/iin/{iin}")
    public UserNoPassword findByIin(String iin) {
        return userRepository.findByIin(iin);
    }

    @Get("/find/role/{role}")
    public List<UserNoPassword> findByRole(String role) {
        return userRepository.findByRole(role);
    }

    @Get("/find/id/{id}")
    public UserNoPassword findByIin(Long id) {
        return userRepository.findById(id);
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/find/phone/{phone}")
    public UserNoPassword findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Get("/view/page/{page}/size/{size}")
    public UserPage findPage(int page, int size) {
        return this.userRepository.findPage(page, size);
    }

}
