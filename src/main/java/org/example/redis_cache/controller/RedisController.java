package org.example.redis_cache.controller;

import lombok.RequiredArgsConstructor;
import org.example.redis_cache.service.RedisTestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/redis")
public class RedisController {
    private final RedisTestService redisTestService;

    @PostMapping("/save")
    public String save(@RequestParam String key, @RequestParam String value) {
        redisTestService.saveData(key, value);
        return "Saved: " + key;
    }

    @GetMapping("/get")
    public String get(@RequestParam String key) {
        return redisTestService.getData(key);
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam String key) {
        redisTestService.deleteData(key);
        return "Deleted: " + key;
    }
}
