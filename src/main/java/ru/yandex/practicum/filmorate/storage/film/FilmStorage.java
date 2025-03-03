package ru.yandex.practicum.filmorate.storage.film;

import jakarta.validation.Valid;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;

public interface FilmStorage {
    Collection<Film> findAll();

    Film create(@Valid Film film);

    Film update(@Valid Film newFilm);

    Film addLike(long filmId, long userId);

    Film deleteLike(long filmId, long userId);

    Collection<Film> getPopularFilms(int count);
}
