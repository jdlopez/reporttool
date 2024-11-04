package es.jdlopez.reporttool.rest;

import es.jdlopez.reporttool.domain.RTConfiguration;
import es.jdlopez.reporttool.domain.RTDataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ConfigServiceRest {

    private final RTConfiguration config;

    public ConfigServiceRest(RTConfiguration config) {
        this.config = config;
    }

    @GetMapping("/config")
    public RTConfiguration getConfig() {
        return config;
    }

    @GetMapping("/source/{id}")
    public RTDataSource getDataSource(@PathVariable("id") String id) {
        return
            this.config.getSources().stream().filter(x -> x.getName().equals(id))
                    .findFirst().orElse(null);
    }

    @GetMapping("/sources")
    public List<String> getDataSourceNames() {
        return
                this.config.getSources().stream()
                        .map(x -> x.getName())
                        .collect(Collectors.toList());
    }
}
