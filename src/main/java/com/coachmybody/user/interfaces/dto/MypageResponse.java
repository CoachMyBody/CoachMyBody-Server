package com.coachmybody.user.interfaces.dto;

import com.coachmybody.user.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@ApiModel(description = "마이페이지 조회 응답")
@Builder
@Value
public class MypageResponse {
    @ApiModelProperty(value = "닉네임", required = true)
    String nickname;

    @ApiModelProperty(value = "프로필")
    String profileUri;

    @ApiModelProperty(value = "내레벨", required = true)
    Integer exerciseLevel;

    @ApiModelProperty(value = "내 뱃지 수", required = true)
    Integer badgeCount;
    
    @ApiModelProperty(value = "운동 일수", required = true)
    Integer exerciseDays;

    public static MypageResponse of(User user) {
        String imageUri = user.getImageUri() == null ? "" : user.getImageUri();

        return MypageResponse.builder()
            .nickname(user.getNickname().toString())
            .profileUri(imageUri)
            .exerciseLevel(0)
            .badgeCount(0)
            .exerciseDays(0)
            .build();
    }
}
