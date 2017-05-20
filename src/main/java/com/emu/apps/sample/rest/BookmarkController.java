package com.emu.apps.sample.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emu.apps.sample.model.Bookmark;
import com.emu.apps.sample.services.BookmarkRepository;

@RestController
@RequestMapping("bookmarks")
public class BookmarkController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private BookmarkRepository  bookmarkRepository;

    @RequestMapping("test")
    public String test() {
        log.info("Test");
        return "OK";
    }

    @RequestMapping("{id}")
    public Bookmark getBookmark(@PathParam("id") long id) {
        log.info("Get user");
        return bookmarkRepository.getBookmark(id);
    }

    
    @RequestMapping(value= "ids", produces = "application/json")
    public List<Bookmark> getBookmarksById(@RequestParam("ids") long[] ids) {
        log.info("Get bookmarks");
        return bookmarkRepository.getBookmarks(ids);
    }
    
    @RequestMapping(produces = "application/json")
    public List<Bookmark> getBookmarks() {
        log.info("Get bookmark");
        return bookmarkRepository.getBookmarks();
    }
} 