package es.jdlopez.reporttool.domain;

import es.jdlopez.reporttool.domain.auth.AuthType;
import es.jdlopez.reporttool.domain.auth.DbAuthConfiguration;
import es.jdlopez.reporttool.domain.auth.LdapConfiguration;
import lombok.Data;

@Data
public class RTAuthentication {
    private AuthType type;
    private LdapConfiguration ldap;
    private DbAuthConfiguration db;
}
