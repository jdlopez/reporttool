package es.jdlopez.reporttool.domain;

import lombok.Data;

import java.util.List;

@Data
public class Report {
    private String id;
    private String name;
    private String description;
    private String sql;
    private String repositoryId;
}
