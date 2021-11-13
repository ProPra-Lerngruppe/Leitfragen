package com.example.registerpraktikum.Benutzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.validation.constraints.NotNull;

public record Benutzer(@NotNull String fullname, @NotNull int matrikelnr, @NotNull String github, @NotNull String unikennung) {

    @Autowired
    private static JdbcTemplate db;

}
