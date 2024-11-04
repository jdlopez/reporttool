package es.jdlopez.reporttool.service;

import es.jdlopez.reporttool.domain.auth.AppUser;
import es.jdlopez.reporttool.domain.auth.DbAuthConfiguration;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

public class DbAuthRepository implements AuthRepository {
    private String salt = "salt";
    private final Sql2o sql2o;
    private final DbAuthConfiguration dbConfig;
    private final DataSourcesService dataSourcesService;

    public DbAuthRepository(DbAuthConfiguration db, DataSourcesService dataSourcesService) {
        this.dataSourcesService = dataSourcesService;
        if (dataSourcesService.getDataSource(db.getDataSource().getName()) != null)
            this.sql2o = new Sql2o(dataSourcesService.getDataSource(db.getDataSource().getName()));
        else
            this.sql2o = new Sql2o(dataSourcesService.buildDatasource(db.getDataSource()));
        this.dbConfig = db;
        if (db.getSaltPassword() != null && !"".equals(db.getSaltPassword()))
            salt = db.getSaltPassword();
    }

    @Override
    public AppUser findByName(String name, String password) {
        try(Connection con = sql2o.open()) {
            String p = password;
            if (dbConfig.isHashPassword())
                p = hashBase64String(salt + password);
            AppUser user = con.createQueryWithParams(dbConfig.getSqlFindUserByName(),
                            name, p)
                    .executeAndFetchUnique(AppUser.class);
            // add roles
            return user;
        }
    }

    @Override
    public List<String> findAllRoles() {
        try(Connection con = sql2o.open()) {
            return con.createQuery(dbConfig.getSqlFindAllRoles())
                    .executeAndFetch(String.class);
        }
    }

    @Override
    public List<String> findRolesByUser(String name) {
        try(Connection con = sql2o.open()) {
            return con.createQueryWithParams(dbConfig.getSqlFindRolesByUser(), name)
                    .executeAndFetch(String.class);
        }
    }

    /**  UTF-8 -> Base64 -> sha256 */
    public static String hashBase64String(String str) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
                    str.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
