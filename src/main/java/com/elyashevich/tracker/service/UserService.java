package com.elyashevich.tracker.service;

import com.elyashevich.tracker.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<User> findAll();

    User findById(final UUID id);

    User create(final User user);

    User update(final UUID id, final User user);

    void delete(final UUID id);
}
