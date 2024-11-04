package es.jdlopez.reporttool.domain;

import lombok.Data;

@Data
public class RTDataSource {
    private String name;
    private String driver;
    private String url;
    private String user;
    private String password;
}
