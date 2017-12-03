package com.emu.apps.qcm.rest;

import com.emu.apps.qcm.services.*;
import com.emu.apps.qcm.services.dtos.*;
import io.swagger.annotations.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

/**
 * Created by eric on 05/06/2017.
 */
@RestController
@RequestMapping("/api/v1/categories")
@Api(value = "category-store", description = "All operations ", tags = "Categories")
public class CategoryRestController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "Find a category by ID", response = CategoryDto.class, tags = "Categories")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public CategoryDto getCategory(@PathVariable("id") Long id) {
        return categoryService.findById(id);
    }

    @ApiOperation(value = "Find all categories",responseContainer = "List", response = CategoryDto.class)
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
