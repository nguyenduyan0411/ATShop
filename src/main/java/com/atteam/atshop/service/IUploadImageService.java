package com.atteam.atshop.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadImageService {
	
	File save(MultipartFile file, String folder);
	
}
