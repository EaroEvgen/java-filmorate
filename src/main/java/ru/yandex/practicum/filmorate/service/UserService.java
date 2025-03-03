package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.FilmorateNotFoundException;
import ru.yandex.practicum.filmorate.exception.FilmorateValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.time.Instant;
import java.util.Collection;

@Slf4j
@Service
public class UserService {
    private final UserStorage users;

    @Autowired
    public UserService(UserStorage users) {
        this.users = users;
    }

    public Collection<User> findAll() {
        return users.findAll();
    }

    public User create(User user) {
        validateUser(user);
        return users.create(user);
    }

    private void validateUser(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank() || !user.getEmail().contains("@")) {
            log.info("Электронная почта не может быть пустой и должна содержать символ @");
            throw new FilmorateValidationException("Электронная почта не может быть пустой и должна содержать символ @");
        }
        if (!users.isFreeEmail(user.getEmail())) {
            log.info("Этот email уже используется");
            throw new FilmorateValidationException("Этот email уже используется");
        }
        if (user.getLogin() == null || user.getLogin().isBlank() || user.getLogin().contains(" ")) {
            log.info("Логин не может быть пустым и содержать пробелы");
            throw new FilmorateValidationException("Логин не может быть пустым и содержать пробелы");
        }
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        if (user.getBirthday() == null || user.getBirthday().isAfter(Instant.now())) {
            log.info("Дата рождения не может быть в будущем");
            throw new FilmorateValidationException("Дата рождения не может быть в будущем");
        }
    }

    public User update(User newUser) {
        if (!users.contains(newUser.getId())) {
            throw new FilmorateNotFoundException("Пользователь с id = " + newUser.getId() + " не найден");
        }
        validateUser(newUser);
        return users.update(newUser);
    }

    public User getUserById(long userId) {
        if (!users.contains(userId)) {
            throw new FilmorateNotFoundException("Пользователь с id = " + userId + " не найден");
        }
        return users.getUserById(userId);
    }

    public Collection<User> getUserFriends(long userId) {
        if (!users.contains(userId)) {
            throw new FilmorateNotFoundException("Пользователь с id = " + userId + " не найден");
        }
        return users.getUserFriends(userId);
    }

    public User addUserFriend(long userId, long friendId) {
        if (!users.contains(userId)) {
            throw new FilmorateNotFoundException("Пользователь с id = " + userId + " не найден");
        }
        if (!users.contains(friendId)) {
            throw new FilmorateNotFoundException("Пользователь с id = " + friendId + " не найден");
        }
        return users.addUserFriend(userId, friendId);
    }

    public User deleteUserFriend(long userId, long friendId) {
        if (!users.contains(userId)) {
            throw new FilmorateNotFoundException("Пользователь с id = " + userId + " не найден");
        }
        if (!users.contains(friendId)) {
            throw new FilmorateNotFoundException("Пользователь с id = " + friendId + " не найден");
        }
        return users.deleteUserFriend(userId, friendId);
    }

    public Collection<User> getCommonUserFriends(long userId, long otherId) {
        if (!users.contains(userId)) {
            throw new FilmorateNotFoundException("Пользователь с id = " + userId + " не найден");
        }
        if (!users.contains(otherId)) {
            throw new FilmorateNotFoundException("Пользователь с id = " + otherId + " не найден");
        }
        Collection<User> userFriends = users.getUserFriends(userId);
        Collection<User> otherFriends = users.getUserFriends(otherId);
        return userFriends.stream().filter(otherFriends::contains).toList();
    }
}
