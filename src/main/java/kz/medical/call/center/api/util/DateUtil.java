package kz.medical.call.center.api.util;

import io.micronaut.core.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public class DateUtil {

    public static LocalDateTime fromStringToIsoDatetime(String date) {
        var strDateWithoutZ = date.replace("Z", "");
        return LocalDateTime.parse(strDateWithoutZ, ISO_LOCAL_DATE_TIME);
    }

    public static LocalDate fromStringToIsoDate(String date) {
        var localDatetime = fromStringToIsoDatetime(date);
        return LocalDate.from(localDatetime);
    }

    public static String fromIsoDateToString(LocalDate date) {
        if(date != null) {
            var localDatetime = LocalDateTime.of(date, LocalTime.now());
            return localDatetime.format(ISO_LOCAL_DATE_TIME);
        }
        return null;
    }
}
