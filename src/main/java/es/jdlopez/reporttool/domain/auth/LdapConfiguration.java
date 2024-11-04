package es.jdlopez.reporttool.domain.auth;

import lombok.Data;

@Data
public class LdapConfiguration {
    private String serverUrl;
    private String bindDN;
    private String password;

}
