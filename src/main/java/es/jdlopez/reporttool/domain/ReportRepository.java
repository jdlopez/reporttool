package es.jdlopez.reporttool.domain;

import lombok.Data;

@Data
public class ReportRepository {
    private String id;
    private String name;
    private String type;
    private ConfigRepository config;
}
