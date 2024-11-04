package es.jdlopez.reporttool.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class AuthServiceRest {

    @GetMapping("/profile")
    public Principal profile(Principal principal) {
        return principal;
    }

}
