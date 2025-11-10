package org.example.redis_cache.service;

import lombok.RequiredArgsConstructor;
import org.example.redis_cache.domain.User;
import org.example.redis_cache.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 조회 시 id로 캐시에서 조회한 후 응답
    // 캐시에 없으면 DB 조회 후 응답 + 캐시에 저장
    // id = 1 일 때 실제로 redis에 저장되는 key = userCache::1
    // User 객체는 직렬화 되어 value 로 저장된다.
    @Cacheable(value = "userCache", key = "#id")
    public User getUser(Long id) {
        System.out.println("📡 DB 조회 발생!");
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User registUser(User user) {
        User registedUser = userRepository.save(user);
        System.out.println("user 입력 = " + registedUser);
        return registedUser;
    }

    // DB 데이터 변경 시 userCache::id 캐시 삭제함.
    @CacheEvict(value = "userCache", key = "#user.id")
    public User updateUser(User user) {
        System.out.println("🧹 캐시 무효화 + DB 업데이트");
        return userRepository.save(user);
    }
}
