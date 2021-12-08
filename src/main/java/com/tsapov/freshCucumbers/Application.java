package com.tsapov.freshCucumbers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Application {

  public static void main(String[] args) {
    System.out.println(System.getenv("CLEARDB_DATABASE_URL"));
    SpringApplication.run(Application.class, args);
  }

}