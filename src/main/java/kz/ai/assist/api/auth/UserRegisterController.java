package kz.ai.assist.api.auth;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@CrossOrigin
@Controller
@Secured(SecurityRule.IS_ANONYMOUS)
public class UserRegisterController {


//    @Value("${javamail.authentication.username}")
//    public String from;
//    private SteamUserRepository steamUserRepository;
//    private ForgetPasswordCodeRepository forgetPasswordCodeRepository;
//    private EmailService emailService;
//    private Logger logger = LoggerFactory.getLogger(UserRegisterController.class);
//
//    private UserRegisterService userRegisterService;
//
//    public UserRegisterController(UserRegisterService userRegisterService, ForgetPasswordCodeRepository forgetPasswordCodeRepository, EmailService emailService) {
//        this.userRegisterService = userRegisterService;
//        this.forgetPasswordCodeRepository = forgetPasswordCodeRepository;
//        this.emailService = emailService;
//    }
//
//    @Post("/register")
//    public HttpResponse register(@Body SteamUserAndOtp userBody) {
//        var result = userRegisterService.register(userBody);
////        if (userOptional.isPresent()) {
//////            return HttpResponse.status(500, "user_already_registered").contentType(MediaType.APPLICATION_JSON);
////            return HttpResponse.serverError("user_already_registered");
////        }
//        return HttpResponse.ok(result);
//    }
//
//    @Get("/on-forgot-password/send-to/{to}")
//    public boolean forgotPassword(String to) {
//        logger.info("Start sending password reset code to username!");
//        try {
//            var userOptional = steamUserRepository.findByUsername(to);
//            if (userOptional.isPresent()) {
//                var user = userOptional.get();
//                var email = user.getEmail_();
//                var code = PasswordUtil.forgotPasswordCode(6);
//                forgetPasswordCodeRepository.deactivatePrev(email);
//                forgetPasswordCodeRepository.create(new ForgetPasswordCode(null, email, code));
//                return emailService.sendForgotPasswordCode(from, to, code);
//            } else {
//                logger.warn("No such user {}", to);
//                return false;
//            }
//        } catch (Exception e) {
//            logger.error("Error on password reset code", e);
//            throw new SteamException("Email send error");
//        }
//    }

}
