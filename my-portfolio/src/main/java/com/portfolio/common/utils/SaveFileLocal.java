package com.portfolio.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.common.constant.SystemConstant;

@Component
public class SaveFileLocal {
	public String saveFile(MultipartFile file) throws IOException {
		if (file == null || file.isEmpty()) {
			return null;
		}

		String fileName = file.getOriginalFilename();
		Path uploadPath = Paths.get(SystemConstant.IMG_FOLDER);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = file.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		} catch (IOException ioe) {
			System.out.println("Error at SaveFileLocal method");
			ioe.printStackTrace();
		}

		return null;
	}
}
