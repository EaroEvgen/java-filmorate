package ru.yandex.practicum.filmorate.storage.film;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.FilmorateNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Long, Film> films = new HashMap<>();

    @Override
    public Collection<Film> findAll() {
        return films.values();
    }

    @Override
    public Film create(Film film) {
        film.setId(getNextId());
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public Film update(Film newFilm) {
        if (!films.containsKey(newFilm.getId())) {
            throw new FilmorateNotFoundException("Фильм с id = " + newFilm.getId() + " не найден");
        }
        films.put(newFilm.getId(), newFilm);
        return newFilm;
    }

    @Override
    public Film addLike(long filmId, long userId) {
        if (!films.containsKey(filmId)) {
            throw new FilmorateNotFoundException("Фильм с id = " + filmId + " не найден");
        }
        films.get(filmId).getLikes().add(userId);
        return films.get(filmId);
    }

    @Override
    public Film deleteLike(long filmId, long userId) {
        films.get(filmId).getLikes().remove(userId);
        return films.get(filmId);
    }

    @Override
    public Collection<Film> getPopularFilms(int count) {
        return films.values().stream()
                .sorted((film1, film2) -> Integer.compare(film2.getLikes().size(), film1.getLikes().size()))
                .limit(count)
                .toList();
    }

    @Override
    public boolean contains(long filmId) {
        return films.containsKey(filmId);
    }

    private long getNextId() {
        long currentMaxId = films.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
