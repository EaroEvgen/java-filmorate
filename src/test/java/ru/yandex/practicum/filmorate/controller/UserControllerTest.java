//package ru.yandex.practicum.filmorate.controller;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import ru.yandex.practicum.filmorate.model.User;
//
//import java.time.Duration;
//import java.time.Instant;
//
//public class UserControllerTest {
//    @Test
//    void chCreate() {
//        UserController userController = new UserController();
//
//        User user = new User();
//        user.setName("User Name");
//        user.setBirthday(Instant.now().minus(Duration.ofHours(1)));
//        user.setEmail("user@mail.ru");
//        user.setLogin("userLogin");
//
//        try {
//            userController.create(user);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertTrue(userController.findAll().contains(user), "Пользователь не добавлен");
//
//
//        user = new User();
//        user.setName("User Name");
//        user.setBirthday(Instant.now().minus(Duration.ofHours(1)));
//        user.setEmail("");
//        user.setLogin("userLogin");
//
//        try {
//            userController.create(user);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertFalse(userController.findAll().contains(user), "Записался пользователь с пустой почтой.");
//
//        user = new User();
//        user.setName("User Name");
//        user.setBirthday(Instant.now().minus(Duration.ofHours(1)));
//        user.setEmail("mail");
//        user.setLogin("userLogin");
//
//        try {
//            userController.create(user);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertFalse(userController.findAll().contains(user), "Записался пользователь с почтой без @.");
//
//        user = new User();
//        user.setName("User Name");
//        user.setBirthday(Instant.now().minus(Duration.ofHours(1)));
//        user.setEmail("user1@mail.ru");
//        user.setLogin("");
//
//        try {
//            userController.create(user);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertFalse(userController.findAll().contains(user), "Записался пользователь с пустым логином.");
//
//        user = new User();
//        user.setName("User Name");
//        user.setBirthday(Instant.now().minus(Duration.ofHours(1)));
//        user.setEmail("user2@mail.ru");
//        user.setLogin("User login");
//
//        try {
//            userController.create(user);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertFalse(userController.findAll().contains(user), "Записался пользователь с пустым логином.");
//
//        user = new User();
//        user.setName("");
//        user.setBirthday(Instant.now().minus(Duration.ofHours(1)));
//        user.setEmail("user3@mail.ru");
//        user.setLogin("userLogin");
//
//        try {
//            userController.create(user);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertEquals(user.getName(), user.getLogin(), "Имя для отображения может быть пустым — в таком случае будет использован логин");
//
//    }
//
//    @Test
//    void chUpdate() {
//        UserController userController = new UserController();
//
//        User user = new User();
//        user.setName("User Name");
//        user.setBirthday(Instant.now().minus(Duration.ofHours(1)));
//        user.setEmail("user@mail.ru");
//        user.setLogin("userLogin");
//
//        try {
//            userController.update(user);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertFalse(userController.findAll().contains(user), "Пользователь обновился, который не добавлен");
//
//        try {
//            userController.create(user);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertTrue(userController.findAll().contains(user), "Пользователь не добавлен");
//
//        long actualId = user.getId();
//
//        user = new User();
//        user.setId(actualId);
//        user.setName("User Name");
//        user.setBirthday(Instant.now().minus(Duration.ofHours(1)));
//        user.setEmail("");
//        user.setLogin("userLogin");
//
//        try {
//            userController.update(user);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertFalse(userController.findAll().contains(user), "Записался пользователь с пустой почтой.");
//
//        user = new User();
//        user.setId(actualId);
//        user.setName("User Name");
//        user.setBirthday(Instant.now().minus(Duration.ofHours(1)));
//        user.setEmail("mail");
//        user.setLogin("userLogin");
//
//        try {
//            userController.update(user);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertFalse(userController.findAll().contains(user), "Записался пользователь с почтой без @.");
//
//        user = new User();
//        user.setId(actualId);
//        user.setName("User Name");
//        user.setBirthday(Instant.now().minus(Duration.ofHours(1)));
//        user.setEmail("user1@mail.ru");
//        user.setLogin("");
//
//        try {
//            userController.update(user);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertFalse(userController.findAll().contains(user), "Записался пользователь с пустым логином.");
//
//        user = new User();
//        user.setId(actualId);
//        user.setName("User Name");
//        user.setBirthday(Instant.now().minus(Duration.ofHours(1)));
//        user.setEmail("user2@mail.ru");
//        user.setLogin("User login");
//
//        try {
//            userController.update(user);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertFalse(userController.findAll().contains(user), "Записался пользователь с пустым логином.");
//
//        user = new User();
//        user.setId(actualId);
//        user.setName("");
//        user.setBirthday(Instant.now().minus(Duration.ofHours(1)));
//        user.setEmail("user3@mail.ru");
//        user.setLogin("userLogin");
//
//        try {
//            userController.update(user);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        Assertions.assertEquals(user.getName(), user.getLogin(), "Имя для отображения может быть пустым — в таком случае будет использован логин");
//    }
//}
