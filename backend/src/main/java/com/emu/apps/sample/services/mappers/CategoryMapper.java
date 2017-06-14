package com.emu.apps.sample.services.mappers;

import com.emu.apps.sample.model.Category;
import com.emu.apps.sample.services.dtos.CategoryDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto modelToDto(Category category);
    Iterable<CategoryDto> modelsToDtos(Iterable<Category> category);

}