package com.ecommerce.project.service;

import java.awt.image.ImagingOpException;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	 String uploadImage(String path, MultipartFile file) throws ImagingOpException, IOException;

}
