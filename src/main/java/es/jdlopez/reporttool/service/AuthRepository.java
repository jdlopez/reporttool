package es.jdlopez.reporttool.service;

import es.jdlopez.reporttool.domain.auth.AppUser;

import java.util.List;

public interface AuthRepository {
    AppUser findByName(String name, String password);
    List<String> findAllRoles();

    List<String> findRolesByUser(String name);
}
