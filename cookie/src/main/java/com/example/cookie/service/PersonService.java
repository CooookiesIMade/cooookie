package com.example.cookie.service;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.example.cookie.repository.PersonMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PersonService {

	private final PersonMapper personMapper;
	

}
