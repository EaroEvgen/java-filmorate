package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.FilmorateNotFoundException;
import ru.yandex.practicum.filmorate.exception.FilmorateOtherException;
import ru.yandex.practicum.filmorate.exception.FilmorateValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.*;

@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage {
    private final Map<Long, User> users = new HashMap<>();

    @Override
    public Collection<User> findAll() {
        return users.values();
    }

    @Override
    public User create(User user) {
        user.setId(getNextId());
        return users.put(user.getId(), user);
    }

    @Override
    public User update(User newUser) {
        if (!users.containsKey(newUser.getId())) {
            log.info("Пользователь не найден");
            throw new FilmorateNotFoundException("Пользователь с id = " + newUser.getId() + " не найден");
        }
        return users.put(newUser.getId(), newUser);
    }

    @Override
    public boolean isFreeEmail(final String email) {
        return users.values().stream()
                .filter(element -> email.equals(element.getEmail()))
                .toList()
                .isEmpty();
    }

    @Override
    public User getUserById(long userId) {
        return users.get(userId);
    }

    @Override
    public Collection<User> getUserFriends(long userId) {
        return users.get(userId).getFriends().stream()
                .map(users::get)
                .toList();
    }

    @Override
    public User addUserFriend(long userId, long friendId) {
        if(users.get(userId).getFriends().contains(friendId)) {
            throw new FilmorateOtherException(String
                    .format("Пользователь с ID = %d уже друг для пользователя с ID = %d.",
                            userId, friendId));
        }
        users.get(userId).getFriends().add(friendId);
        users.get(friendId).getFriends().add(userId);
        return users.get(userId);
    }

    @Override
    public User deleteUserFriend(long userId, long friendId) {
        if(!users.get(userId).getFriends().contains(friendId)) {
            throw new FilmorateOtherException(String
                    .format("Пользователь с ID = %d уже не друг для пользователя с ID = %d.",
                            userId, friendId));
        }
        users.get(userId).getFriends().remove(friendId);
        users.get(friendId).getFriends().remove(userId);
        return users.get(userId);
    }

    @Override
    public boolean contains(long userId) {
        return users.containsKey(userId);
    }

    private long getNextId() {
        long currentMaxId = users.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
