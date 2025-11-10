package org.example.redis_cache.repository;

import org.example.redis_cache.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
