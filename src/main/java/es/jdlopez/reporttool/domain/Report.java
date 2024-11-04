package es.jdlopez.reporttool.domain;

import lombok.Data;

import java.util.List;

@Data
public class Report {
    private String id;
    private String title;
    private String description;
    private String author;
    private List<RTAuthorization> authorizations;
    private RTDataSource source;
    private String query;
    // display config??
}
