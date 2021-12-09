package com.tsapov.freshCucumbers.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class MainConfig {

  @Value("${spring.datasource.url}")
  private String url;
  @Value("${spring.datasource.username}")
  private String username;
  @Value("${spring.datasource.password}")
  private String password;

  @Bean
  public BasicDataSource dataSource() throws URISyntaxException {
//    System.out.println(uri);
//    URI dbUri = new URI(uri);
//    if (System.getenv("CLEARDB_DATABASE_URL") != null) {
//      dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
//    } else {
//      dbUri = new URI("jdbc:mysql://localhost:3306/fresh_cucumbers");
//    }

//    String username = dbUri.getUserInfo().split(":")[0];
//    String password = dbUri.getUserInfo().split(":")[1];
//    String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();

    String dbUrl = url;
    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    basicDataSource.setUrl(dbUrl);
    basicDataSource.setUsername(username);
    basicDataSource.setPassword(password);

    return basicDataSource;
  }
}
