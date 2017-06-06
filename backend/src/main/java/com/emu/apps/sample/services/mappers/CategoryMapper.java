package com.emu.apps.sample.services.mappers;

import com.emu.apps.sample.model.Category;
import com.emu.apps.sample.services.dtos.CategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto modelToDto(Category question);

}