package es.jdlopez.reporttool.service;

import es.jdlopez.reporttool.domain.auth.AppUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoneAuthRepository implements AuthRepository {
    @Override
    public AppUser findByName(String name, String password) {
        return null;
    }

    @Override
    public List<String> findAllRoles() {
        return List.of();
    }

    @Override
    public List<String> findRolesByUser(String name) {
        return List.of();
    }
}
