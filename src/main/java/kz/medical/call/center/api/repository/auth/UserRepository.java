package kz.medical.call.center.api.repository.auth;

import jakarta.inject.Singleton;
import kz.jooq.model.tables.records.MedicalCallCenterUserRecord;
import kz.medical.call.center.api.record.MedicalCallCenterUser;
import kz.medical.call.center.api.record.page.UserPage;
import kz.medical.call.center.api.record.user.UserNoPassword;
import kz.medical.call.center.api.util.PasswordUtil;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.MedicalCallCenterUser.MEDICAL_CALL_CENTER_USER;
import static org.jooq.Records.mapping;

@Singleton
public class UserRepository {

    private DSLContext dsl;

    public UserRepository(DSLContext dsl) {
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
                .set(MEDICAL_CALL_CENTER_USER.ROLE_, user.role())
                .set(MEDICAL_CALL_CENTER_USER.FULLNAME_, user.fullname())
                .set(MEDICAL_CALL_CENTER_USER.ORGANIZATION_, user.organization())
                .returningResult(
                        MEDICAL_CALL_CENTER_USER.ID_,
                        MEDICAL_CALL_CENTER_USER.USERNAME_,
                        MEDICAL_CALL_CENTER_USER.IIN_,
                        MEDICAL_CALL_CENTER_USER.FIRSTNAME_,
                        MEDICAL_CALL_CENTER_USER.LASTNAME_,
                        MEDICAL_CALL_CENTER_USER.ROLE_,
                        MEDICAL_CALL_CENTER_USER.FULLNAME_,
                        MEDICAL_CALL_CENTER_USER.ORGANIZATION_
                ).fetchOne(mapping(UserNoPassword::fromColumnsTo));
    }


    public UserNoPassword update(MedicalCallCenterUser user) {
        return this.dsl
                .update(MEDICAL_CALL_CENTER_USER)
                .set(MEDICAL_CALL_CENTER_USER.USERNAME_, user.username())
                .set(MEDICAL_CALL_CENTER_USER.IIN_, user.iin())
                .set(MEDICAL_CALL_CENTER_USER.FIRSTNAME_, user.firstname())
                .set(MEDICAL_CALL_CENTER_USER.LASTNAME_, user.lastname())
                .set(MEDICAL_CALL_CENTER_USER.ROLE_, user.role())
                .set(MEDICAL_CALL_CENTER_USER.FULLNAME_, user.fullname())
                .set(MEDICAL_CALL_CENTER_USER.ORGANIZATION_, user.organization())
                .where(MEDICAL_CALL_CENTER_USER.ID_.eq(user.id()))
                .returningResult(
                        MEDICAL_CALL_CENTER_USER.ID_,
                        MEDICAL_CALL_CENTER_USER.USERNAME_,
                        MEDICAL_CALL_CENTER_USER.IIN_,
                        MEDICAL_CALL_CENTER_USER.FIRSTNAME_,
                        MEDICAL_CALL_CENTER_USER.LASTNAME_,
                        MEDICAL_CALL_CENTER_USER.ROLE_,
                        MEDICAL_CALL_CENTER_USER.FULLNAME_,
                        MEDICAL_CALL_CENTER_USER.ORGANIZATION_
                ).fetchOne(mapping(UserNoPassword::fromColumnsTo));


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

    public List<MedicalCallCenterUser> findByIds(List<Long> ownerIds) {
                return this.dsl.selectFrom(MEDICAL_CALL_CENTER_USER)
                .where(MEDICAL_CALL_CENTER_USER.ID_.in(ownerIds)).fetch().stream().map(MedicalCallCenterUser::to).collect(Collectors.toUnmodifiableList());
    }

    public UserPage findPage(int page, int size) {
        int offset = (page > 0 ? (page - 1) * size : size);
        var limit = size;
        var total = total();
        var data = page(limit, offset);
        return new UserPage(total, data);
    }

    private List<UserNoPassword> page(int limit, int offset) {
        return this.dsl
                .selectFrom(MEDICAL_CALL_CENTER_USER)
                .limit(limit).offset(offset)
                .fetch().stream().map(UserNoPassword::to)
                .collect(Collectors.toUnmodifiableList());
    }

    private int total() {
        return this.dsl.selectCount().from(MEDICAL_CALL_CENTER_USER).fetch().get(0).value1();
    }

    public UserNoPassword findByPhone(String phone) {
        var record = this.dsl
                .selectFrom(MEDICAL_CALL_CENTER_USER)
                .where(MEDICAL_CALL_CENTER_USER.PHONE_.eq(phone))
                .fetchAny();
        return record != null ? UserNoPassword.to(record) : UserNoPassword.empty();
    }

}
