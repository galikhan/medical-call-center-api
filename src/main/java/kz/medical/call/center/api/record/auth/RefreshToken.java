package kz.medical.call.center.api.record.auth;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.RefreshTokenRecord;

@Serdeable
public record RefreshToken(Long id, Long userId, String username, String refreshToken) {
    public static RefreshToken to(RefreshTokenRecord record) {
        return new RefreshToken(record.getId_(), record.getUserId_(), record.getUsername_(), record.getRefreshToken_());
    }
}
