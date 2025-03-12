package kz.medical.call.center.api.repository.auth;

import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent;
import io.micronaut.security.token.refresh.RefreshTokenPersistence;
import jakarta.inject.Singleton;
import kz.jooq.model.tables.records.RefreshTokenRecord;
import org.jooq.DSLContext;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.HashMap;
import java.util.Optional;

import static kz.jooq.model.tables.RefreshToken.REFRESH_TOKEN;

@Singleton
public class RefreshTokenRepository {

    private static final Logger log = LoggerFactory.getLogger(RefreshTokenRepository.class);
    private DSLContext ctx;

    public RefreshTokenRepository(DSLContext ctx) {
        this.ctx = ctx;
    }

    public void persistToken(RefreshTokenGeneratedEvent event) {
        log.info("persistToken called {}", event.getRefreshToken());
        this.ctx.insertInto(REFRESH_TOKEN)
                .set(REFRESH_TOKEN.USERNAME_, event.getAuthentication().getName())
                .set(REFRESH_TOKEN.REFRESH_TOKEN_, event.getRefreshToken())
                .execute();
    }

    public Optional<RefreshTokenRecord> findByToken(String token) {
        return this.ctx.selectFrom(REFRESH_TOKEN)
                .where(REFRESH_TOKEN.REFRESH_TOKEN_.eq(token))
                .fetchOptional();
    }

}
