package kz.medical.call.center.api.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.medical.call.center.api.repository.UserPhoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@Controller("/api/v1/user-phone")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class UserPhoneController {

    private static final Logger log = LoggerFactory.getLogger(UserPhoneController.class);
    private final UserPhoneRepository userPhoneRepository;


    public UserPhoneController(UserPhoneRepository userPhoneRepository) {
        this.userPhoneRepository = userPhoneRepository;
    }

}
