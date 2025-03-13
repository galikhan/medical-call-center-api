package kz.medical.call.center.api.record.user;

import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public record UserWithPhones(Long id, String username, String iin, String firstname, String lastname, String role,
                             String fullname, Long organization, List<String> phones) {

    public static UserWithPhones to(UserNoPassword user, List<String> phones) {
        return new UserWithPhones(user.id(), user.username(), user.iin(), user.firstname(), user.lastname(), user.role(), user.fullname(), user.organization(), phones);
    }

    public static UserWithPhones empty() {
        return new UserWithPhones(null, null, null, null, null, null, null, null, null);
    }
}
