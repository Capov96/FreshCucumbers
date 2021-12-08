package com.tsapov.freshCucumbers.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class MainConfig {

  @Bean
  public BasicDataSource dataSource() throws URISyntaxException {
    URI dbUri;
    if (System.getenv("CLEARDB_DATABASE_URL") != null) {
      dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
    } else {
      dbUri = new URI("mysql://localhost:3306/fresh_cucumbers");
    }
    String username = dbUri.getUserInfo().split(":")[0];
    String password = dbUri.getUserInfo().split(":")[1];
    String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();

    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setUrl(dbUrl);
    basicDataSource.setUsername(username);
    basicDataSource.setPassword(password);

    return basicDataSource;
  }
}
