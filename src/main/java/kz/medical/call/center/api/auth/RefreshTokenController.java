package kz.medical.call.center.api.auth;


import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.token.render.BearerAccessRefreshToken;
import kz.medical.call.center.api.record.auth.RefreshToken;
import kz.medical.call.center.api.service.auth.RefreshTokenService;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@Controller("/refresh-token")
@Secured(SecurityRule.IS_ANONYMOUS)
public class RefreshTokenController {


    private static final Logger log = LoggerFactory.getLogger(RefreshTokenController.class);
    private RefreshTokenService refreshTokenRepository;

    public RefreshTokenController(RefreshTokenService refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Post
    public BearerAccessRefreshToken refreshToken(@Body RefreshToken token) {
        log.info("token {}", token);
        return this.refreshTokenRepository.handleRefreshToken(token.refreshToken());
    }
}
