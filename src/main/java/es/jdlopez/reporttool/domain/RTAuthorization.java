package es.jdlopez.reporttool.domain;

import es.jdlopez.reporttool.domain.auth.PermissionType;
import lombok.Data;

@Data
public class RTAuthorization {
    private String roleName;
    private PermissionType permission;
}
