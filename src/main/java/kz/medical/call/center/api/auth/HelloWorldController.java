package kz.medical.call.center.api.auth;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("hello")
public class HelloWorldController {

    @Get("/")
    public String sayHello() {
        return "hello world";
    }
}
