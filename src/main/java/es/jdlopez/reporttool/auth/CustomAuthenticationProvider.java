package es.jdlopez.reporttool.auth;

import es.jdlopez.reporttool.domain.auth.AppUser;
import es.jdlopez.reporttool.service.AuthService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final AuthService authService;

    public CustomAuthenticationProvider(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final String name = authentication.getName();
        final String password = authentication.getCredentials().toString();

        AppUser u = authService.getRepo().findByName(name, password);
        if (u == null) {
            final List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            for(String role: authService.getRepo().findRolesByUser(name))
                grantedAuths.add(new SimpleGrantedAuthority(role));
            final UserDetails principal = new User(name, password, grantedAuths);

            return new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
        } else {
            throw new BadCredentialsException("User not found: " + name + " or bad password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}