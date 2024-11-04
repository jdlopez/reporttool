package es.jdlopez.reporttool.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.jdlopez.reporttool.domain.RTConfiguration;
import es.jdlopez.reporttool.service.AuthService;
import es.jdlopez.reporttool.service.DataSourcesService;
import es.jdlopez.reporttool.service.InstallService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstallServiceRest {
    private final RTConfiguration appConfig;
    private final InstallService installService;
    private ObjectMapper mapper = new ObjectMapper();

    public InstallServiceRest(RTConfiguration appConfig, InstallService installService) {
        this.appConfig = appConfig;
        this.installService = installService;
    }

    @GetMapping("/install")
    public RTConfiguration get() {
        RTConfiguration ret = mapper.convertValue(appConfig, RTConfiguration.class);
        ret.setReports(null);
        // make transient or ignorable? views?
        return ret;
    }

    @PostMapping("/install")
    public void install(@RequestBody RTConfiguration cfg) {
        this.appConfig.setAuthentication(cfg.getAuthentication());
        this.appConfig.setSources(cfg.getSources());

        DataSourcesService ds = new DataSourcesService(this.appConfig);
        installService.installService(ds);
        installService.installAuthService(cfg, ds);
        this.appConfig.setInstalled(true);
    }

    @GetMapping("/installed")
    public boolean getInstalled() {
        return this.appConfig.isInstalled();
    }


}
