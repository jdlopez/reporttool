package es.jdlopez.reporttool.service;

import es.jdlopez.reporttool.domain.RTConfiguration;
import es.jdlopez.reporttool.domain.auth.AuthType;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private AuthRepository repo;

    public AuthRepository getRepo() {
        return repo;
    }

    public AuthService(@Nonnull RTConfiguration config, DataSourcesService dataSourcesService) {
        init(config, dataSourcesService);
    }

    protected void init(@Nonnull RTConfiguration config, DataSourcesService dataSourcesService) {
        if (config.getAuthentication() == null) {
            this.repo = new NoneAuthRepository();
        } else {
            if (config.getAuthentication().getType().equals(AuthType.DATASOURCE)) {
                this.repo = new DbAuthRepository(config.getAuthentication().getDb(),
                        dataSourcesService);
            } else { //if (config.getAuthentication().getType().equals(AuthType.LDAP)) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }
    }
}
