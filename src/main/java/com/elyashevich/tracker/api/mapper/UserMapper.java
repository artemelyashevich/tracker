package com.elyashevich.tracker.api.mapper;

import com.elyashevich.tracker.api.dto.UserDto;
import com.elyashevich.tracker.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto> {
}
