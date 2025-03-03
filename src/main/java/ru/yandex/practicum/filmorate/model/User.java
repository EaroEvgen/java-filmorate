package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
public class User {
    // @NotNull
    private long id;
    @Email
    @NotBlank
    @NotNull
    private String email;
    @NotNull
    private String login;
    private String name;
    @Past
    private Instant birthday;
    private Set<Long> friends = new HashSet<>();
}
