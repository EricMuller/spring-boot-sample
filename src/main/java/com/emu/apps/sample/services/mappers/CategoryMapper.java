package com.emu.apps.sample.services.mappers;

import com.emu.apps.sample.model.Category;
import com.emu.apps.sample.model.Question;
import com.emu.apps.sample.services.dtos.CategoryDto;
import com.emu.apps.sample.services.dtos.QuestionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto modelToDto(Category question);

}