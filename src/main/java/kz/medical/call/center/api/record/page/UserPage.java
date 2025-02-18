package kz.medical.call.center.api.record.page;

import io.micronaut.serde.annotation.Serdeable;
import kz.medical.call.center.api.record.user.UserNoPassword;

import java.util.List;

@Serdeable
public record UserPage(Integer total, List<UserNoPassword> data) {
}