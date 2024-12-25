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
import kz.medical.call.center.api.record.user.UserNoPassword;
import kz.medical.call.center.api.repository.MedicalCallCenterUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin
@Controller("/api/v1/user")
@Secured(SecurityRule.IS_ANONYMOUS)

public class MedicalCallCenterUserController {

    private MedicalCallCenterUserRepository medicalCallCenterUserRepository;
    private Logger log = LoggerFactory.getLogger(MedicalCallCenterUserController.class);

    public MedicalCallCenterUserController(MedicalCallCenterUserRepository medicalCallCenterUserRepository) {
        this.medicalCallCenterUserRepository = medicalCallCenterUserRepository;
    }

    @Post
    public UserNoPassword create(@Body MedicalCallCenterUser user) {
        var iin = user.iin();
        log.info("user {}", user);
        log.info("date {}", user.birthDate());

//        var userByIin = medicalCallCenterUserRepository.findByIin(iin);
//        if(userByIin.id() != null) {
//            var userToUpdate = new MedicalCallCenterUser(null, userByIin.username(), null, user.iin(), user.firstname(), user.lastname(), user.birthDate());
//            return medicalCallCenterUserRepository.update(userToUpdate);
//        } else {
            return medicalCallCenterUserRepository.create(user);
//        }
    }

    @Put
    public UserNoPassword update(@Body MedicalCallCenterUser user) {
        return medicalCallCenterUserRepository.update(user);
    }

    @Get("/find/iin/{iin}")
    public UserNoPassword findByIin(String iin) {
        return medicalCallCenterUserRepository.findByIin(iin);
    }

    @Get("/find/role/{role}")
    public List<UserNoPassword> findByRole(String role) {
        return medicalCallCenterUserRepository.findByRole(role);
    }

    @Get("/find/id/{id}")
    public UserNoPassword findByIin(Long id) {
        return medicalCallCenterUserRepository.findById(id);
    }
}
