package es.jdlopez.reporttool.service;

import java.util.List;

public interface AuthRepository {
    List<String> findIdRolesFromUser(String userName);
}
