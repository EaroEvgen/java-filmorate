//package ru.yandex.practicum.filmorate.controller;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import ru.yandex.practicum.filmorate.model.Film;
//
//import java.time.Duration;
//import java.time.Instant;
//
//public class FilmControllerTest {
//
//    @Test
//    void chCreate() {
//        FilmController filmController = new FilmController();
//
//        Film film = new Film();
//        film.setName("Film1");
//        film.setDescription("Film1 description");
//        film.setReleaseDate(Instant.now());
//        film.setDuration(Duration.ofHours(1));
//
//        try {
//            filmController.create(film);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertTrue(filmController.findAll().contains(film), "Фильмы не совпадают");
//
//
//        film = new Film();
//        film.setName("");
//        film.setDescription("Film2 description");
//        film.setReleaseDate(Instant.now());
//        film.setDuration(Duration.ofHours(1));
//        try {
//            filmController.create(film);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertFalse(filmController.findAll().contains(film), "Записался фильм с пустым именем.");
//
//        film = new Film();
//        film.setName("Film3");
//        film.setDescription("Film3 description more 200 length 0123456789 0123456789 0123456789 0123456789 " +
//                "0123456789 0123456789 0123456789 0123456789 0123456789 0123456789 0123456789 0123456789 " +
//                "0123456789 0123456789 0123456789 0123456789");
//        film.setReleaseDate(Instant.now());
//        film.setDuration(Duration.ofHours(1));
//        try {
//            filmController.create(film);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertFalse(filmController.findAll().contains(film), "Записался фильм с длинным описанием.");
//
//        film = new Film();
//        film.setName("Film4");
//        film.setDescription("Film4 description");
//        film.setReleaseDate(Instant.parse("1890-10-20T16:55:30.00Z"));
//        film.setDuration(Duration.ofHours(1));
//        try {
//            filmController.create(film);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertFalse(filmController.findAll().contains(film), "Записался фильм с релизом раньше 28.12.1895.");
//
//        film = new Film();
//        film.setName("Film4");
//        film.setDescription("Film4 description");
//        film.setReleaseDate(Instant.now());
//        film.setDuration(Duration.ofHours(-1));
//        try {
//            filmController.create(film);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertFalse(filmController.findAll().contains(film), "Записался фильм с отрицательной длительностью.");
//    }
//
//    @Test
//    void chUpdate() {
//        FilmController filmController = new FilmController();
//
//        Film film = new Film();
//        film.setName("Film1");
//        film.setDescription("Film1 description");
//        film.setReleaseDate(Instant.now());
//        film.setDuration(Duration.ofHours(1));
//
//        try {
//            filmController.update(film);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertFalse(filmController.findAll().contains(film), "Обновился фильм, которого нет");
//
//        try {
//            filmController.create(film);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertTrue(filmController.findAll().contains(film), "Фильмы не совпадают");
//        long actualId = film.getId();
//
//        film = new Film();
//        film.setId(actualId);
//        film.setName("");
//        film.setDescription("Film2 description");
//        film.setReleaseDate(Instant.now());
//        film.setDuration(Duration.ofHours(1));
//        try {
//            filmController.update(film);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertFalse(filmController.findAll().contains(film), "Записался фильм с пустым именем.");
//
//        film = new Film();
//        film.setId(actualId);
//        film.setName("Film3");
//        film.setDescription("Film3 description more 200 length " + "0123456789".repeat(20));
//        film.setReleaseDate(Instant.now());
//        film.setDuration(Duration.ofHours(1));
//        try {
//            filmController.update(film);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertFalse(filmController.findAll().contains(film), "Записался фильм с длинным описанием.");
//
//        film = new Film();
//        film.setId(actualId);
//        film.setName("Film4");
//        film.setDescription("Film4 description");
//        film.setReleaseDate(Instant.parse("1890-10-20T16:55:30.00Z"));
//        film.setDuration(Duration.ofHours(1));
//        try {
//            filmController.update(film);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertFalse(filmController.findAll().contains(film), "Записался фильм с релизом раньше 28.12.1895.");
//
//        film = new Film();
//        film.setId(actualId);
//        film.setName("Film4");
//        film.setDescription("Film4 description");
//        film.setReleaseDate(Instant.now());
//        film.setDuration(Duration.ofHours(-1));
//        try {
//            filmController.update(film);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertFalse(filmController.findAll().contains(film), "Записался фильм с отрицательной длительностью.");
//    }
//}
