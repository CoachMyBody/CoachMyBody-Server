package com.coachmybody.user.interfaces.dto;

import com.coachmybody.user.type.LoginType;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class MyPageResponse {
	String nickname;
	String imageUri;
	LoginType loginType;
	String birth;
	String phone;
	MyActivityResponse activity;
}
