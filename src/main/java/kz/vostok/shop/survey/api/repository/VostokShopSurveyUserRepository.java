package kz.vostok.shop.survey.api.repository;

import jakarta.inject.Singleton;
import kz.jooq.model.tables.records.VostokShopSurveyUserRecord;
import kz.vostok.shop.survey.api.record.VostokShopSurveyUser;
import org.jooq.DSLContext;

import java.util.Optional;

import static kz.jooq.model.tables.VostokShopSurveyUser.VOSTOK_SHOP_SURVEY_USER;
import static kz.vostok.shop.survey.api.util.PasswordUtil.hashString;
import static org.jooq.Records.mapping;

@Singleton
public class VostokShopSurveyUserRepository {

    private DSLContext dsl;

    public VostokShopSurveyUserRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    //    public SteamUserLimited register(SteamUserAndOtp user) {
//
//        return this.dsl
//                .insertInto(STEAM_USER)
//                .set(STEAM_USER.USERNAME_, user.username())
//                .set(STEAM_USER.PASSWORD_, hashString(user.password()))
//                .set(STEAM_USER.ROLE_, "user")
//                .returningResult(
//                        STEAM_USER.ID_,
//                        STEAM_USER.USERNAME_,
//                        STEAM_USER.FIRSTNAME_,
//                        STEAM_USER.LASTNAME_,
//                        STEAM_USER.ROLE_,
//                        STEAM_USER.AVATAR_,
//                        STEAM_USER.EMAIL_,
//                        STEAM_USER.CITY_
//                ).fetchOne(mapping(SteamUserLimited::new));
//    }
//

    public VostokShopSurveyUser create(VostokShopSurveyUser user) {

        return this.dsl
                .insertInto(VOSTOK_SHOP_SURVEY_USER)
                .set(VOSTOK_SHOP_SURVEY_USER.USERNAME_, user.username())
                .set(VOSTOK_SHOP_SURVEY_USER.PASSWORD_, user.password())
                .returningResult(
                        VOSTOK_SHOP_SURVEY_USER.ID_,
                        VOSTOK_SHOP_SURVEY_USER.USERNAME_,
                        VOSTOK_SHOP_SURVEY_USER.PASSWORD_
                ).fetchOne(mapping(VostokShopSurveyUser::new));
    }


    public VostokShopSurveyUser update(VostokShopSurveyUser user) {
        return this.dsl
                .update(VOSTOK_SHOP_SURVEY_USER)
                .set(VOSTOK_SHOP_SURVEY_USER.USERNAME_, user.username())
                .set(VOSTOK_SHOP_SURVEY_USER.PASSWORD_, user.password())
                .where(VOSTOK_SHOP_SURVEY_USER.ID_.eq(user.id()))
                .returningResult(
                        VOSTOK_SHOP_SURVEY_USER.ID_,
                        VOSTOK_SHOP_SURVEY_USER.USERNAME_,
                        VOSTOK_SHOP_SURVEY_USER.PASSWORD_
                ).fetchOne(mapping(VostokShopSurveyUser::new));


    }

    public Optional<VostokShopSurveyUserRecord> findByUsernameAndPassword(String username, String password) {
        return this.dsl
                .selectFrom(VOSTOK_SHOP_SURVEY_USER)
                .where(VOSTOK_SHOP_SURVEY_USER.USERNAME_.eq(username))
                .and(VOSTOK_SHOP_SURVEY_USER.PASSWORD_.eq(password))
                .fetchOptional();
    }

    public Optional<VostokShopSurveyUserRecord> findByUsername(String username) {
        return this.dsl
                .selectFrom(VOSTOK_SHOP_SURVEY_USER)
                .where(VOSTOK_SHOP_SURVEY_USER.USERNAME_.eq(username))
                .fetchOptional();
    }

    public VostokShopSurveyUser fetchUser(String username) {
        var result = findByUsername(username);
        return result.isPresent() ? VostokShopSurveyUser.to(result.get()) : null;
    }

    public void updatePassword(VostokShopSurveyUser userBody) {
        this.dsl.update(VOSTOK_SHOP_SURVEY_USER)
                .set(VOSTOK_SHOP_SURVEY_USER.PASSWORD_, hashString(userBody.password()))
                .where(VOSTOK_SHOP_SURVEY_USER.USERNAME_.eq(userBody.username()))
                .execute();
    }

    public int updatePassword(Long userId, String password) {
        return this.dsl.update(VOSTOK_SHOP_SURVEY_USER)
                .set(VOSTOK_SHOP_SURVEY_USER.PASSWORD_, hashString(password))
                .where(VOSTOK_SHOP_SURVEY_USER.ID_.eq(userId))
                .execute();
    }

}
