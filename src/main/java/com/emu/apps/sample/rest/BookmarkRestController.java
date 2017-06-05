package com.emu.apps.sample.rest;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.emu.apps.sample.model.Bookmark;
import com.emu.apps.sample.services.repositories.BookmarkJdbcRepository;

@RestController
@RequestMapping("bookmarks")
@Api(value="bookmark-store", description="Operations on bookmarks Store")
public class BookmarkRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private BookmarkJdbcRepository bookmarkRepository;

    @RequestMapping(value = "test",method= RequestMethod.GET)
    @ApiOperation(value = "Dummy Test ",response = String.class)
    public String test() {
        log.info("Test");
        return "OK";
    }

    @RequestMapping(value = "{id}" ,method= RequestMethod.GET)
    @ApiOperation(value = "Search a bookmark with an ID",response = Bookmark.class)
    public Bookmark getBookmark(@PathVariable("id") long id) {
        log.info("Get Bookmark");
        return bookmarkRepository.getBookmark(id);
    }

    
   /* @RequestMapping(value= "ids", produces = "application/json")
    public List<Bookmark> getBookmarksById(@RequestParam("ids") long[] ids) {
        log.info("Get bookmarks");
        return bookmarkRepository.getBookmarks(ids);
    }*/
    
    @RequestMapping(produces = "application/json", method= RequestMethod.GET)
    @ApiOperation(value = "View a list of bookmarks",response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public List<Bookmark> getBookmarks() {
        log.info("Get bookmark");
        return bookmarkRepository.getBookmarks();
    }
} 