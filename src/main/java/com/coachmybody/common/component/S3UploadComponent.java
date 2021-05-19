package com.coachmybody.common.component;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.coachmybody.common.util.UuidUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class S3UploadComponent {
	private final AmazonS3 amazonS3;

	@Value("${aws.s3.bucket}")
	private String bucket;

	@Value("${aws.s3.nunbody-dir}")
	private String nunbodyDir;

	@Value("${aws.cloudfront.domain}")
	private String cloudfrontDomain;

	public String uploadImage(MultipartFile file) throws IOException {
		String uploadDir = bucket + "/" + nunbodyDir;
		ObjectMetadata omd = new ObjectMetadata();

		String fileName = UuidUtils.generateUuid() + ".jpg";
		omd.setContentType(file.getContentType());
		omd.setContentLength(file.getSize());
		omd.setHeader("filename", fileName);

		PutObjectRequest putObjectRequest = new PutObjectRequest(uploadDir, fileName, file.getInputStream(), omd);

		amazonS3.putObject(putObjectRequest
			.withCannedAcl(CannedAccessControlList.PublicRead));

		String imageUri = cloudfrontDomain + "/" + nunbodyDir + "/" + fileName;

		return imageUri;
	}
}
