package com.elyashevich.tracker.service.impl;

import com.elyashevich.tracker.entity.Role;
import com.elyashevich.tracker.entity.User;
import com.elyashevich.tracker.exception.ResourceAlreadyExistsException;
import com.elyashevich.tracker.exception.ResourceNotFoundException;
import com.elyashevich.tracker.repository.UserRepository;
import com.elyashevich.tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findById(final UUID id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id '%s' was not found.".formatted(id)));
    }

    @Transactional
    @Override
    public User create(final User user) {
        if (this.userRepository.existsByEmail(user.getEmail())) {
            throw new ResourceAlreadyExistsException("User with email '%s' already exists.".formatted(user.getEmail()));
        }
        var candidate = User.builder()
                .email(user.getEmail())
                .password(this.passwordEncoder.encode(user.getPassword()))
                .role(Set.of(Role.ROLE_USER))
                .build();
        return this.userRepository.save(candidate);
    }

    @Transactional
    @Override
    public User update(final UUID id, final User user) {
        var candidate = this.findById(id);
        candidate.setEmail(user.getEmail());
        candidate.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(candidate);
    }

    @Transactional
    @Override
    public void delete(final UUID id) {
        var candidate = this.findById(id);
        this.userRepository.delete(candidate);
    }
}
