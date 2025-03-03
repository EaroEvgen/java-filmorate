package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Collection<User> findAll() {
        log.info("Показать всех пользователей");
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        log.info("Создать пользователя");
        return userService.create(user);
    }

    @PutMapping
    public User update(@Valid @RequestBody User newUser) {
        log.info("Обновить пользователя");
        return userService.update(newUser);
    }

    @GetMapping("/{userId}/friends")
    public Collection<User> getUserFriends(@PathVariable long userId) {
        return userService.getUserFriends(userId);
    }

    @GetMapping("{userId}/friends/common/{otherId}")
    public Collection<User> getCommonUserFriends(@PathVariable long userId, @PathVariable long otherId) {
        return userService.getCommonUserFriends(userId, otherId);
    }

    @PutMapping("/{userId}/friends/{friendId}")
    public User addUserFriend(@PathVariable long userId, @PathVariable long friendId) {
        return userService.addUserFriend(userId, friendId);
    }

    @DeleteMapping("{userId}/friends/{friendId}")
    public User deleteUserFriend(@PathVariable long userId, @PathVariable long friendId) {
        return userService.deleteUserFriend(userId, friendId);
    }
}
