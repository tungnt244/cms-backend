package org.tungnt.springcms.controller;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.tungnt.springcms.service.AnoUpload;
import org.tungnt.springcms.service.UploadService;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@RestController
public class HomeController {
	
	@Autowired
	UploadService up;
	
	@Autowired
	AnoUpload ano;
	
	@GetMapping("/secured/article")
	public ResponseEntity<String> getSecuredArticle(){
		return ResponseEntity.ok("secured article");
	}
	
	@GetMapping("/secured/admin/article")
	public ResponseEntity<String> getAdminArticle(){
		return ResponseEntity.ok("min article");
	}
	
	@GetMapping("/secured/user/article")
	public ResponseEntity<String> getUserArticle(){
		return ResponseEntity.ok("user article");
	}
	
	@PostMapping("/public")
	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile uploadFile){
		String client = "ap-southeast-1";
		String bucketName = "tungnt-image-demo";
		String fileName= "file object key";
		String localFileName="/Users/tungnt/screencapture-file-Users-tungnt-work_space-education-service-src-main-resources-mockup-admin-pages-userProfile-html-2018-05-25-10_11_27.png";
		String accessKey = "AKIAJGVW44HAQV5ZZTAA";
		String secretKey = "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";
		
		try {
			File convFile = new File(uploadFile.getOriginalFilename());
			
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(uploadFile.getBytes());
			fos.close();
			
			AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
			AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);
			
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(client).withCredentials(credentialsProvider).build();
			s3Client.putObject(bucketName, fileName, convFile);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	@GetMapping("/public")
	public String demodd() {
		up.executeTask();
		ano.executeTask();
		return "ok";
	}
}
