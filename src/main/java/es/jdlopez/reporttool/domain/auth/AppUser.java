package es.jdlopez.reporttool.domain.auth;

import lombok.Data;

import java.util.List;

@Data
public class AppUser {
    private String name;
    private List<String> roles;
}
