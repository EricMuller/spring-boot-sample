package com.emu.apps.sample.rest;

import com.emu.apps.sample.model.Question;
import com.emu.apps.sample.services.CategoryService;
import com.emu.apps.sample.services.QuestionService;
import com.emu.apps.sample.services.dtos.CategoryDto;
import com.emu.apps.sample.services.dtos.FileQuestionJson;
import com.emu.apps.sample.services.dtos.QuestionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by eric on 05/06/2017.
 */
@RestController
@RequestMapping("api/category")
@Api(value = "category-store", description = "All operations ", tags = "Categories")
public class CategoryRestController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CategoryService categoryService;


    @ApiOperation(value = "Find a category by ID", response = CategoryDto.class)
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public CategoryDto getCategory(@PathVariable("id") Long id) {
        return categoryService.findById(id);
    }

    @ApiOperation(value = "Find all categories", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @PreAuthorize("true")
    public Iterable<CategoryDto> getCategories() {
        return categoryService.findAll();

    }


}
