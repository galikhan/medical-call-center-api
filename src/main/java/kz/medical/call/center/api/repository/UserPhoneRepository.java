package kz.medical.call.center.api.repository;


import jakarta.inject.Singleton;
import kz.jooq.model.tables.records.UserPhoneRecord;
import kz.medical.call.center.api.record.UserPhone;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.UserPhone.USER_PHONE;
import static org.jooq.Records.mapping;

@Singleton
public class UserPhoneRepository {


    private static final Logger log = LoggerFactory.getLogger(UserPhoneRepository.class);
    private DSLContext ctx;

    public UserPhoneRepository(DSLContext ctx) {
        this.ctx = ctx;
    }

    public UserPhone create(UserPhone userPhone) {
        return this.ctx.insertInto(USER_PHONE)
                .set(USER_PHONE.USER_, userPhone.user())
                .set(USER_PHONE.PHONE_, userPhone.phone())
                .set(USER_PHONE.CREATED_, LocalDateTime.now())
                .returningResult(
                        USER_PHONE.ID_,
                        USER_PHONE.USER_,
                        USER_PHONE.PHONE_)
                .fetchOne(mapping(UserPhone::new));
    }

    public UserPhone update(UserPhone userPhone) {
        return this.ctx.update(USER_PHONE)
                .set(USER_PHONE.USER_, userPhone.user())
                .set(USER_PHONE.PHONE_, userPhone.phone())
                .set(USER_PHONE.CREATED_, LocalDateTime.now())
                .where(USER_PHONE.ID_.eq(userPhone.id()))
                .returningResult(
                        USER_PHONE.ID_,
                        USER_PHONE.USER_,
                        USER_PHONE.PHONE_)
                .fetchOne(mapping(UserPhone::new));
    }

    public List<UserPhone> findByUser(Long userId) {
        return this.ctx
                .selectFrom(USER_PHONE)
                .where(USER_PHONE.USER_.eq(userId))
                .fetch().stream().map(UserPhone::to)
                .toList();
    }

    public UserPhoneRecord findByUserAndPhone(Long userId, String phone) {
        return this.ctx
                .selectFrom(USER_PHONE)
                .where(USER_PHONE.USER_.eq(userId))
                .and(USER_PHONE.PHONE_.eq(phone))
                .fetchAny();
    }


}
