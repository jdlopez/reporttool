package es.jdlopez.reporttool.domain.auth;

import es.jdlopez.reporttool.domain.RTDataSource;
import lombok.Data;

@Data
public class DbAuthConfiguration {
    private RTDataSource dataSource;
    private String sqlFindUserByName;
    private String sqlFindAllRoles;
    private String sqlFindRolesByUser;
    private boolean hashPassword = true;
    private String saltPassword;

}
