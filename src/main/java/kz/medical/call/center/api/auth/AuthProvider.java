package kz.medical.call.center.api.auth;

import io.micronaut.core.annotation.Blocking;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.provider.AuthenticationProvider;
import jakarta.inject.Singleton;
import kz.medical.call.center.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;


@Singleton
public class AuthProvider<T, I, S> implements AuthenticationProvider<T, I, S> {

    private Logger logger = LoggerFactory.getLogger(AuthProvider.class);
    private UserRepository userRepository;

    public AuthProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Blocking
    public @NonNull AuthenticationResponse authenticate(@Nullable T requestContext,
                                                        @NonNull AuthenticationRequest<I, S> authRequest) {

        var id = authRequest.getIdentity();
        var sec = authRequest.getSecret();
//        var password = PasswordUtil.hashString(sec.toString());
        var password = sec.toString();
        var userOptional = userRepository.findByUsernameAndPassword(id.toString(), password);

        logger.info("id {},  sec {}, user {}", id, sec, userOptional);
        if (userOptional.isPresent()) {
            var user = userOptional.get();
            var roles = List.of(user.getRole_());
            var map = new HashMap<String, Object>();
            map.put("id", user.getId_());
            return AuthenticationResponse.success(user.getUsername_(), roles, map);
        } else {
            throw AuthenticationResponse.exception();
        }
    }
}