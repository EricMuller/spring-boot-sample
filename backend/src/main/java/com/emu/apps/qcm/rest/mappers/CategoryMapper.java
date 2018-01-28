package com.emu.apps.qcm.rest.mappers;

import com.emu.apps.qcm.model.Category;
import com.emu.apps.qcm.rest.dtos.CategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto modelToDto(Category category);
    Iterable<CategoryDto> modelsToDtos(Iterable<Category> category);
}