package com.example.cookie.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cookie.model.splace.Splace;
import com.example.cookie.repository.PlaceMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class  PlaceService {
	
	private final PlaceMapper placeMapper;
	
	public void savePlace(Splace place) {
		placeMapper.savePlace(place);
	}
}
