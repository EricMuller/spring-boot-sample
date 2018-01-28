package com.emu.apps.qcm.rest;

import com.emu.apps.qcm.rest.dtos.BookmarkDto;
import com.emu.apps.qcm.services.repositories.*;
import io.swagger.annotations.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v0/bookmarks")
@Api(value="bookmark-store", description="Operations on bookmarks Store")
public class BookmarkRestController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BookmarkJdbcRepository bookmarkRepository;

    @RequestMapping(value = "test",method= RequestMethod.GET)
    @ApiOperation(value = "Dummy Test ",response = String.class)
    public String test() {
        return "OK";
    }

    @RequestMapping(value = "{id}" ,method= RequestMethod.GET)
    @ApiOperation(value = "Search a bookmark with an ID",response = BookmarkDto.class)
    public BookmarkDto getBookmark(@PathVariable("id") long id) {
        logger.info("Get Bookmark");
        return bookmarkRepository.getBookmark(id);
    }

   /* @RequestMapping(value= "ids", produces = "application/json")
    public List<Bookmark> getBookmarksById(@RequestParam("ids") long[] ids) {
        LOGGER.info("Get bookmarks");
        return bookmarkRepository.getBookmarks(ids);
    }*/
    @RequestMapping(produces = "application/json", method= RequestMethod.GET)
    @ApiOperation(value = "View a list of bookmarks",responseContainer = "List", response = BookmarkDto.class )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public List<BookmarkDto> getBookmarks() {
        return bookmarkRepository.getBookmarks();
    }
} 