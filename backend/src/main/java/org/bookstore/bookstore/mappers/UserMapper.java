package org.bookstore.bookstore.mappers;


import org.bookstore.bookstore.dtos.UserDto;
import org.bookstore.bookstore.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    @Mapping(target = "userId" , ignore = true)
    void updateUser(UserDto userDto , @MappingTarget User user);

}
