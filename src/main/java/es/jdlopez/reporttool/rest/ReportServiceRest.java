package es.jdlopez.reporttool.rest;

import es.jdlopez.reporttool.domain.RTAuthorization;
import es.jdlopez.reporttool.domain.RTConfiguration;
import es.jdlopez.reporttool.domain.Report;
import es.jdlopez.reporttool.domain.auth.PermissionType;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class ReportServiceRest {

    private final RTConfiguration config;

    public ReportServiceRest(RTConfiguration config) {
        this.config = config;
    }

    // TODO: diferenciar readonly and edit ???
    @GetMapping("/reports")
    public List<String> getUserReports(HttpServletRequest request) {
        Stream<Report> rs;
        if (request.isUserInRole(PermissionType.ADMIN.toString())) {
            rs = config.getReports().stream();
        } else {
            rs = config.getReports().stream().filter(x -> testUserInRoles(request, x.getAuthorizations()));
        }
        return rs.map(x -> x.getId()).collect(Collectors.toList());
    }

    protected boolean testUserInRoles(HttpServletRequest request, List<RTAuthorization> roles) {
        for (RTAuthorization role : roles) {
            if (request.isUserInRole(role.getRoleName()))
                return true;
        }
        return false;
    }
}
