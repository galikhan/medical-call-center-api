package kz.medical.call.center.api.service.auth;

import io.micronaut.core.annotation.Blocking;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent;
import io.micronaut.security.token.generator.AccessRefreshTokenGenerator;
import io.micronaut.security.token.refresh.RefreshTokenPersistence;
import io.micronaut.security.token.render.BearerAccessRefreshToken;
import jakarta.inject.Singleton;
import kz.medical.call.center.api.repository.auth.RefreshTokenRepository;
import kz.medical.call.center.api.repository.auth.UserRepository;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.List;

@Blocking
@Singleton
public class RefreshTokenService implements RefreshTokenPersistence {

    private static final Logger log = LoggerFactory.getLogger(RefreshTokenService.class);
    private final AccessRefreshTokenGenerator tokenGenerator;
    private RefreshTokenRepository refreshTokenRepository;
    private UserRepository userRepository;

    public RefreshTokenService(AccessRefreshTokenGenerator tokenGenerator, UserRepository userRepository, RefreshTokenRepository refreshTokenRepository) {
        this.tokenGenerator = tokenGenerator;
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public void persistToken(RefreshTokenGeneratedEvent event) {
        log.info("persistToken called {}", event.getRefreshToken());
        this.refreshTokenRepository.persistToken(event);
    }

    @Override
    public Publisher<Authentication> getAuthentication(String refreshToken) {
        log.info("getAuthentication refreshToken {}", refreshToken);
        return Flux.create(emitter -> {
            var opt = this.refreshTokenRepository.findByToken(refreshToken);
            if (opt.isPresent()) {
                var token = opt.get();
                emitter.next(Authentication.build(token.getUsername_()));
                emitter.complete();
            } else {
                emitter.error(new RuntimeException("Invalid refresh token"));
            }
        }, FluxSink.OverflowStrategy.ERROR);
    }

    public BearerAccessRefreshToken handleRefreshToken(String refreshToken) {
        var optToken = this.refreshTokenRepository.findByToken(refreshToken);
        if (optToken.isEmpty()) {
            throw new RuntimeException("Invalid refresh token");
        }
        var token = optToken.get();
        var username = token.getUsername_();
        var user = this.userRepository.fetchUser(username);
        var authentication = Authentication.build(username);
        var accessRefreshToken = tokenGenerator.generate(authentication).orElseThrow();
        return new BearerAccessRefreshToken(username, List.of(user.role()), accessRefreshToken.getExpiresIn(), accessRefreshToken.getAccessToken(), accessRefreshToken.getRefreshToken(), accessRefreshToken.getTokenType());
    }
}
