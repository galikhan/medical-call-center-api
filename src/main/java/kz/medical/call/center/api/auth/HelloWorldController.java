package kz.medical.call.center.api.auth;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalUnit;
import java.time.zone.ZoneRules;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;


@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("hello")
public class HelloWorldController {

    public enum OffsetBase {
        GMT, UTC
    }

    @Get("/")
    public String sayHello()
    {
        var preNow = LocalDateTime.now();
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Almaty"));
        LocalDateTime now = LocalDateTime.now();
        ZoneId.getAvailableZoneIds().stream().filter(item -> item.contains("Almaty"))
                .forEach(zone -> {
                    var timezone = TimeZone.getTimeZone(zone);
                    System.out.println(zone);
                    System.out.println(timezone.getDisplayName());
                    System.out.println(timezone.getOffset(LocalDateTime.now().getLong(ChronoField.HOUR_OF_DAY)));
                });

        return "hello world and current time  pre - "+ preNow+" after: " + LocalDateTime.now();

    }

    @Get("/zones")
    public  void getZones() {

        Set<String> allZoneIds = ZoneId.getAvailableZoneIds().stream()
                .filter(item -> item.contains("Asia"))
                .collect(Collectors.toSet());

        // Print timezone and its offset
        for (String zoneIdStr : allZoneIds) {
            ZoneId zoneId = ZoneId.of(zoneIdStr);
            ZoneRules zoneRules = zoneId.getRules();
            ZonedDateTime now = ZonedDateTime.now(zoneId);

            // Print the timezone and its offset
            System.out.println("Timezone: " + zoneIdStr + ", Offset: " + zoneRules.getOffset(now.toInstant()));
        }
    }

    private String getOffset(LocalDateTime dateTime, ZoneId id) {
        return dateTime
                .atZone(id)
                .getOffset()
                .getId()
                .replace("Z", "+00:00");
    }
}
