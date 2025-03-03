package ru.yandex.practicum.filmorate.service;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public User create(@Valid User user) {
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

    public User update(@Valid User newUser) {
        validateUser(newUser);
        return users.update(newUser);
    }

    public User getUserById(long userId) {
        return users.getUserById(userId);
    }

    public Collection<User> getUserFriends(long userId) {
        return users.getUserFriends(userId);
    }

    public User addUserFriend(long userId, long friendId) {
        return users.addUserFriend(userId, friendId);
    }

    public User deleteUserFriend(long userId, long friendId) {
        return users.deleteUserFriend(userId, friendId);
    }

    public Collection<User> getCommonUserFriends(long userId, long otherId) {
        Collection<User> userFriends = users.getUserFriends(userId);
        Collection<User> otherFriends = users.getUserFriends(otherId);
        return userFriends.stream().filter(otherFriends::contains).toList();
    }
}
