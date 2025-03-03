package ru.yandex.practicum.filmorate.service;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.FilmorateNotFoundException;
import ru.yandex.practicum.filmorate.exception.FilmorateOtherException;
import ru.yandex.practicum.filmorate.exception.FilmorateValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.time.Instant;
import java.util.Collection;

@Slf4j
@Service
public class FilmService {
    private final FilmStorage films;
    private final UserStorage users;
    private static final Instant MAX_FILM_AGE = Instant.ofEpochMilli(-2335564800000L);
    private static final int MAX_LENGTH_DESCRIPTION = 200;


    @Autowired
    public FilmService(FilmStorage films, UserStorage users) {
        this.films = films;
        this.users = users;
    }

    public Collection<Film> findAll() {
        return films.findAll();
    }

    public Film create(@Valid Film film) {
        validateFilm(film);
        return films.create(film);
    }

    public Film update(@Valid Film newFilm) {
        validateFilm(newFilm);
        return films.update(newFilm);
    }

    private void validateFilm(Film film) {
        if (film.getReleaseDate().isBefore(MAX_FILM_AGE)) {
            log.info("Этот фильм староват");
            throw new FilmorateValidationException("Этот фильм староват");
        }
        if (film.getName() == null || film.getName().isBlank()) {
            log.info("Имя фильма не может быть пустым");
            throw new FilmorateValidationException("Имя фильма не может быть пустым");
        }
        if (film.getDescription() == null || film.getDescription().length() >= MAX_LENGTH_DESCRIPTION) {
            log.info("Максимальная длина описания {}", MAX_LENGTH_DESCRIPTION);
            throw new FilmorateValidationException("Максимальная длина описания " + MAX_LENGTH_DESCRIPTION);
        }
        if (film.getDuration() == null || film.getDuration().toSeconds() <= 0) {
            log.info("Продолжительность фильма должна быть положительным числом");
            throw new FilmorateValidationException("Продолжительность фильма должна быть положительным числом");
        }
    }

    public Film addLike(long filmId, long userId) {
        if (!films.contains(filmId)) {
            throw new FilmorateNotFoundException("Фильм с id = " + filmId + " не найден");
        }
        if (!users.contains(userId)) {
            throw new FilmorateNotFoundException("Пользователь с id = " + userId + " не найден");
        }
        return films.addLike(filmId, userId);
    }

    public Film deleteLike(long filmId, long userId) {
        if (!films.contains(filmId)) {
            throw new FilmorateNotFoundException("Фильм с id = " + filmId + " не найден");
        }
        if (!users.contains(userId)) {
            throw new FilmorateNotFoundException("Пользователь с id = " + userId + " не найден");
        }
        return films.deleteLike(filmId, userId);
    }

    public Collection<Film> getPopularFilms(int count) {
        if (count <= 0) {
            throw new FilmorateOtherException("Число записей должно быть больше нуля");
        }
        return films.getPopularFilms(count);
    }
}
