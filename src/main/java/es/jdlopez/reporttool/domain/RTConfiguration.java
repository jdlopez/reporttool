package es.jdlopez.reporttool.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RTConfiguration {
    private boolean installed;
    private RTAuthentication authentication;
    private List<RTAuthorization> authorization;
    private List<RTDataSource> sources;
    private List<Report> reports;

}
