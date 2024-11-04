package es.jdlopez.reporttool.service;

import es.jdlopez.reporttool.domain.RTConfiguration;
import es.jdlopez.reporttool.domain.RTDataSource;
import jakarta.annotation.Nonnull;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import com.zaxxer.hikari.HikariDataSource;

@Service
public class DataSourcesService {

    private Map<String, DataSource> mapDatasources = new HashMap<>();

    public DataSourcesService(@Nonnull RTConfiguration config) {
        if (config.getSources() != null) {
            for (RTDataSource source : config.getSources()) {
                this.mapDatasources.put(source.getName(), buildDatasource(source));
            }
        }
    }

    public DataSource buildDatasource(RTDataSource source) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(source.getUrl());
        ds.setUsername(source.getUser());
        ds.setPassword(source.getPassword());
        return ds;
    }

    public DataSource getDataSource(String name) {
        return mapDatasources.get(name);
    }
}
