package com.growthcapital.seeder.mapper;

import com.growthcapital.seeder.dto.UserDto;
import com.growthcapital.seeder.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setTermCap(user.getTermCap());
        dto.setAvailableCredit(user.getAvailableCredit());
        dto.setMaxInterestRate(user.getMaxInterestRate());

        return dto;
    }

    public static User toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }

        User entity = new User();
        entity.setUserId(dto.getUserId());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setTermCap(dto.getTermCap());
        entity.setAvailableCredit(dto.getAvailableCredit());
        entity.setMaxInterestRate(dto.getMaxInterestRate());
        return entity;
    }
}
