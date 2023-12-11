package com.example.cookie.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cookie.model.splace.Splace;
import com.example.cookie.repository.SplaceMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class SplaceService {

    private final SplaceMapper splaceMapper;
    
    @Transactional
    public void saveSplace(Splace splace) {
        try {
            splaceMapper.saveSplace(splace);
        } catch (Exception e) {
            // 예외 처리 (예: 로깅)
            e.printStackTrace();
            throw new RuntimeException("Failed to save splace: " + e.getMessage());
        }
    }

    public Splace findSplace(Long place_id) {
        Splace findSplace = splaceMapper.findSplace(place_id);
        return findSplace;
    }
}