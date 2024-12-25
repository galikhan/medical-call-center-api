package kz.medical.call.center.api.repository;

import jakarta.inject.Singleton;
import kz.jooq.model.tables.records.MedicalCallCenterUserRecord;
import kz.medical.call.center.api.record.MedicalCallCenterUser;
import kz.medical.call.center.api.record.user.UserNoPassword;
import kz.medical.call.center.api.util.PasswordUtil;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.MedicalCallCenterUser.MEDICAL_CALL_CENTER_USER;
import static org.jooq.Records.mapping;

@Singleton
public class MedicalCallCenterUserRepository {

    private DSLContext dsl;

    public MedicalCallCenterUserRepository(DSLContext dsl) {
        this.dsl = dsl;
    }


    public UserNoPassword create(MedicalCallCenterUser user) {

        return this.dsl
                .insertInto(MEDICAL_CALL_CENTER_USER)
                .set(MEDICAL_CALL_CENTER_USER.USERNAME_, user.username())
                .set(MEDICAL_CALL_CENTER_USER.PASSWORD_, user.password())
                .set(MEDICAL_CALL_CENTER_USER.IIN_, user.iin())
                .set(MEDICAL_CALL_CENTER_USER.FIRSTNAME_, user.firstname())
                .set(MEDICAL_CALL_CENTER_USER.LASTNAME_, user.lastname())
                .set(MEDICAL_CALL_CENTER_USER.GENDER_, user.gender())
                .set(MEDICAL_CALL_CENTER_USER.ROLE_, user.role())
                .returningResult(
                        MEDICAL_CALL_CENTER_USER.ID_,
                        MEDICAL_CALL_CENTER_USER.USERNAME_,
                        MEDICAL_CALL_CENTER_USER.IIN_,
                        MEDICAL_CALL_CENTER_USER.FIRSTNAME_,
                        MEDICAL_CALL_CENTER_USER.LASTNAME_,
                        MEDICAL_CALL_CENTER_USER.BIRTH_DATE_,
                        MEDICAL_CALL_CENTER_USER.GENDER_,
                        MEDICAL_CALL_CENTER_USER.ROLE_
                ).fetchOne(mapping(UserNoPassword::new));
    }


    public UserNoPassword update(MedicalCallCenterUser user) {
        return this.dsl
                .update(MEDICAL_CALL_CENTER_USER)
                .set(MEDICAL_CALL_CENTER_USER.USERNAME_, user.username())
                .set(MEDICAL_CALL_CENTER_USER.IIN_, user.iin())
                .set(MEDICAL_CALL_CENTER_USER.FIRSTNAME_, user.firstname())
                .set(MEDICAL_CALL_CENTER_USER.LASTNAME_, user.lastname())
                .set(MEDICAL_CALL_CENTER_USER.GENDER_, user.gender())
                .set(MEDICAL_CALL_CENTER_USER.ROLE_, user.role())
                .where(MEDICAL_CALL_CENTER_USER.ID_.eq(user.id()))
                .returningResult(
                        MEDICAL_CALL_CENTER_USER.ID_,
                        MEDICAL_CALL_CENTER_USER.USERNAME_,
                        MEDICAL_CALL_CENTER_USER.IIN_,
                        MEDICAL_CALL_CENTER_USER.FIRSTNAME_,
                        MEDICAL_CALL_CENTER_USER.LASTNAME_,
                        MEDICAL_CALL_CENTER_USER.BIRTH_DATE_,
                        MEDICAL_CALL_CENTER_USER.GENDER_,
                        MEDICAL_CALL_CENTER_USER.ROLE_
                ).fetchOne(mapping(UserNoPassword::new));


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
                .set(MEDICAL_CALL_CENTER_USER.PASSWORD_, PasswordUtil.hashString(userBody.password()))
                .where(MEDICAL_CALL_CENTER_USER.USERNAME_.eq(userBody.username()))
                .execute();
    }

    public int updatePassword(Long userId, String password) {
        return this.dsl.update(MEDICAL_CALL_CENTER_USER)
                .set(MEDICAL_CALL_CENTER_USER.PASSWORD_, PasswordUtil.hashString(password))
                .where(MEDICAL_CALL_CENTER_USER.ID_.eq(userId))
                .execute();
    }

    public UserNoPassword findByIin(String iin) {
        var opt = this.dsl
                .selectFrom(MEDICAL_CALL_CENTER_USER)
                .where(MEDICAL_CALL_CENTER_USER.IIN_.eq(iin))
                .fetchOptional();
        return opt.isPresent() ? UserNoPassword.to(opt.get()) : UserNoPassword.empty();
    }

    public List<UserNoPassword> findByRole(String role) {
        return this.dsl
                .selectFrom(MEDICAL_CALL_CENTER_USER)
                .where(MEDICAL_CALL_CENTER_USER.ROLE_.eq(role))
                .fetch().stream().map(UserNoPassword::to)
                .collect(Collectors.toUnmodifiableList());
    }

    public UserNoPassword findById(Long id) {
        var opt = this.dsl
                .selectFrom(MEDICAL_CALL_CENTER_USER)
                .where(MEDICAL_CALL_CENTER_USER.ID_.eq(id))
                .fetchOptional();
        return opt.isPresent() ? UserNoPassword.to(opt.get()) : UserNoPassword.empty();
    }
}
