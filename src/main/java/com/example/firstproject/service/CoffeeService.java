package com.example.firstproject.service;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    public Iterable<Coffee> index() {
        return coffeeRepository.findAll();
    }

    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeDto dto) {
        Coffee coffee = dto.toEntity();
        if (coffee.getId() != null){
            return null;
        }
        return coffeeRepository.save(coffee);
    }

    public Coffee update(Long id, CoffeeDto dto) {
        // 1. DTO -> 엔티티 변환하기
        Coffee coffee = dto.toEntity();
        log.info("id: {}, article: {}", id, coffee.toString());
        // 2. 타깃 조회하기
        Coffee target = coffeeRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리하기
        if (target == null || id != coffee.getId()) {
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, article: {}", id, coffee.toString());
            return null;
        }
        // 4. 업데이트 및 정상 응답(200)하기
        target.patch(coffee);
        Coffee updated = coffeeRepository.save(target);
        return updated;
    }

    public Coffee delete(Long id) {
        // 1. 대상 찾기
        Coffee target = coffeeRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리하기
        if (target == null) {
            return null;
        }
        // 3. 대상 삭제하기
        coffeeRepository.delete(target);
        return target;
    }
}
