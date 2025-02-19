package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.time.Instant;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {
    private final Map<Long, Film> films = new HashMap<>();
    private static final Instant MAX_FILM_AGE = Instant.ofEpochMilli(-2335564800000L);
    private static final int MAX_LENGTH_DESCRIPTION = 200;

    @GetMapping
    public Collection<Film> findAll() {
        log.info("Показать все фильмы");
        return films.values();
    }

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        // проверяем выполнение необходимых условий
        log.info("Добавить фильм");
        validateFilm(film);
        // формируем дополнительные данные
        film.setId(getNextId());
        // сохраняем новую публикацию в памяти приложения
        films.put(film.getId(), film);
        return film;
    }

    // вспомогательный метод для генерации идентификатора нового поста
    private long getNextId() {
        long currentMaxId = films.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film newFilm) throws ValidationException {
        log.info("Обновить фильм");
        // проверяем необходимые условия
        if (!films.containsKey(newFilm.getId())) {
            log.info("Фильм не найден");
            throw new ValidationException("Фильм с id = " + newFilm.getId() + " не найден");
        }

        validateFilm(newFilm);

        films.put(newFilm.getId(), newFilm);
        return newFilm;
    }

    private void validateFilm(Film film) {
        long id = film.getId();
        if (film.getReleaseDate().isBefore(MAX_FILM_AGE)) {
            log.info("Этот фильм староват");
            throw new ValidationException("Этот фильм староват");
        }
        if (film.getName() == null || film.getName().isBlank()) {
            log.info("Имя фильма не может быть пустым");
            throw new ValidationException("Имя фильма не может быть пустым");
        }
        if (film.getDescription() == null || film.getDescription().length() >= MAX_LENGTH_DESCRIPTION) {
            log.info("Максимальная длина описания {}", MAX_LENGTH_DESCRIPTION);
            throw new ValidationException("Максимальная длина описания " + MAX_LENGTH_DESCRIPTION);
        }
        if (film.getDuration() == null || film.getDuration().toSeconds() <= 0) {
            log.info("Продолжительность фильма должна быть положительным числом");
            throw new ValidationException("Продолжительность фильма должна быть положительным числом");
        }
    }
}
