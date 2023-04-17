package com.atteam.atshop.service.impl;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.atteam.atshop.service.IUploadImageService;

@Service
public class UploadImageServiceImpl implements IUploadImageService{

	@Autowired
	ServletContext app; // ứng dụng của ServletContext làm việc với đường dẫn
	
	@Override
	public File save(MultipartFile file, String folder) {
		File dir = new File(app.getRealPath("/folder/" + folder));
		if(!dir.exists()) {
			dir.mkdirs();
		}
		//String s = System.currentTimeMillis() + file.getOriginalFilename();
		//String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf(".")); 
		try {
			File saveFile = new File(dir, file.getOriginalFilename());
			file.transferTo(saveFile);
			return saveFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
