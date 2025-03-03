package ru.yandex.practicum.filmorate.storage.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;

public interface UserStorage {
    Collection<User> findAll();

    User create(@Valid User user);

    User update(@Valid User newUser);

    boolean isFreeEmail(@Email @NotBlank @NotNull String email);

    User getUserById(long userId);

    Collection<User> getUserFriends(long userId);

    User addUserFriend(long userId, long friendId);

    User deleteUserFriend(long userId, long friendId);

    boolean contains(long userId);
}
