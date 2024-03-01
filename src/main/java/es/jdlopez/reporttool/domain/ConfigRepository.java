package es.jdlopez.reporttool.domain;

import lombok.Data;

@Data
public class ConfigRepository {
    private String jdbcDriver;
    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPass;
}
