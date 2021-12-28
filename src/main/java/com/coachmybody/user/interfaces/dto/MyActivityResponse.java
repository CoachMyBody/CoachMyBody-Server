package com.coachmybody.user.interfaces.dto;

import java.util.List;

import com.coachmybody.user.type.BadgeType;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class MyActivityResponse {
	Integer level;
	List<BadgeType> badges;
	String startDate;
}
