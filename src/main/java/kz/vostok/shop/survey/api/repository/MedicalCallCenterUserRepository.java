package kz.vostok.shop.survey.api.repository;

import jakarta.inject.Singleton;
import kz.jooq.model.tables.records.MedicalCallCenterUserRecord;
import kz.vostok.shop.survey.api.record.MedicalCallCenterUser;
import org.jooq.DSLContext;

import java.util.Optional;

import static kz.jooq.model.tables.MedicalCallCenterUser.MEDICAL_CALL_CENTER_USER;
import static kz.vostok.shop.survey.api.util.PasswordUtil.hashString;
import static org.jooq.Records.mapping;

@Singleton
public class MedicalCallCenterUserRepository {

    private DSLContext dsl;

    public MedicalCallCenterUserRepository(DSLContext dsl) {
        this.dsl = dsl;
    }


    public MedicalCallCenterUser create(MedicalCallCenterUser user) {

        return this.dsl
                .insertInto(MEDICAL_CALL_CENTER_USER)
                .set(MEDICAL_CALL_CENTER_USER.USERNAME_, user.username())
                .set(MEDICAL_CALL_CENTER_USER.PASSWORD_, user.password())
                .returningResult(
                        MEDICAL_CALL_CENTER_USER.ID_,
                        MEDICAL_CALL_CENTER_USER.USERNAME_,
                        MEDICAL_CALL_CENTER_USER.PASSWORD_
                ).fetchOne(mapping(MedicalCallCenterUser::new));
    }


    public MedicalCallCenterUser update(MedicalCallCenterUser user) {
        return this.dsl
                .update(MEDICAL_CALL_CENTER_USER)
                .set(MEDICAL_CALL_CENTER_USER.USERNAME_, user.username())
                .set(MEDICAL_CALL_CENTER_USER.PASSWORD_, user.password())
                .where(MEDICAL_CALL_CENTER_USER.ID_.eq(user.id()))
                .returningResult(
                        MEDICAL_CALL_CENTER_USER.ID_,
                        MEDICAL_CALL_CENTER_USER.USERNAME_,
                        MEDICAL_CALL_CENTER_USER.PASSWORD_
                ).fetchOne(mapping(MedicalCallCenterUser::new));


    }

    public Optional<MedicalCallCenterUserRecord> findByUsernameAndPassword(String username, String password) {
        return this.dsl
                .selectFrom(MEDICAL_CALL_CENTER_USER)
                .where(MEDICAL_CALL_CENTER_USER.USERNAME_.eq(username))
                .and(MEDICAL_CALL_CENTER_USER.PASSWORD_.eq(password))
                .fetchOptional();
    }

    public Optional<MedicalCallCenterUserRecord> findByUsername(String username) {
        return this.dsl
                .selectFrom(MEDICAL_CALL_CENTER_USER)
                .where(MEDICAL_CALL_CENTER_USER.USERNAME_.eq(username))
                .fetchOptional();
    }

    public MedicalCallCenterUser fetchUser(String username) {
        var result = findByUsername(username);
        return result.isPresent() ? MedicalCallCenterUser.to(result.get()) : null;
    }

    public void updatePassword(MedicalCallCenterUser userBody) {
        this.dsl.update(MEDICAL_CALL_CENTER_USER)
                .set(MEDICAL_CALL_CENTER_USER.PASSWORD_, hashString(userBody.password()))
                .where(MEDICAL_CALL_CENTER_USER.USERNAME_.eq(userBody.username()))
                .execute();
    }

    public int updatePassword(Long userId, String password) {
        return this.dsl.update(MEDICAL_CALL_CENTER_USER)
                .set(MEDICAL_CALL_CENTER_USER.PASSWORD_, hashString(password))
                .where(MEDICAL_CALL_CENTER_USER.ID_.eq(userId))
                .execute();
    }

}
