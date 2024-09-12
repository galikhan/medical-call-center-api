package kz.ai.assist.api.repository;

import jakarta.inject.Singleton;
import kz.ai.assist.api.record.AiAssistUser;
import kz.jooq.model.tables.records.AiAssistUserRecord;
import org.jooq.DSLContext;

import java.util.Optional;

import static kz.jooq.model.tables.AiAssistUser.AI_ASSIST_USER;
import static org.jooq.Records.mapping;

@Singleton
public class AiAssistUserRepository {

    private DSLContext dsl;

    public AiAssistUserRepository(DSLContext dsl) {
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
    public AiAssistUser create(AiAssistUser user) {

        return this.dsl
                .insertInto(AI_ASSIST_USER)
                .set(AI_ASSIST_USER.USERNAME_, user.username())
                .set(AI_ASSIST_USER.PASSWORD_, user.password())
                .returningResult(
                        AI_ASSIST_USER.ID_,
                        AI_ASSIST_USER.USERNAME_,
                        AI_ASSIST_USER.PASSWORD_
                ).fetchOne(mapping(AiAssistUser::new));
    }


    public AiAssistUser update(AiAssistUser user) {
        return this.dsl
                .update(AI_ASSIST_USER)
                .set(AI_ASSIST_USER.USERNAME_, user.username())
                .set(AI_ASSIST_USER.PASSWORD_, user.password())
                .where(AI_ASSIST_USER.ID_.eq(user.id()))
                .returningResult(
                        AI_ASSIST_USER.ID_,
                        AI_ASSIST_USER.USERNAME_,
                        AI_ASSIST_USER.PASSWORD_
                ).fetchOne(mapping(AiAssistUser::new));


    }

//    public int remove(Long id) {
//        return this.dsl
//                .update(STEAM_USER)
//                .set(STEAM_USER.IS_REMOVED_, true)
//                .where(STEAM_USER.ID_.eq(id))
//                .execute();
//    }
//
//    public List<SteamUserLimited> findByRole(String role) {
//        return this.dsl
//                .selectFrom(STEAM_USER)
//                .where(STEAM_USER.IS_REMOVED_.eq(false))
//                .and(STEAM_USER.ROLE_.eq(role))
//                .fetch().stream().map(SteamUserLimited::to)
//                .collect(Collectors.toList());
//    }
//
//    public List<SteamUserLimited> findByRoles(List<String> roles) {
//        return this.dsl
//                .selectFrom(STEAM_USER)
//                .where(STEAM_USER.IS_REMOVED_.eq(false))
//                .and(STEAM_USER.ROLE_.in(roles))
//                .fetch().stream().map(SteamUserLimited::to)
//                .collect(Collectors.toList());
//    }
//
//    public Optional<SteamUserRecord> findById(Long responsibleUserId) {
//        return this.dsl
//                .selectFrom(STEAM_USER)
//                .where(STEAM_USER.ID_.eq(responsibleUserId))
//                .fetchOptional();
//    }

    public Optional<AiAssistUserRecord> findByUsernameAndPassword(String username, String password) {
        return this.dsl
                .selectFrom(AI_ASSIST_USER)
                .where(AI_ASSIST_USER.USERNAME_.eq(username))
                .and(AI_ASSIST_USER.PASSWORD_.eq(password))
                .fetchOptional();
    }

    public Optional<AiAssistUserRecord> findByUsername(String username) {
        return this.dsl
                .selectFrom(AI_ASSIST_USER)
                .where(AI_ASSIST_USER.USERNAME_.eq(username))
                .fetchOptional();
    }

    public AiAssistUser fetchUser(String username) {
        var result = findByUsername(username);
        return result.isPresent() ? AiAssistUser.to(result.get()) : null;
    }

//    public void updatePassword(SteamUser userBody) {
//        this.dsl.update(STEAM_USER)
//                .set(STEAM_USER.PASSWORD_, hashString(userBody.password()))
//                .where(STEAM_USER.USERNAME_.eq(userBody.username()))
//                .execute();
//    }
//
//    public int updatePassword(Long userId, String password) {
//        return this.dsl.update(STEAM_USER)
//                .set(STEAM_USER.PASSWORD_, hashString(password))
//                .where(STEAM_USER.ID_.eq(userId))
//                .execute();
//    }

}
