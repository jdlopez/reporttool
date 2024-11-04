package es.jdlopez.reporttool.service;

import es.jdlopez.reporttool.domain.RTConfiguration;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class InstallService {

    private final ApplicationContext context;

    public InstallService(ApplicationContext context) {
        this.context = context;
    }

    public void installService(Object newService) {
        DefaultSingletonBeanRegistry registry = (DefaultSingletonBeanRegistry) context.getAutowireCapableBeanFactory();
        String svcName = newService.getClass().getSimpleName();
        svcName = svcName.substring(0, 1).toLowerCase() + svcName.substring(1);
        if (registry.containsSingleton(svcName)) {
            registry.destroySingleton(svcName);
            registry.registerSingleton(svcName, newService);
        } else {
            throw new RuntimeException("Service " + svcName + " does not exists");
        }
    }

    public void installAuthService(RTConfiguration cfg, DataSourcesService ds) {
        AuthService authSvc = context.getBean(AuthService.class);
        authSvc.init(cfg, ds);
    }
}
