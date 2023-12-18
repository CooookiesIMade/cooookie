package com.example.cookie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.cookie.model.file.AttachedFile;
import com.example.cookie.model.file.PlaceAttachedFile;
import com.example.cookie.model.rent.RentPlace;
import com.example.cookie.model.rent.RentPlaceRegister;
import com.example.cookie.model.splace.Splace;
import com.example.cookie.repository.PlaceMapper;
import com.example.cookie.util.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class  PlaceService {
	
	private final PlaceMapper placeMapper;
	private final FileService fileService;
	
	@Value("${file.upload.path}")
	private String uploadPath;
	
	@Transactional
	public void savePlace(Splace place, MultipartFile file) {
		
		placeMapper.savePlace(place);
		
		// 첨부파일 저장
		if(file != null && file.getSize() > 0) {
			AttachedFile attachedFile = fileService.saveFile(file);
				PlaceAttachedFile saveFile = new PlaceAttachedFile(attachedFile, place.getPlace_id());
				
				// 첨부파일 내용을 데이터베이스에 저장
				placeMapper.saveFile(saveFile);
		}
	}
	
	public List<Splace> findAllPlaces(){
		List<Splace> findAllPlace = placeMapper.findAllPlace();
		return findAllPlace;
	}
	
	public List<PlaceAttachedFile> findFiles(){
		List<PlaceAttachedFile> files = placeMapper.findFiles();
		
		return files;
	}
	
	public Splace findPlaceById(Long place_id) {
		return placeMapper.findPlaceById(place_id);
		
	}
	public List<Splace> findPlaceByCategory(String place_category){
		return placeMapper.findPlaceByCategory(place_category);
	}
	
	public void rentPlace(RentPlaceRegister rentPlaceRegister) {
		placeMapper.rentPlace(rentPlaceRegister);
	}
	
	public RentPlace findRentPlaceById(Long place_id ,String member_id) {
		return placeMapper.findRentPlaceById(place_id, member_id);
	}

}