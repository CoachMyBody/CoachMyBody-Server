package com.coachmybody.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsConfig {
	@Value("${aws.credential.access-key}")
	private String accessKey;

	@Value("${aws.credential.secret-key}")
	private String secretKey;

	@Bean
	public BasicAWSCredentials awsCredentials() {
		return new BasicAWSCredentials(accessKey, secretKey);
	}

	@Bean
	public AmazonS3 amazonS3() {
		return AmazonS3ClientBuilder.standard()
			.withRegion(Regions.AP_NORTHEAST_2)
			.withCredentials(new AWSStaticCredentialsProvider(awsCredentials()))
			.build();
	}
}
