package com.voco_task.mapper;


import com.voco_task.dto.RegisterRequestRestaurantDto;
import com.voco_task.dto.RegisterRequestUserDto;
import com.voco_task.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {

    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);
    Auth toRegisterUser(RegisterRequestUserDto dto);
    Auth toRegisterRestaurant(RegisterRequestRestaurantDto dto);




}